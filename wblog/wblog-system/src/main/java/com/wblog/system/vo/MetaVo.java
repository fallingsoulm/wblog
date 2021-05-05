package com.wblog.system.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 路由显示信息
 *
 * @author luyanan
 * @since 2021/2/7
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MetaVo implements Serializable {

	private static final long serialVersionUID = 1358776288798446934L;

	/**
	 * 设置该路由在侧边栏和面包屑中展示的名字
	 *
	 * @since 2021/2/7
	 */
	private String title;

	/**
	 * 设置该路由的图标，对应路径src/assets/icons/svg
	 *
	 * @since 2021/2/7
	 */
	private String icon;

	/**
	 * 设置为true，则不会被 <keep-alive>缓存
	 *
	 * @since 2021/2/7
	 */
	private boolean noCache;

	public MetaVo(String title, String icon) {
		this.title = title;
		this.icon = icon;
	}

}
