package com.wblog.social.config;

import com.wblog.social.core.PlatformInfoVo;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * 第三方平台配置
 *
 * @author luyanan
 * @since 2021/7/8
 **/
@Data
@ConfigurationProperties(prefix = "social")
public class SocialProperties {


    /**
     * 平台配置
     *
     * @author luyanan
     * @since 2021/7/8
     */
    private List<PlatformInfoVo> platform;
}
