package com.wblog.social.core;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Assert;
import com.alibaba.fastjson.JSON;
import com.wblog.common.exception.BusinessException;
import com.wblog.social.config.SocialProperties;
import io.github.fallingsoulm.easy.archetype.data.redis.RedisKeyGenerator;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 第三方平台token管理
 *
 * @author luyanan
 * @since 2021/7/8
 **/
@Component
public class PlatformTokenManage {


    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ObjectProvider<IPlatformInfoService> platformInfoLoginObjectProvider;

    @Autowired
    private RedisKeyGenerator redisKeyGenerator;


    @Autowired
    private SocialProperties socialProperties;
    /**
     * key: uuid
     * value 平台
     *
     * @author luyanan
     * @since 2021/7/8
     */
    private Map<String, IPlatformInfoService> platformInfoServiceMap = new HashMap<>();

    /**
     * key: uuid
     * value:PlatformInfoVo
     *
     * @author luyanan
     * @since 2021/7/8
     */
    private Map<String, PlatformInfoVo> platformInfoVoMap = new HashMap<>();

    @PostConstruct
    private void init() {
        List<PlatformInfoVo> platformInfoVos = socialProperties.getPlatform();

        platformInfoLoginObjectProvider.stream().forEach(a -> {
            for (String uuid : a.support()) {
                PlatformInfoVo platformInfoVo = platformInfoVos.stream().filter(p -> p.getUuid().equals(uuid)).findFirst().orElseThrow(() -> new BusinessException("未找见[" + uuid + "]的配置"));
                platformInfoVoMap.put(uuid, platformInfoVo);
                platformInfoServiceMap.put(uuid, a);
            }
        });


    }

    /**
     * 根据平台获取token
     *
     * @param uuid 平台唯一标识
     * @return com.wblog.social.core.PlatformInfoVo
     * @since 2021/7/8
     */
    public PlatformInfoVo getToken(String uuid) {
        Assert.notBlank(uuid, "平台唯一标识不能为空");
        IPlatformInfoService iPlatformInfoService = platformInfoServiceMap.get(uuid);
        PlatformInfoVo platformInfoVo = platformInfoVoMap.get(uuid);

        String redisKey = redisKeyGenerator.generate(SocialRedisKey.PLATFORM_TOKEN, platformInfoVo.getUuid());
        if (redisTemplate.hasKey(redisKey)) {
            return JSON.parseObject(JSON.toJSONString(redisTemplate.opsForValue().get(redisKey)), PlatformInfoVo.class);
        }
        PlatformInfoVo token = iPlatformInfoService.getToken(platformInfoVo);
        Assert.notBlank(token.getToken(), "token不能为空");
        Assert.notNull(token.getExpire(), "token的失效时间不能为空");
        redisTemplate.opsForValue().set(redisKey, token, token.getExpire() - 1000, TimeUnit.SECONDS);
        return token;
    }
}
