package com.wblog.info.manage.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.wblog.common.module.info.vo.NewsVo;
import com.wblog.info.entity.NewsEntity;
import com.wblog.info.manage.INewsManage;
import com.wblog.info.mapper.NewsMapper;
import io.github.fallingsoulm.easy.archetype.data.manage.impl.CacheManageImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 热门资讯 manageImpl
 * </p>
 *
 * @author luyanan
 * @since 2020-07-13
 */
@Service
public class NewsManageImpl extends CacheManageImpl<NewsMapper, NewsEntity> implements INewsManage {

    @Cacheable(sync = true)
    @Override
    public NewsVo lastUrl(Integer source) {
        return this.baseMapper.lastUrl(source);
    }

    @Override
    public List<NewsEntity> findWithoutContent() {


        return this.baseMapper.findWithoutContent();

    }

    @Override
    protected LambdaQueryWrapper<NewsEntity> lambdaQueryWrapper(NewsEntity entity) {
        return super.lambdaQueryWrapper(entity).orderByDesc(NewsEntity::getPublishTime);
    }


}
