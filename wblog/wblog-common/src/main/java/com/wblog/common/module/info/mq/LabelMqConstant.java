package com.wblog.common.module.info.mq;

/**
 * @author luyanan
 * @since 2020/11/30
 * 标签消息 常量
 **/
public interface LabelMqConstant {


    String EXCHANGE = "business.label";

    /**
     * 文章上架的时候增加标签
     *
     * @author luyanan
     * @since 2020/11/30
     */
    String ARTICLE_ADD_LABEL_QUEUE = "article.add.label.queue";

    /**
     * 文章上架增加标签的路由键
     *
     * @author luyanan
     * @since 2020/11/30
     */
    String ARTICLE_ADD_LABEL_ROUTING_KEY = "article.add.label.routing.key";


    /**
     * 标签添加
     *
     * @author luyanan
     * @since 2020/11/30
     */
    String LABEL_ADD_QUEUE = "label.add.queue";

    String LABEL_ADD_ROUTING_KEY = "label.add.routing.key";

}
