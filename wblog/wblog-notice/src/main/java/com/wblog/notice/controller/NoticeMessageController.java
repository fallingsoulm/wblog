
package com.wblog.notice.controller;

import com.wblog.notice.service.IMNoticeMessageService;
import io.github.fallingsoulm.easy.archetype.framework.page.RespEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 消息发送
 *
 * @author luyanan
 * @since 2021/6/30
 **/
@Api(description = "消息发送")
@RestController
@RequestMapping("notice")
public class NoticeMessageController {


    @Autowired
    private IMNoticeMessageService imNoticeMessageService;

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
    @ApiOperation(value = "发送消息")
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = "send/message")
    public RespEntity sendMessage(@RequestParam("token") String token,
                                  @RequestParam(value = "title", required = false) String title,
                                  @RequestParam("content") String content,
                                  @RequestParam(value = "template", defaultValue = "html") String template) {
        imNoticeMessageService.sendMessage(token, title, content, template);
        return RespEntity.success();
    }


}
