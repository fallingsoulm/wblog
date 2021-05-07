package com.wblog.info.manage;


import com.wblog.common.module.info.vo.LabelVo;
import com.wblog.info.entity.ArticleLabelEntity;
import io.github.fallingsoulm.easy.archetype.data.manage.IManage;
import io.github.fallingsoulm.easy.archetype.framework.page.PageRequestParams;

import java.util.List;

/**
 * <p>
 * 文章与标签关联 manage
 * </p>
 *
 * @author luyanan
 * @since 2020-06-10
 */
public interface IArticleLabelManage extends IManage<ArticleLabelEntity> {


    /**
     * <p>根据条件查询</p>
     *
     * @param params
     * @return {@link Integer}
     * @author luyanan
     * @since 2020/6/15
     */
    Integer findAndCount(LabelVo params);

    /**
     * <p>查询列表</p>
     *
     * @param pageRequestParams
     * @return {@link List< LabelVo>}
     * @author luyanan
     * @since 2020/6/15
     */
    List<LabelVo> listAndCount(PageRequestParams<LabelVo> pageRequestParams);

}
