package com.wblog.info.mq.service;

/**
 * @author luyanan
 * @since 2020/11/30
 * 标签的mq 异步处理类
 **/
public interface ILabelMqService {

    /**
     * 文章上架增加标签
     *
     * @param articleId 文章id
     * @author luyanan
     * @since 2020/11/30
     */
    void articleAddLabel(Long articleId);

    /**
     * 添加标签,给文章分配标签
     *
     * @param labelId
     * @author luyanan
     * @since 2020/11/30
     */
    void addLabel(Long labelId);

}
