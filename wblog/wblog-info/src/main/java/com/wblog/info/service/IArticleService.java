package com.wblog.info.service;



import com.wblog.common.module.info.vo.ArticleVo;
import io.github.fallingsoulm.easy.archetype.framework.page.PageInfo;

import java.util.List;

/**
 * <p>
 * 文章表 service
 * </p>
 *
 * @author luyanan
 * @since 2020-06-10
 */
public interface IArticleService {


    /**
     * <p>分页查询</p>
     *
     * @param pageRequestParams 分页参数
     * @return {@link PageInfo}
     * @author luyanan
     * @since 2020-06-10
     */
    PageInfo<ArticleVo> findByPage(PageRequestParams<ArticleVo> pageRequestParams);


    /**
     * <p>根据id查询</p>
     *
     * @param id id
     * @return {@link ArticleVo}
     * @author luyanan
     * @since 2020-06-10
     */
    ArticleVo findById(Long id);


    /**
     * <p>根据条件查询列表</p>
     *
     * @param articleVo 条件
     * @return {@link List}
     * @author luyanan
     * @since 2020-06-10
     */
    List<ArticleVo> findList(ArticleVo articleVo);


    /**
     * <p>根据id集合查询</p>
     *
     * @param ids id集合
     * @return {@link List}
     * @author luyanan
     * @since 2020-06-10
     */
    List<ArticleVo> findByIds(List<Long> ids);

    /**
     * <p>保存</p>
     *
     * @param articleVo vo类
     * @return {@link  Long}
     * @author luyanan
     * @since 2020-06-10
     */
    Long save(ArticleVo articleVo);


    /**
     * <p>根据id修改</p>
     *
     * @param articleVo vo类
     * @author luyanan
     * @since 2020-06-10
     */
    void update(ArticleVo articleVo);


    /**
     * <p>根据id集合删除</p>
     *
     * @param ids id集合
     * @author luyanan
     * @since 2020-06-10
     */
    void deleteByIds(List<Long> ids);


    /**
     * <p>根据id删除</p>
     *
     * @param id id
     * @author luyanan
     * @since 2020-06-10
     */
    void deleteById(Long id);


    /**
     * <p>查看详情</p>
     *
     * @param id 文章id
     * @return {@link ArticleVo}
     * @author luyanan
     * @since 2020/6/15
     */
    ArticleVo info(Long id);

    /**
     * <p>根据标签id分页查询</p>
     *
     * @param pageRequestParams 标签id
     * @return {@link PageInfo< ArticleVo>}
     * @author luyanan
     * @since 2020/6/16
     */
    PageInfo<ArticleVo> findPageByLabelId(PageRequestParams<ArticleLabelVo> pageRequestParams);

    /**
     * <p>访问数量同步到数据库</p>
     *
     * @author luyanan
     * @since 2020/6/16
     */
    void synView();


    /**
     * <p>批量修改文章状态</p>
     *
     * @param articleIds 文章列表
     * @param status     状态
     * @author luyanan
     * @since 2020/6/27
     */
    void updateStatus(List<Long> articleIds, Integer status);

    /**
     * <p>根据条件统计</p>
     *
     * @param articleVo
     * @return {@link Integer}
     * @author luyanan
     * @since 2020/6/30
     */
    Integer count(ArticleVo articleVo);

    /**
     * <p>根据gitid 进行修改</p>
     *
     * @param gitId
     * @param status
     * @author luyanan
     * @since 2020/6/30
     */
    void update(Long gitId, Integer status);

    /**
     * <p>分页查询</p>
     *
     * @param pageRequestParams
     * @return {@link PageInfo< ArticleVo>}
     * @author luyanan
     * @since 2020/6/30
     */
    PageInfo<ArticleVo> findByPageToManage(PageRequestParams<ArticleVo> pageRequestParams);


    /**
     * <p>修改文章的编码</p>
     *
     * @param id      文章id
     * @param charset 文章编码
     * @author luyanan
     * @since 2020/7/10
     */
    void updateCharset(Long id, String charset);

    /**
     * <p>重命名</p>
     *
     * @param id
     * @author luyanan
     * @since 2020/7/11
     */
    void rename(Long id);

    /**
     * <p>需要排除的分页查询</p>
     *
     * @param params
     * @param notInIds 需要排除的id
     * @return {@link PageInfo< ArticleVo>}
     * @author luyanan
     * @since 2020/7/15
     */
    PageInfo<ArticleVo> findByPageAndNotIn(PageRequestParams<ArticleVo> params, List<Long> notInIds);


    /**
     * <p>文章巡逻</p>
     *
     * @author luyanan
     * @since 2020/7/24
     */
    void patrol();



}
