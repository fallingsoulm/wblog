package com.wblog.system.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.wblog.system.entity.SysUserRoleDo;
import com.wblog.system.manage.ISysUserRoleManage;
import com.wblog.system.service.ISysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户和角色关联表 serviceImpl
 * </p>
 *
 * @author luyanan
 * @since 2021-02-03
 */
@Service
public class SysUserRoleServiceImpl implements ISysUserRoleService {

	@Autowired
	private ISysUserRoleManage sysUserRoleManage;

	@Override
	public List<SysUserRoleDo> list(SysUserRoleDo sysUserRoleDo) {
		return Optional.ofNullable(sysUserRoleManage.list(sysUserRoleDo)).orElse(new ArrayList<>());

	}

	@Override
	public void insertBatch(List<Long> roleIds, Long userId) {

		if (CollectionUtil.isEmpty(roleIds) || null == userId) {
			return;
		}
		sysUserRoleManage.insertBatch(roleIds
				.stream()
				.map(roleId -> SysUserRoleDo.builder().roleId(roleId).userId(userId).build())
				.collect(Collectors.toList()));
	}

	@Override
	public void delete(SysUserRoleDo userRoleDo) {
		sysUserRoleManage.delete(userRoleDo);
	}

	@Override
	public Integer count(SysUserRoleDo sysUserRoleDo) {
		return sysUserRoleManage.count(sysUserRoleDo);
	}

	@Override
	public void deleteByRoleIds(Long[] roleIds) {

		sysUserRoleManage.deleteByRoleIds(Arrays.asList(roleIds));
	}

}
