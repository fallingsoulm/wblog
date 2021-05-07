package com.wblog.info.manage.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wblog.info.entity.AlbumArticleEntity;
import com.wblog.info.manage.IAlbumArticleManage;
import com.wblog.info.mapper.AlbumArticleMapper;
import io.github.fallingsoulm.easy.archetype.data.manage.impl.CacheManageImpl;
import io.github.fallingsoulm.easy.archetype.data.manage.impl.ManageImpl;
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
public class AlbumArticleManageImpl extends ManageImpl<AlbumArticleMapper, AlbumArticleEntity> implements IAlbumArticleManage {


    @Override
    protected LambdaQueryWrapper<AlbumArticleEntity> lambdaQueryWrapper(AlbumArticleEntity entity) {
        return super.lambdaQueryWrapper(entity).orderByAsc(AlbumArticleEntity::getOrderNum);
    }

    @Cacheable(sync = true)
    @Override
    public Integer maxOrderNum(Long albumId) {
        return this.baseMapper.maxOrderNum(albumId);


    }

}
