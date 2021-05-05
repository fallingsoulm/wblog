package com.wblog.info.manage;

import com.apes.hub.api.page.PageInfo;
import com.apes.hub.api.page.PageRequestParams;
import com.apes.hub.core.manage.IManage;
import com.apes.hub.info.entity.GitSynDataEntity;

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
