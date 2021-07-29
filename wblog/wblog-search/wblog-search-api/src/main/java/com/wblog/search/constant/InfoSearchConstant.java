package com.wblog.search.constant;

/**
 * 信息搜索 的静态类
 *
 * @author luyanan
 * @since 2021/7/18
 **/
public interface InfoSearchConstant {


    /*********************信息MQ*******************/


    /**
     * 交换机
     *
     * @author luyanan
     * @since 2021/7/18
     */
    String INFO_MQ_EXCHANGE = "search.info.exchange";


    /**
     * 修改队列
     *
     * @author luyanan
     * @since 2021/7/18
     */
    String INFO_MQ_UPDATE_QUEUE = "search.info.update.queue";


    /**
     * 删除队列
     *
     * @author luyanan
     * @since 2021/7/18
     */
    String INFO_MQ_DELETE_QUEUE = "search.info.delete.queue";


    /**
     * 添加|修改的路由键
     *
     * @author luyanan
     * @since 2021/7/18
     */
    String INFO_MQ_UPDATE_ROUTINGKEY = "search.info.routingkey.update";


    /**
     * 删除的路由键
     *
     * @author luyanan
     * @since 2021/7/18
     */
    String INFO_MQ_DELETE_ROUTINGKEY = "search.info.routingkey.delete";

}

