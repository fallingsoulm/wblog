package com.wblog.web.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 配置中心配置
 *
 * @author luyanan
 * @since 2021/1/30
 **/

@Configuration
public class PropertySourceConfiguration {

	@Value("${spring.application.name}")
	private String applicationName;

	// @Bean
	// public PropertySource propertySource(DataSource dataSource) {
	// return new JdbcPropertySource(dataSource, applicationName);
	// }

}
