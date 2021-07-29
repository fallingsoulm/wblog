package com.wblog.search.config;

import com.wblog.search.service.InfoSearchService;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 搜索API自动配置类
 *
 * @author luyanan
 * @since 2021/7/18
 **/
@EnableFeignClients("com.wblog.search.api")
@Configuration
public class SearchApiAutoConfiguration {
    @Bean
    public InfoSearchService infoSearchService() {
        return new InfoSearchService();
    }
}
