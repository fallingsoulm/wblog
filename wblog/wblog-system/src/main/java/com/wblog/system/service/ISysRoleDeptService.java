package com.wblog.system.service;

import com.wblog.system.entity.SysRoleDeptDo;

/**
 * <p>
 * 角色和部门关联表 service
 * </p>
 *
 * @author luyanan
 * @since 2021-02-03
 */
public interface ISysRoleDeptService {

	/**
	 * 根据条件删除
	 *
	 * @param build
	 * @return void
	 * @since 2021/2/15
	 */
	void delete(SysRoleDeptDo build);

	/**
	 * 批量添加
	 *
	 * @param deptIds 部门id集合
	 * @param roleId  角色id
	 * @return void
	 * @since 2021/2/15
	 */
	void insertBatch(Long[] deptIds, Long roleId);

	/**
	 * 根据角色id数组删除
	 *
	 * @param roleIds 角色id数组
	 * @return void
	 * @since 2021/2/15
	 */
	void deleteByRoleIds(Long[] roleIds);
}
