package com.wblog.system.service;

import com.wblog.system.entity.SysDictTypeDo;
import io.github.fallingsoulm.easy.archetype.framework.page.PageInfo;
import io.github.fallingsoulm.easy.archetype.framework.page.PageRequestParams;

import java.util.List;


/**
 * <p>
 * 字典类型表 service
 * </p>
 *
 * @author luyanan
 * @since 2021-02-03
 */
public interface ISysDictTypeService {

    /**
     * 分页查询
     *
     * @param pageRequestParams
     * @return com.wblog.framework.core.page.PageInfo<com.wblog.system.entity.SysDictTypeDo>
     * @since 2021/2/12
     */
    PageInfo<SysDictTypeDo> findByPage(PageRequestParams<SysDictTypeDo> pageRequestParams);

    /**
     * 字典类型新增
     *
     * @param dict
     * @return void
     * @since 2021/2/12
     */
    void save(SysDictTypeDo dict);

    /**
     * 字典类型修改
     *
     * @param dict
     * @return void
     * @since 2021/2/12
     */
    void update(SysDictTypeDo dict);

    /**
     * 根据条件查询
     *
     * @param sysDictTypeDo 条件
     * @return java.util.List<com.wblog.system.entity.SysDictTypeDo>
     * @since 2021/2/12
     */
    List<SysDictTypeDo> list(SysDictTypeDo sysDictTypeDo);

    /**
     * 根据id集合删除
     *
     * @param ids id集合
     * @return void
     * @since 2021/2/12
     */
    void deleteByIds(List<Long> ids);

    /**
     * 根据id查询详情
     *
     * @param dictId
     * @return com.wblog.system.entity.SysDictTypeDo
     * @since 2021/2/12
     */
    SysDictTypeDo findById(Long dictId);
}
