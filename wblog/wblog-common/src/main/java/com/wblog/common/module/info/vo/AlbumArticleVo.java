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
 * 文章与专辑关联
 * </p>
 *
 * @author luyanan
 * @since 2020-07-15
 */
@ApiModel(value = "文章与专辑关联")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlbumArticleVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @ApiModelProperty(value = "")
    private Long id;

    /**
     * 专辑id
     */
    @ApiModelProperty(value = "专辑id")
    private Long albumId;

    /**
     * 文章id
     */
    @ApiModelProperty(value = "文章id")
    private Long articleId;

    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer orderNum;


    @ApiModelProperty(value = "文章")
    private ArticleVo articleVo;

    @ApiModelProperty(value = "文章用户id")
    private Long userId;

    @ApiModelProperty(value = "分类id")
    private Long classifyId;

    public ArticleVo getArticleVo() {
        if (null == articleVo) {
            articleVo = new ArticleVo();
        }
        articleVo.setClassifyId(this.classifyId);
        articleVo.setUserId(this.userId);
        return articleVo;
    }
}
