package com.wblog.info.manage.impl;

import com.wblog.info.entity.ChainCollectionClassifyEntity;
import com.wblog.info.manage.IChainCollectionClassifyManage;
import com.wblog.info.mapper.ChainCollectionClassifyMapper;
import io.github.fallingsoulm.easy.archetype.data.manage.impl.CacheManageImpl;
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
public class ChainCollectionClassifyManageImpl extends CacheManageImpl<ChainCollectionClassifyMapper, ChainCollectionClassifyEntity> implements IChainCollectionClassifyManage {

}
