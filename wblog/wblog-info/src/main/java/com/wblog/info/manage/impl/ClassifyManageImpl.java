package com.wblog.info.manage.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wblog.common.datascope.GlobalDataScopeAspect;
import com.wblog.info.entity.ClassifyEntity;
import com.wblog.info.manage.IClassifyManage;
import com.wblog.info.mapper.ClassifyMapper;
import io.github.fallingsoulm.easy.archetype.data.manage.impl.CacheManageImpl;
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
public class ClassifyManageImpl extends CacheManageImpl<ClassifyMapper, ClassifyEntity> implements IClassifyManage {

    @Autowired
    private GlobalDataScopeAspect globalDataScopeAspect;

    @Override
    protected LambdaQueryWrapper<ClassifyEntity> lambdaQueryWrapper(ClassifyEntity entity) {
        return super.lambdaQueryWrapper(entity)
                //  增加数据权限过滤
                .in(CollectionUtil.isNotEmpty(globalDataScopeAspect.dataScopeUserIds()),
                        ClassifyEntity::getUserId,
                        globalDataScopeAspect.dataScopeUserIds());

    }
}
