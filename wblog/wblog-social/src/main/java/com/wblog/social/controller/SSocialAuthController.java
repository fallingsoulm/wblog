package com.wblog.social.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 第三方社区登录
 *
 * @author luyanan
 * @since 2021/7/6
 **/
@RequestMapping("social/auth")
@RestController
public class SSocialAuthController {


    /**
     * 获取登录页面
     *
     * @param response
     * @param platform
     * @return void
     * @since 2021/7/6
     */
    @RequestMapping("/render/{platform}")
    public void renderAuth(HttpServletResponse response, @PathVariable("platform") Integer platform) throws IOException {
        AuthRequest authRequest = getAuthRequest();
        response.sendRedirect(authRequest.authorize(AuthStateUtils.createState()));
    }
}
