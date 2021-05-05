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
 * git同步历史
 * </p>
 *
 * @author luyanan
 * @since 2020-06-10
 */
@ApiModel(value = "git同步历史")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GitSynHistoryVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private Long id;

    /**
     * gitid
     */
    @ApiModelProperty(value = "gitid")
    private Long gitId;

    /**
     * 新增文件的数量
     */
    @ApiModelProperty(value = "新增文件的数量")
    private Integer addNum;

    /**
     * 修改文件的数量
     */
    @ApiModelProperty(value = "修改文件的数量")
    private Integer updateNum;

    /**
     * 删除的文件
     */
    @ApiModelProperty(value = "删除的文件")
    private Integer deleteNum;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "项目名称")
    private String projectName;


}
