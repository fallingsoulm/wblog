package com.wblog.social.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 第三方平台
 *
 * @author luyanan
 * @since 2021/7/5
 **/
@EnableConfigurationProperties(SocialProperties.class)
@MapperScan("com.wblog.social.mapper")
@ComponentScan("com.wblog.social")
@Configuration
public class SocialAutoConfiguration {
}
