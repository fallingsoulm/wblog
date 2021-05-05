package com.wblog.common.module.info.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
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
 * 热门资讯
 * </p>
 *
 * @author luyanan
 * @since 2020-07-13
 */
@ApiModel(value = "热门资讯")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewsVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private Long id;

    /**
     * 来源
     */
    @ApiModelProperty(value = "来源")
    private Integer source;

    /**
     * 标题
     */
    @ApiModelProperty(value = "标题")
    private String title;

    /**
     * 介绍
     */
    @ApiModelProperty(value = "介绍")
    private String desp;

    /**
     * url
     */
    @ApiModelProperty(value = "url")
    private String url;

    /**
     * 发布时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "发布时间")
    private Date publishTime;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;


    @ApiModelProperty(value = "来源")
    private String sourceStr;


}
