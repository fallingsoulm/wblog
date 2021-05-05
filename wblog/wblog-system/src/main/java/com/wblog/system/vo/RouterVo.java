package com.wblog.system.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 路由配置信息
 *
 * @author luyanan
 * @since 2021/2/7
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RouterVo implements Serializable {

	private static final long serialVersionUID = -8858248411306642081L;

	/**
	 * 路由名字
	 *
	 * @since 2021/2/7
	 */
	private String name;

	/**
	 * 路由地址
	 *
	 * @since 2021/2/7
	 */
	private String path;

	/**
	 * 是否隐藏路由,当设置true的时候路由不会再出现侧边栏
	 *
	 * @since 2021/2/7
	 */
	private boolean hidden;

	/**
	 * 重定向地址，当设置 noRedirect 的时候该路由在面包屑导航中不可被点击
	 *
	 * @since 2021/2/7
	 */
	private String redirect;

	/**
	 * 组件地址
	 *
	 * @since 2021/2/7
	 */
	private String component;

	/**
	 * 当你一个路由下面的 children 声明的路由大于1个时，自动会变成嵌套的模式--如组件页面
	 *
	 * @since 2021/2/7
	 */
	private Boolean alwaysShow;

	/**
	 * 子路由
	 *
	 * @since 2021/2/7
	 */
	private List<RouterVo> children;

	/**
	 * 其他元素
	 *
	 * @since 2021/2/7
	 */
	private MetaVo meta;

}
