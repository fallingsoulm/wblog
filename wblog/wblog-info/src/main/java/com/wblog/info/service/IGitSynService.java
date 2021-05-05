package com.wblog.info.service;

import com.apes.hub.api.module.info.vo.GitSynVo;
import com.apes.hub.api.page.PageInfo;
import com.apes.hub.api.page.PageRequestParams;

import java.util.List;

/**
 * <p>
 * 同步表 service
 * </p>
 *
 * @author luyanan
 * @since 2020-06-10
 */
public interface IGitSynService {


    /**
     * <p>分页查询</p>
     *
     * @param pageRequestParams 分页参数
     * @return {@link PageInfo}
     * @author luyanan
     * @since 2020-06-10
     */
    PageInfo<GitSynVo> findByPage(PageRequestParams<GitSynVo> pageRequestParams);


    /**
     * <p>根据id查询</p>
     *
     * @param id id
     * @return {@link GitSynVo}
     * @author luyanan
     * @since 2020-06-10
     */
    GitSynVo findById(Long id);


    /**
     * <p>根据条件查询列表</p>
     *
     * @param gitSynVo 条件
     * @return {@link List}
     * @author luyanan
     * @since 2020-06-10
     */
    List<GitSynVo> findList(GitSynVo gitSynVo);


    /**
     * <p>根据id集合查询</p>
     *
     * @param ids id集合
     * @return {@link List}
     * @author luyanan
     * @since 2020-06-10
     */
    List<GitSynVo> findByIds(List<Long> ids);

    /**
     * <p>保存</p>
     *
     * @param gitSynVo vo类
     * @return {@link  Long}
     * @author luyanan
     * @since 2020-06-10
     */
    Long save(GitSynVo gitSynVo);


    /**
     * <p>根据id修改</p>
     *
     * @param gitSynVo vo类
     * @author luyanan
     * @since 2020-06-10
     */
    void update(GitSynVo gitSynVo);


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
     * <p>批量插入</p>
     *
     * @param newGitSynVos
     * @author luyanan
     * @since 2020/6/12
     */
    void saveBatch(List<GitSynVo> newGitSynVos);

    /**
     * <p>根据gitid删除</p>
     *
     * @param id
     * @author luyanan
     * @since 2020/6/12
     */
    void deleteGitId(Long id);

    /**
     * <p>查询单个</p>
     *
     * @param build
     * @return {@link GitSynVo}
     * @author luyanan
     * @since 2020/7/10
     */
    GitSynVo findOne(GitSynVo build);

    /**
     * <p>根据文章id集合查询</p>
     *
     * @param articleIds 文章id集合
     * @return {@link List< GitSynVo>}
     * @author luyanan
     * @since 2020/7/11
     */
    List<GitSynVo> findByArticleIds(List<Long> articleIds);


}
