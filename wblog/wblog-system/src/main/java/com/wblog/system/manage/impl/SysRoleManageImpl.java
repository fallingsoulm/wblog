package com.wblog.system.manage.impl;

import com.wblog.system.mapper.SysRoleMapper;
import com.wblog.system.manage.ISysRoleManage;
import com.wblog.system.entity.SysRoleDo;
import io.github.fallingsoulm.easy.archetype.data.manage.impl.ManageImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 角色信息表 manageImpl
 * </p>
 *
 * @author luyanan
 * @since 2021-02-03
 */
@Service
public class SysRoleManageImpl extends ManageImpl<SysRoleMapper, SysRoleDo> implements ISysRoleManage {

	@Override
	public List<SysRoleDo> selectRolePermissionByUserId(Long userId) {

		return this.baseMapper.selectRolePermissionByUserId(userId);

	}

}
