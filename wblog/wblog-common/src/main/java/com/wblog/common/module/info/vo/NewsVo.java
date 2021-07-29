package com.wblog.common.module.info.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 热门资讯
 * </p>
 *
 * @author luyanan
 * @since 2020-07-13
 */
@ApiModel(value = "热门资讯")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewsVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private Long id;

    /**
     * 来源
     */
    @ApiModelProperty(value = "来源")
    private Integer source;

    /**
     * 标题
     */
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "发布时间")
    private Date publishTime;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;


    @ApiModelProperty(value = "来源")
    private String sourceStr;


    /**
     * 内容
     *
     * @since 2021/7/15
     */
    @ApiModelProperty(value = "内容")
    private String content;


    /**
     * 内容类型 0:markdown  1: html
     *
     * @since 2021/7/17
     */
    @ApiModelProperty(value = "内容类型 0:markdown  1: html")
    private Integer type;

    /**
     * 访问量
     *
     * @since 2021/7/17
     */
    @ApiModelProperty(value = "访问量")
    private Long view;

    /**
     * 标签
     *
     * @since 2021/7/17
     */
    @ApiModelProperty(value = "标签")
    private List<LabelVo> labelVos = new ArrayList<>();

    /**
     * 头图
     *
     * @since 2021/7/18
     */
    @ApiModelProperty(value = "头图")
    private String image;
}
