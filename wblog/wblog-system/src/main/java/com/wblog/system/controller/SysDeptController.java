package com.wblog.system.controller;

import com.wblog.system.entity.SysDeptDo;
import com.wblog.system.service.ISysDeptService;
import com.wblog.system.vo.TreeSelectVo;
import io.github.fallingsoulm.easy.archetype.framework.page.RespEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;

/**
 * 部门信息
 *
 * @author luyan
 * @since 2021/2/12
 */
@Api(tags = "部门信息")
@RestController
@RequestMapping("/system/dept")
public class SysDeptController {


    @Autowired
    private ISysDeptService sysDeptService;

    /**
     * 获取部门列表
     *
     * @param dept
     * @return com.wblog.framework.core.page.RespEntity<java.util.List < com.wblog.system.entity.SysDeptDo>>
     * @since 2021/2/12
     */
    @ApiOperation(value = "获取部门列表")
    @PreAuthorize("@ss.hasPermi('system:dept:list')")
    @GetMapping("/list")
    public RespEntity<List<SysDeptDo>> list(SysDeptDo dept) {
        List<SysDeptDo> depts = sysDeptService.list(dept);
        return RespEntity.success(depts);
    }

    /**
     * 查询部门列表(排除节点)
     *
     * @param deptId
     * @return com.wblog.framework.core.page.RespEntity
     * @since 2021/2/12
     */
    @ApiOperation(value = "查询部门列表(排除节点)")
    @PreAuthorize("@ss.hasPermi('system:dept:list')")
    @GetMapping("/list/exclude/{deptId}")
    public RespEntity excludeChild(@PathVariable(value = "deptId", required = false) Long deptId) {
        List<SysDeptDo> depts = sysDeptService.list(new SysDeptDo());
        Iterator<SysDeptDo> it = depts.iterator();
        while (it.hasNext()) {
            SysDeptDo d = (SysDeptDo) it.next();
            if (d.getDeptId().intValue() == deptId
                    || ArrayUtils.contains(StringUtils.split(d.getAncestors(), ","), deptId + "")) {
                it.remove();
            }
        }
        return RespEntity.success(depts);
    }

    /**
     * 根据部门id查询部门详情
     *
     * @param deptId 部门id
     * @return AjaxResult
     * @since 2021/2/12
     */
    @ApiOperation(value = "根据部门id查询部门详情", response = SysDeptDo.class)
    @PreAuthorize("@ss.hasPermi('system:dept:query')")
    @GetMapping(value = "/{deptId}")
    public RespEntity<SysDeptDo> getInfo(@PathVariable Long deptId) {
        SysDeptDo deptDo = sysDeptService.findById(deptId);
        return RespEntity.success(deptDo);
    }


    /**
     * 获取部门下拉树列表
     *
     * @param dept
     * @return com.wblog.framework.core.page.RespEntity
     * @since 2021/2/12
     */
    @ApiOperation(value = "获取部门下拉树列表", response = TreeSelectVo.class)
    @GetMapping("/treeselect")
    public RespEntity treeselect(SysDeptDo dept) {
        return RespEntity.success(sysDeptService.treeselect(dept));
    }

    /**
     * 加载对应角色部门列表树
     *
     * @param roleId 角色id
     * @return com.wblog.framework.core.page.RespEntity
     * @since 2021/2/12
     */
    @ApiOperation(value = "加载对应角色部门列表书")
    @GetMapping(value = "/roleDeptTreeselect/{roleId}")
    public RespEntity roleDeptTreeselect(@PathVariable("roleId") Long roleId) {

        return RespEntity.success(map -> {
            List<Long> deptIds = sysDeptService.listByRoleId(roleId);
            map.put("checkedKeys", deptIds);
            map.put("depts", sysDeptService.treeselect(new SysDeptDo()));
        });
    }

    /**
     * 新增部门
     *
     * @param dept 部门信息
     * @return AjaxResult
     * @since 2021/2/12
     */
    @ApiOperation(value = "新增部门")
    @PreAuthorize("@ss.hasPermi('system:dept:add')")
    @PostMapping
    public RespEntity add(@Validated @RequestBody SysDeptDo dept) {

        sysDeptService.insertDept(dept);
        return RespEntity.success();
    }


    /**
     * 部门修改
     *
     * @param dept 部门信息
     * @return com.wblog.framework.core.page.RespEntity
     * @since 2021/2/12
     */
    @ApiOperation(value = "部门修改")
    @PreAuthorize("@ss.hasPermi('system:dept:edit')")
    @PutMapping
    public RespEntity edit(@Validated @RequestBody SysDeptDo dept) {

        sysDeptService.updateDept(dept);
        return RespEntity.success();
    }


    /**
     * 删除部门
     *
     * @param deptId
     * @return com.wblog.framework.core.page.RespEntity
     * @since 2021/2/12
     */
    @ApiOperation(value = "删除部门")
    @PreAuthorize("@ss.hasPermi('system:dept:remove')")
    @DeleteMapping("/{deptId}")
    public RespEntity remove(@PathVariable Long deptId) {
        sysDeptService.deleteById(deptId);
        return RespEntity.success();
    }

}
