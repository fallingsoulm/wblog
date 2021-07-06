package com.wblog.social.core;

/**
 * 获取session接口
 *
 * @author luyanan
 * @since 2021/7/6
 **/
public interface ISocialPlatformSession {

    /**
     * 平台
     *
     * @return java.lang.String
     * @since 2021/7/6
     */
    String platform();

    /**
     * 平台
     *
     * @param socialInfo 第三方平台信息
     * @return com.wblog.social.core.SocialSession
     * @since 2021/7/6
     */
    SocialSession createSession(SocialInfo socialInfo);

}
