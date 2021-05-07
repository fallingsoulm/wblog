package com.wblog.info.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author luyanan
 * @since 2020/5/27
 * <p>博客的配置文件</p>
 **/
@Data
@Component
@ConfigurationProperties(prefix = BlogConfigProperties.prefix)
public class BlogConfigProperties {


    public final static String prefix = "blog";

    /**
     * <p>git同步文件存放的地址</p>
     *
     * @return {@link }
     * @author luyanan
     * @since 2020/6/10
     */
    private String gitSynPath = "/data/git/";

    private String siteMapPath = "/data/sitemap/";


    /**
     * 随机图片的目录地址
     *
     * @since 2021/5/7
     */

    private String randomImagePath;
    /**
     * <p>文件内容的url</p>
     *
     * @author luyanan
     * @since 2020/6/16
     */
    private String articleInfoUrl;


    /**
     * <p>业务系统域名</p>
     *
     * @author luyanan
     * @since 2020/6/16
     */
    private String businessDomain;


    /**
     * <p>gitee的私人令牌,访问OpenAPI使用</p>
     *
     * @author luyanan
     * @since 2020/6/22
     */
    private String giteeAccessToken;


    private News news;

    /**
     * <p>资讯配置文件</p>
     *
     * @author luyanan
     * @since 2020/7/13
     */
    @Data
    public static class News {

        /**
         * <p>抓取的最大的页数</p>
         *
         * @author luyanan
         * @since 2020/7/13
         */
        private int maxPageSize;

        /**
         * <p>停顿时间</p>
         *
         * @author luyanan
         * @since 2020/7/13
         */
        private Long sleepTime;


        /**
         * <p>资讯内容的url</p>
         *
         * @author luyanan
         * @since 2020/7/27
         */
        private String newsInfoUrl;
    }
}
