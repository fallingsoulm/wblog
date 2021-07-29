package com.wblog.search.vo;

import com.wblog.common.module.info.vo.LabelVo;
import io.github.fallingsoulm.easy.archetype.data.file.annotation.FileHostProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 文章搜索Vo类
 *
 * @author luyanan
 * @since 2021/7/18
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "文章搜索Vo")
public class ArticleSearchVo implements Serializable {


    private static final long serialVersionUID = -3769489470272434611L;
    /**
     * id
     *
     * @since 2021/7/18
     */
    @ApiModelProperty(value = "id")
    private Long id;


    /**
     * 标题
     *
     * @since 2021/7/18
     */
    @ApiModelProperty(value = "标题")
    private String title;


    /**
     * 介绍
     *
     * @since 2021/7/18
     */
    @ApiModelProperty(value = "介绍")
    private String introduction;


    /**
     * 头图
     *
     * @since 2021/7/18
     */
    @ApiModelProperty(value = "头图")
    @FileHostProperty
    private String image;


    /**
     * 内容
     *
     * @since 2021/7/18
     */
    @ApiModelProperty(value = "内容")
    private String content;


    /**
     * 分类
     *
     * @since 2021/7/18
     */
    @ApiModelProperty(value = "分类")
    private Integer classify;


    /**
     * 创建时间
     *
     * @since 2021/7/18
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;


    /**
     * 用户id
     *
     * @since 2021/7/18
     */
    @ApiModelProperty(value = "用户id")
    private Long userId;

    /**
     * 标签列表
     *
     * @since 2021/7/18
     */
    @ApiModelProperty(value = "标签列表")
    private List<LabelVo> labelVos;


    /**
     * 用户名称
     *
     * @since 2021/7/19
     */
    @ApiModelProperty(value = "用户名称")
    private String userName;


    /**
     * 访问量
     *
     * @since 2021/7/19
     */
    @ApiModelProperty(value = "访问量")
    private Long view;

    /**
     * 排序
     *
     * @since 2021/7/19
     */
    @ApiModelProperty(value = "排序")
    private Integer orderBy;

    /**
     * 标签id
     *
     * @since 2021/7/24
     */
    @ApiModelProperty(value = "标签id")
    private Long labelId;
}
