package com.wblog.info.manage.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.wblog.info.entity.CommentEntity;
import com.wblog.info.manage.ICommentManage;
import com.wblog.info.mapper.CommentMapper;
import io.github.fallingsoulm.easy.archetype.data.manage.impl.CacheManageImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author luyanan
 * @since 2020/9/19
 * <p></p>
 **/
@Service
public class CommentManageImpl extends CacheManageImpl<CommentMapper, CommentEntity> implements ICommentManage {


    @Cacheable(sync = true)
    @Override
    public List<CommentEntity> findByBlogIdAndParentCommentNull(Long articleId) {
        LambdaQueryWrapper<CommentEntity> queryWrapper = lambdaQueryWrapper(CommentEntity.builder().articleId(articleId).build());
        queryWrapper.isNull(CommentEntity::getParentCommentId);
        return this.list(queryWrapper);


    }


    @Override
    protected LambdaQueryWrapper<CommentEntity> lambdaQueryWrapper(CommentEntity entity) {
        return new LambdaQueryWrapper<>(entity).orderByDesc(CommentEntity::getCreateTime);
    }


}
