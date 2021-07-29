package com.wblog.info.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 资讯内容
 *
 * @author luyanan
 * @since 2021/7/13
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("b_news_info")
public class NewsInfoEntity implements Serializable {
    private static final long serialVersionUID = -9161800279944927555L;


    /**
     * 资讯id
     *
     * @since 2021/7/14
     */
    @TableId(type = IdType.INPUT)
    @ApiModelProperty(value = "资讯id")
    private Long newsId;
    /**
     * 内容
     *
     * @since 2021/7/14
     */
    @ApiModelProperty(value = "内容")
    private String content;


    /**
     * 内容类型 0:markdown  1: html
     *
     * @author luyanan
     * @since 2021/7/14
     */
    private Integer type;
}
