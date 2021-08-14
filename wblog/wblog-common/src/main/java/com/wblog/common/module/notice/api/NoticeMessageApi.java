package com.wblog.common.module.notice.api;

import com.wblog.common.constant.ApplicationNameConstants;
import io.github.fallingsoulm.easy.archetype.framework.page.RespEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 消息提醒
 *
 * @author luyanan
 * @since 2021/8/1
 **/
@FeignClient(ApplicationNameConstants.NOTICE)
public interface NoticeMessageApi {

    /**
     * 发送消息
     *
     * @param token    token
     * @param title
     * @param content
     * @param template
     * @return io.github.fallingsoulm.easy.archetype.framework.page.RespEntity
     * @since 2021/7/1
     */
    @RequestMapping(method = { RequestMethod.POST}, value = "notice/send/message")
    RespEntity sendMessage(@RequestParam("token") String token,
                           @RequestParam(value = "title", required = false) String title,
                           @RequestParam("content") String content,
                           @RequestParam(value = "template", defaultValue = "html") String template);
}
