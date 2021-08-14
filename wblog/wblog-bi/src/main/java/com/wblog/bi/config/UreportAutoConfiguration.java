package com.wblog.bi.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 自动配置类
 *
 * @author luyanan
 * @since 2021/8/2
 **/
@MapperScan("com.wblog.bi.mapper")
@Configuration
@ComponentScan("com.wblog.bi")
public class UreportAutoConfiguration {
}
