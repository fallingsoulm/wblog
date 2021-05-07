package com.wblog.info.manage;

import com.wblog.info.entity.CommentEntity;
import io.github.fallingsoulm.easy.archetype.data.manage.IManage;

import java.util.List;

/**
 * @author luyanan
 * @since 2020/9/19
 * <p>评论</p>
 **/
public interface ICommentManage extends IManage<CommentEntity> {


    /**
     * <p>根据文章id查询,parentCmment 不为null</p>
     *
     * @param articleId
     * @return {@link List<CommentEntity>}
     * @author luyanan
     * @since 2020/10/26
     */
    List<CommentEntity> findByBlogIdAndParentCommentNull(Long articleId);
}
