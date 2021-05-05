package com.wblog.info.service;

import com.apes.hub.api.module.info.vo.ChainCollectionClassifyVo;
import com.apes.hub.api.page.PageInfo;
import com.apes.hub.api.page.PageRequestParams;

import java.util.List;

/**
* <p>
* 链藏分类表 service
* </p>
*
* @author luyanan
* @since 2020-06-23
*/
public interface IChainCollectionClassifyService{




    /**
    * <p>分页查询</p>
    *
    * @param pageRequestParams 分页参数
    * @return {@link PageInfo}
    * @author luyanan
    * @since 2020-06-23
    */
    PageInfo<ChainCollectionClassifyVo> findByPage(PageRequestParams<ChainCollectionClassifyVo> pageRequestParams);


    /**
    * <p>根据id查询</p>
    *
    * @param id id
    * @return {@link ChainCollectionClassifyVo}
    * @author luyanan
    * @since 2020-06-23
    */
    ChainCollectionClassifyVo findById(Long id);


    /**
    * <p>根据条件查询列表</p>
    *
    * @param chainCollectionClassifyVo 条件
    * @return {@link List}
    * @author luyanan
    * @since 2020-06-23
    */
    List<ChainCollectionClassifyVo> findList(ChainCollectionClassifyVo chainCollectionClassifyVo);


    /**
    * <p>根据id集合查询</p>
    *
    * @param ids id集合
    * @return {@link List}
    * @author luyanan
    * @since 2020-06-23
    */
    List<ChainCollectionClassifyVo> findByIds(List<Long> ids);

    /**
    * <p>保存</p>
    *
    * @param chainCollectionClassifyVo vo类
    * @return {@link  Long}
    * @author luyanan
    * @since 2020-06-23
    */
    Long save(ChainCollectionClassifyVo chainCollectionClassifyVo);


    /**
    * <p>根据id修改</p>
    *
    * @param chainCollectionClassifyVo vo类
    * @author luyanan
    * @since 2020-06-23
    */
    void update(ChainCollectionClassifyVo chainCollectionClassifyVo);


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


}
