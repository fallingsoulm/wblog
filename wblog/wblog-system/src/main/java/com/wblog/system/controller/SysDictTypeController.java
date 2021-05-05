package com.wblog.system.controller;

import com.wblog.system.entity.SysDictTypeDo;
import com.wblog.system.service.ISysDictTypeService;
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
import java.util.stream.Collectors;

/**
 * 数据字典类型
 *
 * @author luyan
 * @since 2021/2/12
 */
@Api(tags = "数据字典类型")
@RestController
@RequestMapping("/system/dict/type")
public class SysDictTypeController {

    @Autowired
    private ISysDictTypeService sysDictTypeService;

    /**
     * 字典类型分页查询
     *
     * @param pageRequestParams
     * @return com.wblog.framework.core.page.RespEntity<com.wblog.framework.core.page.PageInfo < com.wblog.system.entity.SysDictTypeDo>>
     * @since 2021/2/12
     */
    @ApiOperation(value = "字典类型分页查询", response = SysDictTypeDo.class)
    @PreAuthorize("@ss.hasPermi('system:dict:list')")
    @PostMapping("/list")
    public RespEntity<PageInfo<SysDictTypeDo>> list(@RequestBody PageRequestParams<SysDictTypeDo> pageRequestParams) {

        PageInfo<SysDictTypeDo> pageInfo = sysDictTypeService.findByPage(pageRequestParams);
        return RespEntity.success(pageInfo);
    }

    /**
     * 查询所有的字典类型
     *
     * @param sysDictTypeDo
     * @return com.wblog.framework.core.page.RespEntity<java.util.List < com.wblog.system.entity.SysDictTypeDo>>
     * @since 2021/2/15
     */
    @ApiOperation(value = "查询所有的字典类型")
    @PreAuthorize("@ss.hasPermi('system:dict:list')")
    @PostMapping("/list/all")
    public RespEntity<List<SysDictTypeDo>> listAll(SysDictTypeDo sysDictTypeDo) {

        List<SysDictTypeDo> list = sysDictTypeService.list(sysDictTypeDo);
        return RespEntity.success(list);
    }

    /**
     * 根据字典类型id查询字典详情
     *
     * @param dictId
     * @return com.wblog.framework.core.page.RespEntity
     * @since 2021/2/12
     */
    @ApiOperation(value = "根据字典类型id查询字典详情")
    @PreAuthorize("@ss.hasPermi('system:dict:query')")
    @GetMapping(value = "/{dictId}")
    public RespEntity getInfo(@PathVariable Long dictId) {
        SysDictTypeDo sysDictTypeDo = sysDictTypeService.findById(dictId);
        return RespEntity.success(sysDictTypeDo);
    }

    /**
     * 字典类型新增
     *
     * @param dict 字典信息
     * @return com.wblog.framework.core.page.RespEntity
     * @since 2021/2/12
     */
    @ApiOperation(value = "字典类型新增")
    @PreAuthorize("@ss.hasPermi('system:dict:add')")
    @PostMapping
    public RespEntity add(@Validated @RequestBody SysDictTypeDo dict) {
        sysDictTypeService.save(dict);
        return RespEntity.success();
    }

    /**
     * 字典类型修改
     *
     * @param dict
     * @return com.wblog.framework.core.page.RespEntity
     * @since 2021/2/12
     */
    @ApiOperation(value = "字典类型修改")
    @PreAuthorize("@ss.hasPermi('system:dict:edit')")
    @PutMapping
    public RespEntity edit(@Validated @RequestBody SysDictTypeDo dict) {

        sysDictTypeService.update(dict);
        return RespEntity.success();
    }

    /**
     * 删除字典类型
     *
     * @param dictIds 字典类型id
     * @return com.wblog.framework.core.page.RespEntity
     * @since 2021/2/12
     */
    @ApiOperation(value = "删除字典类型")
    @PreAuthorize("@ss.hasPermi('system:dict:remove')")
    @DeleteMapping("/{dictIds}")
    public RespEntity remove(@PathVariable Long[] dictIds) {
        sysDictTypeService.deleteByIds(Arrays.stream(dictIds).collect(Collectors.toList()));
        return RespEntity.success();
    }

    /**
     * 获取字典选择框列表
     *
     * @return com.wblog.framework.core.page.RespEntity
     * @since 2021/2/12
     */
    @ApiOperation(value = "获取字典选择框列表")
    @GetMapping("/optionselect")
    public RespEntity optionselect() {

        List<SysDictTypeDo> sysDictTypeDoList = sysDictTypeService.list(SysDictTypeDo.builder().build());
        return RespEntity.success(sysDictTypeDoList);
    }
}
