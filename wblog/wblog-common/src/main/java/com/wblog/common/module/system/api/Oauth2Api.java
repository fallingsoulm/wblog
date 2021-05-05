package com.wblog.common.module.system.api;

import io.github.fallingsoulm.easy.archetype.framework.page.RespEntity;
import io.github.fallingsoulm.easy.archetype.security.core.LoginUserVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author luyanan
 * @since 2020/9/4
 * <p>Oauth的接口</p>
 **/
@FeignClient("apes-hub-uaa-web")
public interface Oauth2Api {


    /**
     * <p>获取token</p>
     *
     * @param client_id
     * @param client_secret
     * @param grant_type
     * @param username
     * @param password
     * @return {@link Object}
     * @author luyanan
     * @since 2020/9/4
     */
    @PostMapping("uaa/oauth/token")
    Map<String, Object> oauthToken(@RequestParam("client_id") String client_id,
                                   @RequestParam("client_secret") String client_secret,
                                   @RequestParam("grant_type") String grant_type,
                                   @RequestParam("username") String username,
                                   @RequestParam("password") String password);


    /**
     * <p>获取当前登陆用户</p>
     *
     * @return {@link LoginUserVo}
     * @author luyanan
     * @since 2020/10/30
     */
    @GetMapping("uaa/api/v1/getCurrentUser")
    RespEntity<LoginUserVo> getCurrentUser();

}
