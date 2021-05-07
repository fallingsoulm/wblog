package com.wblog.info.manage.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wblog.info.entity.GitSynHistoryEntity;
import com.wblog.info.manage.IGitSynHistoryManage;
import com.wblog.info.mapper.GitSynHistoryMapper;
import io.github.fallingsoulm.easy.archetype.data.manage.impl.CacheManageImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * git同步历史 manageImpl
 * </p>
 *
 * @author luyanan
 * @since 2020-06-10
 */
@Service
public class GitSynHistoryManageImpl extends CacheManageImpl<GitSynHistoryMapper, GitSynHistoryEntity> implements IGitSynHistoryManage {
    @Override
    protected LambdaQueryWrapper<GitSynHistoryEntity> lambdaQueryWrapper(GitSynHistoryEntity entity) {
        return super.lambdaQueryWrapper(entity).orderByDesc(GitSynHistoryEntity::getCreateTime);
    }
}
