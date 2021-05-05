package com.wblog.info.api;

import com.apes.hub.api.uitils.RespEntity;
import com.apes.hub.core.constant.Version;
import com.apes.hub.data.thread.BusinessThreadPoolTaskExecutor;
import com.apes.hub.info.config.BlogConfigProperties;
import com.apes.hub.info.service.ISiteMapService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author luyanan
 * @since 2020/9/17
 * <p>sitemap</p>
 **/
@Api(description = "sitemap")
@RestController
@RequestMapping(Version.VERSION_1 + "sitemap")
public class SiteMapApiController {


    @Autowired
    private BusinessThreadPoolTaskExecutor businessThreadPoolTaskExecutor;
    @Autowired
    private ISiteMapService siteMapService;

    @Autowired
    private BlogConfigProperties blogConfigProperties;

    /**
     * <p>返回sitemap的内容</p>
     *
     * @param fileName
     * @return {@link String}
     * @author luyanan
     * @since 2020/9/17
     */
    @GetMapping("sitemap/{fileName}")
    @ApiOperation(value = "生成sitemap")
    public RespEntity<String> sitemap(@PathVariable(value = "fileName") String fileName) {
        String url = siteMapService.sitemap(fileName);
        return RespEntity.success(url);
    }


    @ApiOperation(value = "生成sitemap文件")
    @GetMapping("siteMap")
    public RespEntity siteMap() {
        businessThreadPoolTaskExecutor.execute(new Runnable() {
            @Override
            public void run() {
                siteMapService.siteMap();
            }
        });

        return RespEntity.success();
    }

    @ApiOperation(value = "生成sitemap文件路径")
    @PostMapping("siteMapPath")
    public RespEntity<String> siteMapPath(@RequestParam("fileName") String fileName) {
        return RespEntity.success(blogConfigProperties.getSiteMapPath() + fileName);
    }

}
