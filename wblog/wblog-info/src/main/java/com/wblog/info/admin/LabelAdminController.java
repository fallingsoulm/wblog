package com.wblog.info.admin;

import com.wblog.common.constant.Version;
import com.wblog.common.module.info.vo.LabelVo;
import com.wblog.info.service.ILabelService;
import io.github.fallingsoulm.easy.archetype.framework.page.PageInfo;
import io.github.fallingsoulm.easy.archetype.framework.page.PageRequestParams;
import io.github.fallingsoulm.easy.archetype.framework.page.RespEntity;
import io.github.fallingsoulm.easy.archetype.framework.validation.AddGroup;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author luyanan
 * @since 2020/10/22
 * <p>标签, 提供给后台管理系统</p>
 **/
@Api(description = "标签")
@RestController
@RequestMapping(Version.VERSION_1 + "/label/admin")
public class LabelAdminController {
    @Autowired
    private ILabelService labelService;

    /**
     * <p>分页查询标签并且统计数量</p>
     *
     * @param pageRequestParams 分页参数
     * @return {@link PageInfo < LabelVo >}
     * @author luyanan
     * @since 2020/6/15
     */
    @PreAuthorize("hasAnyAuthority('info:label:query')")
    @PostMapping("find/page/count")
    @ApiOperation(value = "分页查询标签并且统计数量")
    public RespEntity<PageInfo<LabelVo>> findByPageAndCount(@RequestBody PageRequestParams<LabelVo> pageRequestParams) {
        PageInfo<LabelVo> pageInfo = labelService.findByPage(pageRequestParams);
        return RespEntity.success(pageInfo);
    }

    /**
     * <p>根据id查询</p>
     *
     * @param labelId
     * @return {@link LabelVo}
     * @author luyanan
     * @since 2020/9/17
     */
    @PreAuthorize("hasAnyAuthority('info:label:query')")
    @ApiOperation(value = "根据id查询", response = LabelVo.class)
    @GetMapping("find/id/{labelId}")
    RespEntity<LabelVo> findById(@PathVariable("labelId") Long labelId) {
        LabelVo labelVo = labelService.findById(labelId);
        return RespEntity.success(labelVo);
    }

    @PreAuthorize("hasAnyAuthority('info:label:add')")
    @ApiOperation(value = "新增")
    @PostMapping()
    public RespEntity save(@RequestBody @Validated(AddGroup.class) LabelVo labelVo) {
        labelService.save(labelVo);
        return RespEntity.success();
    }

    @PreAuthorize("hasAnyAuthority('info:label:edit')")
    @ApiOperation(value = "修改")
    @PutMapping
    public RespEntity edit(@RequestBody LabelVo labelVo) {
        labelService.update(labelVo);
        return RespEntity.success();
    }

    @PreAuthorize("hasAnyAuthority('info:label:remove')")
    @ApiOperation(value = "删除")
    @DeleteMapping("/{ids}")
    public RespEntity remove(@PathVariable("ids") Long[] ids) {
        labelService.deleteByIds(Arrays.stream(ids).collect(Collectors.toList()));
        return RespEntity.success();
    }

}
