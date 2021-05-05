package com.wblog.system.service;

import com.wblog.system.entity.SysRoleDo;
import com.wblog.system.vo.SysRoleVo;
import io.github.fallingsoulm.easy.archetype.framework.page.PageInfo;
import io.github.fallingsoulm.easy.archetype.framework.page.PageRequestParams;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 角色信息表 service
 * </p>
 *
 * @author luyanan
 * @since 2021-02-03
 */
public interface ISysRoleService {

    /**
     * 根据用户id查询角色列表
     *
     * @param userId 用户id
     * @return java.util.List<com.wblog.system.entity.SysRoleDo>
     * @since 2021/2/4
     */
    List<SysRoleDo> listByUserId(Long userId);

    /**
     * 根据id集合查询
     *
     * @param ids id集合
     * @return java.util.List<com.wblog.system.entity.SysRoleDo>
     * @since 2021/2/12
     */
    List<SysRoleDo> findByIds(Collection<Long> ids);


    /**
     * 根据条件查询
     *
     * @param sysRoleDo 条件
     * @return java.util.List<com.wblog.system.entity.SysRoleDo>
     * @since 2021/2/12
     */
    List<SysRoleDo> list(SysRoleDo sysRoleDo);

    /**
     * 根据id查询
     *
     * @param roleId 角色id
     * @return com.wblog.system.entity.SysRoleDo
     * @since 2021/2/12
     */
    SysRoleDo findById(Long roleId);

    /**
     * 角色列表分页查询
     *
     * @param pageRequestParams
     * @return com.wblog.framework.core.page.PageInfo<com.wblog.system.entity.SysRoleDo>
     * @since 2021/2/15
     */
    PageInfo<SysRoleDo> findByPage(PageRequestParams<SysRoleDo> pageRequestParams);

    /**
     * 角色新增
     *
     * @param role
     * @return void
     * @since 2021/2/15
     */
    void insert(SysRoleVo role);

    /**
     * 角色编辑
     *
     * @param role
     * @return void
     * @since 2021/2/15
     */
    void update(SysRoleVo role);

    /**
     * 修改保存数据权限
     *
     * @param role
     * @return void
     * @since 2021/2/15
     */
    void dataScope(SysRoleVo role);

    /**
     * 角色状态修改
     *
     * @param role
     * @return void
     * @since 2021/2/15
     */
    void changeStatus(SysRoleDo role);

    /**
     * 根据数组批量删除
     *
     * @param roleIds 角色数组
     * @return void
     * @since 2021/2/15
     */
    void deleteByIds(Long[] roleIds);
}
