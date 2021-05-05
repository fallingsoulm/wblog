package com.wblog.system.manage;

import com.wblog.system.entity.SysRoleDeptDo;
import io.github.fallingsoulm.easy.archetype.data.manage.IManage;

import java.util.List;

/**
 * <p>
 * 角色和部门关联表 manage
 * </p>
 *
 * @author luyanan
 * @since 2021-02-03
 */
public interface ISysRoleDeptManage extends IManage<SysRoleDeptDo> {
	/**
	 * 根据角色id列表删除
	 *
	 * @param roleIds 角色id列表
	 * @return void
	 * @since 2021/2/15
	 */
	void deleteByRoleIds(List<Long> roleIds);
}
