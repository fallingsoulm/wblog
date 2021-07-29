package com.wblog.info.mq.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;
import com.wblog.common.enums.ConstantEnum;
import com.wblog.common.module.info.mq.LabelMqConstant;
import com.wblog.common.module.info.vo.ArticleInfoVo;
import com.wblog.common.module.info.vo.ArticleLabelVo;
import com.wblog.info.entity.ArticleEntity;
import com.wblog.info.entity.LabelEntity;
import com.wblog.info.entity.NewsInfoEntity;
import com.wblog.info.manage.IArticleManage;
import com.wblog.info.manage.ILabelManage;
import com.wblog.info.manage.INewsInfoManage;
import com.wblog.info.mq.service.ILabelMqService;
import com.wblog.info.service.IArticleInfoService;
import com.wblog.info.service.IArticleLabelService;
import io.github.fallingsoulm.easy.archetype.data.mybatisplus.MybatisPlusUtils;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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


    @Autowired
    private INewsInfoManage newsInfoManage;

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
    public void articleAddLabel(Long id, Integer classify) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("classify", classify);
        rabbitTemplate.convertAndSend(LabelMqConstant.EXCHANGE,
                LabelMqConstant.ARTICLE_ADD_LABEL_ROUTING_KEY,
                JSON.toJSONString(map),
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
    public void articleAddLabelListener(Message message, Channel channel, @Payload String msg) throws IOException {

        Long id = JSON.parseObject(msg).getLong("id");
        Integer classify = JSON.parseObject(msg).getInteger("classify");
        try {

            //删除这个文章所有关联
            articleLabelService.delete(ArticleLabelVo.builder().articleId(id).classify(classify).build());
            String content = null;
            if (ConstantEnum.SEARCH_INFO_TYPE_ARTICLE.getValue().equals(classify)) {
                // 查询文章内容
                ArticleInfoVo articleInfoVo = articleInfoService.findById(id);
                if (null == articleInfoVo || StrUtil.isBlank(articleInfoVo.getContent())) {
                    return;
                }
                content = articleInfoVo.getContent();
            } else if (ConstantEnum.SEARCH_INFO_TYPE_NEWS.equalsValue(classify)) {

                NewsInfoEntity newsInfoEntity = newsInfoManage.findById(id);
                if (null == newsInfoEntity || StrUtil.isBlank(newsInfoEntity.getContent())) {
                    return;
                }
                content = newsInfoEntity.getContent();
            }

            // 查询所有的标签
            String finalContent = content;
            plusUtils.bigDataList(null, iLabelManage, data -> {
                List<Long> relatedLabelIds = data.stream()
                        .filter(a -> isMatchLabel(a, finalContent)
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
                                .articleId(id)
                                .classify(classify)
                                .labelId(a)
                                .build())
                        .collect(Collectors.toList());
                articleLabelService.saveBatch(articleLabelVoList);
            });
//            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
//            channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
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
                            boolean matchLabel = isMatchLabel(labelEntity, articleInfoVo.getContent());
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

            // 资讯的
            plusUtils.bigDataList(NewsInfoEntity.builder().build(), newsInfoManage, new MybatisPlusUtils.BigDataListHandler<NewsInfoEntity>() {
                @Override
                public void handler(List<NewsInfoEntity> data) {
                    //查询文章内容
                    if (CollectionUtil.isEmpty(data)) {
                        return;
                    }
                    List<Long> relatedArticleIds = data.stream().filter(a -> {

                        boolean matchLabel = isMatchLabel(labelEntity, a.getContent());
                        return matchLabel;
                    }).map(NewsInfoEntity::getNewsId).distinct().collect(Collectors.toList());

                    if (CollectionUtil.isEmpty(relatedArticleIds)) {
                        return;
                    }
                    articleLabelService.saveBatch(relatedArticleIds
                            .stream()
                            .map(a -> ArticleLabelVo.builder().labelId(labelId)
                                    .articleId(a).build())
                            .collect(Collectors.toList()));
                }
            });

//            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
//            channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
            e.printStackTrace();
        }
    }

    private boolean isMatchLabel(LabelEntity a, String content) {
        boolean flg = false;
        if (content
                .toLowerCase().contains(a.getName().toLowerCase())) {
            flg = true;
        }
        if (StrUtil.isNotBlank(a.getAliases())) {
            for (String s : a.getAliases().split(" ")) {
                if (content.toLowerCase().contains(s.toLowerCase())) {
                    flg = true;
                }
            }
        }
        return flg;
    }

}
