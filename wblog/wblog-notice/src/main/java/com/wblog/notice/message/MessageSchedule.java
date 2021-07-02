package com.wblog.notice.message;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.wblog.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 消息定时器
 *
 * @author luyanan
 * @since 2021/6/30
 **/
@Component
@Slf4j
public class MessageSchedule {

    /**
     * 消息前缀
     *
     * @author luyanan
     * @since 2021/6/30
     */
    public static final String REDIS_KEY_PREFIX = "NOTICE_MESSAGE_";

    @Autowired
    private RedisTemplate redisTemplate;


    @Autowired
    private MessageTypeRegister messageTypeRegister;

    @PostConstruct
    public void init() {

        //org.apache.commons.lang3.concurrent.BasicThreadFactory
        ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(10,
                new BasicThreadFactory.Builder().namingPattern("message-schedule-pool-%d").daemon(true).build());
        executorService.scheduleAtFixedRate(() -> {
            log.debug("开启消息定时任务:" + DateUtil.date());

//            String redisKey = "NOTICE_MESSAGE_20:22";
            String redisKey = REDIS_KEY_PREFIX + DateUtil.format(new Date(), "HH:mm");
            if (redisTemplate.hasKey(redisKey)) {

                List<MessageVo> messages = JSON.parseArray(JSON.toJSONString(redisTemplate.opsForList().range(redisKey, 0, -1)), MessageVo.class);
                messageTypeRegister.sendMessage(messages);
                log.debug("执行key:{}的定时任务{}条", redisKey, messages.size());
            }

        }, 1, 1, TimeUnit.MINUTES);
    }


    /**
     * 添加定时消息
     *
     * @param messageVo 消息体
     * @return void
     * @since 2021/6/30
     */
    public void addScheduledMessage(List<MessageVo> messageVo) {

        if (CollectionUtil.isEmpty(messageVo)) {
            return;
        }

        if (messageVo.stream()
                .filter(a -> StrUtil.isBlank(a.getSendTime()))
                .findFirst().isPresent()) {
            throw new BusinessException("发送时间不能为空");
        }

        messageVo
                .stream()
                .collect(Collectors.groupingBy(MessageVo::getSendTime))
                .entrySet()
                .stream()
                .forEach(entry -> {
                    final String sendTime = entry.getKey();
                    final List<MessageVo> messageVos =
                            entry.getValue();
                    String key = REDIS_KEY_PREFIX + sendTime;
                    redisTemplate.opsForList().leftPush(key, messageVos);
                });

    }
}
