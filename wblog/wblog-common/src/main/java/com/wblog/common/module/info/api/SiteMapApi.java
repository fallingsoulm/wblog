package com.wblog.common.module.info.api;

import com.wblog.common.constant.Version;
import io.github.fallingsoulm.easy.archetype.framework.page.RespEntity;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author luyanan
 * @since 2020/9/17
 * <p>sitemap</p>
 **/
@FeignClient("apes-hub-business")
public interface SiteMapApi {

    String prefix = "business/" + Version.VERSION_1 + "sitemap/";

    /**
     * <p>返回sitemap的内容</p>
     *
     * @param fileName
     * @return {@link String}
     * @author luyanan
     * @since 2020/9/17
     */
    @GetMapping(prefix + "sitemap/{fileName}")
    @ApiOperation(value = "生成sitemap")
    public RespEntity<String> sitemap(@PathVariable(value = "fileName") String fileName);

    /**
     * <p>生成sitemap文件</p>
     *
     * @author luyanan
     * @since 2020/9/21
     */
    @GetMapping(prefix + "siteMap")
    RespEntity siteMap();

    @PostMapping(prefix + "siteMapPath")
    RespEntity<String> siteMapPath(@RequestParam("fileName") String fileName);

}
