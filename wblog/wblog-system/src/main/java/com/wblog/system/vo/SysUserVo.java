package com.wblog.system.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.wblog.system.entity.SysDeptDo;
import com.wblog.system.entity.SysRoleDo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 用户Vo类
 *
 * @author luyanan
 * @since 2021/2/12
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SysUserVo implements Serializable {
	/**
	 * 用户ID
	 */
	@ApiModelProperty(value = "用户ID")
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
	@ApiModelProperty(value = "创建者")
	private String createBy;

	/**
	 * 创建时间
	 */
	@ApiModelProperty(value = "创建时间")
	private Date createTime;

	/**
	 * 更新者
	 */
	@ApiModelProperty(value = "更新者")
	private String updateBy;

	/**
	 * 更新时间
	 */
	@ApiModelProperty(value = "更新时间")
	private Date updateTime;

	/**
	 * 备注
	 */
	@ApiModelProperty(value = "备注")
	private String remark;


	/**
	 * 部门
	 *
	 * @since 2021/2/12
	 */
	@ApiModelProperty(value = "部门")
	private SysDeptDo dept;

	/**
	 * 角色对象
	 *
	 * @since 2021/2/12
	 */
	@ApiModelProperty(value = "角色对象")
	private List<SysRoleDo> roles;

	/**
	 * 角色组
	 *
	 * @since 2021/2/12
	 */
	@ApiModelProperty(value = "角色组")
	private Long[] roleIds;
	/**
	 * 岗位组
	 *
	 * @since 2021/2/12
	 */
	@ApiModelProperty(value = "岗位组")
	private Long[] postIds;
}
