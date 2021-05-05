package com.wblog.info.mq.service;

import java.util.List;

/**
 * @author luyanan
 * @since 2020/12/1
 * 文章MQ分析
 **/
public interface IArticleMqService {


    /**
     * 上架文章
     *
     * @param articleIds
     * @author luyanan
     * @since 2020/12/1
     */
    void enableArticle(List<Long> articleIds);






}
