package com.wblog.social.core;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 社交token存储
 *
 * @author luyanan
 * @since 2021/7/6
 **/
@Slf4j
public class SocialSessionManage {

    @Autowired
    private RedisTemplate redisTemplate;


    /**
     * session保存
     *
     * @param userId     用户id
     * @param session
     * @param expireTime
     * @return void
     * @since 2021/7/6
     */
    public void saveSession(Long userId, String platform, String session, int expireTime) {
        String sessionKey = getSessionKey(userId, platform);
        redisTemplate.opsForValue().set(sessionKey, session, expireTime, TimeUnit.SECONDS);
    }


    /**
     * 获取session
     *
     * @param userId   用户id
     * @param platform 平台
     * @return java.lang.String
     * @since 2021/7/6
     */
    public String getSession(Long userId, String platform) {
        String sessionKey = getSessionKey(userId, platform);
        return (String) redisTemplate.opsForValue().get(sessionKey);
    }


    /**
     * 获取所有的session
     *
     * @param userId   用户id
     * @param platform 平台
     * @return void
     * @since 2021/7/6
     */
    public void deleteSession(Long userId, String platform) {
        String sessionKey = getSessionKey(userId, platform);
        redisTemplate.delete(sessionKey);
    }


    /**
     * 获取session 的redis key
     *
     * @param userId   用户id
     * @param platform 平台
     * @return java.lang.String
     * @since 2021/7/6
     */
    private String getSessionKey(Long userId, String platform) {
        String redisKey = "social_" + platform + "_" + userId;
        return redisKey;
    }


    /**
     * 根据平台获取用户id
     *
     * @param platform 平台
     * @return java.util.Set<java.lang.Long>
     * @since 2021/7/6
     */
    public Set<Long> getUserByPlatform(String platform) {
        String sessionKey = getSessionKey(null, platform);

        Set scan = scan(redisTemplate, sessionKey + "*");
        return scan;
    }



    /**
     * scan 实现
     *
     * @param redisTemplate redisTemplate
     * @param pattern       表达式，如：abc*，找出所有以abc开始的键
     */
    public static Set<String> scan(RedisTemplate<String, Object> redisTemplate, String pattern) {
        return redisTemplate.execute((RedisCallback<Set<String>>) connection -> {
            Set<String> keysTmp = new HashSet<>();
            try (Cursor<byte[]> cursor = connection.scan(new ScanOptions.ScanOptionsBuilder()
                    .match(pattern)
                    .count(10000).build())) {

                while (cursor.hasNext()) {
                    keysTmp.add(new String(cursor.next(), "Utf-8"));
                }
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                throw new RuntimeException(e);
            }
            return keysTmp;
        });
    }
}
