package com.wblog.search.sitemap;

/**
 * sitemap提交
 *
 * @author luyanan
 * @since 2021/8/13
 **/
public interface SubmitSitemap {


    /**
     * 类型
     *
     * @return java.lang.String
     * @since 2021/8/13
     */
    String type();


    /**
     * 提交
     *
     * @return void
     * @since 2021/8/13
     */
    void submit();


    /**
     * 剩余条数
     *
     * @return java.lang.Long
     * @since 2021/8/14
     */
    Long remain();
}
