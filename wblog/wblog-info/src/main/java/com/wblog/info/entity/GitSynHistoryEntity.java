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
@TableName("b_git_syn_history")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GitSynHistoryEntity implements Serializable{

private static final long serialVersionUID = 1L;

    /**
    * id
    */
    @ApiModelProperty(value = "id")
    @TableId(type = IdType.AUTO)
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



}
