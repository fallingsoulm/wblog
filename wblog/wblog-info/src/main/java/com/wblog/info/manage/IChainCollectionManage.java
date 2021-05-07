package com.wblog.info.manage;


import com.wblog.info.entity.ChainCollectionEntity;
import io.github.fallingsoulm.easy.archetype.data.manage.IManage;

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
