package com.wblog.info.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
 * 链藏分类表
 * </p>
 *
 * @author luyanan
 * @since 2020-06-23
 */
@ApiModel(value = "链藏分类表")
@TableName("b_chain_collection_classify")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChainCollectionClassifyEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @ApiModelProperty(value = "")
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 分类名称
     */
    @ApiModelProperty(value = "分类名称")
    private String name;

    /**
     * 分类,用户自定义/系统
     */
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


}
