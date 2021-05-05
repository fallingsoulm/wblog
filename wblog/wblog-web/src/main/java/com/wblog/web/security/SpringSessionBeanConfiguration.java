package com.wblog.web.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.session.data.redis.config.ConfigureRedisAction;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.DefaultCookieSerializer;

/**
 * spring session的配置
 *
 * @author luyanan
 * @since 2021/1/30
 **/
@EnableRedisHttpSession()
@Configuration
public class SpringSessionBeanConfiguration {

	public static final String TOKEN_KEY = "Authorization";

	@Bean
	public DefaultCookieSerializer cookieSerializer() {
		DefaultCookieSerializer cookieSerializer = new DefaultCookieSerializer();
		cookieSerializer.setCookiePath("/");
		cookieSerializer.setRememberMeRequestAttribute("readMe");
		cookieSerializer.setCookieName(TOKEN_KEY);
		cookieSerializer.setDomainNamePattern("^.+?\\.(\\w+\\.[a-z]+)$");
		return cookieSerializer;
	}

	@Bean
	public RedisSerializer redisSerializer() {
		return new GenericJackson2JsonRedisSerializer();
	}

	/**
	 * 这里有个小坑，如果服务器用的是云服务器，不加这个会报错
	 * @return org.springframework.session.data.redis.config.ConfigureRedisAction
	 * @since 2021/1/30
	 */
	@Bean
	public static ConfigureRedisAction configureRedisAction() {
		return ConfigureRedisAction.NO_OP;
	}

	/**
	 * session策略，这里配置的是Header方式（有提供Header，Cookie等方式）
	 * @return org.springframework.session.web.http.HeaderHttpSessionIdResolver
	 * @since 2021/1/30
	 */
	@Bean
	public HeaderAndCookieHttpSessionIdResolver headerHttpSessionIdResolver() {
		HeaderAndCookieHttpSessionIdResolver headerAndCookieHttpSessionIdResolver = new HeaderAndCookieHttpSessionIdResolver(
				TOKEN_KEY);
		headerAndCookieHttpSessionIdResolver.setCookieSerializer(cookieSerializer());
		return headerAndCookieHttpSessionIdResolver;
	}

}
