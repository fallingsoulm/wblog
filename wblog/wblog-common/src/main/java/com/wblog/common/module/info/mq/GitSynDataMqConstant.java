package com.wblog.common.module.info.mq;

/**
 * @author luyanan
 * @since 2020/11/28
 * git 消息服务
 **/

public interface GitSynDataMqConstant {

    /**
     * 交换机
     *
     * @author luyanan
     * @since 2020/11/28
     */
    String EXCHANGE = "business.gitsyn.data";

    /**
     * 同步的队列
     *
     * @author luyanan
     * @since 2020/11/28
     */
    String SYN_QUEUE = "syn_queue";

    /**
     * 同步的路由键
     *
     * @author luyanan
     * @since 2020/11/28
     */
    String SYN_ROUTING_KEY = "ryn_routing_queue";
}
