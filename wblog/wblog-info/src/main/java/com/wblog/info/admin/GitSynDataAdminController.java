package com.wblog.info.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * @author luyanan
 * @since 2020/10/27
 * <p>git同步信息</p>
 **/

@Api(description = "git同步信息")
@RestController
@RequestMapping(Version.VERSION_1 + "git/syn/data/admin")
public class GitSynDataAdminController {

    @Autowired
    private IGitSynDataService gitSynDataService;

    @Autowired
    private LoginUserManage loginUserManage;

    /**
     * 查询git同步信息配置列表
     */
    @GlobalDataScope
    @ApiOperation(value = "列表查询")
    @PreAuthorize("hasAnyAuthority('info:gitSynData:list')")
    @PostMapping("/list")
    public RespEntity<PageInfo<GitSynDataVo>> list(@RequestBody PageRequestParams<GitSynDataVo> pageRequestParams) {
        GitSynDataVo params = pageRequestParams.getParams();
        if (null == params) {
            params = new GitSynDataVo();
        }
        params.setStatusList(Arrays.asList(ConstantEnum.GIT_SYN_DATA_STATUS_ENABLE.getValue()
                , ConstantEnum.GIT_SYN_DATA_STATUS_CHECKING.getValue()
                , ConstantEnum.GIT_SYN_DATA_STATUS_STOP.getValue()));
        pageRequestParams.setParams(params);
        PageInfo<GitSynDataVo> pageInfo = gitSynDataService.findByPage(pageRequestParams);
        return RespEntity.success(pageInfo);
    }

    /**
     * 导出git同步信息配置列表
     */
    @PreAuthorize("hasAnyAuthority('info:gitSynData:export')")
    @GetMapping("/export")
    public RespEntity export(GitSynDataVo gitSynDataVo) {
        List<GitSynDataVo> list = gitSynDataService.findList(gitSynDataVo);
        ExcelUtil<GitSynDataVo> util = new ExcelUtil<GitSynDataVo>(GitSynDataVo.class);
        return util.exportExcel(list, "gitSynData");
    }

    /**
     * 获取git同步信息配置详细信息
     */
    @ApiOperation(value = "根据id查询")
    @PreAuthorize("hasAnyAuthority('info:gitSynData:query')")
    @GetMapping(value = "/{id}")
    public RespEntity getInfo(@PathVariable("id") Long id) {
        return RespEntity.success(gitSynDataService.findById(id));
    }

    /**
     * 新增git同步信息配置
     */
    @ApiOperation(value = "添加")
    @PreAuthorize("hasAnyAuthority('info:gitSynData:add')")
    @PostMapping
    public RespEntity add(@RequestBody @Validated GitSynDataVo gitSynDataVo) {
        gitSynDataVo.setUserId(loginUserManage.userId());
        gitSynDataVo.setUserName(loginUserManage.user().getUsername());
        gitSynDataVo.setOwner(loginUserManage.user().getUsername());

        //设置默认值
        if (StrUtil.isBlank(gitSynDataVo.getBranch())) {
            gitSynDataVo.setBranch("master");
        }
        if (null == gitSynDataVo.getStatus()) {
            gitSynDataVo.setStatus(ConstantEnum.GIT_SYN_DATA_STATUS_STOP.getValue());
        }


        return RespEntity.success(gitSynDataService.save(gitSynDataVo));
    }

    /**
     * 修改git同步信息配置
     */
    @ApiOperation(value = "修改")
    @PreAuthorize("hasAnyAuthority('info:gitSynData:edit')")
    @PutMapping
    public RespEntity edit(@RequestBody GitSynDataVo gitSynDataVo) {
        gitSynDataService.update(gitSynDataVo);
        return RespEntity.success();
    }

    /**
     * 删除git同步信息配置
     */
    @ApiOperation(value = "删除")
    @PreAuthorize("hasAnyAuthority('info:gitSynData:remove')")
    @DeleteMapping("/{ids}")
    public RespEntity remove(@PathVariable Long[] ids) {
        gitSynDataService.deleteByIds(Arrays.stream(ids).collect(Collectors.toList()));
        return RespEntity.success();
    }


    /**
     * <p>修改状态</p>
     *
     * @param ids
     * @param status
     * @return {@link RespEntity}
     * @author luyanan
     * @since 2020/9/22
     */
    @ApiOperation(value = "修改状态")
    @GetMapping("update/status/{ids}/{status}")
    public RespEntity updateStatus(@PathVariable("ids") Long[] ids,
                                   @PathVariable("status") Integer status) {

        gitSynDataService.updateStatus(ids, status);
        return RespEntity.success();
    }


    @ApiOperation(value = "同步")
    @GetMapping("syn/{id}")
    public RespEntity syn(@PathVariable("id") Long id) {
        CompletableFuture.runAsync(() -> {
            gitSynDataService.syn(id);
        });
        return RespEntity.success();
    }
}
