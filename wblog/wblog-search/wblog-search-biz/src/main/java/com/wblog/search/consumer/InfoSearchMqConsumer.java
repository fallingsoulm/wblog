package com.wblog.search.consumer;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.wblog.common.enums.ConstantEnum;
import com.wblog.common.module.info.api.ArticleApi;
import com.wblog.common.module.info.api.LabelApi;
import com.wblog.common.module.info.api.NewsApi;
import com.wblog.common.module.info.vo.ArticleVo;
import com.wblog.common.module.info.vo.LabelVo;
import com.wblog.common.module.info.vo.NewsVo;
import com.wblog.search.service.InfoElasticsearchService;
import com.wblog.search.constant.InfoSearchConstant;
import com.wblog.search.vo.ArticleSearchVo;
import io.github.fallingsoulm.easy.archetype.framework.thread.BusinessThreadPoolTaskExecutor;
import io.micrometer.core.instrument.binder.jvm.ExecutorServiceMetrics;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.*;

/**
 * 信息搜索MQ 消费者
 *
 * @author luyanan
 * @since 2021/7/18
 **/
@Slf4j
@Component
public class InfoSearchMqConsumer {

    @Autowired
    private InfoElasticsearchService infoElasticsearchService;


    @Autowired
    private NewsApi newsApi;

    @Autowired
    private ArticleApi articleApi;

    @Autowired
    private LabelApi labelApi;

//    protected ThreadPoolExecutor executorService = new ThreadPoolExecutor(10, 50, 5, TimeUnit.SECONDS, new LinkedBlockingDeque<>());

    /**
     * 信息修改监听
     *
     * @param msg
     * @param deliveryTag
     * @param channel
     * @return void
     * @since 2021/7/18
     */
    @SneakyThrows
    @RabbitListener(queues = InfoSearchConstant.INFO_MQ_UPDATE_QUEUE)
    public void infoUpdateHandler(@Payload String msg, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag, Channel channel) {


        if (StrUtil.isBlank(msg)) {
            return;
        }


        List<Long> ids = JSON.parseObject(msg).getJSONArray("ids").toJavaList(Long.class);
        Integer classify = JSON.parseObject(msg).getInteger("classify");
        if (ConstantEnum.SEARCH_INFO_TYPE_NEWS.getValue().equals(classify)) {
            // 资讯
            for (Long id : ids) {
                NewsVo newsVo = newsApi.findById(id).getData();
                if (null == newsVo || StrUtil.isBlank(newsVo.getContent())) {
                    continue;
                }
                List<LabelVo> labelVos = labelApi.findByArticleId(id, 10).getData();
                ArticleSearchVo articleSearchVo = ArticleSearchVo.builder().title(newsVo.getTitle())
                        .classify(ConstantEnum.SEARCH_INFO_TYPE_NEWS.getValue())
                        .labelVos(labelVos)
                        .createTime(newsVo.getCreateTime())
                        .content(newsVo.getContent())
                        .introduction(newsVo.getDesp())
                        .image(newsVo.getImage())
                        .id(newsVo.getId())
                        .build();
                log.info("接受到的需要修改的信息的id为:{}", articleSearchVo.getId());
                infoElasticsearchService.update(JSON.toJSONString(articleSearchVo), id(articleSearchVo));

            }

        } else if (ConstantEnum.SEARCH_INFO_TYPE_ARTICLE.getValue().equals(classify)) {
            for (Long id : ids) {
                ArticleVo articleVo = articleApi.findById(id).getData();
                List<LabelVo> labelVos = labelApi.findByArticleId(id, 10).getData();
                ArticleSearchVo articleSearchVo = ArticleSearchVo.builder().image(articleVo.getImage())
                        .introduction(articleVo.getIntroduction())
                        .content(articleVo.getContent())
                        .createTime(articleVo.getCreateTime())
                        .labelVos(labelVos)
                        .classify(ConstantEnum.SEARCH_INFO_TYPE_ARTICLE.getValue())
                        .title(articleVo.getTitle())
                        .userId(articleVo.getUserId())
                        .id(articleVo.getId())
                        .build();
                log.info("接受到的需要修改的信息的id为:{}", articleSearchVo.getId());
                infoElasticsearchService.update(JSON.toJSONString(articleSearchVo), id(articleSearchVo));
            }
        }
        System.out.println(msg);

//        ArticleSearchVo articleSearchVo = JSON.parseObject(msg, ArticleSearchVo.class);
//
//        log.info("接受到的需要修改的信息的id为:{}", articleSearchVo.getId());
//        infoElasticsearchService.update(JSON.toJSONString(articleSearchVo), id(articleSearchVo));

    }

    protected String id(ArticleSearchVo articleSearchVo) {
        return articleSearchVo.getClassify() + "-" + articleSearchVo.getId();
    }

    /**
     * 信息删除监听
     *
     * @param deliveryTag
     * @param channel
     * @return void
     * @since 2021/7/18
     */
    @RabbitListener(queues = InfoSearchConstant.INFO_MQ_DELETE_QUEUE)
    public void infoDeleteHandler(@Payload String msg, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag, AMQP.Channel channel) {
        if (StrUtil.isBlank(msg)) {
            return;
        }
        ArticleSearchVo articleSearchVo = JSON.parseObject(msg, ArticleSearchVo.class);
        log.info("接受到的需要删除的信息为:{}", articleSearchVo.getId());
        infoElasticsearchService.deleteById(id(articleSearchVo));
    }
}
