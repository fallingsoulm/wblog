package com.wblog.search.config;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 搜索服务配置类
 *
 * @author luyanan
 * @since 2021/7/18
 **/
@Data
@ConfigurationProperties(prefix = "es")
public class SearchProperties {

    /**
     * es的host
     *
     * @since 2021/7/18
     */
    @ApiModelProperty(value = "es的host")
    private String host;


    /**
     * 端口号
     *
     * @since 2021/7/18
     */
    @ApiModelProperty(value = "端口号")
    private Integer port;


    /**
     * info的索引
     *
     * @author luyanan
     * @since 2021/7/18
     */
    private String infoType;
}
