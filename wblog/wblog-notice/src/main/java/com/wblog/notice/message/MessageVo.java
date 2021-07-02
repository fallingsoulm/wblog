package com.wblog.notice.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 消息
 *
 * @author luyanan
 * @since 2021/6/30
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageVo {

    /**
     * 消息类型
     *
     * @author luyanan
     * @since 2021/6/30
     */
    private Integer messageType;
    /**
     * 标题
     *
     * @author luyanan
     * @since 2021/6/30
     */
    private String title;


    /**
     * 内容
     *
     * @author luyanan
     * @since 2021/6/30
     */
    private String content;


    /**
     * 调用对象
     *
     * @author luyanan
     * @since 2021/6/30
     */
    private String invokeTarget;


    /**
     * 发送类型,立即发送或者定时发送
     *
     * @author luyanan
     * @since 2021/6/30
     */
    private Integer sendType;


    /**
     * 发送时间
     *
     * @author luyanan
     * @since 2021/6/30
     */
    private String sendTime;

    /**
     * 模板类型 html/json
     *
     * @author luyanan
     * @since 2021/7/1
     */
    private String template;
    /**
     * 消息模板id
     *
     * @author luyanan
     * @since 2021/7/2
     */
    private Long templateId;
}
