package com.wblog.info.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
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
 * 文章专辑
 * </p>
 *
 * @author luyanan
 * @since 2020-07-15
 */
@ApiModel(value = "文章专辑")
@TableName("b_album")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlbumEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id")
    private Long userId;

    /**
     * 专辑标题
     */
    @ApiModelProperty(value = "专辑标题")
    private String title;

    /**
     * 专辑介绍
     */
    @ApiModelProperty(value = "专辑介绍")
    private String desp;

    /**
     * 专辑封面图片
     */
    @ApiModelProperty(value = "专辑封面图片")
    private String image;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    /**
     * 删除标识
     */
    @TableLogic()
    @ApiModelProperty(value = "删除标识")
    private Integer delFlag;


    @ApiModelProperty(value = "状态")
    private Integer status;

}
