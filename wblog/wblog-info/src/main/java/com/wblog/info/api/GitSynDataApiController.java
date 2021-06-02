package com.wblog.info.api;

import cn.hutool.core.util.StrUtil;
import com.wblog.common.constant.Version;
import com.wblog.common.datascope.annotation.GlobalDataScope;
import com.wblog.common.enums.ConstantEnum;
import com.wblog.common.module.info.vo.GitSynDataVo;
import com.wblog.info.service.IGitSynDataService;
import io.github.fallingsoulm.easy.archetype.framework.page.PageInfo;
import io.github.fallingsoulm.easy.archetype.framework.page.PageRequestParams;
import io.github.fallingsoulm.easy.archetype.framework.page.RespEntity;
import io.github.fallingsoulm.easy.archetype.framework.thread.BusinessThreadPoolTaskExecutor;
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

@Api(description = "git同步信息")
@RestController
@RequestMapping(Version.VERSION_1 + "git/syn/data")
public class GitSynDataApiController {

    @Autowired
    private IGitSynDataService gitSynDataService;

    @Autowired
    private BusinessThreadPoolTaskExecutor businessThreadPoolTaskExecutor;

    /**
     * <p>从github 抓取地址</p>
     *
     * @author luyanan
     * @since 2020/9/21
     */
    @ApiOperation(value = "从github抓取数据")
    @GetMapping("getGitUrlFromGithub")
    public RespEntity getGitUrlFromGithub() {

        businessThreadPoolTaskExecutor.execute(new Runnable() {
            @Override
            public void run() {
                gitSynDataService.getGitUrlFromGithub();
            }
        });
        return RespEntity.success();
    }


    /**
     * <p>同步</p>
     *
     * @author luyanan
     * @since 2020/9/21
     */
    @ApiOperation(value = "同步")
    @GetMapping("synAll")
    public RespEntity synAll() {
        businessThreadPoolTaskExecutor.execute(new Runnable() {
            @Override
            public void run() {
                gitSynDataService.synAll();
            }
        });
        return RespEntity.success();
    }


    /**
     * <p>从gitee抓取url</p>
     *
     * @author luyanan
     * @since 2020/9/21
     */
    @ApiOperation(value = "从gitee同步数据")
    @GetMapping("getGitUrlFromGitee")
    public RespEntity getGitUrlFromGitee() {
        businessThreadPoolTaskExecutor.execute(() -> {
            gitSynDataService.getGitUrlFromGitee();
        });

        return RespEntity.success();
    }

    /**
     * 查询git同步信息配置列表
     */
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
//
//    /**
//     * 导出git同步信息配置列表
//     */
//    @PreAuthorize("hasAnyAuthority('info:gitSynData:export')")
//    @GetMapping("/export")
//    public RespEntity export(GitSynDataVo gitSynDataVo) {
//        List<GitSynDataVo> list = gitSynDataService.findList(gitSynDataVo);
//        ExcelUtil<GitSynDataVo> util = new ExcelUtil<GitSynDataVo>(GitSynDataVo.class);
//        return util.exportExcel(list, "gitSynData");
//    }

    /**
     * 获取git同步信息配置详细信息
     */
    @PreAuthorize("hasAnyAuthority('info:gitSynData:query')")
    @GetMapping(value = "/{id}")
    public RespEntity getInfo(@PathVariable("id") Long id) {
        return RespEntity.success(gitSynDataService.findById(id));
    }

    /**
     * 新增git同步信息配置
     */
    @PreAuthorize("hasAnyAuthority('info:gitSynData:add')")
    @PostMapping
    public RespEntity add(@RequestBody GitSynDataVo gitSynDataVo) {
        return RespEntity.success(gitSynDataService.save(gitSynDataVo));
    }

    /**
     * 修改git同步信息配置
     */
    @PreAuthorize("hasAnyAuthority('info:gitSynData:edit')")
    @PutMapping
    public RespEntity edit(@RequestBody GitSynDataVo gitSynDataVo) {
        gitSynDataService.update(gitSynDataVo);
        return RespEntity.success();
    }

    /**
     * 删除git同步信息配置
     */
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
