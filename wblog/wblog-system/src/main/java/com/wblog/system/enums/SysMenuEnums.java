package com.wblog.system.enums;

/**
 * 菜单枚举
 *
 * @author luyanan
 * @since 2021/2/7
 **/
public enum SysMenuEnums {

	/**
	 * 目录
	 *
	 * @since 2021/2/10
	 */
	TYPE_DIR("M", "目录"),

	/**
	 * 菜单
	 *
	 * @since 2021/2/10
	 */
	TYPE_MENU("C", "菜单"),
	/**
	 * 按钮
	 *
	 * @since 2021/2/10
	 */
	TYPE_BUTTON("F", "按钮"),

	/**
	 * 显示
	 *
	 * @since 2021/2/10
	 */
	STATUS_NORMAL(0, "显示"),
	/**
	 * 状态-隐藏
	 *
	 * @since 2021/2/10
	 */
	STATUS_EXCEPTION(1, "隐藏"),

	/**
	 * 是
	 *
	 * @since 2021/2/10
	 */
	YES_FRAME(0, "是"),

	/**
	 * 不是
	 *
	 * @since 2021/2/10
	 */
	NO_FRAME(1, "否"),

	/**
	 * Layout组件标识
	 *
	 * @since 2021/2/10
	 */
	LAYOUT("Layout", "Layout组件标识"),

	/**
	 * ParentView组件标识
	 *
	 * @since 2021/2/10
	 */
	PARENT_VIEW("ParentView", "ParentView组件标识"),




	;

	private String code;

	private String desp;

	SysMenuEnums(String code, String desp) {
		this.code = code;
		this.desp = desp;
	}

	SysMenuEnums(Integer code, String desp) {
		this.code = code + "";
		this.desp = desp;
	}

	public String getCode() {
		return code;
	}

	public Integer getCodeInt() {
		return Integer.valueOf(code);
	}

	public String getDesp() {
		return desp;
	}

}
