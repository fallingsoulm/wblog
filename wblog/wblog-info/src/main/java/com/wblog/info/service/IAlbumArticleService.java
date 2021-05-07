package com.wblog.info.service;


import com.wblog.common.module.info.vo.AlbumArticleVo;
import com.wblog.common.module.info.vo.ArticleVo;
import io.github.fallingsoulm.easy.archetype.framework.page.PageInfo;
import io.github.fallingsoulm.easy.archetype.framework.page.PageRequestParams;

import java.util.List;

/**
 * <p>
 * 文章与专辑关联 service
 * </p>
 *
 * @author luyanan
 * @since 2020-07-15
 */
public interface IAlbumArticleService {


    /**
     * <p>分页查询</p>
     *
     * @param pageRequestParams 分页参数
     * @return {@link PageInfo}
     * @author luyanan
     * @since 2020-07-15
     */
    PageInfo<AlbumArticleVo> findByPage(PageRequestParams<AlbumArticleVo> pageRequestParams);


    /**
     * <p>根据id查询</p>
     *
     * @param id id
     * @return {@link AlbumArticleVo}
     * @author luyanan
     * @since 2020-07-15
     */
    AlbumArticleVo findById(Long id);


    /**
     * <p>根据条件查询列表</p>
     *
     * @param albumArticleVo 条件
     * @return {@link List}
     * @author luyanan
     * @since 2020-07-15
     */
    List<AlbumArticleVo> findList(AlbumArticleVo albumArticleVo);


    /**
     * <p>根据id集合查询</p>
     *
     * @param ids id集合
     * @return {@link List}
     * @author luyanan
     * @since 2020-07-15
     */
    List<AlbumArticleVo> findByIds(List<Long> ids);

    /**
     * <p>保存</p>
     *
     * @param albumArticleVo vo类
     * @return {@link  Long}
     * @author luyanan
     * @since 2020-07-15
     */
    Long save(AlbumArticleVo albumArticleVo);


    /**
     * <p>根据id修改</p>
     *
     * @param albumArticleVo vo类
     * @author luyanan
     * @since 2020-07-15
     */
    void update(AlbumArticleVo albumArticleVo);


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


    /**
     * <p>根据条件进行统计</p>
     *
     * @param build
     * @return
     * @author luyanan
     * @since 2020/7/15
     */
    Integer count(AlbumArticleVo build);

    /**
     * <p>根据专辑id查询不关联的文章列表</p>
     *
     * @param pageRequestParams
     * @return {@link PageInfo< ArticleVo>}
     * @author luyanan
     * @since 2020/7/15
     */
    PageInfo<ArticleVo> notAssociatedArticles(PageRequestParams<AlbumArticleVo> pageRequestParams);

    /**
     * <p>批量添加</p>
     *
     * @param articleIds 文章id
     * @param albumId    专辑id
     * @author luyanan
     * @since 2020/7/15
     */
    void saveBatch(List<Long> articleIds, Long albumId);

    /**
     * <p>根据专辑id查询</p>
     *
     * @param albumId 专辑id
     * @return {@link List< ArticleVo>}
     * @author luyanan
     * @since 2020/7/15
     */
    List<ArticleVo> findArticle(Long albumId);
}
