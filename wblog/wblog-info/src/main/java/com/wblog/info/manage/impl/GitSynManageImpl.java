package com.wblog.info.manage.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wblog.info.entity.GitSynEntity;
import com.wblog.info.manage.IGitSynManage;
import com.wblog.info.mapper.GitSynMapper;
import io.github.fallingsoulm.easy.archetype.data.manage.impl.CacheManageImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 同步表 manageImpl
 * </p>
 *
 * @author luyanan
 * @since 2020-06-10
 */

@Service
public class GitSynManageImpl extends CacheManageImpl<GitSynMapper, GitSynEntity> implements IGitSynManage {
    @Override
    protected LambdaQueryWrapper<GitSynEntity> lambdaQueryWrapper(GitSynEntity entity) {
        return super.lambdaQueryWrapper(entity).orderByDesc(GitSynEntity::getUpdateTime);
    }

    @Cacheable(sync = true)
    @Override
    public List<GitSynEntity> findByArticleIds(List<Long> articleIds) {


        LambdaQueryWrapper<GitSynEntity> queryWrapper = lambdaQueryWrapper(null);
        queryWrapper.in(GitSynEntity::getArticleId, articleIds);
        return this.list(queryWrapper);
    }
}
