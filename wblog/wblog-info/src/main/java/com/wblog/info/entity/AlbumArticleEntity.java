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
 * 文章与专辑关联
 * </p>
 *
 * @author luyanan
 * @since 2020-07-15
*/
@ApiModel(value = "文章与专辑关联")
@TableName("b_album_article")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlbumArticleEntity implements Serializable{

private static final long serialVersionUID = 1L;

    /**
    *
    */
    @ApiModelProperty(value = "")
    @TableId(type = IdType.AUTO)
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



}
