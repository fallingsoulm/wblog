package com.wblog.info.news;

import cn.hutool.core.util.StrUtil;

import cn.hutool.http.HttpException;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import com.wblog.common.enums.ConstantEnum;
import com.wblog.common.module.info.vo.NewsVo;
import com.wblog.common.redis.RedisKeyEnums;
import com.wblog.info.config.BlogConfigProperties;
import com.wblog.info.entity.NewsInfoEntity;
import com.wblog.info.service.INewsService;
import com.wblog.info.utils.UserAgent;
import io.github.fallingsoulm.easy.archetype.data.id.SimpleSnowflake;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

/**
 * @author luyanan
 * @since 2020/7/13
 * <p>抽象类</p>
 **/
@Slf4j
public abstract class AbstractNewsSpiderHandler implements NewsSpiderHandler {


    @Autowired
    protected INewsService newsService;

    @Autowired
    protected BlogConfigProperties blogConfigProperties;


    @Autowired
    protected SimpleSnowflake snowflake;


    @Autowired
    private RedisTemplate redisTemplate;


    @Override
    public void syn() {
        log.info("{}资讯开始同步", source().getDesp());
        /**
         * <p>上次最后一次的url</p>
         *
         * @author luyanan
         * @since 2020/7/13
         */
        Integer page;
        try {
            NewsVo lastOffset = lastOffset();
            page = 1;
            boolean stop = false;
            while (page < blogConfigProperties.getNews().getMaxPageSize() && !stop) {
                try {
                    String content = getHtmlContent(page);
                    if (StrUtil.isBlank(content)) {
                        continue;
                    }
                    List<NewsVo> newsVoList = parserHtml(content);
                    page++;
                    if (CollectionUtils.isEmpty(newsVoList)) {
                        stop = true;
                    }
                    for (NewsVo newsVo : newsVoList) {
                        newsVo.setSource(source().getValue());
                        newsService.save(newsVo);

                        if (!breakCondition() &&
                                null != lastOffset &&
                                lastOffset.getPublishTime().getTime() > newsVo.getPublishTime().getTime()) {
                            stop = true;
                            break;
                        }
                    }

                    Thread.sleep(blogConfigProperties.getNews().getSleepTime());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        } finally {
            finishProcessor();
            page = 1;
            log.info("{}资讯同步结束", source().getDesp());
        }

    }


    /**
     * <p>break的条件</p>
     *
     * @return {@link boolean}
     * @author luyanan
     * @since 2020/7/14
     */
    protected boolean breakCondition() {
        return false;
    }

    @Override
    public NewsVo lastOffset() {
        return newsService.lastUrl(source().getValue());
    }

    /**
     * <p>解析url</p>
     *
     * @param html
     * @return {@link List< NewsVo>}
     * @author luyanan
     * @since 2020/7/13
     */
    protected abstract List<NewsVo> parserHtml(String html);

    /**
     * <p>获取页面内容</p>
     *
     * @param page
     * @return {@link String}
     * @author luyanan
     * @since 2020/7/13
     */
    protected abstract String getHtmlContent(Integer page);


    /**
     * <p>newsVo构建 </p>
     *
     * @return {@link NewsVo.NewsVoBuilder}
     * @author luyanan
     * @since 2020/7/14
     */
    protected NewsVo.NewsVoBuilder buildNewVos() {
        return NewsVo.builder().source(source().getValue()).createTime(new Date()).id(snowflake.nextId());
    }

    /**
     * <p>结束的处理</p>
     *
     * @author luyanan
     * @since 2020/7/14
     */
    protected void finishProcessor() {

    }


    @Override
    public NewsInfoEntity getContent(NewsVo newsVo) {
        NewsInfoEntity newsInfoEntity = doGetContent(newsVo);
        // 这里检查,当多次为null 时候, 列入id黑名单,当出现黑名单5次之后,则删除
        if (null == newsInfoEntity || StrUtil.isBlank(newsInfoEntity.getContent())) {
            Object value = redisTemplate.opsForHash().get(RedisKeyEnums.NEWS_ID_BLACK_LIST.getKey(), newsVo.getId().toString());
            int count;
            if (null == value) {
                count = 1;
            } else {
                count = (int) value + 1;
            }
            log.info("资讯:{}, 黑名单次数为:{}", newsVo.getId(), count);
            if (count > 5) {
                newsService.deleteById(newsVo.getId());
            } else {
                redisTemplate.opsForHash().put(RedisKeyEnums.NEWS_ID_BLACK_LIST.getKey(), newsVo.getId().toString(), count);
            }

        }
        return newsInfoEntity;
    }

    protected NewsInfoEntity doGetContent(NewsVo newsVo) {
        if (null == newsVo || StrUtil.isBlank(newsVo.getUrl())) {
            return null;
        }

        String content = null;
        try {
            String body = httpRequest(newsVo.getUrl())
                    .execute().body();
            if (StrUtil.isBlank(body)) {
                return null;
            }


            content = parserContent(body);
            if (StrUtil.isBlank(content)) {
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        return NewsInfoEntity.builder().type(ConstantEnum.NEWS_INFO_TYPE_HTML.getValue())
                .content(content)
                .newsId(newsVo.getId())
                .build();
    }

    /**
     * 页面解析为内容
     *
     * @param html html 页面
     * @return java.lang.String
     * @since 2021/7/14
     */
    protected abstract String parserContent(String html);

    /**
     * 设置httpRequest
     *
     * @param url
     * @return cn.hutool.http.HttpRequest
     * @since 2021/7/14
     */
    protected HttpRequest httpRequest(String url) {
        return HttpUtil.createGet(url).
                header("user-agent", UserAgent.getUserAgent()).setFollowRedirects(true);
    }
}
