package com.wblog.common.module.info.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 链藏分类表
 * </p>
 *
 * @author luyanan
 * @since 2020-06-23
 */
@ApiModel(value = "链藏分类表")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChainCollectionClassifyVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @ApiModelProperty(value = "")
    private Long id;

    /**
     * 分类名称
     */
    @NotBlank(message = "分类名称不能为空")
    @ApiModelProperty(value = "分类名称")
    private String name;

    /**
     * 分类,用户自定义/系统
     */
    @NotNull(message = "分类类型不能为空")
    @ApiModelProperty(value = "分类,用户自定义/系统")
    private Integer type;

    /**
     * 父id
     */
    @ApiModelProperty(value = "父id")
    private Long parentId;

    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private Long createBy;

    /**
     * 创建事件
     */
    @ApiModelProperty(value = "创建事件")
    private Date createTime;

    @ApiModelProperty(value = "父分类名称")
    private String parentName;


}
