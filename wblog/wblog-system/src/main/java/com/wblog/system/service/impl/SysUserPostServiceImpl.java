package com.wblog.system.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.wblog.system.entity.SysUserPostDo;
import com.wblog.system.manage.ISysUserPostManage;
import com.wblog.system.service.ISysUserPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户与岗位关联表 serviceImpl
 * </p>
 *
 * @author luyanan
 * @since 2021-02-03
 */
@Service
public class SysUserPostServiceImpl implements ISysUserPostService {

	@Autowired
	private ISysUserPostManage iSysUserPostManage;

	@Override
	public List<SysUserPostDo> list(SysUserPostDo sysUserPostDo) {
		return iSysUserPostManage.list(sysUserPostDo);
	}

	@Override
	public void insertBatch(List<SysUserPostDo> userPostDoList) {
		if (CollectionUtil.isNotEmpty(userPostDoList)) {
			return;
		}
		iSysUserPostManage.insertBatch(userPostDoList);
	}

	@Override
	public void insertBatch(List<Long> postIds, Long userId) {
		if (CollectionUtil.isNotEmpty(postIds) || null == userId) {
			return;
		}
		iSysUserPostManage.insertBatch(postIds.stream().map(a -> {
			return SysUserPostDo.builder().postId(a).userId(userId).build();
		}).collect(Collectors.toList()));
	}

	@Override
	public void delete(SysUserPostDo sysUserPostDo) {
		iSysUserPostManage.delete(sysUserPostDo);
	}
}
