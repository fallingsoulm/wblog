package com.wblog.system.service;

import com.wblog.system.entity.SysMenuDo;
import com.wblog.system.vo.RouterVo;
import com.wblog.system.vo.TreeSelectVo;

import java.util.List;

/**
 * <p>
 * 菜单权限表 service
 * </p>
 *
 * @author luyanan
 * @since 2021-02-03
 */
public interface ISysMenuService {

	/**
	 * 根据角色id查询菜单列表
	 *
	 * @param roleIds 角色id集合
	 * @return java.util.List<com.wblog.system.entity.SysMenuDo>
	 * @since 2021/2/4
	 */
	List<SysMenuDo> listByRoleIds(List<Long> roleIds);

	/**
	 * 构建前端路由所需要的菜单
	 *
	 * @param userId 用户id
	 * @return java.util.List<com.wblog.system.vo.RouterVo>
	 * @since 2021/2/7
	 */
	List<RouterVo> getRouters(Long userId);

	/**
	 * 根据用户id查询菜单树信息
	 *
	 * @param userId 用户id
	 * @return java.util.List<com.wblog.system.entity.SysMenuDo>
	 * @since 2021/2/7
	 */
	List<SysMenuDo> listByUserId(Long userId);

	/**
	 * 根据实体条件和用户id查询
	 *
	 * @param menu   实体条件
	 * @param userId 用户id
	 * @return java.util.List<com.wblog.system.entity.SysMenuDo>
	 * @since 2021/2/14
	 */
	List<SysMenuDo> listByUserId(SysMenuDo menu, Long userId);

	/**
	 * 根据id查询
	 *
	 * @param menuId 菜单id
	 * @return com.wblog.system.entity.SysMenuDo
	 * @since 2021/2/14
	 */
	SysMenuDo findById(Long menuId);

	/**
	 * 获取菜单下拉树列表
	 *
	 * @param menu   条件
	 * @param userId 用户id
	 * @return java.util.List<com.wblog.system.vo.TreeSelectVo>
	 * @since 2021/2/14
	 */
	List<TreeSelectVo> treeselect(SysMenuDo menu, Long userId);

	/**
	 * 菜单新增
	 *
	 * @param menu 新增
	 * @return void
	 * @since 2021/2/14
	 */
	void insert(SysMenuDo menu);

	/**
	 * 菜单修改
	 *
	 * @param menu
	 * @return void
	 * @since 2021/2/14
	 */
	void update(SysMenuDo menu);

	/**
	 * 根据菜单id删除
	 *
	 * @param menuId 菜单id
	 * @return void
	 * @since 2021/2/14
	 */
	void deleteById(Long menuId);


	/**
	 * 根据条件查询
	 *
	 * @param menu
	 * @return java.util.List<com.wblog.system.entity.SysMenuDo>
	 * @since 2021/2/15
	 */
	List<SysMenuDo> list(SysMenuDo menu);
}
