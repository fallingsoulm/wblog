package com.wblog.info.manage;


import com.wblog.common.module.info.vo.NewsVo;
import com.wblog.info.entity.NewsEntity;
import io.github.fallingsoulm.easy.archetype.data.manage.IManage;

/**
 * <p>
 * 热门资讯 manage
 * </p>
 *
 * @author luyanan
 * @since 2020-07-13
 */
public interface INewsManage extends IManage<NewsEntity> {


    /**
     * <p>查询当前来源下的最新的url</p>
     *
     * @param source
     * @return {@link String}
     * @author luyanan
     * @since 2020/7/13
     */
    NewsVo lastUrl(Integer source);


}
