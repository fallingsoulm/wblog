package com.wblog.system.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 菜单Vo类
 *
 * @author luyanan
 * @since 2021/2/7
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SysMenuVo implements Serializable {

	private static final long serialVersionUID = -4361294696102053777L;

	/**
	 * 菜单ID
	 */
	@ApiModelProperty(value = "菜单ID")
	@TableId(type = IdType.AUTO)
	private Long menuId;

	/**
	 * 菜单名称
	 */
	@ApiModelProperty(value = "菜单名称")
	private String menuName;

	/**
	 * 父菜单ID
	 */
	@ApiModelProperty(value = "父菜单ID")
	private Long parentId;

	/**
	 * 显示顺序
	 */
	@ApiModelProperty(value = "显示顺序")
	private Integer orderNum;

	/**
	 * 路由地址
	 */
	@ApiModelProperty(value = "路由地址")
	private String path;

	/**
	 * 组件路径
	 */
	@ApiModelProperty(value = "组件路径")
	private String component;

	/**
	 * 是否为外链（0是 1否）
	 */
	@ApiModelProperty(value = "是否为外链（0是 1否）")
	private Integer isFrame;

	/**
	 * 是否缓存（0缓存 1不缓存）
	 */
	@ApiModelProperty(value = "是否缓存（0缓存 1不缓存）")
	private String isCache;

	/**
	 * 菜单类型（M目录 C菜单 F按钮）
	 */
	@ApiModelProperty(value = "菜单类型（M目录 C菜单 F按钮）")
	private String menuType;

	/**
	 * 菜单状态（0显示 1隐藏）
	 */
	@ApiModelProperty(value = "菜单状态（0显示 1隐藏）")
	private String visible;

	/**
	 * 菜单状态（0正常 1停用）
	 */
	@ApiModelProperty(value = "菜单状态（0正常 1停用）")
	private String status;

	/**
	 * 权限标识
	 */
	@ApiModelProperty(value = "权限标识")
	private String perms;

	/**
	 * 菜单图标
	 */
	@ApiModelProperty(value = "菜单图标")
	private String icon;

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
	 * 子菜单
	 *
	 * @since 2021/2/7
	 */
	List<SysMenuVo> children = new ArrayList<>();

}
