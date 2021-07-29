package com.wblog.common.module.info.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>
 * 文章与标签关联
 * </p>
 *
 * @author luyanan
 * @since 2020-06-10
 */
@ApiModel(value = "文章与标签关联")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleLabelVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private Long id;

    /**
     * 文章id
     */
    @ApiModelProperty(value = "文章id")
    private Long articleId;

    /**
     * 标签id
     */
    @ApiModelProperty(value = "标签id")
    private Long labelId;

    /**
     * 分类
     *
     * @since 2021/7/21
     */
    @ApiModelProperty(value = "分类")
    private Integer classify;


}
