package com.wblog.common.redis;

import io.github.fallingsoulm.easy.archetype.data.redis.IRedisKeyEnums;

/**
 * redis key 的枚举类
 *
 * @author luyanan
 * @since 2021/5/14
 **/
public enum RedisKeyEnums implements IRedisKeyEnums {
    /**
     * <p>文章访问数量</p>
     *
     * @author luyanan
     * @since 2020/6/16
     */
    ARTICLE_VIEW("ARTICLE_VIEW"),

    /**
     * <p>gitee 搜索关键字的仓库的总页数</p>
     *
     * @author luyanan
     * @since 2020/6/22
     */
    GITEE_SEARCH_REPOSITORIES_TOTAL_PAGE("GITEE_SEARCH_REPOSITORIES_TOTAL_PAGE_{0}"),

    /**
     * <p>git 同步列表</p>
     *
     * @author luyanan
     * @since 2020/6/26
     */
    GIT_SYN_DATA_LIST("GIT_SYN_DATA_LIST"),


    /**
     * <p>慢SQL记录</p>
     *
     * @author luyanan
     * @since 2020/6/27
     */
    SLOW_SQL_LIST("SLOW_SQL_LIST"),

    /**
     * <p>验证码</p>
     *
     * @author luyanan
     * @since 2020/9/9
     */
    CAPTCHA_CODE_KEY("captcha_codes:{0}"),

    /**
     * sitemap
     *
     * @author luyanan
     * @since 2020/11/23
     */
    SITEMAP_URL("sitemap_url"),


    /**
     * 随机头图
     *
     * @author luyanan
     * @since 2021/7/24
     */
    INFO_RANDOM_IMAGE("INFO_RANDOM_IMAGE"),

    /**
     * 资讯id黑名单
     *
     * @author luyanan
     * @since 2021/7/28
     */
    NEWS_ID_BLACK_LIST("NEWS_ID_BLACK_LIST"),


    /**
     * sitemap提交
     *
     * @author luyanan
     * @since 2021/8/13
     */
    SUBMIT_SITEMAP("SUBMIT_SITEMAP");

    private String key;


    RedisKeyEnums(String key) {
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
