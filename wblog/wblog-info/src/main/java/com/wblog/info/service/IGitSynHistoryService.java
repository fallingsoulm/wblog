package com.wblog.info.service;

import com.apes.hub.api.module.info.vo.GitSynHistoryVo;
import com.apes.hub.api.page.PageInfo;
import com.apes.hub.api.page.PageRequestParams;

import java.util.List;

/**
 * <p>
 * git同步历史 service
 * </p>
 *
 * @author luyanan
 * @since 2020-06-10
 */
public interface IGitSynHistoryService {


    /**
     * <p>分页查询</p>
     *
     * @param pageRequestParams 分页参数
     * @return {@link PageInfo}
     * @author luyanan
     * @since 2020-06-10
     */
    PageInfo<GitSynHistoryVo> findByPage(PageRequestParams<GitSynHistoryVo> pageRequestParams);


    /**
     * <p>根据id查询</p>
     *
     * @param id id
     * @return {@link GitSynHistoryVo}
     * @author luyanan
     * @since 2020-06-10
     */
    GitSynHistoryVo findById(Long id);


    /**
     * <p>根据条件查询列表</p>
     *
     * @param gitSynHistoryVo 条件
     * @return {@link List}
     * @author luyanan
     * @since 2020-06-10
     */
    List<GitSynHistoryVo> findList(GitSynHistoryVo gitSynHistoryVo);


    /**
     * <p>根据id集合查询</p>
     *
     * @param ids id集合
     * @return {@link List}
     * @author luyanan
     * @since 2020-06-10
     */
    List<GitSynHistoryVo> findByIds(List<Long> ids);

    /**
     * <p>保存</p>
     *
     * @param gitSynHistoryVo vo类
     * @return {@link  Long}
     * @author luyanan
     * @since 2020-06-10
     */
    Long save(GitSynHistoryVo gitSynHistoryVo);


    /**
     * <p>根据id修改</p>
     *
     * @param gitSynHistoryVo vo类
     * @author luyanan
     * @since 2020-06-10
     */
    void update(GitSynHistoryVo gitSynHistoryVo);


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


}
