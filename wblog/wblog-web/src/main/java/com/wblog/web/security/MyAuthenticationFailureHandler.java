package com.wblog.web.security;

import com.wblog.common.exception.IMsgCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.fallingsoulm.easy.archetype.framework.page.RespEntity;
import io.github.fallingsoulm.easy.archetype.security.validatecode.ValidateCodeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * 自定义失败处理器
 *
 * @author luyanan
 * @since 2021/2/10
 **/
@Component
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException ex) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        PrintWriter out = response.getWriter();
        Map<String, Object> map = new HashMap<String, Object>(16);
        String msg = "登录失败";
        if (ex instanceof UsernameNotFoundException || ex instanceof BadCredentialsException) {
            msg = "用户名或密码错误";
        } else if (ex instanceof DisabledException) {
            msg = "账户被禁用";
        } else if (ex instanceof ValidateCodeException) {
            msg = "验证码错误";
        }
        out.write(objectMapper.writeValueAsString(RespEntity.error(IMsgCode.INTERNAL_SERVER_ERROR, msg)));
        out.flush();
        out.close();
    }
}
