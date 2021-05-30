package com.wblog.info.service.impl;


import com.wblog.common.module.info.vo.ClassifyVo;
import com.wblog.info.entity.ClassifyEntity;
import com.wblog.info.manage.IClassifyManage;
import com.wblog.info.service.IClassifyService;
import io.github.fallingsoulm.easy.archetype.data.mybatisplus.MybatisPlusUtils;
import io.github.fallingsoulm.easy.archetype.framework.page.PageInfo;
import io.github.fallingsoulm.easy.archetype.framework.page.PageRequestParams;
import io.github.fallingsoulm.easy.archetype.framework.utils.BeanUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 文章分类 serviceImpl
 * </p>
 *
 * @author luyanan
 * @since 2020-06-10
 */
@Service
@Slf4j
public class ClassifyServiceImpl implements IClassifyService {


    @Autowired
    private IClassifyManage iClassifyManage;


    @Autowired
    private MybatisPlusUtils plusUtils;


    @Override
    public PageInfo<ClassifyVo> findByPage(PageRequestParams<ClassifyVo> pageRequestParams) {
        PageRequestParams<ClassifyEntity> params = plusUtils.convertPageRequestParams(pageRequestParams, ClassifyEntity.class);
        PageInfo<ClassifyEntity> entityPageInfo = iClassifyManage.listByPage(params);
        return plusUtils.convertPageInfo(entityPageInfo, ClassifyVo.class);
    }

    @Override
    public ClassifyVo findById(Long id) {
        ClassifyEntity classifyEntity = iClassifyManage.findById(id);
        if (classifyEntity == null) {
            return null;
        }
        return BeanUtils.copyProperties(classifyEntity, ClassifyVo.class);
    }

    @Override
    public List<ClassifyVo> findList(ClassifyVo classifyVo) {
        ClassifyEntity ClassifyEntity = BeanUtils.copyProperties(classifyVo, ClassifyEntity.class);
        List<ClassifyEntity> list = iClassifyManage.list(ClassifyEntity);
        return BeanUtils.copyList(list, ClassifyVo.class);
    }

    @Override
    public List<ClassifyVo> findByIds(List<Long> ids) {
        if (ids.isEmpty()) {
            return new ArrayList<>();
        }
        List<ClassifyEntity> entities = iClassifyManage.findByIds(ids);
        return BeanUtils.copyList(entities, ClassifyVo.class);
    }

    @Override
    public Long save(ClassifyVo classifyVo) {
        ClassifyEntity classifyEntity = BeanUtils.copyProperties(classifyVo, ClassifyEntity.class);
        List<ClassifyEntity> list = this.iClassifyManage.list(ClassifyEntity.builder().name(classifyVo.getName()).build());
        if (list.isEmpty()) {
            iClassifyManage.insert(classifyEntity);
            return classifyEntity.getId();
        } else {
            return list.get(0).getId();
        }
    }

    @Override
    public void update(ClassifyVo classifyVo) {
        ClassifyEntity classifyEntity = BeanUtils.copyProperties(classifyVo, ClassifyEntity.class);
        iClassifyManage.update(classifyEntity);

    }

    @Override
    public void deleteByIds(List<Long> ids) {
        iClassifyManage.deleteBatch(ids);
    }

    @Override
    public void deleteById(Long id) {
        iClassifyManage.deleteById(ClassifyEntity.builder().id(id).build());
    }

}
