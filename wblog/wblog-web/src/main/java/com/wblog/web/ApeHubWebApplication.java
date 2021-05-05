package com.wblog.web;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import io.github.fallingsoulm.easy.archetype.security.oauth.client.annotation.EnableOauthClient;
import io.github.fallingsoulm.easy.archetype.security.oauth.server.annotation.EnableOauthServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * <p>
 * 主启动类
 * </p>
 *
 * @author luyanan
 * @since 2021/1/18
 **/
@EnableKnife4j
@EnableOauthClient
@EnableOauthServer
//@EnableSecurity
@EnableCaching
@SpringBootApplication
public class ApeHubWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApeHubWebApplication.class, args);
    }

}
