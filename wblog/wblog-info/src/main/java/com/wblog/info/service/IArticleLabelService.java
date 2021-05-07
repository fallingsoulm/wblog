package com.wblog.info.service;

import com.wblog.common.module.info.vo.ArticleLabelVo;
import com.wblog.common.module.info.vo.LabelVo;
import io.github.fallingsoulm.easy.archetype.framework.page.PageInfo;
import io.github.fallingsoulm.easy.archetype.framework.page.PageRequestParams;

import java.util.List;

/**
 * <p>
 * 文章与标签关联 service
 * </p>
 *
 * @author luyanan
 * @since 2020-06-10
 */
public interface IArticleLabelService {


    /**
     * <p>分页查询</p>
     *
     * @param pageRequestParams 分页参数
     * @return {@link PageInfo}
     * @author luyanan
     * @since 2020-06-10
     */
    PageInfo<ArticleLabelVo> findByPage(PageRequestParams<ArticleLabelVo> pageRequestParams);


    /**
     * <p>根据id查询</p>
     *
     * @param id id
     * @return {@link ArticleLabelVo}
     * @author luyanan
     * @since 2020-06-10
     */
    ArticleLabelVo findById(Long id);


    /**
     * <p>根据条件查询列表</p>
     *
     * @param articleLabelVo 条件
     * @return {@link List}
     * @author luyanan
     * @since 2020-06-10
     */
    List<ArticleLabelVo> findList(ArticleLabelVo articleLabelVo);


    /**
     * <p>根据id集合查询</p>
     *
     * @param ids id集合
     * @return {@link List}
     * @author luyanan
     * @since 2020-06-10
     */
    List<ArticleLabelVo> findByIds(List<Long> ids);

    /**
     * <p>保存</p>
     *
     * @param articleLabelVo vo类
     * @return {@link  Long}
     * @author luyanan
     * @since 2020-06-10
     */
    Long save(ArticleLabelVo articleLabelVo);


    /**
     * <p>根据id修改</p>
     *
     * @param articleLabelVo vo类
     * @author luyanan
     * @since 2020-06-10
     */
    void update(ArticleLabelVo articleLabelVo);


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
     * <p>根据条件删除</p>
     *
     * @param articleLabelVo
     * @author luyanan
     * @since 2020/6/10
     */
    void delete(ArticleLabelVo articleLabelVo);


    /**
     * <p>分页查询并且统计数量</p>
     *
     * @param pageRequestParams 分页参数
     * @return {@link PageInfo< LabelVo>}
     * @author luyanan
     * @since 2020/6/15
     */
    PageInfo<LabelVo> findByPageAndCount(PageRequestParams<LabelVo> pageRequestParams);


    /**
     * <p>统计</p>
     *
     * @param articleLabelVo
     * @return {@link Integer}
     * @author luyanan
     * @since 2020/6/30
     */
    Integer count(ArticleLabelVo articleLabelVo);

    /**
     * <p>批量插入</p>
     *
     * @param articleLabelVoList
     * @author luyanan
     * @since 2020/7/25
     */
    void saveBatch(List<ArticleLabelVo> articleLabelVoList);

}
