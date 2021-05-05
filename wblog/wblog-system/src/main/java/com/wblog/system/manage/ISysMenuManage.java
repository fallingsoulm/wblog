package com.wblog.system.manage;

import com.wblog.system.entity.SysMenuDo;
import io.github.fallingsoulm.easy.archetype.data.manage.IManage;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 菜单权限表 manage
 * </p>
 *
 * @author luyanan
 * @since 2021-02-03
 */
public interface ISysMenuManage extends IManage<SysMenuDo> {

	/**
	 * 根据id集合和菜单类型查询
	 *
	 * @param menuIds  id集合
	 * @param menuType 菜单类型
	 * @return java.util.List<com.wblog.system.entity.SysMenuDo>
	 * @since 2021/2/7
	 */
	List<SysMenuDo> findByIds(Collection<Long> menuIds, List<String> menuType);

	/**
	 * 根据菜单id集合和实体条件查询
	 *
	 * @param menuIds   菜单id集合
	 * @param sysMenuDo 实体条件
	 * @return java.util.List<com.wblog.system.entity.SysMenuDo>
	 * @since 2021/2/14
	 */
	List<SysMenuDo> findByIds(List<Long> menuIds, SysMenuDo sysMenuDo);
}
