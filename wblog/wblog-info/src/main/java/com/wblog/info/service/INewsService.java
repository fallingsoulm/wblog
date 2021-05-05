package com.wblog.info.service;

import com.apes.hub.api.module.info.vo.NewsVo;
import com.apes.hub.api.page.PageInfo;
import com.apes.hub.api.page.PageRequestParams;

import java.util.List;

/**
 * <p>
 * 热门资讯 service
 * </p>
 *
 * @author luyanan
 * @since 2020-07-13
 */
public interface INewsService {


    /**
     * <p>分页查询</p>
     *
     * @param pageRequestParams 分页参数
     * @return {@link PageInfo}
     * @author luyanan
     * @since 2020-07-13
     */
    PageInfo<NewsVo> findByPage(PageRequestParams<NewsVo> pageRequestParams);


    /**
     * <p>根据id查询</p>
     *
     * @param id id
     * @return {@link NewsVo}
     * @author luyanan
     * @since 2020-07-13
     */
    NewsVo findById(Long id);


    /**
     * <p>根据条件查询列表</p>
     *
     * @param newsVo 条件
     * @return {@link List}
     * @author luyanan
     * @since 2020-07-13
     */
    List<NewsVo> findList(NewsVo newsVo);


    /**
     * <p>根据id集合查询</p>
     *
     * @param ids id集合
     * @return {@link List}
     * @author luyanan
     * @since 2020-07-13
     */
    List<NewsVo> findByIds(List<Long> ids);

    /**
     * <p>保存</p>
     *
     * @param newsVo vo类
     * @return {@link  Long}
     * @author luyanan
     * @since 2020-07-13
     */
    Long save(NewsVo newsVo);


    /**
     * <p>根据id修改</p>
     *
     * @param newsVo vo类
     * @author luyanan
     * @since 2020-07-13
     */
    void update(NewsVo newsVo);


    /**
     * <p>根据id集合删除</p>
     *
     * @param ids id集合
     * @author luyanan
     * @since 2020-07-13
     */
    void deleteByIds(List<Long> ids);


    /**
     * <p>根据id删除</p>
     *
     * @param id id
     * @author luyanan
     * @since 2020-07-13
     */
    void deleteById(Long id);


    /**
     * <p>查看当前来源下的最新的一条url</p>
     *
     * @param source
     * @return {@link NewsVo}
     * @author luyanan
     * @since 2020/7/13
     */
    NewsVo lastUrl(Integer source);


    /**
     * <p>同步</p>
     *
     * @param source
     * @author luyanan
     * @since 2020/7/14
     */
    void syn(Integer source);

}
