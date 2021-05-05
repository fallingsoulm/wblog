package com.wblog.info.manage.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.apes.hub.api.page.PageInfo;
import com.apes.hub.api.page.PageRequestParams;
import com.apes.hub.core.datascope.GlobalDataScopeAspect;
import com.apes.hub.core.datascope.UserDataScopeVo;
import com.apes.hub.core.manage.MybatisPlusCacheManageImpl;
import com.apes.hub.info.entity.ArticleEntity;
import com.apes.hub.info.manage.IArticleManage;
import com.apes.hub.info.mapper.ArticleMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 文章表 manageImpl
 * </p>
 *
 * @author luyanan
 * @since 2020-06-10
 */
@Service
public class ArticleManageImpl extends MybatisPlusCacheManageImpl<ArticleMapper, ArticleEntity> implements IArticleManage {


    @Override
    protected LambdaQueryWrapper<ArticleEntity> createQueryWrapper(ArticleEntity entity) {
        LambdaQueryWrapper<ArticleEntity> queryWrapper = super.createQueryWrapper(entity);
//        queryWrapper.orderByDesc(ArticleEntity::getCreateTime);
        return queryWrapper;
    }

    @CacheEvict(allEntries = true)
    @Override
    public void updateStatus(List<Long> articleIds, Integer status) {
        LambdaQueryWrapper<ArticleEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(ArticleEntity::getId, articleIds);
        super.update(ArticleEntity.builder().status(status).build(), queryWrapper);

    }

    @Cacheable(sync = true)
    @Override
    public PageInfo<ArticleEntity> findByPage(PageRequestParams<ArticleEntity> params, List<Integer> statusList) {
        UserDataScopeVo userDataScopeVo = GlobalDataScopeAspect.threadLocal.get();

        LambdaQueryWrapper<ArticleEntity> queryWrapper =
                createQueryWrapper(params.getParams());
        if (null != userDataScopeVo) {
//            权限过滤
            queryWrapper.in(CollectionUtil.isNotEmpty(userDataScopeVo.getUserIds()), ArticleEntity::getUserId, userDataScopeVo.getUserIds());
        }
//
        queryWrapper.in(CollectionUtil.isNotEmpty(statusList), ArticleEntity::getStatus, statusList);
        queryWrapper.orderByDesc(ArticleEntity::getStatus);
        queryWrapper.orderByDesc(ArticleEntity::getCreateTime);
        return this.mybatisPlusUtils.toPageInfo(params, queryWrapper, this);
    }

    @Cacheable(sync = true)
    @Override
    public PageInfo<ArticleEntity> findByPageAndNotIn(PageRequestParams<ArticleEntity> params, List<Long> notInIds) {
        LambdaQueryWrapper<ArticleEntity> queryWrapper =
                createQueryWrapper(params.getParams());
        queryWrapper.notIn(CollectionUtil.isNotEmpty(notInIds), ArticleEntity::getId, notInIds);
        queryWrapper.orderByDesc(ArticleEntity::getCreateTime);
        return this.mybatisPlusUtils.toPageInfo(params, queryWrapper, this);


    }
}
