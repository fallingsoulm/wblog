package com.wblog.search.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

/**
 * 搜索业务系统自动配置类
 *
 * @author luyanan
 * @since 2021/7/18
 **/
@EnableConfigurationProperties(SearchProperties.class)
@ComponentScan("com.wblog.search")
public class SearchBizAutoConfiguration {
}
