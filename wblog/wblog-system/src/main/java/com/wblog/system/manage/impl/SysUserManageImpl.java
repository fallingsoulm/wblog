package com.wblog.system.manage.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wblog.system.entity.SysUserDo;
import com.wblog.system.manage.ISysUserManage;
import com.wblog.system.mapper.SysUserMapper;
import io.github.fallingsoulm.easy.archetype.data.manage.impl.ManageImpl;
import io.github.fallingsoulm.easy.archetype.framework.page.PageInfo;
import io.github.fallingsoulm.easy.archetype.framework.page.PageRequestParams;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户信息表 manageImpl
 * </p>
 *
 * @author luyanan
 * @since 2021-02-03
 */
@Service
public class SysUserManageImpl extends ManageImpl<SysUserMapper, SysUserDo> implements ISysUserManage {

	@Override
	public PageInfo<SysUserDo> listByPage(PageRequestParams<SysUserDo> pageRequestParams) {

		IPage<SysUserDo> page = new Page<>(pageRequestParams.getPageNum(), pageRequestParams.getPageSize());
		page = this.baseMapper.listByPage(page, pageRequestParams.getParams());

		PageInfo<SysUserDo> pageInfo = new PageInfo<SysUserDo>(page.getRecords(), page.getTotal(), pageRequestParams);
		return pageInfo;
	}
}
