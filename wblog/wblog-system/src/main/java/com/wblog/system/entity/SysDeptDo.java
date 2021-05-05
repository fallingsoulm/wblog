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

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * <p>
 * 部门表
 * </p>
 *
 * @author luyanan
 * @since 2021-02-03
 */
@ApiModel(value = "部门表")
@TableName("sys_dept")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysDeptDo implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 部门id
	 */
	@ApiModelProperty(value = "部门id")
	@TableId(type = IdType.AUTO)
	private Long deptId;

	/**
	 * 父部门id
	 */
	@ApiModelProperty(value = "父部门id")
	private Long parentId;

	/**
	 * 祖级列表
	 */
	@ApiModelProperty(value = "祖级列表")
	private String ancestors;

	/**
	 * 部门名称
	 */
	@NotBlank(message = "部门名称不能为空")
	@Size(min = 0, max = 30, message = "部门名称长度不能超过30个字符")
	@TableField(condition = SqlCondition.LIKE)
	@ApiModelProperty(value = "部门名称")
	private String deptName;

	/**
	 * 显示顺序
	 */
	@NotBlank(message = "显示顺序不能为空")
	@ApiModelProperty(value = "显示顺序")
	private String orderNum;

	/**
	 * 负责人
	 */
	@ApiModelProperty(value = "负责人")
	private String leader;

	/**
	 * 联系电话
	 */
	@Size(min = 0, max = 11, message = "联系电话长度不能超过11个字符")
	@ApiModelProperty(value = "联系电话")
	private String phone;

	/**
	 * 邮箱
	 */

	@Email(message = "邮箱格式不正确")
	@Size(min = 0, max = 50, message = "邮箱长度不能超过50个字符")
	@ApiModelProperty(value = "邮箱")
	private String email;

	/**
	 * 部门状态（0正常 1停用）
	 */
	@ApiModelProperty(value = "部门状态（0正常 1停用）")
	private String status;

	/**
	 * 删除标志（0代表存在 2代表删除）
	 */
	@ApiModelProperty(value = "删除标志（0代表存在 2代表删除）")
	private String delFlag;

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

}
