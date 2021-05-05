package com.wblog.info.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 文章表
 * </p>
 *
 * @author luyanan
 * @since 2020-06-10
 */
@ApiModel(value = "文章表")
@TableName("b_article")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    @TableId(type = IdType.INPUT)
    private Long id;

    /**
     * 标题
     */
    @TableField(condition = SqlCondition.LIKE)
    @ApiModelProperty(value = "标题")
    private String title;

    /**
     * 介绍
     */
    @TableField(condition = SqlCondition.LIKE)
    @ApiModelProperty(value = "介绍")
    private String introduction;

    /**
     * 头图
     */
    @ApiModelProperty(value = "头图")
    private String image;

    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id")
    private Long userId;

    /**
     * 访问量
     */
    @ApiModelProperty(value = "访问量")
    private Long view;

    /**
     * 分类id
     */
    @ApiModelProperty(value = "分类id")
    private Long classifyId;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private Integer status;

    /**
     * 删除标识
     */
    @TableLogic
    @ApiModelProperty(value = "删除标识")
    private Integer delFlag;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private Long createBy;

    /**
     * 来源 原创/转载/翻译
     */
    @ApiModelProperty(value = "来源 原创/转载/翻译")
    private Integer source;

    @ApiModelProperty(value = "gitId")
    private Long gitId;
}
