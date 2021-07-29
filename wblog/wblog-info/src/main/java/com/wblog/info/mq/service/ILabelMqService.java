package com.wblog.info.mq.service;

/**
 * @author luyanan
 * @since 2020/11/30
 * 标签的mq 异步处理类
 **/
public interface ILabelMqService {

    /**
     * 增加标签
     *
     * @param id       文章id
     * @param classify 分类
     * @author luyanan
     * @since 2020/11/30
     */
    void articleAddLabel(Long id, Integer classify);

    /**
     * 添加标签,给文章分配标签
     *
     * @param labelId
     * @author luyanan
     * @since 2020/11/30
     */
    void addLabel(Long labelId);

}
