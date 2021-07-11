package com.wblog.notice.message;

import com.wblog.common.enums.ConstantEnum;
import org.springframework.stereotype.Component;

/**
 * 钉钉机器人 消息服务
 *
 * @author luyanan
 * @since 2021/7/11
 **/
@Component
public class DingtalkRobotMessageServer extends WorkWechatRobotMessageServer {

    @Override
    public Integer type() {
        return ConstantEnum.NOTICE_MESSAGE_DINGTALK.getValue();
    }
}
