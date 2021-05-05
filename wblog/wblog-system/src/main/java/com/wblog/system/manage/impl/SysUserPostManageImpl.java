package com.wblog.system.manage.impl;

import com.wblog.system.manage.ISysUserPostManage;
import com.wblog.system.mapper.SysUserPostMapper;
import com.wblog.system.entity.SysUserPostDo;
import io.github.fallingsoulm.easy.archetype.data.manage.impl.ManageImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户与岗位关联表 manageImpl
 * </p>
 *
 * @author luyanan
 * @since 2021-02-03
 */
@Service
public class SysUserPostManageImpl extends ManageImpl<SysUserPostMapper, SysUserPostDo> implements ISysUserPostManage {

}
