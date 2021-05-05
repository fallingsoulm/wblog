package com.wblog.info.service;


import com.wblog.common.module.info.vo.AlbumVo;
import io.github.fallingsoulm.easy.archetype.framework.page.PageInfo;
import io.github.fallingsoulm.easy.archetype.framework.page.PageRequestParams;

import java.util.List;

/**
 * <p>
 * 文章专辑 service
 * </p>
 *
 * @author luyanan
 * @since 2020-07-15
 */
public interface IAlbumService {


    /**
     * <p>分页查询</p>
     *
     * @param pageRequestParams 分页参数
     * @return {@link PageInfo}
     * @author luyanan
     * @since 2020-07-15
     */
    PageInfo<AlbumVo> findByPage(PageRequestParams<AlbumVo> pageRequestParams);


    /**
     * <p>根据id查询</p>
     *
     * @param id id
     * @return {@link AlbumVo}
     * @author luyanan
     * @since 2020-07-15
     */
    AlbumVo findById(Long id);


    /**
     * <p>根据条件查询列表</p>
     *
     * @param albumVo 条件
     * @return {@link List}
     * @author luyanan
     * @since 2020-07-15
     */
    List<AlbumVo> findList(AlbumVo albumVo);


    /**
     * <p>根据id集合查询</p>
     *
     * @param ids id集合
     * @return {@link List}
     * @author luyanan
     * @since 2020-07-15
     */
    List<AlbumVo> findByIds(List<Long> ids);

    /**
     * <p>保存</p>
     *
     * @param albumVo vo类
     * @return {@link  Long}
     * @author luyanan
     * @since 2020-07-15
     */
    Long save(AlbumVo albumVo);


    /**
     * <p>根据id修改</p>
     *
     * @param albumVo vo类
     * @author luyanan
     * @since 2020-07-15
     */
    void update(AlbumVo albumVo);


    /**
     * <p>根据id集合删除</p>
     *
     * @param ids id集合
     * @author luyanan
     * @since 2020-07-15
     */
    void deleteByIds(List<Long> ids);


    /**
     * <p>根据id删除</p>
     *
     * @param id id
     * @author luyanan
     * @since 2020-07-15
     */
    void deleteById(Long id);


}
