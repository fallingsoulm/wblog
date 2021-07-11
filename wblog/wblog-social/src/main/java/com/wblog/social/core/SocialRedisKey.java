package com.wblog.social.core;

import io.github.fallingsoulm.easy.archetype.data.redis.IRedisKeyEnums;

/**
 * Platform redis key
 *
 * @author luyanan
 * @since 2021/7/8
 **/
public enum SocialRedisKey implements IRedisKeyEnums {
    /**
     * 第三方 平台token
     *
     * @author luyanan
     * @since 2021/7/8
     */
    PLATFORM_TOKEN("PLATFORM_TOKEN_{0}");

    private String key;


    SocialRedisKey(String key) {
        this.key = key;
    }

    @Override
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

}
