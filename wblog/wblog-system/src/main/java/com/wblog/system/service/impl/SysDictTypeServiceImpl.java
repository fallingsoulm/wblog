package com.wblog.system.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.wblog.common.exception.BusinessException;
import com.wblog.system.SystemMsgCode;
import com.wblog.system.entity.SysDictDataDo;
import com.wblog.system.entity.SysDictTypeDo;
import com.wblog.system.manage.ISysDictTypeManage;
import com.wblog.system.service.ISysDictDataService;
import com.wblog.system.service.ISysDictTypeService;
import io.github.fallingsoulm.easy.archetype.framework.page.PageInfo;
import io.github.fallingsoulm.easy.archetype.framework.page.PageRequestParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 字典类型表 serviceImpl
 * </p>
 *
 * @author luyanan
 * @since 2021-02-03
 */
@Service
public class SysDictTypeServiceImpl implements ISysDictTypeService {

    @Autowired
    private ISysDictTypeManage iSysDictTypeManage;

    @Autowired
    private ISysDictDataService sysDictDataService;

    @Override
    public PageInfo<SysDictTypeDo> findByPage(PageRequestParams<SysDictTypeDo> pageRequestParams) {
        return iSysDictTypeManage.listByPage(pageRequestParams);
    }

    @Override
    public void save(SysDictTypeDo dict) {

        // 校验类型唯一
        if (this.iSysDictTypeManage.count(SysDictTypeDo.builder().dictType(dict.getDictType()).build()) > 0) {
            throw new BusinessException(SystemMsgCode.DICT_TYPE_UNIQUE);
        }
        this.iSysDictTypeManage.insert(dict);

    }

    @Override
    public void update(SysDictTypeDo dict) {
        SysDictTypeDo oldDictType = iSysDictTypeManage.findById(dict.getDictId());
        if (!oldDictType.getDictType().equals(dict.getDictType()) &&
                iSysDictTypeManage.count(SysDictTypeDo.builder().dictType(dict.getDictType()).build()) > 0) {
            throw new BusinessException(SystemMsgCode.DICT_TYPE_UNIQUE);
        }

        // 修改字典数据
        sysDictDataService.updateDictType(dict.getDictType(), oldDictType.getDictType());
        iSysDictTypeManage.update(dict);
    }

    @Override
    public List<SysDictTypeDo> list(SysDictTypeDo sysDictTypeDo) {
        return iSysDictTypeManage.list(sysDictTypeDo);
    }

    @Override
    public void deleteByIds(List<Long> ids) {
        if (CollectionUtil.isEmpty(ids)) {
            return;
        }
        for (Long id : ids) {
            SysDictTypeDo dictTypeDo = iSysDictTypeManage.findById(id);
            // 查询字典数据
            if (sysDictDataService.count(SysDictDataDo.builder().dictType(dictTypeDo.getDictType()).build()) > 0) {
                throw new BusinessException(SystemMsgCode.DICT_TYPE_DISTRIBUTION, dictTypeDo.getDictName());
            }
        }
    }

    @Override
    public SysDictTypeDo findById(Long dictId) {
        return iSysDictTypeManage.findById(dictId);
    }
}
