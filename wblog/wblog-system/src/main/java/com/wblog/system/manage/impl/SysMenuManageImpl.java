package com.wblog.system.manage.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wblog.system.entity.SysMenuDo;
import com.wblog.system.manage.ISysMenuManage;
import com.wblog.system.mapper.SysMenuMapper;
import io.github.fallingsoulm.easy.archetype.data.manage.impl.ManageImpl;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 菜单权限表 manageImpl
 * </p>
 *
 * @author luyanan
 * @since 2021-02-03
 */
@Service
public class SysMenuManageImpl extends ManageImpl<SysMenuMapper, SysMenuDo> implements ISysMenuManage {

	@Override
	public List<SysMenuDo> findByIds(Collection<Long> menuIds, List<String> menuType) {
		LambdaQueryWrapper<SysMenuDo> queryWrapper = lambdaQueryWrapper(null);
		queryWrapper.in(CollectionUtil.isNotEmpty(menuType), SysMenuDo::getMenuType, menuType);
		queryWrapper.in(CollectionUtil.isNotEmpty(menuIds), SysMenuDo::getMenuId, menuIds);
		return this.list(queryWrapper);
	}

	@Override
	public List<SysMenuDo> findByIds(List<Long> menuIds, SysMenuDo sysMenuDo) {
		LambdaQueryWrapper<SysMenuDo> queryWrapper = lambdaQueryWrapper(sysMenuDo);
		queryWrapper.in(CollectionUtil.isNotEmpty(menuIds), SysMenuDo::getMenuId, menuIds);
		return this.list(queryWrapper);

	}

}
