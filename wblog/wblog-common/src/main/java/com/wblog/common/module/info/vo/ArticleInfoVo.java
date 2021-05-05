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
 * 文章详情表
 * </p>
 *
 * @author luyanan
 * @since 2020-06-10
*/
@ApiModel(value = "文章详情表")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleInfoVo implements Serializable{

private static final long serialVersionUID = 1L;

    /**
    * 主键id
    */
    @ApiModelProperty(value = "主键id")
    private Long articleId;

    /**
    * 内容
    */
    @ApiModelProperty(value = "内容")
    private String content;



}
