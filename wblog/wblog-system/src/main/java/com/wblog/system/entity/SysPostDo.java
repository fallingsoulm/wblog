package com.wblog.system.entity;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * <p>
 * 岗位信息表
 * </p>
 *
 * @author luyanan
 * @since 2021-02-03
 */
@ApiModel(value = "岗位信息表")
@TableName("sys_post")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysPostDo implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 岗位ID
	 */
	@ApiModelProperty(value = "岗位ID")
	@TableId(type = IdType.AUTO)
	private Long postId;

	/**
	 * 岗位编码
	 */

	@NotBlank(message = "岗位编码不能为空")
	@Size(min = 0, max = 64, message = "岗位编码长度不能超过64个字符")
	@ApiModelProperty(value = "岗位编码")
	@TableField(condition = SqlCondition.LIKE)
	private String postCode;

	/**
	 * 岗位名称
	 */
	@NotBlank(message = "岗位名称不能为空")
	@Size(min = 0, max = 50, message = "岗位名称长度不能超过50个字符")
	@ApiModelProperty(value = "岗位名称")
	@TableField(condition = SqlCondition.LIKE)
	private String postName;

	/**
	 * 显示顺序
	 */
	@NotBlank(message = "显示顺序不能为空")
	@ApiModelProperty(value = "显示顺序")
	private String postSort;

	/**
	 * 状态（0正常 1停用）
	 */
	@ApiModelProperty(value = "状态（0正常 1停用）")
	private String status;

	/**
	 * 创建者
	 */
	@TableField(fill = FieldFill.INSERT)
	@ApiModelProperty(value = "创建者")
	private String createBy;

	/**
	 * 创建时间
	 */
	@TableField(fill = FieldFill.INSERT)
	@ApiModelProperty(value = "创建时间")
	private Date createTime;

	/**
	 * 更新者
	 */
	@TableField(fill = FieldFill.INSERT_UPDATE)
	@ApiModelProperty(value = "更新者")
	private String updateBy;

	/**
	 * 更新时间
	 */
	@TableField(fill = FieldFill.INSERT_UPDATE)
	@ApiModelProperty(value = "更新时间")
	private Date updateTime;

	/**
	 * 备注
	 */
	@ApiModelProperty(value = "备注")
	private String remark;

}
