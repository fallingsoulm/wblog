package com.wblog.system.controller;


import com.wblog.system.entity.SysRoleDo;
import com.wblog.system.service.ISysRoleService;
import com.wblog.system.vo.SysRoleVo;
import io.github.fallingsoulm.easy.archetype.framework.page.PageInfo;
import io.github.fallingsoulm.easy.archetype.framework.page.PageRequestParams;
import io.github.fallingsoulm.easy.archetype.framework.page.RespEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 角色信息
 *
 * @author luyan
 * @since 2021/2/15
 */

@Api(tags = "角色信息")
@RestController
@RequestMapping("/system/role")
public class SysRoleController {


    @Autowired
    private ISysRoleService sysRoleService;

    /**
     * 角色列表查询
     *
     * @param pageRequestParams
     * @return com.wblog.framework.core.page.RespEntity<com.wblog.framework.core.page.PageInfo < com.wblog.system.entity.SysRoleDo>>
     * @since 2021/2/15
     */
    @ApiOperation(value = "角色列表查询")
    @PreAuthorize("@ss.hasPermi('system:role:list')")
    @PostMapping("/list")
    public RespEntity<PageInfo<SysRoleDo>> list(@RequestBody PageRequestParams<SysRoleDo> pageRequestParams) {
        PageInfo<SysRoleDo> pageInfo = sysRoleService.findByPage(pageRequestParams);
        return RespEntity.success(pageInfo);
    }

    /**
     * 根据id查询详情
     *
     * @param roleId 角色id
     * @return com.wblog.framework.core.page.RespEntity<com.wblog.system.entity.SysRoleDo>
     * @since 2021/2/15
     */
    @ApiOperation(value = "根据id查询详情")
    @PreAuthorize("@ss.hasPermi('system:role:query')")
    @GetMapping(value = "/{roleId}")
    public RespEntity<SysRoleDo> getInfo(@PathVariable Long roleId) {
        SysRoleDo roleDo = sysRoleService.findById(roleId);
        return RespEntity.success(roleDo);
    }

    /**
     * 角色新增
     *
     * @param role
     * @return com.wblog.framework.core.page.RespEntity
     * @since 2021/2/15
     */
    @ApiOperation(value = "角色新增")
    @PreAuthorize("@ss.hasPermi('system:role:add')")
    @PostMapping
    public RespEntity add(@Validated @RequestBody SysRoleVo role) {


        sysRoleService.insert(role);
        return RespEntity.success();
    }

    /**
     * 角色编辑
     *
     * @param role
     * @return com.wblog.framework.core.page.RespEntity
     * @since 2021/2/15
     */
    @ApiOperation(value = "角色编辑")
    @PreAuthorize("@ss.hasPermi('system:role:edit')")
    @PutMapping
    public RespEntity edit(@Validated @RequestBody SysRoleVo role) {

        sysRoleService.update(role);
        return RespEntity.success();
    }


    /**
     * 修改保存数据权限
     *
     * @param role
     * @return com.wblog.framework.core.page.RespEntity
     * @since 2021/2/15
     */
    @ApiOperation(value = "修改保存数据权限")
    @PreAuthorize("@ss.hasPermi('system:role:edit')")
    @PutMapping("/dataScope")
    public RespEntity dataScope(@RequestBody SysRoleVo role) {

        sysRoleService.dataScope(role);
        return RespEntity.success();
    }


    /**
     * 角色状态修改
     *
     * @param role
     * @return com.wblog.framework.core.page.RespEntity
     * @since 2021/2/15
     */
    @ApiOperation(value = "角色状态修改")
    @PreAuthorize("@ss.hasPermi('system:role:edit')")
    @PutMapping("/changeStatus")
    public RespEntity changeStatus(@RequestBody SysRoleDo role) {
        sysRoleService.changeStatus(role);
        return RespEntity.success();
    }


    /**
     * 角色删除
     *
     * @param roleIds 角色id集合
     * @return com.wblog.framework.core.page.RespEntity
     * @since 2021/2/15
     */
    @ApiOperation(value = "角色删除")
    @PreAuthorize("@ss.hasPermi('system:role:remove')")
    @DeleteMapping("/{roleIds}")
    public RespEntity remove(@PathVariable Long[] roleIds) {
        sysRoleService.deleteByIds(roleIds);
        return RespEntity.success();
    }


    /**
     * 获取角色选择框列表
     *
     * @return com.wblog.framework.core.page.RespEntity<java.util.List < com.wblog.system.entity.SysRoleDo>>
     * @since 2021/2/15
     */
    @ApiOperation(value = "获取角色选择框列表")
    @PreAuthorize("@ss.hasPermi('system:role:query')")
    @GetMapping("/optionselect")
    public RespEntity<List<SysRoleDo>> optionselect() {
        List<SysRoleDo> list =
                sysRoleService.list(null);

        return RespEntity.success(list);
    }
}
