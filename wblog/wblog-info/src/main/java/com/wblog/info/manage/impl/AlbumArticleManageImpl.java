package com.wblog.info.manage.impl;

import com.apes.hub.core.manage.MybatisPlusCacheManageImpl;
import com.apes.hub.info.entity.AlbumArticleEntity;
import com.apes.hub.info.manage.IAlbumArticleManage;
import com.apes.hub.info.mapper.AlbumArticleMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 文章与专辑关联 manageImpl
 * </p>
 *
 * @author luyanan
 * @since 2020-07-15
 */
@Service
public class AlbumArticleManageImpl extends MybatisPlusCacheManageImpl<AlbumArticleMapper, AlbumArticleEntity> implements IAlbumArticleManage {


    @Override
    protected LambdaQueryWrapper<AlbumArticleEntity> createQueryWrapper(AlbumArticleEntity entity) {
        return super.createQueryWrapper(entity).orderByAsc(AlbumArticleEntity::getOrderNum);
    }

    @Cacheable(sync = true)
    @Override
    public Integer maxOrderNum(Long albumId) {
        return this.baseMapper.maxOrderNum(albumId);


    }
}
