package com.wblog.system.manage.impl;

import com.wblog.system.entity.SysPostDo;
import com.wblog.system.mapper.SysPostMapper;
import com.wblog.system.manage.ISysPostManage;
import io.github.fallingsoulm.easy.archetype.data.manage.impl.ManageImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 岗位信息表 manageImpl
 * </p>
 *
 * @author luyanan
 * @since 2021-02-03
 */
@Service
public class SysPostManageImpl extends ManageImpl<SysPostMapper, SysPostDo> implements ISysPostManage {

}
