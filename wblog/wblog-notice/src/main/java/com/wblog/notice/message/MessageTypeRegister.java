package com.wblog.notice.message;

import cn.hutool.core.collection.CollectionUtil;
import com.wblog.common.exception.BusinessException;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 消息类型注册
 *
 * @author luyanan
 * @since 2021/6/30
 **/
@Component
public class MessageTypeRegister {


    @Autowired
    private ObjectProvider<IMessageServer> objectProvider;


    /**
     * 根据消息类型获取消息服务端
     *
     * @param type
     * @return com.wblog.notice.message.IMessageServer
     * @since 2021/6/30
     */
    public IMessageServer getServer(Integer type) {
        return objectProvider
                .stream()
                .filter(a -> a.type().equals(type))
                .findFirst()
                .orElseThrow(() -> new BusinessException("未知的类型:" + type));
    }


    /**
     * 消息发送
     *
     * @param messageVos
     * @return void
     * @since 2021/6/30
     */
    public void sendMessage(List<MessageVo> messageVos) {
        if (CollectionUtil.isEmpty(messageVos)) {
            return;
        }
        messageVos
                .stream()
                .collect(Collectors.groupingBy(MessageVo::getMessageType))
                .entrySet()
                .stream()
                .forEach(entry -> {
                    Integer messageType = entry.getKey();
                    if (CollectionUtil.isEmpty(entry.getValue())) {
                        return;
                    }
                    getServer(messageType).sendMessage(entry.getValue());
                });
    }
}
