package com.wblog.front.controller;

import cn.hutool.core.io.FileUtil;
import com.wblog.common.module.info.api.SiteMapApi;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author luyanan
 * @since 2020/6/17
 * <p>siteMap控制层</p>
 **/

@Api(value = "生成sitemap")
@RestController
@RequestMapping("sitemap")
public class SiteMapController {

    @Autowired
    private SiteMapApi siteMapApi;

    @ApiOperation(value = "获取sitemap")
    @GetMapping("/{fileName}")
    public String sitemap(@PathVariable(value = "fileName") String fileName) {
        String path = siteMapApi.siteMapPath(fileName).getData();
        String string = FileUtil.readString(path, "utf-8");
        return string;
    }


}
