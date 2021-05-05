package com.wblog.system.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
 * 用户信息表
 * </p>
 *
 * @author luyanan
 * @since 2021-02-03
 */
@ApiModel(value = "用户信息表")
@TableName("sys_user")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysUserDo implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 用户ID
	 */
	@ApiModelProperty(value = "用户ID")
	@TableId(type = IdType.AUTO)
	private Long userId;

	/**
	 * 部门ID
	 */
	@ApiModelProperty(value = "部门ID")
	private Long deptId;

	/**
	 * 用户账号
	 */
	@NotBlank(message = "用户账号不能为空")
	@Size(min = 0, max = 30, message = "用户账号长度不能超过30个字符")
	@ApiModelProperty(value = "用户账号")
//	@TableField(SqlCondition.LIKE)
	private String userName;

	/**
	 * 用户昵称
	 */
	@Size(min = 0, max = 30, message = "用户昵称长度不能超过30个字符")
	@ApiModelProperty(value = "用户昵称")
	private String nickName;

	/**
	 * 用户类型（00系统用户）
	 */
	@ApiModelProperty(value = "用户类型（00系统用户）")
	private String userType;

	/**
	 * 用户邮箱
	 */
	@Email(message = "邮箱格式不正确")
	@Size(min = 0, max = 50, message = "邮箱长度不能超过50个字符")
	@ApiModelProperty(value = "用户邮箱")
	private String email;

	/**
	 * 手机号码
	 */
	@Size(min = 0, max = 11, message = "手机号码长度不能超过11个字符")
	@ApiModelProperty(value = "手机号码")
//	@TableField(SqlCondition.LIKE)
	private String phonenumber;

	/**
	 * 用户性别（0男 1女 2未知）
	 */
	@ApiModelProperty(value = "用户性别（0男 1女 2未知）")
	private String sex;

	/**
	 * 头像地址
	 */
	@ApiModelProperty(value = "头像地址")
	private String avatar;

	/**
	 * 密码
	 */
	@JsonIgnore
	@JsonProperty
	@ApiModelProperty(value = "密码")
	private String password;

	/**
	 * 帐号状态（0正常 1停用）
	 */
	@ApiModelProperty(value = "帐号状态（0正常 1停用）")
	private String status;

	/**
	 * 删除标志（0代表存在 2代表删除）
	 */
	@ApiModelProperty(value = "删除标志（0代表存在 2代表删除）")
	private String delFlag;

	/**
	 * 最后登录IP
	 */
	@ApiModelProperty(value = "最后登录IP")
	private String loginIp;

	/**
	 * 最后登录时间
	 */
	@ApiModelProperty(value = "最后登录时间")
	private Date loginDate;

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

	public boolean isAdmin() {
		return isAdmin(this.userId);
	}

	private boolean isAdmin(Long userId) {
		return userId != null && 1L == userId;
	}

}
