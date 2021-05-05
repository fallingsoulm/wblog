package com.wblog.web.config;//package com.wblog.web.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
///**
// * 跨域
// *
// * @author luyanan
// * @since 2021/2/10
// **/
//@Configuration
//public class SpringCrossConfig implements WebMvcConfigurer {
//	@Override
//	public void addCorsMappings(CorsRegistry registry) {
//		registry.addMapping("/**")
//				.allowedOrigins("*")
//				.allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS")
//				.allowCredentials(true)
//				.maxAge(3600)
//				.allowedHeaders("*");
//	}
//
//
//}
