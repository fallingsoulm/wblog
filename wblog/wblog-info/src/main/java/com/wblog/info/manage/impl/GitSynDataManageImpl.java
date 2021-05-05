package com.wblog.info.manage.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.apes.hub.api.page.PageInfo;
import com.apes.hub.api.page.PageRequestParams;
import com.apes.hub.core.datascope.GlobalDataScopeAspect;
import com.apes.hub.core.manage.MybatisPlusCacheManageImpl;
import com.apes.hub.info.entity.GitSynDataEntity;
import com.apes.hub.info.manage.IGitSynDataManage;
import com.apes.hub.info.mapper.GitSynDataMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * git同步信息配置 manageImpl
 * </p>
 *
 * @author luyanan
 * @since 2020-06-07
 */
@Service
public class GitSynDataManageImpl extends MybatisPlusCacheManageImpl<GitSynDataMapper, GitSynDataEntity> implements IGitSynDataManage {

    @Autowired
    private GlobalDataScopeAspect globalDataScopeAspect;

    @Cacheable(sync = true)
    @Override
    public PageInfo<GitSynDataEntity> findByPage(PageRequestParams<GitSynDataEntity> params, List<Integer> statusList) {
        LambdaQueryWrapper<GitSynDataEntity> queryWrapper =
                getIn(params.getParams());
        queryWrapper.in(CollectionUtil.isNotEmpty(statusList), GitSynDataEntity::getStatus, statusList);
        queryWrapper.orderByDesc(GitSynDataEntity::getStatus);
        queryWrapper.orderByDesc(GitSynDataEntity::getCreateTime);
        return this.mybatisPlusUtils.toPageInfo(params, queryWrapper, this);
    }


    @Override
    protected LambdaQueryWrapper<GitSynDataEntity> createQueryWrapper(GitSynDataEntity entity) {
        return getIn(entity).orderByDesc(GitSynDataEntity::getCreateTime);
    }

    private LambdaQueryWrapper<GitSynDataEntity> getIn(GitSynDataEntity entity) {
        return super.createQueryWrapper(entity).in(CollectionUtil.isNotEmpty(globalDataScopeAspect.dataScopeUserIds()),
                GitSynDataEntity::getUserId, globalDataScopeAspect.dataScopeUserIds());
    }
}
