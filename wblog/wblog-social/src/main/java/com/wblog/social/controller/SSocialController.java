package com.wblog.social.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;

import java.util.Arrays;

import org.springframework.security.access.prepost.PreAuthorize;
import io.github.fallingsoulm.easy.archetype.framework.page.PageRequestParams;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.annotations.Api;
import io.github.fallingsoulm.easy.archetype.framework.page.PageInfo;
import com.wblog.common.module.social.vo.SSocialVo;
import com.wblog.social.service.ISSocialService;
import io.github.fallingsoulm.easy.archetype.framework.page.RespEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 第三方平台表 controller
 * </p>
 *
 * @author luyanan
 * @since 2021-07-05
 */
@Api(tags = "第三方平台表")
@RestController
@RequestMapping("s/social")
public class SSocialController {
    /**
     * 权限前缀
     *
     * @since 2021-07-05
     */
    private static final String PERMISS_PREFIX = "s:social:";

    @Autowired
    private ISSocialService iSSocialService;

    /**
     * 分页查询
     *
     * @param pageRequestParams 分页参数
     * @return RespEntity<PageInfo < SSocialVo>>
     * @since 2021-07-05
     */
    @ApiOperation(value = "分页查询", response = SSocialVo.class)
    @PreAuthorize("@ss.hasPermi('" + PERMISS_PREFIX + "list')")
    @PostMapping("/list")
    public RespEntity<PageInfo<SSocialVo>> listByPage(@RequestBody PageRequestParams<SSocialVo> pageRequestParams) {
        PageInfo<SSocialVo> pageInfo = iSSocialService.listByPage(pageRequestParams);
        return RespEntity.success(pageInfo);
    }

    /**
     * 根据id查询详情
     *
     * @param id id
     * @return RespEntity<SSocialVo>
     * @since 2021-07-05
     */
    @ApiOperation(value = "根据id查询详情", response = SSocialVo.class)
    @PreAuthorize("@ss.hasPermi('" + PERMISS_PREFIX + "query')")
    @GetMapping(value = "/{id}")
    public RespEntity<SSocialVo> findById(@PathVariable("id") Long id) {
        SSocialVo sSocialVo = iSSocialService.findById(id);
        return RespEntity.success(sSocialVo);
    }

    /**
     * 新增
     *
     * @param sSocialVo sSocialVo
     * @return RespEntity
     * @since 2021-07-05
     */
    @ApiOperation(value = "新增")
    @PreAuthorize("@ss.hasPermi('" + PERMISS_PREFIX + "add')")
    @PostMapping()
    public RespEntity add(@Validated @RequestBody SSocialVo sSocialVo) {
        iSSocialService.insert(sSocialVo);
        return RespEntity.success();
    }

    /**
     * 修改
     *
     * @param sSocialVo sSocialVo
     * @return RespEntity
     * @since 2021-07-05
     */
    @ApiOperation(value = "修改")
    @PreAuthorize("@ss.hasPermi('" + PERMISS_PREFIX + "edit')")
    @PutMapping
    public RespEntity edit(@Validated @RequestBody SSocialVo sSocialVo) {
        iSSocialService.update(sSocialVo);
        return RespEntity.success();
    }

    /**
     * 根据id集合删除
     *
     * @param ids id删除
     * @return RespEntity
     * @since 2021-07-05
     */
    @ApiOperation(value = "删除")
    @PreAuthorize("@ss.hasPermi('" + PERMISS_PREFIX + "remove')")
    @DeleteMapping("/{ids}")
    public RespEntity remove(@PathVariable("ids") Long[] ids) {
        iSSocialService.deleteByIds(Arrays.asList(ids));
        return RespEntity.success();
    }
}
