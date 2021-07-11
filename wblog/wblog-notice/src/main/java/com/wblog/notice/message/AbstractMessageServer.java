package com.wblog.notice.message;

import java.util.*;

/**
 * 消息推送服务抽象类
 *
 * @author luyanan
 * @since 2021/7/2
 **/
public abstract class AbstractMessageServer implements IMessageServer {
    protected String getContent(MessageVo messageVo) {

        StringBuffer sb = new StringBuffer();
        sb.append("标题:").append(messageVo.getTitle()).append("\n")
                .append("内容:").append(messageVo.getContent());
        return sb.toString();
    }

    @Override
    public void sendMessage(List<MessageVo> messageVos) {
        Map<String, List<MessageVo>> messageMap = new HashMap<>();
        messageVos.stream().forEach(ms -> {
            String[] split =
                    ms.getInvokeTarget().split(",");
            for (String s : split) {
                final List<MessageVo> voList = Optional.ofNullable(messageMap.get(s)).orElse(new ArrayList<>());
                voList.add(ms);
                messageMap.put(s, voList);
            }
        });
        sendMessage(messageMap);
    }

    /**
     * 发送消息
     *
     * @param messageMap
     * @return void
     * @since 2021/7/2
     */
    protected abstract void sendMessage(Map<String, List<MessageVo>> messageMap);
}
