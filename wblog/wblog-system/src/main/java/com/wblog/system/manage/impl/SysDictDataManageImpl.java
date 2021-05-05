package com.wblog.system.manage.impl;

import com.wblog.system.mapper.SysDictDataMapper;
import com.wblog.system.manage.ISysDictDataManage;
import com.wblog.system.entity.SysDictDataDo;
import io.github.fallingsoulm.easy.archetype.data.manage.impl.ManageImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 字典数据表 manageImpl
 * </p>
 *
 * @author luyanan
 * @since 2021-02-03
 */
@Service
public class SysDictDataManageImpl extends ManageImpl<SysDictDataMapper, SysDictDataDo> implements ISysDictDataManage {

}
