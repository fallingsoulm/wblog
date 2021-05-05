package com.wblog.info.manage.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.apes.hub.core.manage.MybatisPlusCacheManageImpl;
import com.apes.hub.info.entity.ChainCollectionEntity;
import com.apes.hub.info.manage.IChainCollectionManage;
import com.apes.hub.info.mapper.ChainCollectionMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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
public class ChainCollectionManageImpl extends MybatisPlusCacheManageImpl<ChainCollectionMapper, ChainCollectionEntity> implements IChainCollectionManage {

    @Override
    protected LambdaQueryWrapper<ChainCollectionEntity> createQueryWrapper(ChainCollectionEntity entity) {
        LambdaQueryWrapper<ChainCollectionEntity> queryWrapper = super.createQueryWrapper(entity);

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
