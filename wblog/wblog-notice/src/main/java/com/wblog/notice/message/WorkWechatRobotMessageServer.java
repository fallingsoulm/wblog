package com.wblog.notice.message;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.wblog.common.enums.ConstantEnum;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 企业微信机器人消息服务
 *
 * @author luyanan
 * @since 2021/7/11
 **/
@Component
public class WorkWechatRobotMessageServer extends AbstractMessageServer {
    @Override
    protected void sendMessage(Map<String, List<MessageVo>> messageMap) {

        for (Map.Entry<String, List<MessageVo>> entry : messageMap.entrySet()) {
            String key = entry.getKey();
            for (MessageVo messageVo : entry.getValue()) {
                Map<String, Object> params = new HashMap<>();
                params.put("msgtype", "text");
                Map<String, Object> content = new HashMap<>();
                content.put("content", getContent(messageVo));
                params.put("text", content);
                String body = HttpUtil.createPost(key)
                        .body(JSON.toJSONString(params))
                        .execute().body();

            }

        }
    }

    @Override
    public Integer type() {
        return ConstantEnum.NOTICE_MESSAGE_WORK_WEIXIN_ROBOT.getValue();
    }
}
