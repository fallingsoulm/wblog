package com.wblog.system.manage.impl;

import com.wblog.system.manage.ISysJobManage;
import com.wblog.system.mapper.SysJobMapper;
import com.wblog.system.entity.SysJobDo;
import io.github.fallingsoulm.easy.archetype.data.manage.impl.ManageImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 定时任务调度表 manageImpl
 * </p>
 *
 * @author luyanan
 * @since 2021-02-03
 */
@Service
public class SysJobManageImpl extends ManageImpl<SysJobMapper, SysJobDo> implements ISysJobManage {

}
