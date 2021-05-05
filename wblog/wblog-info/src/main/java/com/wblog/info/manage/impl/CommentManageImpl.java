package com.wblog.info.manage.impl;

import com.apes.hub.core.manage.MybatisPlusCacheManageImpl;
import com.apes.hub.info.entity.CommentEntity;
import com.apes.hub.info.manage.ICommentManage;
import com.apes.hub.info.mapper.CommentMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author luyanan
 * @since 2020/9/19
 * <p></p>
 **/
@Service
public class CommentManageImpl extends MybatisPlusCacheManageImpl<CommentMapper, CommentEntity> implements ICommentManage {


    @Cacheable(sync = true)
    @Override
    public List<CommentEntity> findByBlogIdAndParentCommentNull(Long articleId) {
        LambdaQueryWrapper<CommentEntity> queryWrapper = queryWrapper(CommentEntity.builder().articleId(articleId).build());
        queryWrapper.isNull(CommentEntity::getParentCommentId);
        return this.list(queryWrapper);


    }


    protected LambdaQueryWrapper<CommentEntity> queryWrapper(CommentEntity entity) {
        return new LambdaQueryWrapper<>(entity).orderByDesc(CommentEntity::getCreateTime);
    }


}
