package com.wblog.info.event;

import com.wblog.common.enums.ConstantEnum;
import com.wblog.common.module.search.dto.SearchDto;
import com.wblog.common.module.search.mq.SearchMqConstant;
import com.wblog.info.service.IArticleService;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author luyanan
 * @since 2020/12/21
 * <p>文章事件监听者</p>
 **/
@Component
public class ArticleEventListener implements ApplicationListener<ArticleEvent> {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private IArticleService articleService;

    @Override
    public void onApplicationEvent(ArticleEvent event) {

        Object source = event.getSource();
        if (null != source && source instanceof EventSourceVo) {
            EventSourceVo sourceVo = (EventSourceVo) source;
            switch (sourceVo.getType()) {
                case SAVE:
                    doSaveListener(((EventSourceVo) source).getId());
                    break;
                case UPDATE:
                    doUpdateListener(sourceVo.getId());
                    break;
                case DELETE:
                    doDeleteListener(sourceVo.getId());
                    break;
                case STOP_STATUS:
                    doEnableStatusListener(sourceVo.getId());
                    break;
                case ENABLE_STATUS:
                    doStopStatusListener(sourceVo.getId());
                    break;
                default:
                    break;
            }
        }

    }

    /**
     * <p>执行下架的监听</p>
     *
     * @param id
     * @author luyanan
     * @since 2020/12/22
     */
    private void doStopStatusListener(String id) {
        // 发送搜索服务删除文章的消息
        rabbitTemplate.convertAndSend(SearchMqConstant.SEARCH_ARTICLE_EXCHANGE,
                SearchMqConstant.SEARCH_ARTICLE_DELETE_ROUTINGKEY,
                SearchDto.builder().id(id).belong(ConstantEnum.SEARCH_BELONG_ARTICLE.getValue()).build(),
                new CorrelationData(UUID.randomUUID().toString()));
    }

    /**
     * <p>执行上架的监听</p>
     *
     * @param id
     * @author luyanan
     * @since 2020/12/22
     */
    private void doEnableStatusListener(String id) {
        // 发送搜索服务上架文章的消息

        rabbitTemplate.convertAndSend(SearchMqConstant.SEARCH_ARTICLE_EXCHANGE,
                SearchMqConstant.SEARCH_ARTICLE_SAVE_ROUTINGKEY,
                conver(id),
                new CorrelationData(UUID.randomUUID().toString()));

    }

    /**
     * <p>执行删除的监听</p>
     *
     * @param id
     * @author luyanan
     * @since 2020/12/22
     */
    private void doDeleteListener(String id) {
        // 发送搜索服务删除文章的消息
        rabbitTemplate.convertAndSend(SearchMqConstant.SEARCH_ARTICLE_EXCHANGE,
                SearchMqConstant.SEARCH_ARTICLE_DELETE_ROUTINGKEY,
                SearchDto.builder().id(id).belong(ConstantEnum.SEARCH_BELONG_ARTICLE.getValue()).build(),
                new CorrelationData(UUID.randomUUID().toString()));

    }

    /**
     * <p>执行修改的监听</p>
     *
     * @param id
     * @author luyanan
     * @since 2020/12/22
     */
    private void doUpdateListener(String id) {

        // 发送搜索服务上架文章的消息
        rabbitTemplate.convertAndSend(SearchMqConstant.SEARCH_ARTICLE_EXCHANGE,
                SearchMqConstant.SEARCH_ARTICLE_SAVE_ROUTINGKEY,
                conver(id),
                new CorrelationData(UUID.randomUUID().toString()));
    }

    private SearchDto conver(String id) {
        ArticleVo articleVo = articleService.findById(Long.valueOf(id));
        return SearchDto
                .builder()
                .id(id)
                .belong(ConstantEnum.SEARCH_BELONG_ARTICLE.getValue())
                .content(articleVo.getContent())
                .image(articleVo.getImage())
                .createTime(articleVo.getCreateTime())
                .introduction(articleVo.getIntroduction())
                .title(articleVo.getTitle())
                .userId(articleVo.getUserId())
                .build();
    }

    /**
     * <p>执行添加的监听</p>
     *
     * @param id
     * @author luyanan
     * @since 2020/12/22
     */
    private void doSaveListener(String id) {


    }


}
