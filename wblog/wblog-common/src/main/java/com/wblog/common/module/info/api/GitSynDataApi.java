package com.wblog.common.module.info.api;

import com.wblog.common.constant.ApplicationNameConstants;
import com.wblog.common.constant.Version;
import io.github.fallingsoulm.easy.archetype.framework.page.RespEntity;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(ApplicationNameConstants.INFO)
public interface GitSynDataApi {

    String prefix = ApplicationNameConstants.INFO_PATH_PREFIX + Version.VERSION_1 + "git/syn/data/";

    /**
     * <p>从github 抓取地址</p>
     *
     * @author luyanan
     * @since 2020/9/21
     */
    @ApiOperation(value = "从github抓取数据")
    @GetMapping(prefix + "getGitUrlFromGithub")
    public RespEntity getGitUrlFromGithub();


    /**
     * <p>同步</p>
     *
     * @author luyanan
     * @since 2020/9/21
     */
    @ApiOperation(value = "同步")
    @GetMapping(prefix + "synAll")

    public RespEntity synAll();


    /**
     * <p>从gitee抓取url</p>
     *
     * @author luyanan
     * @since 2020/9/21
     */
    @ApiOperation(value = "从gitee同步数据")
    @GetMapping(prefix + "getGitUrlFromGitee")
    public RespEntity getGitUrlFromGitee();
}
