package com.wblog.info.manage.impl;

import com.wblog.common.module.info.vo.LabelVo;
import com.wblog.info.entity.ArticleLabelEntity;
import com.wblog.info.manage.IArticleLabelManage;
import com.wblog.info.mapper.ArticleLabelMapper;
import io.github.fallingsoulm.easy.archetype.data.manage.impl.CacheManageImpl;
import io.github.fallingsoulm.easy.archetype.framework.page.PageRequestParams;
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
public class ArticleLabelManageImpl extends CacheManageImpl<ArticleLabelMapper, ArticleLabelEntity> implements IArticleLabelManage {
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
