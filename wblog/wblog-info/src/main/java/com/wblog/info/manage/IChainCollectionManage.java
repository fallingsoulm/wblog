package com.wblog.info.manage;

import com.apes.hub.core.manage.IManage;
import com.apes.hub.info.entity.ChainCollectionEntity;

import java.util.List;

/**
 * <p>
 * 链藏 manage
 * </p>
 *
 * @author luyanan
 * @since 2020-06-23
 */
public interface IChainCollectionManage extends IManage<ChainCollectionEntity> {


    /**
     * <p>根据分类列表和状态该查询</p>
     *
     * @param classifyIds 分类列表
     * @param status      状态
     * @return {@link List< ChainCollectionEntity>}
     * @author luyanan
     * @since 2020/6/27
     */
    List<ChainCollectionEntity> listByIn(List<Long> classifyIds, Integer status);
}
