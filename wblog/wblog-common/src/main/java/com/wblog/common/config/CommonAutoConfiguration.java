package com.wblog.common.config;

import io.github.fallingsoulm.easy.archetype.data.id.SimpleSnowflake;
import io.github.fallingsoulm.easy.archetype.data.mybatisplus.MybatisPlusUtils;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 自动配置
 *
 * @author luyanan
 * @since 2021/1/24
 **/
@EnableFeignClients("com.wblog.common.module")
@Configuration
@ComponentScan("com.wblog.common")
public class CommonAutoConfiguration {


    @Bean
    public SimpleSnowflake simpleSnowflake() {
        return new SimpleSnowflake(0, 0);
    }

    @Bean
    public MybatisPlusUtils mybatisPlusUtils() {
        return new MybatisPlusUtils();
    }
}
