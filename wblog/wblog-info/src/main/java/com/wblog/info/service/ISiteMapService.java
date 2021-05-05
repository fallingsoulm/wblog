package com.wblog.info.service;

/**
 * @author luyanan
 * @since 2020/7/24
 * <p>控制层</p>
 **/
public interface ISiteMapService {


    /**
     * <p>生成SiteMap 文件</p>
     *
     * @author luyanan
     * @since 2020/7/24
     */
    void siteMap();


    /**
     * <p>根据文件名查询文件内容</p>
     *
     * @param fileName
     * @return {@link String}
     * @author luyanan
     * @since 2020/9/17
     */
    String sitemap(String fileName);

}
