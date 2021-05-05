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
 * 文章详情表
 * </p>
 *
 * @author luyanan
 * @since 2020-06-10
*/
@ApiModel(value = "文章详情表")
@TableName("b_article_info")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleInfoEntity implements Serializable{

private static final long serialVersionUID = 1L;

    /**
    * 主键id
    */
    @ApiModelProperty(value = "主键id")
    @TableId(type = IdType.INPUT)
    private Long articleId;

    /**
    * 内容
    */
    @ApiModelProperty(value = "内容")
    private String content;



}
