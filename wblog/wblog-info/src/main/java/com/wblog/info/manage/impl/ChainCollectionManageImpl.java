package com.wblog.info.manage.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wblog.info.entity.ChainCollectionEntity;
import com.wblog.info.manage.IChainCollectionManage;
import com.wblog.info.mapper.ChainCollectionMapper;
import io.github.fallingsoulm.easy.archetype.data.manage.impl.CacheManageImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 链藏 manageImpl
 * </p>
 *
 * @author luyanan
 * @since 2020-06-23
 */
@Service
public class ChainCollectionManageImpl extends CacheManageImpl<ChainCollectionMapper, ChainCollectionEntity> implements IChainCollectionManage {

    @Override
    protected LambdaQueryWrapper<ChainCollectionEntity> lambdaQueryWrapper(ChainCollectionEntity entity) {
        LambdaQueryWrapper<ChainCollectionEntity> queryWrapper = super.lambdaQueryWrapper(entity);

        queryWrapper.apply(entity.getClassifyId() != null && (!entity.getClassifyId().equals(0L)),

                " (classify_id = " + entity.getClassifyId() + " OR classify_id IN ( " +
                        "SELECT id FROM b_chain_collection_classify t WHERE FIND_IN_SET (" + entity.getClassifyId() + ",parent_id) ))");

        entity.setClassifyId(null);

        queryWrapper.setEntity(entity);
        return queryWrapper;
    }

    @Cacheable(sync = true)
    @Override
    public List<ChainCollectionEntity> listByIn(List<Long> classifyIds, Integer status) {
        LambdaQueryWrapper<ChainCollectionEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(CollectionUtil.isNotEmpty(classifyIds), ChainCollectionEntity::getClassifyId, classifyIds);

        queryWrapper.eq(ChainCollectionEntity::getStatus, status);
        return this.list(queryWrapper);
    }
}
