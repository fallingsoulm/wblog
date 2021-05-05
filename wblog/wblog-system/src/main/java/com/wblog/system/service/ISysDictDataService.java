package com.wblog.system.service;

import com.wblog.system.entity.SysDictDataDo;
import io.github.fallingsoulm.easy.archetype.framework.page.PageInfo;
import io.github.fallingsoulm.easy.archetype.framework.page.PageRequestParams;

import java.util.List;

/**
 * <p>
 * 字典数据表 service
 * </p>
 *
 * @author luyanan
 * @since 2021-02-03
 */
public interface ISysDictDataService {

    /**
     * 根据条件查询
     *
     * @param sysDictDataDo
     * @return java.util.List<com.wblog.system.entity.SysDictDataDo>
     * @since 2021/2/11
     */
    List<SysDictDataDo> list(SysDictDataDo sysDictDataDo);

    /**
     * 修改字典类型
     *
     * @param newDictType 新的字典类型
     * @param oldDictType 旧的字典类型
     * @return void
     * @since 2021/2/12
     */
    void updateDictType(String newDictType, String oldDictType);

    /**
     * 根据条件统计
     *
     * @param sysDictDataDo
     * @return java.lang.Integer
     * @since 2021/2/12
     */
    Integer count(SysDictDataDo sysDictDataDo);

    /**
     * 分页查询
     *
     * @param pageRequestParams 分页参数
     * @return com.wblog.framework.core.page.PageInfo<com.wblog.system.entity.SysDictDataDo>
     * @since 2021/2/14
     */
    PageInfo<SysDictDataDo> findByPage(PageRequestParams<SysDictDataDo> pageRequestParams);

    /**
     * 插入操作
     *
     * @param dict
     * @return void
     * @since 2021/2/14
     */
    void insert(SysDictDataDo dict);

    /**
     * 根据id进行修改
     *
     * @param dict
     * @return void
     * @since 2021/2/14
     */
    void update(SysDictDataDo dict);

    /**
     * 根据id删除
     *
     * @param dictCodes
     * @return void
     * @since 2021/2/14
     */
    void deleteById(Long[] dictCodes);

}
