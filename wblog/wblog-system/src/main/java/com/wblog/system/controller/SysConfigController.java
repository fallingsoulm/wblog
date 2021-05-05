package com.wblog.system.controller;

import com.wblog.system.service.ConfigService;
import io.github.fallingsoulm.easy.archetype.framework.page.RespEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 初始化密码
 *
 * @author luyanan
 * @since 2021/2/11
 **/
@RestController
@RequestMapping("system/config")
public class SysConfigController {

    @Autowired
    private ConfigService configService;

    @GetMapping(value = "/configKey/{configKey}")
    public RespEntity getConfigKey(@PathVariable String configKey) {
        String value = configService.getKey(configKey);
        return RespEntity.success(value);
    }


}
