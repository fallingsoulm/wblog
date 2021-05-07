package com.wblog.info.service;


import com.wblog.common.module.info.vo.LabelVo;
import io.github.fallingsoulm.easy.archetype.framework.page.PageInfo;
import io.github.fallingsoulm.easy.archetype.framework.page.PageRequestParams;

import java.util.List;

/**
 * <p>
 * 标签 service
 * </p>
 *
 * @author luyanan
 * @since 2020-06-10
 */
public interface ILabelService {


    /**
     * <p>分页查询</p>
     *
     * @param pageRequestParams 分页参数
     * @return {@link PageInfo}
     * @author luyanan
     * @since 2020-06-10
     */
    PageInfo<LabelVo> findByPage(PageRequestParams<LabelVo> pageRequestParams);


    /**
     * <p>根据id查询</p>
     *
     * @param id id
     * @return {@link LabelVo}
     * @author luyanan
     * @since 2020-06-10
     */
    LabelVo findById(Long id);


    /**
     * <p>根据条件查询列表</p>
     *
     * @param labelVo 条件
     * @return {@link List}
     * @author luyanan
     * @since 2020-06-10
     */
    List<LabelVo> findList(LabelVo labelVo);


    /**
     * <p>根据id集合查询</p>
     *
     * @param ids id集合
     * @return {@link List}
     * @author luyanan
     * @since 2020-06-10
     */
    List<LabelVo> findByIds(List<Long> ids);

    /**
     * <p>保存</p>
     *
     * @param labelVo vo类
     * @return {@link  Long}
     * @author luyanan
     * @since 2020-06-10
     */
    Long save(LabelVo labelVo);


    /**
     * <p>根据id修改</p>
     *
     * @param labelVo vo类
     * @author luyanan
     * @since 2020-06-10
     */
    void update(LabelVo labelVo);


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
     * <p>更新标签</p>
     *
     * @param articleId 文章id
     * @author luyanan
     * @since 2020/6/10
     */
    void updateLabel(Long articleId);


    /**
     * <p>分页查询标签并且统计数量</p>
     *
     * @param pageRequestParams 分页参数
     * @return {@link PageInfo< LabelVo>}
     * @author luyanan
     * @since 2020/6/15
     */
    PageInfo<LabelVo> findByPageAndCount(PageRequestParams<LabelVo> pageRequestParams);

    /**
     * <p>根据文章id 查询关联的标签列表</p>
     *
     * @param id 文章id
     * @return {@link List< LabelVo>}
     * @author luyanan
     * @since 2020/6/15
     */
    default List<LabelVo> findByArticleId(Long id) {
        return findByArticleId(id, null);
    }


    /**
     * <p>根据文章id 查询关联的标签列表</p>
     *
     * @param id    文章id
     * @param limit 数量
     * @return {@link List< LabelVo>}
     * @author luyanan
     * @since 2020/6/15
     */
    List<LabelVo> findByArticleId(Long id, Integer limit);



}
