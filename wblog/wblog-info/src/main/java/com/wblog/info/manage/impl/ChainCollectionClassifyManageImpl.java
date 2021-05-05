package com.wblog.info.manage.impl;

import com.apes.hub.core.manage.MybatisPlusCacheManageImpl;
import com.apes.hub.info.entity.ChainCollectionClassifyEntity;
import com.apes.hub.info.manage.IChainCollectionClassifyManage;
import com.apes.hub.info.mapper.ChainCollectionClassifyMapper;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 链藏分类表 manageImpl
 * </p>
 *
 * @author luyanan
 * @since 2020-06-23
 */
@Service
public class ChainCollectionClassifyManageImpl extends MybatisPlusCacheManageImpl<ChainCollectionClassifyMapper, ChainCollectionClassifyEntity> implements IChainCollectionClassifyManage {

}
