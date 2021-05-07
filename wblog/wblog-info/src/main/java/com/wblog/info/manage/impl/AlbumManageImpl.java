package com.wblog.info.manage.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wblog.common.datascope.GlobalDataScopeAspect;
import com.wblog.info.entity.AlbumEntity;
import com.wblog.info.manage.IAlbumManage;
import com.wblog.info.mapper.AlbumMapper;
import io.github.fallingsoulm.easy.archetype.data.manage.impl.CacheManageImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 文章专辑 manageImpl
 * </p>
 *
 * @author luyanan
 * @since 2020-07-15
 */
@Service
public class AlbumManageImpl extends CacheManageImpl<AlbumMapper, AlbumEntity> implements IAlbumManage {

    @Autowired
    private GlobalDataScopeAspect globalDataScopeAspect;

    @Override
    protected LambdaQueryWrapper<AlbumEntity> lambdaQueryWrapper(AlbumEntity entity) {
        return super.lambdaQueryWrapper(entity)
                .in(CollectionUtil.isNotEmpty(globalDataScopeAspect.dataScopeUserIds()), AlbumEntity::getUserId, globalDataScopeAspect.dataScopeUserIds()).orderByAsc(AlbumEntity::getCreateTime);
    }
}
