package com.wblog.info.manage.impl;

import com.apes.hub.api.module.info.vo.NewsVo;
import com.apes.hub.core.manage.MybatisPlusCacheManageImpl;
import com.apes.hub.info.entity.NewsEntity;
import com.apes.hub.info.manage.INewsManage;
import com.apes.hub.info.mapper.NewsMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 热门资讯 manageImpl
 * </p>
 *
 * @author luyanan
 * @since 2020-07-13
 */
@Service
public class NewsManageImpl extends MybatisPlusCacheManageImpl<NewsMapper, NewsEntity> implements INewsManage {

    @Cacheable(sync = true)
    @Override
    public NewsVo lastUrl(Integer source) {
        return this.baseMapper.lastUrl(source);
    }

    @Override
    protected LambdaQueryWrapper<NewsEntity> createQueryWrapper(NewsEntity entity) {
        return super.createQueryWrapper(entity).orderByDesc(NewsEntity::getPublishTime);
    }
}
