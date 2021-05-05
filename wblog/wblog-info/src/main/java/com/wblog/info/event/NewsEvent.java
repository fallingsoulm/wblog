package com.wblog.info.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author luyanan
 * @since 2020/12/22
 * <p>资讯事件</p>
 **/
public class NewsEvent extends ApplicationEvent {

    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public NewsEvent(Object source) {
        super(source);
    }
}
