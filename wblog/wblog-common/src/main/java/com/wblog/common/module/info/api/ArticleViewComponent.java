package com.wblog.common.module.info.api;

import com.wblog.common.enums.ConstantEnum;
import com.wblog.common.exception.BusinessException;
import com.wblog.common.module.info.vo.ArticleVo;
import com.wblog.common.module.info.vo.NewsVo;
import com.wblog.common.redis.RedisKeyEnums;
import io.github.fallingsoulm.easy.archetype.data.redis.RedisKeyGenerator;
import io.github.fallingsoulm.easy.archetype.framework.page.PageRequestParams;
import io.github.fallingsoulm.easy.archetype.framework.page.RespEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * 文章访问量
 *
 * @author luyanan
 * @since 2021/7/20
 **/
@Component
public class ArticleViewComponent {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisKeyGenerator redisKeyGenerator;

    @Autowired
    private NewsApi newsApi;

    @Autowired
    private ArticleApi articleApi;

    /**
     * 根据访问量排序,获取id集合
     *
     * @param pageRequestParams 分页参数
     * @return java.util.LinkedHashSet<java.lang.Long>
     * @since 2021/7/20
     */
    public LinkedHashSet<String> getListByView(PageRequestParams pageRequestParams) {
        LinkedHashSet set = (LinkedHashSet) redisTemplate.opsForZSet().reverseRange(getKey(),
                pageRequestParams.getOffset(), pageRequestParams.getOffset().intValue() + pageRequestParams.getPageSize().intValue() - 1);
        LinkedHashSet<String> articleIds = new LinkedHashSet<>();
        for (Object o : set) {

            articleIds.add(o.toString());
        }
        return articleIds;
    }


    /**
     * 自增访问量
     *
     * @param classify 分类
     * @param id       id
     * @return java.lang.Double
     * @since 2021/7/20
     */
    public Long incrementView(Integer classify, Long id, Long initialValue) {
        if (null == redisTemplate.opsForZSet().score(getKey(), classify + "-" + id)) {
            getView(classify, id, initialValue);
        }
        Double view = redisTemplate.opsForZSet().incrementScore(getKey(), classify + "-" + id, 1);
        return view.longValue();
    }

    /**
     * 获取访问量
     *
     * @param classify 分类
     * @param id       id
     * @return java.lang.Double
     * @since 2021/7/20
     */
    public Long getView(Integer classify, Long id, Long initialValue) {
        Long result = 0L;
        Double view = redisTemplate.opsForZSet().score(getKey(), classify + "-" + id);
        if (null == view) {
            if (null == initialValue) {
                // 从数据库中查询
                if (ConstantEnum.SEARCH_INFO_TYPE_NEWS.getValue().equals(classify)) {
                    // 资讯
                    RespEntity<NewsVo> entity =
                            newsApi.findById(id);
                    if (null == entity || null == entity.getData()) {
                        throw new BusinessException(id + ":资讯不存在");
                    }
                    if (null == entity.getData().getView()) {
                        result = entity.getData().getView();
                    }


                } else if (ConstantEnum.SEARCH_INFO_TYPE_ARTICLE.getValue().equals(classify)) {
                    RespEntity<ArticleVo> respEntity = articleApi.findById(id);
                    if (null == respEntity || null == respEntity.getData()) {
                        throw new BusinessException(id + ":文章不存在");
                    }
                    if (null != respEntity.getData().getView()) {
                        result = respEntity.getData().getView();
                    }
                }
            } else {
                result = initialValue;

            }


            // 存放redis中,并且返回
            redisTemplate.opsForZSet().add(getKey(), classify + "-" + id, null == result ? 0L : result);
            return result;
        } else {
            return view.longValue();
        }

    }

    /**
     * 获取redis访问的key
     *
     * @return java.lang.String
     * @since 2021/7/20
     */
    public String getKey() {
        String viewKey = redisKeyGenerator.generate(RedisKeyEnums.ARTICLE_VIEW);
        return viewKey;
    }

    /**
     * 获取总数量
     *
     * @return java.lang.Long
     * @since 2021/7/20
     */
    public Long getTotal() {
        return redisTemplate.opsForZSet().size(getKey());
    }
}
