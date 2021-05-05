package com.wblog.info.mq.listener;//package com.apes.hub.info.mq.listener;
//
//import cn.hutool.core.collection.CollectionUtil;
//import cn.hutool.core.util.StrUtil;
//import com.apes.hub.api.enums.ConstantEnum;
//import com.apes.hub.api.module.info.vo.ArticleInfoVo;
//import com.apes.hub.api.module.info.vo.ArticleLabelVo;
//import com.apes.hub.info.entity.ArticleEntity;
//import com.apes.hub.info.entity.LabelEntity;
//import com.apes.hub.info.manage.IArticleManage;
//import com.apes.hub.info.manage.ILabelManage;
//import com.apes.hub.info.mq.to.UpdateArticleLabelTo;
//import com.apes.hub.info.service.IArticleInfoService;
//import com.apes.hub.info.service.IArticleLabelService;
//import com.rabbitmq.client.Channel;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.amqp.core.*;
//import org.springframework.amqp.rabbit.annotation.RabbitHandler;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//import java.io.IOException;
//import java.util.List;
//import java.util.stream.Collectors;
//
///**
// * @author luyanan
// * @since 2020/11/21
// * <p>修改文章标签listener</p>
// **/
//@Slf4j
//@Component
//@RabbitListener(queues = UpdateArticleLabelListener.QUEUE)
//public class UpdateArticleLabelListener {
//
//
//    public static final String EXCHANGE = "update_article_label_exchange";
//    public static final String QUEUE = "update_article_label_queue";
//    public static final String ROUTING_KEY = "update_article_label_routing_key";
//
//    @Autowired
//    private AmqpAdmin amqpAdmin;
//
//    @Autowired
//    private IArticleInfoService articleInfoService;
//
//    @Autowired
//    private ILabelManage labelManage;
//
//
//    @Autowired
//    private IArticleLabelService articleLabelService;
//
//    @Autowired
//    private IArticleManage articleManage;
//
//    @PostConstruct
//    public void init() {
//        Queue queue = new Queue(QUEUE);
//        DirectExchange directExchange = new DirectExchange(EXCHANGE);
//        Binding binding = new Binding(QUEUE, Binding.DestinationType.QUEUE, EXCHANGE, ROUTING_KEY, null);
//        amqpAdmin.declareQueue(queue);
//        amqpAdmin.declareExchange(directExchange);
//        amqpAdmin.declareBinding(binding);
//    }
//
//    @RabbitHandler()
//    public void listener(Message message, UpdateArticleLabelTo updateArticleLabelTo, Channel channel) throws IOException {
//        try {
//            updateArticleLabel(updateArticleLabelTo);
//        } catch (Exception e) {
//            e.printStackTrace();
//            channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
//        } finally {
//            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
//        }
//    }
//
//    private void updateArticleLabel(UpdateArticleLabelTo updateArticleLabelTo) {
//        List<Long> articleIds = updateArticleLabelTo.getArticleIds();
//        List<Long> labelIdLists = updateArticleLabelTo.getLabelIds();
//        log.info(Thread.currentThread().getName() + "----更新文章标签信息");
//
//        if (CollectionUtil.isEmpty(articleIds)) {
//            // 查询全部上架的
//            articleIds = articleManage.findList(ArticleEntity
//                    .builder()
//                    .status(ConstantEnum.ARTICLE_STATUS_ENABLE.getValue())
//                    .build())
//                    .stream()
//                    .map(ArticleEntity::getId)
//                    .distinct()
//                    .collect(Collectors.toList());
//
//        }
//
//        if (CollectionUtil.isEmpty(labelIdLists)) {
//            labelIdLists = this.labelManage.findList(null).stream().map(LabelEntity::getId).distinct().collect(Collectors.toList());
//        }
//
//        List<LabelEntity> list = labelManage.findByIds(labelIdLists);
//        for (Long articleId : articleIds) {
//            articleLabelService.delete(ArticleLabelVo.builder().articleId(articleId).build());
//            ArticleInfoVo articleInfoVo = articleInfoService.findById(articleId);
//
//            if (null == articleInfoVo || StrUtil.isBlank(articleInfoVo.getContent())) {
//                continue;
//            }
//            List<Long> labelIds = list.stream()
//                    .filter(a -> {
//                                boolean flg = false;
//                                if (articleInfoVo.getContent().toLowerCase().contains(a.getName().toLowerCase())) {
//                                    flg = true;
//                                }
//                                if (StrUtil.isNotBlank(a.getAliases())) {
//                                    for (String s : a.getAliases().split(" ")) {
//                                        if (articleInfoVo.getContent().toLowerCase().contains(s.toLowerCase())) {
//                                            flg = true;
//                                        }
//                                    }
//                                }
//                                return flg;
//                            }
//                    )
//                    .distinct()
//                    .map(LabelEntity::getId)
//                    .collect(Collectors.toList());
//
//            if (labelIds.isEmpty()) {
//                return;
//            }
//            List<ArticleLabelVo> articleLabelVoList = labelIds.stream().map(a -> {
//                return ArticleLabelVo.builder().articleId(articleId).labelId(a).build();
//
//            }).collect(Collectors.toList());
//            articleLabelService.saveBatch(articleLabelVoList);
//
//            log.info("文章:{},插入标签{}成功", articleId, labelIds);
//
//        }
//    }
//}
