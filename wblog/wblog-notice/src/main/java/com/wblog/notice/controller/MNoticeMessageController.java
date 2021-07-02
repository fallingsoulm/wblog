package com.wblog.notice.controller;

import com.wblog.notice.service.IMNoticeMessageService;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;

import java.util.Arrays;

import org.springframework.security.access.prepost.PreAuthorize;
import com.wblog.common.module.notice.vo.MNoticeMessageVo;
import io.github.fallingsoulm.easy.archetype.framework.page.PageRequestParams;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.annotations.Api;
import io.github.fallingsoulm.easy.archetype.framework.page.PageInfo;
import io.github.fallingsoulm.easy.archetype.framework.page.RespEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 通知消息详情 controller
 * </p>
 *
 * @author luyanan
 * @since 2021-06-29
 */
@Api(tags = "通知消息详情")
@RestController
@RequestMapping("m/notice/message")
public class MNoticeMessageController {
    /**
     * 权限前缀
     *
     * @since 2021-06-29
     */
    private static final String PERMISS_PREFIX = "m:notice:message:";

    @Autowired
    private IMNoticeMessageService iMNoticeMessageService;

    /**
     * 分页查询
     *
     * @param pageRequestParams 分页参数
     * @return RespEntity<PageInfo < MNoticeMessageVo>>
     * @since 2021-06-29
     */
    @ApiOperation(value = "分页查询", response = MNoticeMessageVo.class)
    @PreAuthorize("hasAnyAuthority('" + PERMISS_PREFIX + "list')")
    @PostMapping("/list")
    public RespEntity<PageInfo<MNoticeMessageVo>> listByPage(@RequestBody PageRequestParams<MNoticeMessageVo> pageRequestParams) {
        PageInfo<MNoticeMessageVo> pageInfo = iMNoticeMessageService.listByPage(pageRequestParams);
        return RespEntity.success(pageInfo);
    }

    /**
     * 根据id查询详情
     *
     * @param id id
     * @return RespEntity<MNoticeMessageVo>
     * @since 2021-06-29
     */
    @ApiOperation(value = "根据id查询详情", response = MNoticeMessageVo.class)
    @PreAuthorize("hasAnyAuthority('" + PERMISS_PREFIX + "query')")
    @GetMapping(value = "/{id}")
    public RespEntity<MNoticeMessageVo> findById(@PathVariable("id") Long id) {
        MNoticeMessageVo mNoticeMessageVo = iMNoticeMessageService.findById(id);
        return RespEntity.success(mNoticeMessageVo);
    }

    /**
     * 新增
     *
     * @param mNoticeMessageVo mNoticeMessageVo
     * @return RespEntity
     * @since 2021-06-29
     */
    @ApiOperation(value = "新增")
    @PreAuthorize("hasAnyAuthority('" + PERMISS_PREFIX + "add')")
    @PostMapping()
    public RespEntity add(@Validated @RequestBody MNoticeMessageVo mNoticeMessageVo) {
        iMNoticeMessageService.insert(mNoticeMessageVo);
        return RespEntity.success();
    }

    /**
     * 修改
     *
     * @param mNoticeMessageVo mNoticeMessageVo
     * @return RespEntity
     * @since 2021-06-29
     */
    @ApiOperation(value = "修改")
    @PreAuthorize("hasAnyAuthority('" + PERMISS_PREFIX + "edit')")
    @PutMapping
    public RespEntity edit(@Validated @RequestBody MNoticeMessageVo mNoticeMessageVo) {
        iMNoticeMessageService.update(mNoticeMessageVo);
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
        iMNoticeMessageService.deleteByIds(Arrays.asList(ids));
        return RespEntity.success();
    }
}
