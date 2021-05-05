package com.wblog.system.controller;


import com.wblog.system.entity.SysPostDo;
import com.wblog.system.service.ISysPostService;
import io.github.fallingsoulm.easy.archetype.framework.page.PageInfo;
import io.github.fallingsoulm.easy.archetype.framework.page.PageRequestParams;
import io.github.fallingsoulm.easy.archetype.framework.page.RespEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 岗位信息
 *
 * @author luyan
 * @since 2021/2/14
 */
@Api(tags = "岗位信息")
@RestController
@RequestMapping("/system/post")
public class SysPostController {

    @Autowired
    private ISysPostService sysPostService;

    /**
     * 岗位列表
     *
     * @param pageRequestParams
     * @return com.wblog.framework.core.page.RespEntity<com.wblog.framework.core.page.PageInfo < com.wblog.system.entity.SysPostDo>>
     * @since 2021/2/14
     */
    @ApiOperation(value = "岗位列表")
    @PreAuthorize("@ss.hasPermi('system:post:list')")
    @PostMapping("/list")
    public RespEntity<PageInfo<SysPostDo>> list(@RequestBody PageRequestParams<SysPostDo> pageRequestParams) {
        PageInfo<SysPostDo> pageInfo = sysPostService.findByPage(pageRequestParams);
        return RespEntity.success(pageInfo);
    }

    /**
     * 根据id查新岗位详情
     *
     * @param postId 岗位id
     * @return com.wblog.framework.core.page.RespEntity<com.wblog.system.entity.SysPostDo>
     * @since 2021/2/14
     */
    @ApiOperation(value = "根据id查新岗位详情")
    @PreAuthorize("@ss.hasPermi('system:post:query')")
    @GetMapping(value = "/{postId}")
    public RespEntity<SysPostDo> getInfo(@PathVariable Long postId) {

        SysPostDo sysPostDo = sysPostService.findById(postId);
        return RespEntity.success(sysPostDo);
    }


    /**
     * 岗位添加
     *
     * @param post
     * @return com.wblog.framework.core.page.RespEntity
     * @since 2021/2/14
     */
    @ApiOperation(value = "岗位添加")
    @PreAuthorize("@ss.hasPermi('system:post:add')")
    @PostMapping
    public RespEntity add(@Validated @RequestBody SysPostDo post) {
        sysPostService.insert(post);
        return RespEntity.success();
    }

    /**
     * 岗位信息修改
     *
     * @param post
     * @return RespEntity
     * @since 2021/2/14
     */
    @ApiOperation(value = "岗位信息修改")
    @PreAuthorize("@ss.hasPermi('system:post:edit')")
    @PutMapping
    public RespEntity edit(@Validated @RequestBody SysPostDo post) {

        sysPostService.update(post);
        return RespEntity.success();
    }

    /**
     * 岗位删除
     *
     * @param postIds 岗位id集合
     * @return com.wblog.framework.core.page.RespEntity
     * @since 2021/2/14
     */
    @ApiOperation(value = "岗位删除")
    @PreAuthorize("@ss.hasPermi('system:post:remove')")
    @DeleteMapping("/{postIds}")
    public RespEntity remove(@PathVariable Long[] postIds) {
        sysPostService.deleteByIds(Arrays.asList(postIds));
        return RespEntity.success();
    }

    /**
     * 获取岗位选择框列表
     *
     * @return com.wblog.framework.core.page.RespEntity
     * @since 2021/2/14
     */
    @ApiOperation(value = "获取岗位选择框列表")
    @GetMapping("/optionselect")
    public RespEntity optionselect() {

        List<SysPostDo> postDos = sysPostService.list(null);
        return RespEntity.success(postDos);
    }

}
