package com.wblog.social.core;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * 社区账号信息
 *
 * @author luyanan
 * @since 2021/7/6
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SocialInfo {

    /**
     * 平台
     */
    @ApiModelProperty(value = "平台")
    private String platform;

    /**
     * 客户端id
     */
    @ApiModelProperty(value = "客户端id")
    private String clientId;

    /**
     * 客户端密钥
     */
    @ApiModelProperty(value = "客户端密钥")
    private String clientSecret;

    /**
     * 应用id(企业微信使用)
     */
    @ApiModelProperty(value = "应用id(企业微信使用)")
    private String agentId;

    /**
     * 回调地址
     */
    @ApiModelProperty(value = "回调地址")
    private String redirectUri;

    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id")
    private Long userId;

    /**
     * 附加数据
     *
     * @since 2021/7/6
     */
    @ApiModelProperty(value = "附加数据")
    private Map<String, Object> data;

}
