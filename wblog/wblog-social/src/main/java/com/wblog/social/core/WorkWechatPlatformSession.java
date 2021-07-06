package com.wblog.social.core;

import com.wblog.common.enums.ConstantEnum;

/**
 * 企业微信平台session
 *
 * @author luyanan
 * @since 2021/7/6
 **/
public class WorkWechatPlatformSession implements ISocialPlatformSession {
    @Override
    public String platform() {
        return ConstantEnum.SOCIAL_PLATFORM_WORK_WECHAT.getValue().toString();
    }

    @Override
    public SocialSession createSession(SocialInfo socialInfo) {
        return null;
    }
}
