package com.wblog.info.manage.impl;

import com.apes.hub.api.module.info.vo.LabelVo;
import com.apes.hub.api.page.PageRequestParams;
import com.apes.hub.core.manage.MybatisPlusCacheManageImpl;
import com.apes.hub.info.entity.ArticleLabelEntity;
import com.apes.hub.info.manage.IArticleLabelManage;
import com.apes.hub.info.mapper.ArticleLabelMapper;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 文章与标签关联 manageImpl
 * </p>
 *
 * @author luyanan
 * @since 2020-06-10
 */
@Service
public class ArticleLabelManageImpl extends MybatisPlusCacheManageImpl<ArticleLabelMapper, ArticleLabelEntity> implements IArticleLabelManage {
    @Cacheable(sync = true)
    @Override
    public Integer findAndCount(LabelVo params) {

        return this.baseMapper.findAndCount(params);
    }

    @Cacheable(sync = true)
    @Override
    public List<LabelVo> listAndCount(PageRequestParams<LabelVo> pageRequestParams) {
        return this.baseMapper.listAndCount(pageRequestParams.getParams(), pageRequestParams.getOffset(), pageRequestParams.getPageSize());
    }
}
