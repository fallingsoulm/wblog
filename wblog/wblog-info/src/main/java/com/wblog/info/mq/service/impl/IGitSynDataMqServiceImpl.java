package com.wblog.info.mq.service.impl;

import com.rabbitmq.client.Channel;
import com.wblog.common.module.info.mq.GitSynDataMqConstant;
import com.wblog.info.mq.service.IGitSynDataMqService;
import com.wblog.info.service.IGitSynDataService;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;

/**
 * @author luyanan
 * @since 2020/11/28
 **/
@Service
public class IGitSynDataMqServiceImpl implements IGitSynDataMqService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    AmqpAdmin amqpAdmin;

    @Autowired
    private IGitSynDataService gitSynDataService;


    @PostConstruct
    public void init() {

        DirectExchange directExchange = new DirectExchange(GitSynDataMqConstant.EXCHANGE, true, false, null);


        Queue synQueue = new Queue(GitSynDataMqConstant.SYN_QUEUE, true, false, false, null);

        Binding binding = new Binding(GitSynDataMqConstant.SYN_QUEUE, Binding.DestinationType.QUEUE,
                GitSynDataMqConstant.EXCHANGE, GitSynDataMqConstant.SYN_ROUTING_KEY, null);

        amqpAdmin.declareQueue(synQueue);
        amqpAdmin.declareExchange(directExchange);
        amqpAdmin.declareBinding(binding);
    }

    @Override
    public void syn(Long id) {
        rabbitTemplate.convertAndSend(GitSynDataMqConstant.EXCHANGE, GitSynDataMqConstant.SYN_ROUTING_KEY, id);
    }


    /**
     * 同步队列监听
     *
     * @author luyanan
     * @since 2020/11/28
     */
    @RabbitListener(queues = GitSynDataMqConstant.SYN_QUEUE)
    public void synListener(Message message, Channel channel,Long id) throws IOException {

        try {

            gitSynDataService.doGitSynData(id);
        } finally {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        }
    }

}
