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
 * 标签
 * </p>
 *
 * @author luyanan
 * @since 2020-06-10
 */
@ApiModel(value = "标签")
@TableName("b_label")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LabelEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 标签id
     */
    @ApiModelProperty(value = "标签id")
    private String name;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 创建人
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "创建人")
    private Long createBy;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.UPDATE)
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    /**
     * 修改人
     */
    @TableField(fill = FieldFill.UPDATE)
    @ApiModelProperty(value = "修改人")
    private Long updateBy;


    @ApiModelProperty(value = "别名")
    private String aliases;

    @ApiModelProperty(value = "图标")
    private String icon;

}
