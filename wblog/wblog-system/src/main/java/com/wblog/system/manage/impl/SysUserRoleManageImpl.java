package com.wblog.system.manage.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.wblog.system.mapper.SysUserRoleMapper;
import com.wblog.system.entity.SysUserRoleDo;
import com.wblog.system.manage.ISysUserRoleManage;
import io.github.fallingsoulm.easy.archetype.data.manage.impl.ManageImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户和角色关联表 manageImpl
 * </p>
 *
 * @author luyanan
 * @since 2021-02-03
 */
@Service
public class SysUserRoleManageImpl extends ManageImpl<SysUserRoleMapper, SysUserRoleDo> implements ISysUserRoleManage {

	@Override
	public void deleteByRoleIds(List<Long> roleIds) {
		if (CollectionUtil.isEmpty(roleIds)) {
			return;
		}
		this.remove(lambdaQueryWrapper(null).in(SysUserRoleDo::getRoleId, roleIds));
	}
}
