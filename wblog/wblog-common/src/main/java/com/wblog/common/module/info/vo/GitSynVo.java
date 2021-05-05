package com.wblog.common.module.info.vo;

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
 * 同步表
 * </p>
 *
 * @author luyanan
 * @since 2020-06-10
 */
@ApiModel(value = "同步表")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GitSynVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private Long id;

    /**
     * git的id
     */
    @ApiModelProperty(value = "git的id")
    private Long gitId;

    /**
     * 文章id
     */
    @ApiModelProperty(value = "文章id")
    private Long articleId;

    /**
     * 文件所在的路径
     */
    @ApiModelProperty(value = "文件所在的路径")
    private String path;

    /**
     * 文件内容MD5
     */
    @ApiModelProperty(value = "文件内容MD5")
    private String md5;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "文件内容")
    private String content;


    /**
     * <p>编码</p>
     *
     * @author luyanan
     * @since 2020/7/10
     */
    @ApiModelProperty(value = "编码")
    private String charset;
}
