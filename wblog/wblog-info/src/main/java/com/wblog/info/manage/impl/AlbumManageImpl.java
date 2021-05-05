package com.wblog.info.manage.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.apes.hub.core.datascope.GlobalDataScopeAspect;
import com.apes.hub.core.manage.MybatisPlusCacheManageImpl;
import com.apes.hub.info.entity.AlbumEntity;
import com.apes.hub.info.manage.IAlbumManage;
import com.apes.hub.info.mapper.AlbumMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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
public class AlbumManageImpl extends MybatisPlusCacheManageImpl<AlbumMapper, AlbumEntity> implements IAlbumManage {

    @Autowired
    private GlobalDataScopeAspect globalDataScopeAspect;

    @Override
    protected LambdaQueryWrapper<AlbumEntity> createQueryWrapper(AlbumEntity entity) {
        return super.createQueryWrapper(entity)
                .in(CollectionUtil.isNotEmpty(globalDataScopeAspect.dataScopeUserIds()), AlbumEntity::getUserId, globalDataScopeAspect.dataScopeUserIds()).orderByAsc(AlbumEntity::getCreateTime);
    }
}
