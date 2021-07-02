package com.wblog.notice.message;

import cn.hutool.core.util.StrUtil;
import com.wblog.common.enums.ConstantEnum;
import com.wblog.common.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 消息发送模板方法
 *
 * @author luyanan
 * @since 2021/6/30
 **/
@Component
public class MessageTemplate {

    @Autowired
    private MessageTypeRegister messageTypeRegister;

    @Autowired
    private MessageSchedule messageSchedule;

    /**
     * 消息发送
     *
     * @param messageVo 消息体
     * @return void
     * @since 2021/6/30
     */
    public void sendMessage(MessageVo... messageVos) {
        if (null == messageVos || messageVos.length == 0) {
            throw new BusinessException("消息体不能为空");
        }
        if (Arrays.stream(messageVos)
                .filter(a -> StrUtil.isBlank(a.getTitle()))
                .findFirst().isPresent()) {
            throw new BusinessException("消息标题不能为空");
        }

        if (Arrays.stream(messageVos)
                .filter(a -> StrUtil.isBlank(a.getContent()))
                .findFirst().isPresent()) {
            throw new BusinessException("消息内容不能为空");
        }

        Arrays.stream(messageVos).collect(Collectors.groupingBy(MessageVo::getSendType)).entrySet().forEach(entry -> {
            final Integer sendType = entry.getKey();
            if (sendType.equals(ConstantEnum.NOTICE_SEND_TYPE_NOW.getValue())) {
                // 立即发送
                messageTypeRegister.sendMessage(entry.getValue());
            } else if (sendType.equals(ConstantEnum.NOTICE_SEND_TYPE_TIMING.getValue())) {
                // 定时发送
                // 将任务存放到定时任务中
                messageSchedule.addScheduledMessage(entry.getValue());
            }
        });


    }

}
