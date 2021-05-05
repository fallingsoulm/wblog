package com.wblog.info.manage.impl;

import com.apes.hub.core.manage.MybatisPlusManageImpl;
import com.apes.hub.info.entity.GitSynEntity;
import com.apes.hub.info.manage.IGitSynManage;
import com.apes.hub.info.mapper.GitSynMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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
public class GitSynManageImpl extends MybatisPlusManageImpl<GitSynMapper, GitSynEntity> implements IGitSynManage {
    @Override
    protected LambdaQueryWrapper<GitSynEntity> createQueryWrapper(GitSynEntity entity) {
        return super.createQueryWrapper(entity).orderByDesc(GitSynEntity::getUpdateTime);
    }

    @Cacheable(sync = true)
    @Override
    public List<GitSynEntity> findByArticleIds(List<Long> articleIds) {


        LambdaQueryWrapper<GitSynEntity> queryWrapper = createQueryWrapper(null);
        queryWrapper.in(GitSynEntity::getArticleId, articleIds);
        return this.list(queryWrapper);
    }
}
