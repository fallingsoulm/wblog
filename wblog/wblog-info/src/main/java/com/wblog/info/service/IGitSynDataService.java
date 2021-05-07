package com.wblog.info.service;



import com.wblog.common.module.info.vo.GitSynDataVo;
import io.github.fallingsoulm.easy.archetype.framework.page.PageInfo;
import io.github.fallingsoulm.easy.archetype.framework.page.PageRequestParams;

import java.util.List;

/**
 * <p>
 * git同步信息配置 service
 * </p>
 *
 * @author luyanan
 * @since 2020-06-07
 */
public interface IGitSynDataService {


    /**
     * <p>分页查询</p>
     *
     * @param pageRequestParams 分页参数
     * @return {@link PageInfo}
     * @author luyanan
     * @since 2020-06-07
     */
    PageInfo<GitSynDataVo> findByPage(PageRequestParams<GitSynDataVo> pageRequestParams);


    /**
     * <p>根据id查询</p>
     *
     * @param id id
     * @return {@link GitSynDataVo}
     * @author luyanan
     * @since 2020-06-07
     */
    GitSynDataVo findById(Long id);


    /**
     * <p>根据条件查询列表</p>
     *
     * @param gitSynDataVo 条件
     * @return {@link List}
     * @author luyanan
     * @since 2020-06-07
     */
    List<GitSynDataVo> findList(GitSynDataVo gitSynDataVo);


    /**
     * <p>根据id集合查询</p>
     *
     * @param ids id集合
     * @return {@link List}
     * @author luyanan
     * @since 2020-06-07
     */
    List<GitSynDataVo> findByIds(List<Long> ids);

    /**
     * <p>保存</p>
     *
     * @param gitSynDataVo vo类
     * @return {@link Long}
     * @author luyanan
     * @since 2020/6/7
     */
    Long save(GitSynDataVo gitSynDataVo);


    /**
     * <p>根据id修改</p>
     *
     * @param gitSynDataVo vo类
     * @author luyanan
     * @since 2020-06-07
     */
    void update(GitSynDataVo gitSynDataVo);


    /**
     * <p>根据id集合删除</p>
     *
     * @param ids id集合
     * @author luyanan
     * @since 2020-06-07
     */
    void deleteByIds(List<Long> ids);


    /**
     * <p>根据id删除</p>
     *
     * @param id id
     * @author luyanan
     * @since 2020-06-07
     */
    void deleteById(Long id);


    /**
     * <p>根据id进行同步</p>
     *
     * @param id
     * @author luyanan
     * @since 2020/6/10
     */
    void syn(Long id);

    /**
     * <p>同步所有</p>
     *
     * @author luyanan
     * @since 2020/6/10
     */
    void synAll();


    /**
     * <p>从gitee中获取git地址</p>
     *
     * @author luyanan
     * @since 2020/6/22
     */
    void getGitUrlFromGitee();


    /**
     * <p>从github中获取gitee地址</p>
     *
     * @author luyanan
     * @since 2020/6/22
     */
    void getGitUrlFromGithub();

    /**
     * <p>批量插入</p>
     *
     * @param gitSynDataVos
     * @author luyanan
     * @since 2020/6/22
     */
    void saveBatch(List<GitSynDataVo> gitSynDataVos);

    /**
     * 执行git信息同步
     *
     * @param id
     * @author luyanan
     * @since 2020/11/28
     */
    void doGitSynData(Long id);

    /**
     * <p>修改文章状态</p>
     *
     * @param gitSynDataVo
     * @author luyanan
     * @since 2020/6/30
     */
    void updateArticleStatus(GitSynDataVo gitSynDataVo);

    /**
     * <p>修改状态</p>
     *
     * @param ids    id集合
     * @param status 状态
     * @author luyanan
     * @since 2020/9/22
     */
    void updateStatus(Long[] ids, Integer status);

}
