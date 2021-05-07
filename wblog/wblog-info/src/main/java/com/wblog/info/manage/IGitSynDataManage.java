package com.wblog.info.manage;


import com.wblog.info.entity.GitSynDataEntity;
import io.github.fallingsoulm.easy.archetype.data.manage.IManage;
import io.github.fallingsoulm.easy.archetype.framework.page.PageInfo;
import io.github.fallingsoulm.easy.archetype.framework.page.PageRequestParams;

import java.util.List;

/**
 * <p>
 * git同步信息配置 manage
 * </p>
 *
 * @author luyanan
 * @since 2020-06-07
 */
public interface IGitSynDataManage extends IManage<GitSynDataEntity> {


    /**
     * <p>根据状态列表查询</p>
     *
     * @param params
     * @param statusList
     * @return {@link PageInfo< GitSynDataEntity>}
     * @author luyanan
     * @since 2020/6/28
     */
    PageInfo<GitSynDataEntity> findByPage(PageRequestParams<GitSynDataEntity> params, List<Integer> statusList);
}
