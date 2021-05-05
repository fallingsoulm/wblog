package com.wblog.common.module.info.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luyanan
 * @since 2020/9/19
 * <p>评论的vo</p>
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentVo {
    /**
     *
     */

    @ApiModelProperty(value = "")
    private Long id;

    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称")
    private String nickName;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    private String email;

    /**
     * 内容
     */
    @ApiModelProperty(value = "内容")
    private String content;

    /**
     * 头像
     */
    @ApiModelProperty(value = "头像")
    private String avatar;

    /**
     * 本人评价
     */
    @ApiModelProperty(value = "本人评价")
    private Boolean adminComment;

    /**
     * 文章id
     */
    @ApiModelProperty(value = "文章id")
    private Long articleId;

    /**
     * 评论父id
     */
    @ApiModelProperty(value = "评论父id")
    private Long parentCommentId;

    @ApiModelProperty(value = "评论父昵称")
    private String parentCommentNickName;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Long createTime;


    private ArticleVo article;

    private List<CommentVo> replyComments = new ArrayList<>();

    private CommentVo parentComment;

}
