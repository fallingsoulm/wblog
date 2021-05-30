package com.wblog.info.task;

import cn.hutool.core.util.StrUtil;

import com.wblog.common.enums.ConstantEnum;
import com.wblog.common.module.info.vo.NewsVo;
import com.wblog.info.config.BlogConfigProperties;
import com.wblog.info.service.INewsService;
import io.github.fallingsoulm.easy.archetype.data.id.SimpleSnowflake;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

/**
 * @author luyanan
 * @since 2020/7/13
 * <p>抽象类</p>
 **/
@Slf4j
public abstract class AbstractNewsTask implements NewsTask {


    @Autowired
    protected INewsService newsService;

    @Autowired
    protected BlogConfigProperties blogConfigProperties;


    @Autowired
    protected SimpleSnowflake snowflake;


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
     * <p>来源</p>
     *
     * @return {@link ConstantEnum}
     * @author luyanan
     * @since 2020/7/13
     */
    protected abstract ConstantEnum source();


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
}
