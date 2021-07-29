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
 * 热门资讯
 * </p>
 *
 * @author luyanan
 * @since 2020-07-13
 */
@ApiModel(value = "热门资讯")
@TableName("b_news")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewsEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    @TableId(type = IdType.INPUT)
    private Long id;

    /**
     * 来源
     */

    @ApiModelProperty(value = "来源")
    private Integer source;

    /**
     * 头图
     *
     * @since 2021/7/18
     */
    @ApiModelProperty(value = "头图")
    private String image;
    /**
     * 标题
     */
    @TableField(condition = SqlCondition.LIKE)
    @ApiModelProperty(value = "标题")
    private String title;

    /**
     * 介绍
     */
    @ApiModelProperty(value = "介绍")
    private String desp;

    /**
     * url
     */
    @ApiModelProperty(value = "url")
    private String url;

    /**
     * 发布时间
     */
    @ApiModelProperty(value = "发布时间")
    private Date publishTime;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;


    /**
     * 访问量
     *
     * @since 2021/7/21
     */
    @ApiModelProperty(value = "访问量")
    private Long view;

    /**
     * 删除
     *
     * @since 2021/7/29
     */
    @TableLogic
    @ApiModelProperty(value = "删除")
    private Integer delFlag;

}
