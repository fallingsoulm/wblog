package com.wblog.common.module.info.mq;

/**
 * @author luyanan
 * @since 2020/12/1
 * 文章消息模块静态常量
 **/
public interface ArticleMqConstant {

    String EXCHANGE = "business.article";

    /**
     * 文章上架使用的队列
     *
     * @author luyanan
     * @since 2020/12/1
     */
    String ENABLE_ARTICLE_QUEUE = "enable.article.queue";

    /**
     * 文章上架的路由键
     *
     * @author luyanan
     * @since 2020/12/1
     */
    String ENABLE_ARTICLE_ROUTING_KEY = "enable.article.routing.key";
}
