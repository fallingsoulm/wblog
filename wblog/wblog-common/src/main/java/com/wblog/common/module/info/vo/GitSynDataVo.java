package com.wblog.common.module.info.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * git同步信息配置
 * </p>
 *
 * @author luyanan
 * @since 2020-06-07
 */
@ApiModel(value = "git同步信息配置")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GitSynDataVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @ApiModelProperty(value = "")
    private Long id;

    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id")
    private Long userId;

    /**
     * 项目名称
     */
    @NotBlank(message = "项目名称不能为空")
    @ApiModelProperty(value = "项目名称")
    private String projectName;

    /**
     * git地址
     */
    @NotBlank(message = "url地址不能为空")
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
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private Long createBy;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 修改人
     */
    @ApiModelProperty(value = "修改人")
    private Long updateBy;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;


    /**
     * <p>创建人 </p>
     *
     * @author luyanan
     * @since 2020/6/8
     */
    private String createName;
    @ApiModelProperty(value = "用户名")
    private String userName;
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

    /**
     * <p>状态集合</p>
     *
     * @author luyanan
     * @since 2020/6/28
     */
    private List<Integer> statusList;

    @ApiModelProperty(value = "上架的数量")
    private Integer enableNum;

    @ApiModelProperty(value = "下架的数量")
    private Integer stopNum;


    @ApiModelProperty(value = "废弃的数量")
    private Integer discardNum;

}
