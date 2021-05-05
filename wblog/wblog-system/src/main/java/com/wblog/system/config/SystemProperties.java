package com.wblog.system.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 系统模块配置
 *
 * @author luyanan
 * @since 2021/2/4
 **/
@Data
@ConfigurationProperties(SystemProperties.PERFIX)
public class SystemProperties {

	public static final String PERFIX = "system";


}
