package com.wblog.info.manage.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wblog.common.datascope.GlobalDataScopeAspect;
import com.wblog.info.entity.GitSynDataEntity;
import com.wblog.info.manage.IGitSynDataManage;
import com.wblog.info.mapper.GitSynDataMapper;
import io.github.fallingsoulm.easy.archetype.data.manage.impl.CacheManageImpl;
import io.github.fallingsoulm.easy.archetype.framework.page.PageInfo;
import io.github.fallingsoulm.easy.archetype.framework.page.PageRequestParams;
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
public class GitSynDataManageImpl extends CacheManageImpl<GitSynDataMapper, GitSynDataEntity> implements IGitSynDataManage {

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
        return this.toPageInfo(params, queryWrapper);
    }


    @Override
    protected LambdaQueryWrapper<GitSynDataEntity> lambdaQueryWrapper(GitSynDataEntity entity) {
        return getIn(entity).orderByDesc(GitSynDataEntity::getCreateTime);
    }

    private LambdaQueryWrapper<GitSynDataEntity> getIn(GitSynDataEntity entity) {
        return super.lambdaQueryWrapper(entity).in(CollectionUtil.isNotEmpty(globalDataScopeAspect.dataScopeUserIds()),
                GitSynDataEntity::getUserId, globalDataScopeAspect.dataScopeUserIds());
    }
}
