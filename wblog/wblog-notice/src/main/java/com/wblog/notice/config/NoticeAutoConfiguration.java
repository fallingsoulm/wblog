package com.wblog.notice.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 推送消息自动化配置
 *
 * @author luyanan
 * @since 2021/6/29
 **/
@MapperScan("com.wblog.notice.mapper")
@ComponentScan("com.wblog.notice")
@Configuration
public class NoticeAutoConfiguration {
}
