package com.wblog.system.manage.impl;

import com.wblog.system.mapper.SysDictTypeMapper;
import com.wblog.system.manage.ISysDictTypeManage;
import com.wblog.system.entity.SysDictTypeDo;
import io.github.fallingsoulm.easy.archetype.data.manage.impl.ManageImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 字典类型表 manageImpl
 * </p>
 *
 * @author luyanan
 * @since 2021-02-03
 */
@Service
public class SysDictTypeManageImpl extends ManageImpl<SysDictTypeMapper, SysDictTypeDo> implements ISysDictTypeManage {

}
