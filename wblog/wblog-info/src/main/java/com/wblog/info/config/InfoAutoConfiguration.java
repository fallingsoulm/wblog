package com.wblog.info.config;

import com.wblog.info.component.FileTemplatePlus;
import io.github.fallingsoulm.easy.archetype.data.file.FileProperties;
import io.github.fallingsoulm.easy.archetype.data.file.IFileService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 信息模块自动配置类
 *
 * @author luyanan
 * @since 2021/5/31
 **/
@Configuration
@MapperScan("com.wblog.info.mapper")
@Import(BlogConfigProperties.class)
@ComponentScan("com.wblog.info")
public class InfoAutoConfiguration {

    @Bean
    public FileTemplatePlus fileTemplatePlus(IFileService fileService, FileProperties fileProperties) {
        return new FileTemplatePlus(fileService, fileProperties);
    }
}
