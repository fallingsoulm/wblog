package com.wblog.system.service;

import com.wblog.system.entity.SysRoleMenuDo;

import java.util.List;
import java.util.stream.Stream;

/**
 * <p>
 * 角色和菜单关联表 service
 * </p>
 *
 * @author luyanan
 * @since 2021-02-03
 */
public interface ISysRoleMenuService {

	/**
	 * 根据角色id集合查询关联的菜单
	 *
	 * @param roleIds 角色id集合
	 * @return java.util.List<com.wblog.system.entity.SysRoleMenuDo>
	 * @since 2021/2/4
	 */
	List<SysRoleMenuDo> findRoleIds(List<Long> roleIds);

	/**
	 * 批量添加
	 *
	 * @param roleId  角色id
	 * @param menuIds 菜单id集合
	 * @return void
	 * @since 2021/2/15
	 */
	void insertBatch(Long roleId, List<Long> menuIds);

	/**
	 * 根据条件删除
	 *
	 * @param sysRoleMenuDo
	 * @return void
	 * @since 2021/2/15
	 */
	void delete(SysRoleMenuDo sysRoleMenuDo);
}
