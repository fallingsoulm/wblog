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
 * 链藏
 * </p>
 *
 * @author luyanan
 * @since 2020-06-23
 */
@ApiModel(value = "链藏")
@TableName("b_chain_collection")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChainCollectionEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @ApiModelProperty(value = "")
    @TableId(type = IdType.INPUT)
    private Long id;

    /**
     * 名称
     */
    @TableField(condition = SqlCondition.LIKE)
    @ApiModelProperty(value = "名称")
    private String name;

    /**
     * 介绍
     */
    @ApiModelProperty(value = "介绍")
    private String introduction;

    /**
     * 图标
     */
    @ApiModelProperty(value = "图标")
    private String icon;

    /**
     * 跳转地址
     */
    @ApiModelProperty(value = "跳转地址")
    private String url;

    /**
     * 分类id
     */
    @ApiModelProperty(value = "分类id")
    private Long classifyId;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private Long createBy;

    @ApiModelProperty(value = "状态")
    private Integer status;

}
