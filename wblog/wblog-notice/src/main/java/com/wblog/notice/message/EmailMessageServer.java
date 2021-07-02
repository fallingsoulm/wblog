package com.wblog.notice.message;

import com.wblog.common.enums.ConstantEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 邮件消息服务端
 *
 * @author luyanan
 * @since 2021/6/30
 **/
@Component
public class EmailMessageServer extends AbstractMessageServer {
    @Autowired
    private MailSender mailSender;
    @Value("${spring.mail.username}")
    private String from;

    @Override
    public Integer type() {
        return ConstantEnum.NOTICE_MESSAGE_EMAIL.getValue();
    }

    @Override
    protected void sendMessage(Map<String, List<MessageVo>> messageMap) {

        for (Map.Entry<String, List<MessageVo>> entry : messageMap.entrySet()) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(from);
            message.setTo(entry.getKey());
            message.setSubject(getTitle(entry.getValue()));
            message.setText(getContent(entry.getValue()));
            mailSender.send(message);
        }
    }

    /**
     * 标题
     *
     * @param messageVos
     * @return java.lang.String
     * @since 2021/7/2
     */
    private String getTitle(List<MessageVo> messageVos) {
        return messageVos.stream().map(MessageVo::getTitle).collect(Collectors.joining(","));
    }

    /**
     * 邮件内容
     *
     * @param messageVos
     * @return java.lang.String
     * @since 2021/7/2
     */
    private String getContent(List<MessageVo> messageVos) {
        return messageVos.stream().map(ms -> ms.getTitle() + "\n" + ms.getContent() + "\n"+"####################################" + "\n").collect(Collectors.joining());
    }

}
