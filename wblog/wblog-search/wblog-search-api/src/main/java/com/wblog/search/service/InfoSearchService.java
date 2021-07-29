package com.wblog.search.service;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSON;
import com.wblog.search.constant.InfoSearchConstant;
import com.wblog.search.vo.ArticleSearchVo;
import io.github.fallingsoulm.easy.archetype.framework.page.PageInfo;
import io.github.fallingsoulm.easy.archetype.framework.page.PageRequestParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * 搜索服务
 *
 * @author luyanan
 * @since 2021/7/18
 **/
@Slf4j
@Component
public class InfoSearchService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private RabbitAdmin rabbitAdmin;

    @PostConstruct
    public void init() {
        log.debug("初始化mq操作");
        // 声明交换机
        rabbitAdmin.declareExchange(new DirectExchange(InfoSearchConstant.INFO_MQ_EXCHANGE, false, false));

        //声明队列
        rabbitAdmin.declareQueue(new Queue(InfoSearchConstant.INFO_MQ_DELETE_QUEUE, false, false, false));


        //声明队列
        rabbitAdmin.declareQueue(new Queue(InfoSearchConstant.INFO_MQ_UPDATE_QUEUE, false, false, false));
        // 声明绑定
        rabbitAdmin.declareBinding(new Binding(InfoSearchConstant.INFO_MQ_UPDATE_QUEUE, Binding.DestinationType.QUEUE,
                InfoSearchConstant.INFO_MQ_EXCHANGE, InfoSearchConstant.INFO_MQ_UPDATE_ROUTINGKEY, null));


        rabbitAdmin.declareBinding(new Binding(InfoSearchConstant.INFO_MQ_DELETE_QUEUE, Binding.DestinationType.QUEUE,
                InfoSearchConstant.INFO_MQ_EXCHANGE, InfoSearchConstant.INFO_MQ_DELETE_ROUTINGKEY, null));

    }


    /**
     * 添加/修改
     *
     * @param ids      id集合
     * @param classify 分类
     * @return void
     * @since 2021/7/18
     */
    public void update(List<Long> ids, Integer classify) {

        if (CollectionUtil.isEmpty(ids)) {
            return;
        }
        Map<String, Object> map = new HashMap<>();
        map.put("classify", classify);
        map.put("ids", ids);
        rabbitTemplate.convertAndSend(InfoSearchConstant.INFO_MQ_EXCHANGE, InfoSearchConstant.INFO_MQ_UPDATE_ROUTINGKEY,
                JSON.toJSONString(map)
                , new CorrelationData(UUID.randomUUID().toString()));
    }


    /**
     * 批量删除
     *
     * @param articleSearchVo
     * @return void
     * @since 2021/7/18
     */
    public void deleteById(ArticleSearchVo articleSearchVo) {
        rabbitTemplate.convertAndSend(InfoSearchConstant.INFO_MQ_EXCHANGE, InfoSearchConstant.INFO_MQ_DELETE_ROUTINGKEY, articleSearchVo);
    }


    /**
     * 根据条件分页查询
     *
     * @param pageRequestParams
     * @return io.github.fallingsoulm.easy.archetype.framework.page.PageInfo<com.wblog.search.vo.ArticleSearchVo>
     * @since 2021/7/18
     */
    public PageInfo<ArticleSearchVo> findByPage(PageRequestParams<ArticleSearchVo> pageRequestParams) {


        return null;
    }

}
