package com.wblog.info.event;

import com.wblog.common.enums.ConstantEnum;
import com.wblog.common.module.info.vo.NewsVo;
import com.wblog.common.module.search.dto.SearchDto;
import com.wblog.common.module.search.mq.SearchMqConstant;
import com.wblog.info.service.INewsService;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author luyanan
 * @since 2020/12/22
 * <p>资讯事件监听</p>
 **/
@Component
public class NewsEventListener implements ApplicationListener<ArticleEvent> {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private INewsService newsService;

    @Override
    public void onApplicationEvent(ArticleEvent event) {
        Object source = event.getSource();
        if (null == source || !(source instanceof EventSourceVo)) {
            return;
        }
        EventSourceVo eventSourceVo = (EventSourceVo) source;
        switch (eventSourceVo.getType()) {
            case SAVE:
                doSaveListener(eventSourceVo.getId());
                break;
            default:
                break;
        }
    }

    /**
     * <p>添加的监听</p>
     *
     * @param id
     * @author luyanan
     * @since 2020/12/22
     */
    private void doSaveListener(String id) {

        NewsVo newsVo = newsService.findById(Long.valueOf(id));
        // 发布资讯添加消息
        rabbitTemplate.convertAndSend(SearchMqConstant.SEARCH_NEWS_EXCHANGE, SearchMqConstant.SEARCH_NEWS_SAVE_ROUTINGKEY,
                SearchDto.builder().belong(ConstantEnum.SEARCH_BELONG_NEWS.getValue())
                        .createTime(newsVo.getCreateTime())
                        .content(newsVo.getDesp())
                        .image(newsVo.getUrl())
                        .introduction(newsVo.getDesp())
                        .title(newsVo.getTitle())
                        .source(newsVo.getSource())
                        .id(newsVo.getDesp())
                        .build(), new CorrelationData(UUID.randomUUID().toString()));

    }
}
