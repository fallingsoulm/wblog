package com.wblog.info.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.FileUtil;
import com.apes.hub.api.enums.ConstantEnum;
import com.apes.hub.core.page.MybatisPlusUtils;
import com.apes.hub.core.redis.RedisKeyEnums;
import com.apes.hub.info.config.BlogConfigProperties;
import com.apes.hub.info.entity.ArticleEntity;
import com.apes.hub.info.manage.IArticleManage;
import com.apes.hub.info.manage.INewsManage;
import com.apes.hub.info.service.ISiteMapService;
import com.redfin.sitemapgenerator.W3CDateFormat;
import com.redfin.sitemapgenerator.WebSitemapGenerator;
import com.redfin.sitemapgenerator.WebSitemapUrl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.File;
import java.net.MalformedURLException;
import java.util.Date;

/**
 * @author luyanan
 * @since 2020/7/24
 * <p></p>
 **/
@Slf4j
@Service
public class SiteMapServiceImpl implements ISiteMapService {


    @Autowired
    private BlogConfigProperties blogConfigProperties;


    @Autowired
    private MybatisPlusUtils plusUtils;

    private static final W3CDateFormat DATE_FORMAT = new W3CDateFormat(W3CDateFormat.Pattern.DAY);

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private IArticleManage articleManage;

    @Autowired
    private INewsManage newsManage;

    @Override

    public void siteMap() {
        // 先清空文件夹
        File sitemapDir = new File(blogConfigProperties.getSiteMapPath());
        if (sitemapDir.exists()) {
            //空文件夹
            FileUtil.clean(sitemapDir);
        } else {
            //新建文件夹
            sitemapDir.mkdir();
        }
        String key = RedisKeyEnums.SITEMAP_URL.getKey();
        redisTemplate.delete(key);

        BoundListOperations ops = redisTemplate.boundListOps(key);
        // 文章
        plusUtils.bigDataList(ArticleEntity.builder().status(ConstantEnum.ARTICLE_STATUS_ENABLE.getValue()).build(),
                articleManage, data -> {
                    if (CollectionUtil.isNotEmpty(data)) {
                        data
                                .stream()
                                .forEach(a -> ops.leftPush(blogConfigProperties.getArticleInfoUrl() + a.getId() + ".html"));
                    }
                }
        );

        // 资讯 ;
        plusUtils.bigDataList(null, newsManage, data -> {
                    if (CollectionUtil.isNotEmpty(data)) {
                        data.forEach(a -> ops.leftPush(blogConfigProperties.getNews().getNewsInfoUrl() + a.getId() + ".html"));
                    }


                }
        );


        log.info("生成sitemap的索引总数为:" + ops.size());
        try {

            WebSitemapGenerator generator = WebSitemapGenerator.builder(blogConfigProperties.getBusinessDomain() + "sitemap/", sitemapDir)
                    .fileNamePrefix("sitemap")
                    .dateFormat(DATE_FORMAT)
                    .build();

            while (true) {
                Object leftPop = ops.leftPop();
                if (null == leftPop) {
                    break;
                }
                WebSitemapUrl indexSitemap = new WebSitemapUrl
                        .Options(leftPop.toString())
                        .lastMod(new Date()).build();
                generator.addUrl(indexSitemap);

            }
            generator.write();
            generator.writeSitemapsWithIndex(new File(blogConfigProperties.getSiteMapPath() + "/index.xml"));

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        log.info("生成sitemap文件成功");
    }

    @Override
    public String sitemap(String fileName) {
        String string = FileUtil.readString(new File(blogConfigProperties.getSiteMapPath() + fileName), "utf-8");
        return string;
    }


}
