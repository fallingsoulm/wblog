package com.wblog.info.service.impl;

import com.apes.hub.api.module.info.vo.ClassifyVo;
import com.apes.hub.api.page.PageInfo;
import com.apes.hub.api.page.PageRequestParams;
import com.apes.hub.core.page.MybatisPlusUtils;
import com.apes.hub.info.conver.ClassifyConver;
import com.apes.hub.info.entity.ClassifyEntity;
import com.apes.hub.info.manage.IClassifyManage;
import com.apes.hub.info.service.IClassifyService;
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
    private ClassifyConver classifyConver;


    @Autowired
    private MybatisPlusUtils plusUtils;


    @Override
    public PageInfo<ClassifyVo> findByPage(PageRequestParams<ClassifyVo> pageRequestParams) {
        PageRequestParams<ClassifyEntity> params = plusUtils.convertPageRequestParams(pageRequestParams, ClassifyEntity.class, classifyConver);
        PageInfo<ClassifyEntity> entityPageInfo = iClassifyManage.findByPage(params);
        return plusUtils.convertPageInfo(entityPageInfo, ClassifyVo.class, classifyConver);
    }

    @Override
    public ClassifyVo findById(Long id) {
        ClassifyEntity classifyEntity = iClassifyManage.findById(id);
        if (classifyEntity == null) {
            return null;
        }
        return classifyConver.map(classifyEntity, ClassifyVo.class);
    }

    @Override
    public List<ClassifyVo> findList(ClassifyVo classifyVo) {
        ClassifyEntity ClassifyEntity = classifyConver.map(classifyVo, ClassifyEntity.class);
        List<ClassifyEntity> list = iClassifyManage.findList(ClassifyEntity);
        return classifyConver.mapAsList(list, ClassifyVo.class);
    }

    @Override
    public List<ClassifyVo> findByIds(List<Long> ids) {
        if (ids.isEmpty()) {
            return new ArrayList<>();
        }
        List<ClassifyEntity> entities = iClassifyManage.findByIds(ids);
        return classifyConver.mapAsList(entities, ClassifyVo.class);
    }

    @Override
    public Long save(ClassifyVo classifyVo) {
        ClassifyEntity classifyEntity = classifyConver.map(classifyVo, ClassifyEntity.class);
        List<ClassifyEntity> list = this.iClassifyManage.findList(ClassifyEntity.builder().name(classifyVo.getName()).build());
        if (list.isEmpty()) {
            iClassifyManage.insert(classifyEntity);
            return classifyEntity.getId();
        } else {
            return list.get(0).getId();
        }
    }

    @Override
    public void update(ClassifyVo classifyVo) {
        ClassifyEntity classifyEntity = classifyConver.map(classifyVo, ClassifyEntity.class);
        iClassifyManage.update(classifyEntity);

    }

    @Override
    public void deleteByIds(List<Long> ids) {
        iClassifyManage.deleteBatch(new ClassifyEntity(), ids);
    }

    @Override
    public void deleteById(Long id) {
        iClassifyManage.deleteById(ClassifyEntity.builder().id(id).build());
    }

}
