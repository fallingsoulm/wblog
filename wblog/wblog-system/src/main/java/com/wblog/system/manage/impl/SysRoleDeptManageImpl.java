package com.wblog.system.manage.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.wblog.system.entity.SysRoleDeptDo;
import com.wblog.system.manage.ISysRoleDeptManage;
import com.wblog.system.mapper.SysRoleDeptMapper;
import io.github.fallingsoulm.easy.archetype.data.manage.impl.ManageImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 角色和部门关联表 manageImpl
 * </p>
 *
 * @author luyanan
 * @since 2021-02-03
 */
@Service
public class SysRoleDeptManageImpl extends ManageImpl<SysRoleDeptMapper, SysRoleDeptDo> implements ISysRoleDeptManage {

	@Override
	public void deleteByRoleIds(List<Long> roleIds) {
		if (CollectionUtil.isEmpty(roleIds)) {
			return;
		}
		this.remove(lambdaQueryWrapper(null).in(SysRoleDeptDo::getRoleId, roleIds));
	}
}
