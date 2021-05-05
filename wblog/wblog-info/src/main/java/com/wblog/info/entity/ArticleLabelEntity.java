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

/**
 * <p>
 * 文章与标签关联
 * </p>
 *
 * @author luyanan
 * @since 2020-06-10
 */
@ApiModel(value = "文章与标签关联")
@TableName("b_article_label")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleLabelEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    @TableId(type = IdType.AUTO)
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


}
