package com.wblog.system.controller;

import com.wblog.system.enums.SystemRedisKeyEnums;
import io.github.fallingsoulm.easy.archetype.data.logger.annotation.IgnoreLogger;
import io.github.fallingsoulm.easy.archetype.framework.page.RespEntity;
import io.github.fallingsoulm.easy.archetype.security.annotation.IgnoringLogin;
import io.github.fallingsoulm.easy.archetype.security.validatecode.ValidateCodeTemplate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.UUID;

/**
 * 验证码控制层
 *
 * @author luyanan
 * @since 2021/2/4
 **/
@Api(tags = "验证码")
@IgnoringLogin
@RestController
public class CaptchaController {


    @Autowired(required = false)
    private ValidateCodeTemplate validateCodeTemplate;

    @ApiOperation(value = "生成验证码")
    @IgnoreLogger(type = IgnoreLogger.IgnoreLoggerType.RESULT)
    @GetMapping("captchaImage")
    public RespEntity getCode() {
        String uuid = UUID.randomUUID().toString();
        // 创建验证码
        Map<String, Object> map =
                validateCodeTemplate.create(uuid, SystemRedisKeyEnums.CAPTCHA_CODES.getExpire());

        return RespEntity.success(map);
    }

}
