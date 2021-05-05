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
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LabelVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private Long id;

    /**
     * 标签名称
     */
    @NotBlank(message = "标签名称不能为空", groups = AddGroup.class)
    @ApiModelProperty(value = "标签名称")
    private String name;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private Long createBy;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    /**
     * 修改人
     */
    @ApiModelProperty(value = "修改人")
    private Long updateBy;

    @NotBlank(message = "别名不能为空", groups = AddGroup.class)
    @ApiModelProperty(value = "别名")
    private String aliases;

    @ApiModelProperty(value = "图标")
    private String icon;

    private Integer num;

    /**
     * <p>文章的状态</p>
     *
     * @author luyanan
     * @since 2020/10/27
     */
    private Integer articleStatus;

}
