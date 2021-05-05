package com.wblog.system.manage;

import com.wblog.system.entity.SysRoleMenuDo;
import io.github.fallingsoulm.easy.archetype.data.manage.IManage;

import java.util.List;

/**
 * <p>
 * 角色和菜单关联表 manage
 * </p>
 *
 * @author luyanan
 * @since 2021-02-03
 */
public interface ISysRoleMenuManage extends IManage<SysRoleMenuDo> {

	/**
	 * 根据角色id集合查询
	 * @param roleIds 角色id集合
	 * @return java.util.List<com.wblog.system.entity.SysRoleMenuDo>
	 * @since 2021/2/4
	 */
	List<SysRoleMenuDo> findRoleIds(List<Long> roleIds);

}
