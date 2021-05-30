package com.wblog.info.manage.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wblog.common.datascope.GlobalDataScopeAspect;
import com.wblog.common.datascope.UserDataScopeVo;
import com.wblog.info.entity.ArticleEntity;
import com.wblog.info.manage.IArticleManage;
import com.wblog.info.mapper.ArticleMapper;
import io.github.fallingsoulm.easy.archetype.data.manage.impl.CacheManageImpl;
import io.github.fallingsoulm.easy.archetype.framework.page.PageInfo;
import io.github.fallingsoulm.easy.archetype.framework.page.PageRequestParams;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ArticleManageImpl extends CacheManageImpl<ArticleMapper, ArticleEntity> implements IArticleManage {


    @Override
    protected LambdaQueryWrapper<ArticleEntity> lambdaQueryWrapper(ArticleEntity entity) {
        LambdaQueryWrapper<ArticleEntity> queryWrapper = super.lambdaQueryWrapper(entity);
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
                lambdaQueryWrapper(params.getParams());
        if (null != userDataScopeVo) {
//            权限过滤
            queryWrapper.in(CollectionUtil.isNotEmpty(userDataScopeVo.getUserIds()), ArticleEntity::getUserId, userDataScopeVo.getUserIds());
        }
//
        queryWrapper.in(CollectionUtil.isNotEmpty(statusList), ArticleEntity::getStatus, statusList);
        queryWrapper.orderByDesc(ArticleEntity::getStatus);
        queryWrapper.orderByDesc(ArticleEntity::getCreateTime);
        return this.toPageInfo(params, queryWrapper);
    }

    @Cacheable(sync = true)
    @Override
    public PageInfo<ArticleEntity> findByPageAndNotIn(PageRequestParams<ArticleEntity> params, List<Long> notInIds) {
        LambdaQueryWrapper<ArticleEntity> queryWrapper =
                lambdaQueryWrapper(params.getParams());
        queryWrapper.notIn(CollectionUtil.isNotEmpty(notInIds), ArticleEntity::getId, notInIds);
        queryWrapper.orderByDesc(ArticleEntity::getCreateTime);
        return super.toPageInfo(params, queryWrapper);


    }
}
