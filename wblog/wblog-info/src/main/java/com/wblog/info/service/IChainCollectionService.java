package com.wblog.info.service;

import com.apes.hub.api.module.info.vo.ChainCollectionVo;
import com.apes.hub.api.page.PageInfo;
import com.apes.hub.api.page.PageRequestParams;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 链藏 service
 * </p>
 *
 * @author luyanan
 * @since 2020-06-23
 */
public interface IChainCollectionService {


    /**
     * <p>分页查询</p>
     *
     * @param pageRequestParams 分页参数
     * @return {@link PageInfo}
     * @author luyanan
     * @since 2020-06-23
     */
    PageInfo<ChainCollectionVo> findByPage(PageRequestParams<ChainCollectionVo> pageRequestParams);


    /**
     * <p>根据id查询</p>
     *
     * @param id id
     * @return {@link ChainCollectionVo}
     * @author luyanan
     * @since 2020-06-23
     */
    ChainCollectionVo findById(Long id);


    /**
     * <p>根据条件查询列表</p>
     *
     * @param chainCollectionVo 条件
     * @return {@link List}
     * @author luyanan
     * @since 2020-06-23
     */
    List<ChainCollectionVo> findList(ChainCollectionVo chainCollectionVo);


    /**
     * <p>根据id集合查询</p>
     *
     * @param ids id集合
     * @return {@link List}
     * @author luyanan
     * @since 2020-06-23
     */
    List<ChainCollectionVo> findByIds(List<Long> ids);

    /**
     * <p>保存</p>
     *
     * @param chainCollectionVo vo类
     * @return {@link  Long}
     * @author luyanan
     * @since 2020-06-23
     */
    Long save(ChainCollectionVo chainCollectionVo);


    /**
     * <p>根据id修改</p>
     *
     * @param chainCollectionVo vo类
     * @author luyanan
     * @since 2020-06-23
     */
    void update(ChainCollectionVo chainCollectionVo);


    /**
     * <p>根据id集合删除</p>
     *
     * @param ids id集合
     * @author luyanan
     * @since 2020-06-23
     */
    void deleteByIds(List<Long> ids);


    /**
     * <p>根据id删除</p>
     *
     * @param id id
     * @author luyanan
     * @since 2020-06-23
     */
    void deleteById(Long id);


    /**
     * <p>根据父类id查询下面的所有链藏</p>
     *
     * @param classifyId
     * @return {@link List< Map< String, List< ChainCollectionVo>>>}
     * @author luyanan
     * @since 2020/6/27
     */
    Map<String, List<ChainCollectionVo>> listyByMap(Long classifyId);

}
