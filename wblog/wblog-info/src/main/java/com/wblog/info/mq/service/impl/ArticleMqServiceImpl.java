package com.wblog.info.mq.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.rabbitmq.client.Channel;
import com.wblog.common.enums.ConstantEnum;
import com.wblog.common.module.info.mq.ArticleMqConstant;
import com.wblog.info.manage.IArticleManage;
import com.wblog.info.mq.service.IArticleMqService;
import com.wblog.info.service.ILabelService;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * @author luyanan
 * @since 2020/12/1
 **/
@Service
public class ArticleMqServiceImpl implements IArticleMqService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private AmqpAdmin amqpAdmin;

    @Autowired
    private IArticleManage articleManage;

    @Autowired
    private ILabelService labelService;

    @PostConstruct
    public void init() {
        DirectExchange directExchange = new DirectExchange(ArticleMqConstant.EXCHANGE, true, false, null);

        Queue enableQueue = new Queue(ArticleMqConstant.ENABLE_ARTICLE_QUEUE, true, false, false, null);

        Binding enableArticleBinding = new Binding(ArticleMqConstant.ENABLE_ARTICLE_QUEUE,
                Binding.DestinationType.QUEUE,
                ArticleMqConstant.EXCHANGE,
                ArticleMqConstant.ENABLE_ARTICLE_ROUTING_KEY,
                null);
        amqpAdmin.declareExchange(directExchange);
        amqpAdmin.declareQueue(enableQueue);
        amqpAdmin.declareBinding(enableArticleBinding);

    }

    @Override
    public void enableArticle(List<Long> articleIds) {
        rabbitTemplate.convertAndSend(ArticleMqConstant.EXCHANGE,
                ArticleMqConstant.ENABLE_ARTICLE_ROUTING_KEY,
                articleIds,
                new CorrelationData(UUID.randomUUID().toString()));
    }


    @RabbitListener(queues = ArticleMqConstant.ENABLE_ARTICLE_QUEUE)
    public void enableArticleListener(Message message, Channel channel, List<Long> articleIds) throws IOException {
        try {
            if (CollectionUtil.isNotEmpty(articleIds)) {
                // 1. 修改文章状态
                articleManage.updateStatus(articleIds, ConstantEnum.ARTICLE_STATUS_ENABLE.getValue());
                // 2.  匹配标签
                for (Long articleId : articleIds) {
                    labelService.updateLabel(articleId);
                }
                // 3.  TODO  推送到搜索引擎
                // 4. 推送到 开源中国
                // 5. 推送到ES

            }
//            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
//            channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
            e.printStackTrace();
        }

    }


}
