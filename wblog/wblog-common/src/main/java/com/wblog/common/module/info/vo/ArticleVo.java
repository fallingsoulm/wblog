package com.wblog.common.module.info.vo;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.wblog.common.module.system.vo.SysUserVo;
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
 * <p>
 * 文章表
 * </p>
 *
 * @author luyanan
 * @since 2020-06-10
 */
@ApiModel(value = "文章表")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private Long id;

    /**
     * 标题
     */
    @ApiModelProperty(value = "标题")
    private String title;

    /**
     * 介绍
     */
    @ApiModelProperty(value = "介绍")
    private String introduction;

    /**
     * 头图
     */
    @FileHostProperty
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
    @ApiModelProperty(value = "删除标识")
    private Integer delFlag;

    /**
     * 创建时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
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


    @ApiModelProperty(value = "来源")
    private String sourceVal;


    /**
     * <p>内容</p>
     *
     * @author luyanan
     * @since 2020/6/10
     */
    @ApiModelProperty(value = "详情")
    private String content;

    @ApiModelProperty(value = "分类名称")
    private String classifyName;

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "标签列表")
    List<LabelVo> labelVos;

    @ApiModelProperty(value = "用户")
    private SysUserVo userVo;

    @ApiModelProperty(value = "搜索的字段")
    private String search;

    private String idStr;

    @ApiModelProperty(value = "访问的url")
    private String url;


    public String getIdStr() {
        return id == null ? null : id + "";
    }


    /**
     * <p>状态集合</p>
     *
     * @author luyanan
     * @since 2020/6/28
     */
    private List<Integer> statusList;

    @ApiModelProperty(value = "gitId")
    private Long gitId;


    @ApiModelProperty(value = "审核标签")
    private String reviewLabel;


    @ApiModelProperty(value = "同步编码")
    private String synCharset;

    private boolean check;

    /**
     * <p>排序的方式</p>
     *
     * @return {@link }
     * @author luyanan
     * @since 2020/8/8
     */
    @ApiModelProperty(value = "排序的方式")
    private Integer orderBy;

    /**
     * 是否可以评论
     */
    private Boolean commentabled;
    @ApiModelProperty(value = "评论")
    private List<CommentVo> comments;


    /**
     * <p>是否赞赏</p>
     *
     * @author luyanan
     * @since 2020/9/19
     */
    private Boolean appreciation = Boolean.FALSE;
    /**
     * <p>是否可以转载</p>
     *
     * @author luyanan
     * @since 2020/9/19
     */
    private Boolean shareStatement = Boolean.FALSE;
}
