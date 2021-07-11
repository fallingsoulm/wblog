package com.wblog.social.core;

/**
 * 第三方平台登录
 *
 * @author luyanan
 * @since 2021/7/8
 **/
public interface IPlatformInfoService {


    /**
     * 支持的平台
     *
     * @return java.lang.String[]
     * @since 2021/7/8
     */
    String[] support();


    /**
     * 获取token
     *
     * @param platformInfoVo 平台唯一标识
     * @return com.wblog.social.core.PlatformInfoVo
     * @since 2021/7/8
     */
    PlatformInfoVo getToken(PlatformInfoVo platformInfoVo);




}
