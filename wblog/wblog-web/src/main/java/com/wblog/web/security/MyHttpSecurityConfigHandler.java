package com.wblog.web.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.fallingsoulm.easy.archetype.security.security.HttpSecurityConfigHandler;
import io.github.fallingsoulm.easy.archetype.security.validatecode.ValidateCodeFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;

/**
 * 自定义安全配置
 *
 * @author luyanan
 * @since 2021/2/19
 **/
@Slf4j
@Component
public class MyHttpSecurityConfigHandler implements HttpSecurityConfigHandler {

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private ValidateCodeFilter validateCodeFilter;

	@Autowired
	private DataSource dataSource;

	@Autowired
	private PasswordEncoder passwordEncoder;


	@Autowired
	private CustomUserDetailsServiceImpl customUserDetailsService;

	@Autowired
	private MyAuthenticationFailureHandler authenticationFailureHandler;

	@Autowired
	private MyAuthenticationSuccessHandler authenticationSuccessHandler;

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
				.formLogin()
				.loginPage("/login")
				.loginProcessingUrl("/login")
				.successHandler(authenticationSuccessHandler)
				.failureHandler(authenticationFailureHandler)
				.and()
				.logout()
				.logoutUrl("/logout")
				.logoutSuccessHandler((httpServletRequest, httpServletResponse, authentication) -> log.debug("{}注销登录成功"));
		// 开启模拟请求，比如API POST测试工具的测试，不开启时，API POST为报403错误
		http.csrf().disable().cors().disable();
	}

	@Override
	public void configure(WebSecurity web) {
		// 对于在header里面增加token等类似情况，放行所有OPTIONS请求。
		web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		// 对默认的UserDetailsService进行覆盖
		authenticationProvider.setUserDetailsService(customUserDetailsService);
		authenticationProvider.setPasswordEncoder(passwordEncoder);
		return authenticationProvider;
	}

	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
		tokenRepository.setDataSource(dataSource);
		//启动的时候是否创建该表，这个表格是保存用户登录信息的
//		tokenRepository.setCreateTableOnStartup(true);
		return tokenRepository;
	}
}
