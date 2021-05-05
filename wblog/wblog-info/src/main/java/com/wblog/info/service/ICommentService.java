package com.wblog.info.service;

import com.apes.hub.api.module.info.vo.CommentVo;

import java.util.List;

/**
 * @author luyanan
 * @since 2020/9/19
 * <p>评论</p>
 **/
public interface ICommentService {


    /**
     * <p>根据文章id查询</p>
     *
     * @param articleId
     * @return {@link List<CommentVo>}
     * @author luyanan
     * @since 2020/9/19
     */
    List<CommentVo> findByArticleId(Long articleId);

    /**
     * <p>保存</p>
     *
     * @param commentVo
     * @author luyanan
     * @since 2020/9/19
     */
    void save(CommentVo commentVo);

}
