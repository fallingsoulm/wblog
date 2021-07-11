package com.wblog.social.core.workwechat;

import cn.hutool.core.lang.Assert;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wblog.common.enums.ConstantEnum;
import com.wblog.common.exception.BusinessException;
import com.wblog.social.core.IPlatformInfoService;
import com.wblog.social.core.PlatformInfoVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 企业微信登录
 *
 * @author luyanan
 * @since 2021/7/8
 **/
@Slf4j
@Component
public class WorkWechatPlatformInfoService implements IPlatformInfoService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public PlatformInfoVo getToken(PlatformInfoVo platformInfoVo) {
        Assert.notBlank(platformInfoVo.getClientId(), "客户端id不能为空");
        Assert.notBlank(platformInfoVo.getClientSecret(), "客户端密钥不能为空");
        String url = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=" + platformInfoVo.getClientId() + "&corpsecret=" + platformInfoVo.getClientSecret();

        String result = HttpUtil.get(url);
        Integer errcode = JSON.parseObject(result).getInteger("errcode");
        if (null == errcode || errcode.intValue() != 0) {
            log.error("调用平台:{}:地址为:{}失败,返回异常为:{}", platformInfoVo.getPlatform(), url, result);
            throw new BusinessException("登录失败");
        }
        platformInfoVo.setToken(JSON.parseObject(result).getString("access_token"));
        platformInfoVo.setExpire(JSON.parseObject(result).getInteger("expires_in"));
        return platformInfoVo;
    }

    @Override
    public String[] support() {
        return new String[]{ConstantEnum.SOCIAL_PLATFORM_WORK_WECHAT_IT.getValueStr(), ConstantEnum.SOCIAL_PLATFORM_WORK_WECHAT_ADDRESS_BOOK.getValueStr()};
    }


    /**
     * 获取部门成员列表
     *
     * @param departmentId 部门id
     * @return java.util.List<java.util.Map < java.lang.String, java.lang.Object>>
     * @since 2021/7/11
     */
    public List<Map<String, Object>> getDepartmentList(Long departmentId, String token) {
        String url = "https://qyapi.weixin.qq.com/cgi-bin/user/simplelist?access_token=" + token +
                "&department_id=" + departmentId + "&fetch_child=1";
        ResponseEntity<WorkWechatResponse> entity = restTemplate.getForEntity(url, WorkWechatResponse.class);
        return entity.getBody().getData("userlist", new TypeReference<List<Map<String, Object>>>() {
        });
    }


    /**
     * 获取企业邀请二维码
     *
     * @return java.lang.String
     * @since 2021/7/11
     */
    public String getJoinQrcode(String accessToken) {
        String url = "https://qyapi.weixin.qq.com/cgi-bin/corp/get_join_qrcode?access_token=" + accessToken;
        return restTemplate.getForEntity(url, WorkWechatResponse.class).getBody().getData("join_qrcode", new TypeReference<String>() {
        });
    }

    /**
     * 发送消息
     *
     * @param msgContentVo 消息内容
     * @param token        token
     * @return void
     * @since 2021/7/11
     */
    public void sendMsg(MsgContentVo msgContentVo, String token) {

        Map<String, Object> params = new HashMap<>(10);
        MsgTypeEnum typeEnum = msgContentVo.getTypeEnum();

        params.put("touser", msgContentVo.getTouser());
        params.put("toparty", msgContentVo.getToparty());
        params.put("totag", msgContentVo.getTotag());
        params.put("msgtype", msgContentVo.getTypeEnum().getType());
        params.put("agentid", msgContentVo.getAgentid());
        params.put("safe", 0);
        params.put("enable_id_trans", 0);
        params.put("enable_duplicate_check", 0);
        params.put("duplicate_check_interval", 0);
        if (typeEnum.equals(MsgTypeEnum.TEXT)) {
            Map<String, Object> content = new HashMap<>();
            content.put("content", msgContentVo.getContent());
            params.put(typeEnum.getType(), content);
        }
        String url = "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=" + token;
        String body = HttpUtil.createPost(url).body(JSON.toJSONString(params)).execute().body();
        WorkWechatResponse workWechatResponse = JSON.parseObject(body, WorkWechatResponse.class);
        workWechatResponse.check();
    }


    /**
     * 企业微信接口调用返回
     *
     * @author luyanan
     * @since 2021/7/11
     */
    public static class WorkWechatResponse extends HashMap {
        /**
         * 返回码
         *
         * @author luyanan
         * @since 2021/7/11
         */
        private Integer errcode;

        /**
         * 对返回码的文本描述内容
         *
         * @author luyanan
         * @since 2021/7/11
         */
        private String errmsg;


        public Integer getErrcode() {
            return (Integer) this.get("errcode");
        }

        public String getErrmsg() {
            return (String) this.get("errmsg");
        }

        /**
         * 获取数据
         *
         * @param field         字段
         * @param typeReference
         * @return T
         * @since 2021/7/11
         */
        public <T> T getData(String field, TypeReference typeReference) {
            check();
            return JSON.parseObject(JSON.toJSONString(this)).getObject(field, typeReference);
        }

        public void check() {
            if (!this.getErrcode().equals(0)) {
                log.error("获取数据异常,返回数据为:{}", JSON.toJSONString(this));
                throw new BusinessException("获取数据异常:errcode" + this.getErrcode() + ",errmsg:" + this.getErrmsg());
            }
        }

    }

    public enum MsgTypeEnum {


        // 文本 其中text参数的content字段可以支持换行、以及A标签，即可打开自定义的网页（可参考以上示例代码）(注意：换行符请用转义过的\n)
        TEXT("text"),

        ;

        public String getType() {
            return type;
        }

        private String type;


        MsgTypeEnum(String type) {
            this.type = type;
        }
    }

    /**
     * 消息内容
     *
     * @author luyanan
     * @since 2021/7/11
     */
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MsgContentVo {
        /**
         * 指定接收消息的成员，成员ID列表（多个接收者用‘|’分隔，最多支持1000个）。
         * 特殊情况：指定为”@all”，则向该企业应用的全部成员发送
         *
         * @author luyanan
         * @since 2021/7/11
         */
        private String touser;

        /**
         * 指定接收消息的部门，部门ID列表，多个接收者用‘|’分隔，最多支持100个。
         * 当touser为”@all”时忽略本参数
         *
         * @author luyanan
         * @since 2021/7/11
         */
        private String toparty;

        /**
         * 指定接收消息的标签，标签ID列表，多个接收者用‘|’分隔，最多支持100个。
         * 当touser为”@all”时忽略本参数
         *
         * @author luyanan
         * @since 2021/7/11
         */
        private String totag;

        /**
         * 消息类型
         *
         * @author luyanan
         * @since 2021/7/11
         */
        private MsgTypeEnum typeEnum;

        /**
         * 企业应用的id，
         *
         * @author luyanan
         * @since 2021/7/11
         */
        private String agentid;

        /**
         * 内容
         *
         * @author luyanan
         * @since 2021/7/11
         */
        private String content;
    }
}
