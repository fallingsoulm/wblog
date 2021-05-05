package com.wblog.info.service;

import com.apes.hub.api.module.info.vo.ArticleInfoVo;
import com.apes.hub.api.page.PageInfo;
import com.apes.hub.api.page.PageRequestParams;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 文章详情表 service
 * </p>
 *
 * @author luyanan
 * @since 2020-06-10
 */
public interface IArticleInfoService {


    /**
     * <p>分页查询</p>
     *
     * @param pageRequestParams 分页参数
     * @return {@link PageInfo}
     * @author luyanan
     * @since 2020-06-10
     */
    PageInfo<ArticleInfoVo> findByPage(PageRequestParams<ArticleInfoVo> pageRequestParams);


    /**
     * <p>根据id查询</p>
     *
     * @param articleId articleId
     * @return {@link ArticleInfoVo}
     * @author luyanan
     * @since 2020-06-10
     */
    ArticleInfoVo findById(Long articleId);


    /**
     * <p>根据id查询.返回map</p>
     *
     * @param articleIds
     * @return {@link Map<Long,ArticleInfoVo>}
     * @author luyanan
     * @since 2020/10/13
     */
    Map<Long, ArticleInfoVo> findByIdsToMap(List<Long> articleIds);


    /**
     * <p>根据条件查询列表</p>
     *
     * @param articleInfoVo 条件
     * @return {@link List}
     * @author luyanan
     * @since 2020-06-10
     */
    List<ArticleInfoVo> findList(ArticleInfoVo articleInfoVo);


    /**
     * <p>根据articleId集合查询</p>
     *
     * @param articleIds articleId集合
     * @return {@link List}
     * @author luyanan
     * @since 2020-06-10
     */
    List<ArticleInfoVo> findByIds(List<Long> articleIds);

    /**
     * <p>保存</p>
     *
     * @param articleInfoVo vo类
     * @return {@link  Long}
     * @author luyanan
     * @since 2020-06-10
     */
    Long save(ArticleInfoVo articleInfoVo);


    /**
     * <p>根据articleId修改</p>
     *
     * @param articleInfoVo vo类
     * @author luyanan
     * @since 2020-06-10
     */
    void update(ArticleInfoVo articleInfoVo);


    /**
     * <p>根据articleId集合删除</p>
     *
     * @param articleIds articleId集合
     * @author luyanan
     * @since 2020-06-10
     */
    void deleteByIds(List<Long> articleIds);


    /**
     * <p>根据id删除</p>
     *
     * @param articleId articleId
     * @author luyanan
     * @since 2020-06-10
     */
    void deleteById(Long articleId);


}
