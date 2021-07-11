package com.wblog.notice.message;

import cn.hutool.core.text.StrBuilder;
import com.wblog.common.enums.ConstantEnum;
import com.wblog.social.core.PlatformInfoVo;
import com.wblog.social.core.PlatformTokenManage;
import com.wblog.social.core.workwechat.WorkWechatPlatformInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 微信企业发送应用通知
 *
 * @author luyanan
 * @since 2021/7/8
 **/
@Component
public class WorkWechatMessageServer extends AbstractMessageServer {

    @Autowired
    private PlatformTokenManage platformTokenManage;

    @Autowired
    private WorkWechatPlatformInfoService workWechatPlatformInfoService;

    @Override
    protected void sendMessage(Map<String, List<MessageVo>> messageMap) {
        PlatformInfoVo token = platformTokenManage.getToken(ConstantEnum.SOCIAL_PLATFORM_WORK_WECHAT_IT.getValueStr());

        for (Map.Entry<String, List<MessageVo>> entry : messageMap.entrySet()) {
            String touser = entry.getKey();
            for (MessageVo messageVo : entry.getValue()) {
                workWechatPlatformInfoService.sendMsg(WorkWechatPlatformInfoService.MsgContentVo
                        .builder()
                        .agentid(token.getAgentId())
                        .content(getContent(messageVo))
                        .typeEnum(WorkWechatPlatformInfoService.MsgTypeEnum.TEXT)
                        .touser(touser)
                        .build(), token.getToken());
            }
        }
    }


    @Override
    public Integer type() {
        return ConstantEnum.NOTICE_MESSAGE_WORK_WEIXIN.getValue();
    }
}
