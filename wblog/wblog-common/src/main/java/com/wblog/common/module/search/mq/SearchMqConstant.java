package com.wblog.common.module.search.mq;

/**
 * @author luyanan
 * @since 2020/12/21
 * <p>搜索的MQ的静态常量</p>
 **/
public interface SearchMqConstant {


    /***************文章**********************/
    String SEARCH_ARTICLE_EXCHANGE = "search.article.exchange";


    String SEARCH_ARTICLE_SAVE_QUEUE = "search.article.save.queue";
    String SEARCH_ARTICLE_SAVE_ROUTINGKEY = "search.article.save.routingKey";

    String SEARCH_ARTICLE_UPDATE_QUEUE = "search.article.update.queue";
    String SEARCH_ARTICLE_UPDATE_ROUTINGKEY = "search.article.update.routingKey";

    String SEARCH_ARTICLE_DELETE_QUEUE = "search.article.delete.queue";
    String SEARCH_ARTICLE_DELETE_ROUTINGKEY = "search.article.delete.RoutingKey";


    /**
     * <p>检查文章是否被删除的队列</p>
     *
     * @author luyanan
     * @since 2021/1/2
     */
    String SEARCH_ARTICLE_IS_DELETE_QUEUE = "search.article.is.delete.queue";

    /**
     * <p>检查文章是否被删除的路由键</p>
     *
     * @author luyanan
     * @since 2021/1/2
     */
    String SEARCH_ARTICLE_IS_DELETE_ROUTING_KEY = "search.article.is.delete.routingKey";
    /*************资讯****************/
    String SEARCH_NEWS_EXCHANGE = "search.news.exchange";

    String SEARCH_NEWS_SAVE_QUEUE = "search.news.save.queue";
    String SEARCH_NEWS_SAVE_ROUTINGKEY = "search.news.save.routingKey";

    String SEARCH_NEWS_UPDATE_QUEUE = "search.news.update.queue";
    String SEARCH_NEWS_UPDATE_ROUTINGKEY = "search.news.update.routingKey";

    String SEARCH_NEWS_DELETE_QUEUE = "search.news.delete.queue";
    String SEARCH_NEWS_DELETE_ROUTINGKEY = "search.news.delete.RoutingKey";


    /**
     * <p>检查资讯是否被删除的队列</p>
     *
     * @author luyanan
     * @since 2021/1/2
     */
    String SEARCH_NEWS_IS_DELETE_QUEUE = "search.news.is.delete.queue";

    /**
     * <p>检查资讯是否被删除的路由键</p>
     *
     * @author luyanan
     * @since 2021/1/2
     */
    String SEARCH_NEWS_IS_DELETE_ROUTING_KEY = "search.news.is.delete.routingKey";

}
