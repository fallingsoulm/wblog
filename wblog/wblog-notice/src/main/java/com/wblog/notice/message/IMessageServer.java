package com.wblog.notice.message;

import java.util.List;

/**
 * 消息发送
 *
 * @author luyanan
 * @since 2021/6/30
 **/
public interface IMessageServer {


    /**
     * 类型
     *
     * @return java.lang.String
     * @since 2021/6/30
     */
    Integer type();


    /**
     * 消息发送
     *
     * @param messageVos 消息体
     * @return void
     * @since 2021/6/30
     */
    void sendMessage(List<MessageVo> messageVos);
}



