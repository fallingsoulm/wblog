package com.wblog.info.mq.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.apes.hub.api.enums.ConstantEnum;
import com.apes.hub.api.module.info.mq.LabelMqConstant;
import com.apes.hub.api.module.info.vo.ArticleInfoVo;
import com.apes.hub.api.module.info.vo.ArticleLabelVo;
import com.apes.hub.core.page.MybatisPlusUtils;
import com.apes.hub.info.entity.ArticleEntity;
import com.apes.hub.info.entity.LabelEntity;
import com.apes.hub.info.manage.IArticleManage;
import com.apes.hub.info.manage.ILabelManage;
import com.apes.hub.info.mq.service.ILabelMqService;
import com.apes.hub.info.service.IArticleInfoService;
import com.apes.hub.info.service.IArticleLabelService;
import com.rabbitmq.client.Channel;
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
import java.util.stream.Collectors;

/**
 * @author luyanan
 * @since 2020/11/30
 **/
@Service
public class LabelMqServiceImpl implements ILabelMqService {


    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private AmqpAdmin amqpAdmin;

    @Autowired
    private MybatisPlusUtils plusUtils;

    @Autowired
    private ILabelManage iLabelManage;
    @Autowired
    private IArticleLabelService articleLabelService;


    @Autowired
    private IArticleManage articleManage;

    @Autowired
    private IArticleInfoService articleInfoService;

    @PostConstruct
    public void init() {

        DirectExchange directExchange = new DirectExchange(LabelMqConstant.EXCHANGE, true, false, null);
        // 添加文章, 关联标签
        Queue queue = new Queue(LabelMqConstant.ARTICLE_ADD_LABEL_QUEUE, true, false, false, null);
        Binding binding = new Binding(LabelMqConstant.ARTICLE_ADD_LABEL_QUEUE, Binding.DestinationType.QUEUE,
                LabelMqConstant.EXCHANGE, LabelMqConstant.ARTICLE_ADD_LABEL_ROUTING_KEY, null);

        //  添加标签,关联文章
        Queue addLabelQueue = new Queue(LabelMqConstant.LABEL_ADD_QUEUE, true, false, false, null);
        Binding addLabelBinding = new Binding(LabelMqConstant.LABEL_ADD_QUEUE, Binding.DestinationType.QUEUE,
                LabelMqConstant.EXCHANGE, LabelMqConstant.ARTICLE_ADD_LABEL_QUEUE, null);
        amqpAdmin.declareQueue(queue);
        amqpAdmin.declareExchange(directExchange);
        amqpAdmin.declareBinding(binding);
        amqpAdmin.declareQueue(addLabelQueue);
        amqpAdmin.declareBinding(addLabelBinding);
    }

    @Override
    public void articleAddLabel(Long articleId) {
        rabbitTemplate.convertAndSend(LabelMqConstant.EXCHANGE,
                LabelMqConstant.ARTICLE_ADD_LABEL_ROUTING_KEY,
                articleId,
                new CorrelationData(UUID.randomUUID().toString()));

    }

    @Override
    public void addLabel(Long labelId) {
        rabbitTemplate.convertAndSend(LabelMqConstant.EXCHANGE,
                LabelMqConstant.LABEL_ADD_ROUTING_KEY,
                labelId,
                new CorrelationData(UUID.randomUUID().toString()));
    }

    @RabbitListener(queues = LabelMqConstant.ARTICLE_ADD_LABEL_QUEUE)
    public void articleAddLabelListener(Message message, Channel channel, Long articleId) throws IOException {

        try {

            //删除这个文章所有关联
            articleLabelService.delete(ArticleLabelVo.builder().articleId(articleId).build());
            // 查询文章内容
            ArticleInfoVo articleInfoVo = articleInfoService.findById(articleId);
            if (null == articleInfoVo || StrUtil.isBlank(articleInfoVo.getContent())) {
                return;
            }
            // 查询所有的标签
            plusUtils.bigDataList(null, iLabelManage, data -> {
                List<Long> relatedLabelIds = data.stream()
                        .filter(a -> isMatchLabel(a, articleInfoVo)
                        )
                        .distinct()
                        .map(LabelEntity::getId)
                        .collect(Collectors.toList());
                if (CollectionUtil.isEmpty(relatedLabelIds)) {
                    return;
                }
                List<ArticleLabelVo> articleLabelVoList = relatedLabelIds
                        .stream()
                        .map(a -> ArticleLabelVo
                                .builder()
                                .articleId(articleId)
                                .labelId(a)
                                .build())
                        .collect(Collectors.toList());
                articleLabelService.saveBatch(articleLabelVoList);
            });
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
            e.printStackTrace();
        }
    }


    @RabbitListener(queues = LabelMqConstant.LABEL_ADD_QUEUE)
    public void addLabelListener(Message message, Channel channel, Long labelId) throws IOException {
        try {
            LabelEntity labelEntity = this.iLabelManage.findById(labelId);
            if (null == labelEntity) {
                return;
            }
            //删除这个标签所有关联
            articleLabelService.delete(ArticleLabelVo.builder().labelId(labelId).build());
            // 查询所有的文章
            plusUtils.bigDataList(ArticleEntity
                            .builder()
                            .status(ConstantEnum.ARTICLE_STATUS_ENABLE.getValue())
                            .build(),
                    articleManage,
                    data -> {
                        //查询文章内容
                        if (CollectionUtil.isEmpty(data)) {
                            return;
                        }
                        List<Long> relatedArticleIds = data.stream().filter(a -> {
                            ArticleInfoVo articleInfoVo = articleInfoService.findById(a.getId());
                            boolean matchLabel = isMatchLabel(labelEntity, articleInfoVo);
                            return matchLabel;
                        }).map(ArticleEntity::getId).distinct().collect(Collectors.toList());

                        if (CollectionUtil.isEmpty(relatedArticleIds)) {
                            return;
                        }

                        articleLabelService.saveBatch(relatedArticleIds
                                .stream()
                                .map(a -> ArticleLabelVo.builder().labelId(labelId)
                                        .articleId(a).build())
                                .collect(Collectors.toList()));
                    });
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
            e.printStackTrace();
        }
    }

    private boolean isMatchLabel(LabelEntity a, ArticleInfoVo articleInfoVo) {
        boolean flg = false;
        if (articleInfoVo
                .getContent()
                .toLowerCase().contains(a.getName().toLowerCase())) {
            flg = true;
        }
        if (StrUtil.isNotBlank(a.getAliases())) {
            for (String s : a.getAliases().split(" ")) {
                if (articleInfoVo.getContent().toLowerCase().contains(s.toLowerCase())) {
                    flg = true;
                }
            }
        }
        return flg;
    }

}
