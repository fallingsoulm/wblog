package com.wblog.system.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.wblog.system.entity.SysRoleMenuDo;
import com.wblog.system.service.ISysRoleMenuService;
import com.wblog.system.manage.ISysRoleMenuManage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>
 * 角色和菜单关联表 serviceImpl
 * </p>
 *
 * @author luyanan
 * @since 2021-02-03
 */
@Service
public class SysRoleMenuServiceImpl implements ISysRoleMenuService {

	@Autowired
	private ISysRoleMenuManage iSysRoleMenuManage;

	@Override
	public List<SysRoleMenuDo> findRoleIds(List<Long> roleIds) {

		List<SysRoleMenuDo> sysRoleMenuDos = iSysRoleMenuManage.findRoleIds(roleIds);

		return Optional.ofNullable(sysRoleMenuDos).orElse(new ArrayList<>());
	}

	@Override
	public void insertBatch(Long roleId, List<Long> menuIds) {
		if (CollectionUtil.isEmpty(menuIds)) {
			return;
		}
		List<SysRoleMenuDo> roleMenuDoList = menuIds
				.stream()
				.map(menuId -> SysRoleMenuDo.builder().roleId(roleId).menuId(menuId).build())
				.collect(Collectors.toList());
		iSysRoleMenuManage.insertBatch(roleMenuDoList);

	}

	@Override
	public void delete(SysRoleMenuDo sysRoleMenuDo) {
		iSysRoleMenuManage.delete(sysRoleMenuDo);
	}

}
