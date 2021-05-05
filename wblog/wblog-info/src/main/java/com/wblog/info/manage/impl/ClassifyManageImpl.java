package com.wblog.info.manage.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.apes.hub.core.datascope.GlobalDataScopeAspect;
import com.apes.hub.core.manage.MybatisPlusCacheManageImpl;
import com.apes.hub.info.entity.ClassifyEntity;
import com.apes.hub.info.manage.IClassifyManage;
import com.apes.hub.info.mapper.ClassifyMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 文章分类 manageImpl
 * </p>
 *
 * @author luyanan
 * @since 2020-06-10
 */
@Service
public class ClassifyManageImpl extends MybatisPlusCacheManageImpl<ClassifyMapper, ClassifyEntity> implements IClassifyManage {

    @Autowired
    private GlobalDataScopeAspect globalDataScopeAspect;

    @Override
    protected LambdaQueryWrapper<ClassifyEntity> createQueryWrapper(ClassifyEntity entity) {
        return super.createQueryWrapper(entity)
                //  增加数据权限过滤
                .in(CollectionUtil.isNotEmpty(globalDataScopeAspect.dataScopeUserIds()),
                        ClassifyEntity::getUserId,
                        globalDataScopeAspect.dataScopeUserIds());

    }
}
