package com.wblog.web;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import io.github.fallingsoulm.easy.archetype.data.file.annotation.EnableFileServer;
import io.github.fallingsoulm.easy.archetype.framework.spring.SpringContextHolder;
import io.github.fallingsoulm.easy.archetype.job.annotation.EnableJob;
import io.github.fallingsoulm.easy.archetype.security.oauth.client.annotation.EnableOauthClient;
import io.github.fallingsoulm.easy.archetype.security.oauth.server.annotation.EnableOauthServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * <p>
 * 主启动类
 * </p>
 *
 * @author luyanan
 * @since 2021/1/18
 **/
@EnableFileServer(mode = EnableFileServer.FileMode.SERVER)
@EnableKnife4j
@EnableOauthClient
@EnableOauthServer
//@EnableSecurity
@EnableCaching
@EnableFeignClients
@SpringBootApplication
@EnableJob(basePackages = {"io.github.fallingsoulm.easy.archetype.job", "com.wblog"})
public class WblogWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(WblogWebApplication.class, args);
    }

}
