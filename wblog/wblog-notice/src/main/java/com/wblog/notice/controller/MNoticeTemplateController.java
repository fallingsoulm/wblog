package com.wblog.notice.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;

import java.util.Arrays;

import org.springframework.security.access.prepost.PreAuthorize;
import io.github.fallingsoulm.easy.archetype.framework.page.PageRequestParams;
import com.wblog.common.module.notice.vo.MNoticeTemplateVo;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.annotations.Api;
import io.github.fallingsoulm.easy.archetype.framework.page.PageInfo;
import com.wblog.notice.service.IMNoticeTemplateService;
import io.github.fallingsoulm.easy.archetype.framework.page.RespEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 通知消息之消息模板 controller
 * </p>
 *
 * @author luyanan
 * @since 2021-06-29
 */
@Api(tags = "通知消息之消息模板")
@RestController
@RequestMapping("m/notice/template")
public class MNoticeTemplateController {
    /**
     * 权限前缀
     *
     * @since 2021-06-29
     */
    private static final String PERMISS_PREFIX = "m:notice:template:";

    @Autowired
    private IMNoticeTemplateService iMNoticeTemplateService;

    /**
     * 分页查询
     *
     * @param pageRequestParams 分页参数
     * @return RespEntity<PageInfo < MNoticeTemplateVo>>
     * @since 2021-06-29
     */
    @ApiOperation(value = "分页查询", response = MNoticeTemplateVo.class)
    @PreAuthorize("hasAnyAuthority('" + PERMISS_PREFIX + "list')")
    @PostMapping("/list")
    public RespEntity<PageInfo<MNoticeTemplateVo>> listByPage(@RequestBody PageRequestParams<MNoticeTemplateVo> pageRequestParams) {
        PageInfo<MNoticeTemplateVo> pageInfo = iMNoticeTemplateService.listByPage(pageRequestParams);
        return RespEntity.success(pageInfo);
    }

    /**
     * 根据id查询详情
     *
     * @param id id
     * @return RespEntity<MNoticeTemplateVo>
     * @since 2021-06-29
     */
    @ApiOperation(value = "根据id查询详情", response = MNoticeTemplateVo.class)
    @PreAuthorize("hasAnyAuthority('" + PERMISS_PREFIX + "query')")
    @GetMapping(value = "/{id}")
    public RespEntity<MNoticeTemplateVo> findById(@PathVariable("id") Long id) {
        MNoticeTemplateVo mNoticeTemplateVo = iMNoticeTemplateService.findById(id);
        return RespEntity.success(mNoticeTemplateVo);
    }

    /**
     * 新增
     *
     * @param mNoticeTemplateVo mNoticeTemplateVo
     * @return RespEntity
     * @since 2021-06-29
     */
    @ApiOperation(value = "新增")
    @PreAuthorize("hasAnyAuthority('" + PERMISS_PREFIX + "add')")
    @PostMapping()
    public RespEntity add(@Validated @RequestBody MNoticeTemplateVo mNoticeTemplateVo) {
        iMNoticeTemplateService.insert(mNoticeTemplateVo);
        return RespEntity.success();
    }

    /**
     * 修改
     *
     * @param mNoticeTemplateVo mNoticeTemplateVo
     * @return RespEntity
     * @since 2021-06-29
     */
    @ApiOperation(value = "修改")
    @PreAuthorize("hasAnyAuthority('" + PERMISS_PREFIX + "edit')")
    @PutMapping
    public RespEntity edit(@Validated @RequestBody MNoticeTemplateVo mNoticeTemplateVo) {
        iMNoticeTemplateService.update(mNoticeTemplateVo);
        return RespEntity.success();
    }

    /**
     * 根据id集合删除
     *
     * @param ids id删除
     * @return RespEntity
     * @since 2021-06-29
     */
    @ApiOperation(value = "删除")
    @PreAuthorize("hasAnyAuthority('" + PERMISS_PREFIX + "remove')")
    @DeleteMapping("/{ids}")
    public RespEntity remove(@PathVariable("ids") Long[] ids) {
        iMNoticeTemplateService.deleteByIds(Arrays.asList(ids));
        return RespEntity.success();
    }


}
