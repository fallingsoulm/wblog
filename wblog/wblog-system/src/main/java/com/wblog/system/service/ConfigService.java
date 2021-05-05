package com.wblog.system.service;

import cn.hutool.core.util.StrUtil;
import com.wblog.common.exception.BusinessException;
import com.wblog.common.exception.IMsgCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

/**
 * 配置类 html调用 thymeleaf 实现参数管理
 *
 * @author luyanan
 * @since 2021/1/24
 **/
@Service("config")
public class ConfigService {

    /**
     * 业务配置前缀
     *
     * @since 2021/1/24
     */

    @Autowired
    private Environment environment;

    /**
     * 根据key获取参数配置信息
     *
     * @param configKey key
     * @return java.lang.String
     * @since 2021/1/24
     */
    public String getKey(String configKey) {
        String property = environment.getProperty(configKey);
        if (StrUtil.isBlank(property)) {
            throw new BusinessException(IMsgCode.CONFIG_NOT_FOUND, configKey);
        }
        return property;
    }

}
