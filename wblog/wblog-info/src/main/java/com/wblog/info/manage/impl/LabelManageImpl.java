package com.wblog.info.manage.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wblog.info.entity.LabelEntity;
import com.wblog.info.manage.ILabelManage;
import com.wblog.info.mapper.LabelMapper;
import io.github.fallingsoulm.easy.archetype.data.manage.impl.CacheManageImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 标签 manageImpl
 * </p>
 *
 * @author luyanan
 * @since 2020-06-10
 */
@Service
public class LabelManageImpl extends CacheManageImpl<LabelMapper, LabelEntity> implements ILabelManage {
    @Override
    protected LambdaQueryWrapper<LabelEntity> lambdaQueryWrapper(LabelEntity entity) {
        return super.lambdaQueryWrapper(entity).orderByDesc(LabelEntity::getCreateTime);
    }
}
