package com.wblog.info.service;

import com.apes.hub.api.module.info.vo.ClassifyVo;
import com.apes.hub.api.page.PageInfo;
import com.apes.hub.api.page.PageRequestParams;

import java.util.List;

/**
 * <p>
 * 文章分类 service
 * </p>
 *
 * @author luyanan
 * @since 2020-06-10
 */
public interface IClassifyService {


    /**
     * <p>分页查询</p>
     *
     * @param pageRequestParams 分页参数
     * @return {@link PageInfo}
     * @author luyanan
     * @since 2020-06-10
     */
    PageInfo<ClassifyVo> findByPage(PageRequestParams<ClassifyVo> pageRequestParams);


    /**
     * <p>根据id查询</p>
     *
     * @param id id
     * @return {@link ClassifyVo}
     * @author luyanan
     * @since 2020-06-10
     */
    ClassifyVo findById(Long id);


    /**
     * <p>根据条件查询列表</p>
     *
     * @param classifyVo 条件
     * @return {@link List}
     * @author luyanan
     * @since 2020-06-10
     */
    List<ClassifyVo> findList(ClassifyVo classifyVo);


    /**
     * <p>根据id集合查询</p>
     *
     * @param ids id集合
     * @return {@link List}
     * @author luyanan
     * @since 2020-06-10
     */
    List<ClassifyVo> findByIds(List<Long> ids);

    /**
     * <p>保存</p>
     *
     * @param classifyVo vo类
     * @return {@link  Long}
     * @author luyanan
     * @since 2020-06-10
     */
    Long save(ClassifyVo classifyVo);


    /**
     * <p>根据id修改</p>
     *
     * @param classifyVo vo类
     * @author luyanan
     * @since 2020-06-10
     */
    void update(ClassifyVo classifyVo);


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
