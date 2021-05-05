package com.wblog.system.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 系统模块配置
 *
 * @author luyanan
 * @since 2021/1/24
 **/
@MapperScan("com.wblog.system.mapper")
@Configuration
@ComponentScan("com.wblog.system")
@EnableConfigurationProperties({ SystemProperties.class })
public class SystemAutoConfiguration {

}
