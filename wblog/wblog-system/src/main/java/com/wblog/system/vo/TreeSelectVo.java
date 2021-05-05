package com.wblog.system.vo;

import com.wblog.system.entity.SysDeptDo;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * TreeSelect 树结构vo类
 *
 * @author luyanan
 * @since 2021/2/11
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TreeSelectVo implements Serializable {


	/**
	 * 节点id
	 *
	 * @since 2021/2/11
	 */
	private Long id;


	/**
	 * 节点名称
	 *
	 * @since 2021/2/11
	 */
	private String label;


	/**
	 * 子节点
	 *
	 * @since 2021/2/11
	 */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private List<TreeSelectVo> children;

	public TreeSelectVo(SysDeptVo dept) {
		this.id = dept.getDeptId();
		this.label = dept.getDeptName();
		this.children = dept.getChildren().stream().map(TreeSelectVo::new).collect(Collectors.toList());
	}

	public TreeSelectVo(SysMenuVo menu) {
		this.id = menu.getMenuId();
		this.label = menu.getMenuName();
		this.children = menu.getChildren().stream().map(TreeSelectVo::new).collect(Collectors.toList());
	}
}
