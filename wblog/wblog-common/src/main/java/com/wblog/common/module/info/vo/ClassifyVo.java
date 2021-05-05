package com.wblog.common.module.info.vo;

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
 * 文章分类
 * </p>
 *
 * @author luyanan
 * @since 2020-06-10
*/
@ApiModel(value = "文章分类")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClassifyVo implements Serializable{

private static final long serialVersionUID = 1L;

    /**
    * id
    */
    @ApiModelProperty(value = "id")
    private Long id;

    /**
    * 分类id
    */
    @ApiModelProperty(value = "分类id")
    private String name;

    /**
    * 用户id
    */
    @ApiModelProperty(value = "用户id")
    private Long userId;

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



}
