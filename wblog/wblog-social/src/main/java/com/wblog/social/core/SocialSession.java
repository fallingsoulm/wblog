package com.wblog.social.core;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 社区的session
 *
 * @author luyanan
 * @since 2021/7/6
 **/
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SocialSession {


    /**
     * 用户id
     *
     * @since 2021/7/6
     */
    @ApiModelProperty(value = "用户id")
    private Long userId;


    /**
     * 平台
     *
     * @since 2021/7/6
     */
    @ApiModelProperty(value = "平台")
    private String platform;
    /**
     * session
     *
     * @since 2021/7/6
     */
    @ApiModelProperty(value = "session")
    private String session;
    /**
     * 失效时间
     *
     * @since 2021/7/6
     */
    @ApiModelProperty(value = "失效时间")
    private int expireTime;

    /**
     * 状态 0 正常, 1:失效
     *
     * @since 2021/7/6
     */
    @ApiModelProperty(value = "状态 0 正常, 1:失效")
    private int status;
}
