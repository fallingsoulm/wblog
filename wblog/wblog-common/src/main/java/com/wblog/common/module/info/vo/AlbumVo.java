package com.wblog.common.module.info.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.github.fallingsoulm.easy.archetype.framework.validation.AddGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * <p>
 * 文章专辑
 * </p>
 *
 * @author luyanan
 * @since 2020-07-15
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@ApiModel(value = "文章专辑")
public class AlbumVo {


    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private Long id;

    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id")
    private Long userId;

    /**
     * 专辑标题
     */
    @NotBlank(message = "标题不能为空", groups = AddGroup.class)
    @ApiModelProperty(value = "专辑标题")
    private String title;

    /**
     * 专辑介绍
     */
    @NotBlank(message = "介绍不能为空", groups = AddGroup.class)
    @ApiModelProperty(value = "专辑介绍")
    private String desp;

    /**
     * 专辑封面图片
     */
//    @NotBlank(message = "封面图片不能为空", groups = AddGroup.class)
    @ApiModelProperty(value = "专辑封面图片")
    private String image;

    /**
     * 创建时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 修改时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    /**
     * 删除标识
     */
    @ApiModelProperty(value = "删除标识")
    private Integer delFlag;


    @ApiModelProperty(value = "状态")
    private Integer status;

    @ApiModelProperty(value = "文章数量")
    private Integer articleNum;

    @ApiModelProperty(value = "用户名")
    private String userName;


}
