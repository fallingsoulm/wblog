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
 * git同步信息配置
 * </p>
 *
 * @author luyanan
 * @since 2020-06-07
 */
@ApiModel(value = "git同步信息配置")
@TableName("b_git_syn_data")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GitSynDataEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @ApiModelProperty(value = "")
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id")
    private Long userId;

    /**
     * 项目名称
     */
    @TableField(condition = SqlCondition.LIKE)
    @ApiModelProperty(value = "项目名称")
    private String projectName;

    /**
     * git地址
     */
    @TableField(condition = SqlCondition.LIKE)
    @ApiModelProperty(value = "git地址")
    private String gitUrl;

    /**
     * 分支名
     */
    @ApiModelProperty(value = "分支名")
    private String branch;

    /**
     * 基础路径
     */
    @ApiModelProperty(value = "基础路径")
    private String basePath;

    /**
     * 忽略文件
     */
    @ApiModelProperty(value = "忽略文件")
    private String ignoreFile;

    /**
     * 状态, 100:正常, 101 : 下架: 102: 审核中
     */
    @ApiModelProperty(value = "状态, 100:正常, 101 : 下架: 102: 审核中")
    private Integer status;

    /**
     * 删除标识, 100:正常,101:删除
     */
    @TableLogic
    @ApiModelProperty(value = "删除标识")
    private Integer delFlag;

    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private Long createBy;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 修改人
     */
    @ApiModelProperty(value = "修改人")
    private Long updateBy;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.UPDATE)
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    /**
     * <p>来源</p>
     *
     * @author luyanan
     * @since 2020/6/22
     */
    @ApiModelProperty(value = "来源")
    private Integer source;

    /**
     * <p>简介</p>
     *
     * @author luyanan
     * @since 2020/6/22
     */
    @ApiModelProperty(value = "简介")
    private String description;


    @ApiModelProperty(value = "作者")
    private String owner;
}
