package com.wblog.info.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author luyanan
 * @since 2020/9/19
 * <p>评论表</p>
 **/
@TableName("b_comment")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentEntity {


    /**
     *
     */
    @ApiModelProperty(value = "")
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称")
    @TableField(value = "nick_name")
    private String nickName;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    @TableField(value = "email")
    private String email;

    /**
     * 内容
     */
    @ApiModelProperty(value = "内容")
    @TableField(value = "content")
    private String content;

    /**
     * 头像
     */
    @ApiModelProperty(value = "头像")
    @TableField(value = "avatar")
    private String avatar;

    /**
     * 本人评价
     */
    @ApiModelProperty(value = "本人评价")
    @TableField(value = "admin_comment")
    private Boolean adminComment;

    /**
     * 文章id
     */
    @ApiModelProperty(value = "文章id")
    @TableField(value = "article_id")
    private Long articleId;

    /**
     * 评论父id
     */
    @ApiModelProperty(value = "评论父id")
    @TableField(value = "parent_comment_id")
    private Long parentCommentId;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField(value = "create_time")
    private Long createTime;
}
