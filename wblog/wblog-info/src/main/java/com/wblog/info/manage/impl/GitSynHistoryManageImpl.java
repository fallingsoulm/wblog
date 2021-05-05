package com.wblog.info.manage.impl;

import com.apes.hub.core.manage.MybatisPlusManageImpl;
import com.apes.hub.info.entity.GitSynHistoryEntity;
import com.apes.hub.info.manage.IGitSynHistoryManage;
import com.apes.hub.info.mapper.GitSynHistoryMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.cache.annotation.Cacheable;
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
public class GitSynHistoryManageImpl extends MybatisPlusManageImpl<GitSynHistoryMapper, GitSynHistoryEntity> implements IGitSynHistoryManage {
    @Override
    protected LambdaQueryWrapper<GitSynHistoryEntity> createQueryWrapper(GitSynHistoryEntity entity) {
        return super.createQueryWrapper(entity).orderByDesc(GitSynHistoryEntity::getCreateTime);
    }
}
