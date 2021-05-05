package com.wblog.info.manage.impl;

import com.apes.hub.core.manage.MybatisPlusCacheManageImpl;
import com.apes.hub.info.entity.LabelEntity;
import com.apes.hub.info.manage.ILabelManage;
import com.apes.hub.info.mapper.LabelMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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
public class LabelManageImpl extends MybatisPlusCacheManageImpl<LabelMapper, LabelEntity> implements ILabelManage {
    @Override
    protected LambdaQueryWrapper<LabelEntity> createQueryWrapper(LabelEntity entity) {
        return super.createQueryWrapper(entity).orderByDesc(LabelEntity::getCreateTime);
    }
}
