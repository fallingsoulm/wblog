package com.wblog.social.core;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * 平台信息管理
 *
 * @author luyanan
 * @since 2021/7/8
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlatformInfoVo {

    /**
     * 唯一标识
     *
     * @since 2021/7/8
     */
    @ApiModelProperty(value = "唯一标识")
    private String uuid;

    /**
     * 平台
     *
     * @since 2021/7/8
     */
    @ApiModelProperty(value = "平台")
    private String platform;

    /**
     * 客户端id
     *
     * @author luyanan
     * @since 2021/7/8
     */
    private String clientId;

    /**
     * 客户端密钥
     *
     * @since 2021/7/8
     */
    @ApiModelProperty(value = "客户端密钥")
    private String clientSecret;

    /**
     * 客户端id
     *
     * @since 2021/7/8
     */
    @ApiModelProperty(value = "客户端id")
    private String agentId;

    /**
     * token
     *
     * @since 2021/7/8
     */
    @ApiModelProperty(value = "token")
    private String token;


    /**
     * token失效时间
     *
     * @author luyanan
     * @since 2021/7/8
     */
    private int expire;


    /**
     * 附加信息
     *
     * @since 2021/7/8
     */
    @ApiModelProperty(value = "附加信息")
    private Map<String, Object> data;
}
