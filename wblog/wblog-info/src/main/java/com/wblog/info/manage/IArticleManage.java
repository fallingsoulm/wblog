package com.wblog.info.manage;


import com.wblog.info.entity.ArticleEntity;
import io.github.fallingsoulm.easy.archetype.data.manage.IManage;
import io.github.fallingsoulm.easy.archetype.framework.page.PageInfo;
import io.github.fallingsoulm.easy.archetype.framework.page.PageRequestParams;

import java.util.List;

/**
 * <p>
 * 文章表 manage
 * </p>
 *
 * @author luyanan
 * @since 2020-06-10
 */
public interface IArticleManage extends IManage<ArticleEntity> {


    /**
     * <p>批量修改状态</p>
     *
     * @param articleIds 文章id集合
     * @param status     状态
     * @author luyanan
     * @since 2020/6/27
     */
    void updateStatus(List<Long> articleIds, Integer status);

    /**
     * <p>根据状态列表查询</p>
     *
     * @param params
     * @param statusList
     * @return {@link PageInfo< ArticleEntity>}
     * @author luyanan
     * @since 2020/6/28
     */
    PageInfo<ArticleEntity> findByPage(PageRequestParams<ArticleEntity> params, List<Integer> statusList);

    /**
     * <p>增加排除的分页查询</p>
     *
     * @param params
     * @param notInIds
     * @return {@link PageInfo< ArticleEntity>}
     * @author luyanan
     * @since 2020/7/15
     */
    PageInfo<ArticleEntity> findByPageAndNotIn(PageRequestParams<ArticleEntity> params, List<Long> notInIds);
}
