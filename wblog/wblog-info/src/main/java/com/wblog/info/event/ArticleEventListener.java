package com.wblog.info.event;

import com.wblog.common.enums.ConstantEnum;
import com.wblog.common.module.info.vo.ArticleVo;
import com.wblog.common.module.info.vo.LabelVo;
import com.wblog.common.module.search.dto.SearchDto;
import com.wblog.common.module.search.mq.SearchMqConstant;
import com.wblog.info.entity.ArticleEntity;
import com.wblog.info.entity.ArticleInfoEntity;
import com.wblog.info.manage.IArticleInfoManage;
import com.wblog.info.manage.IArticleManage;
import com.wblog.info.mq.service.ILabelMqService;
import com.wblog.info.service.IArticleService;
import com.wblog.info.service.ILabelService;
import com.wblog.info.utils.ArticleSearchVoBuilder;
import com.wblog.search.service.InfoSearchService;
import com.wblog.search.vo.ArticleSearchVo;
import io.swagger.annotations.ApiImplicitParam;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * @author luyanan
 * @since 2020/12/21
 * <p>文章事件监听者</p>
 **/
@Component
public class ArticleEventListener implements ApplicationListener<ArticleEvent> {


    @Autowired
    private InfoSearchService infoSearchService;

    @Autowired
    private IArticleManage articleManage;

    @Autowired
    private IArticleInfoManage articleInfoManage;

    @Autowired
    private ILabelService labelService;

    @Autowired
    private ILabelMqService labelMqService;

    @Override
    public void onApplicationEvent(ArticleEvent event) {

        Object source = event.getSource();
        if (null != source && source instanceof EventSourceVo) {
            EventSourceVo sourceVo = (EventSourceVo) source;
            switch (sourceVo.getType()) {
                case SAVE:

                    update(Long.valueOf(sourceVo.getId()));
                    break;
                case UPDATE:
                    update(Long.valueOf(sourceVo.getId()));
                    break;
                case DELETE:
                    delete(Long.valueOf(sourceVo.getId()));
                    break;
                case STOP_STATUS:
                    delete(Long.valueOf(sourceVo.getId()));
                    break;
                case ENABLE_STATUS:
                    labelMqService.articleAddLabel(Long.valueOf(sourceVo.getId()), ConstantEnum.SEARCH_INFO_TYPE_ARTICLE.getValue());
                    update(Long.valueOf(sourceVo.getId()));
                    break;
                default:
                    break;
            }
        }

    }

    private void update(Long id) {
        // 根据id查询详情
//        ArticleEntity articleEntity = articleManage.findById(id);
//        ArticleSearchVo searchVo = ArticleSearchVoBuilder.build(articleEntity, articleInfoManage, labelService);
        infoSearchService.update(Arrays.asList(id), ConstantEnum.SEARCH_INFO_TYPE_ARTICLE.getValue());
    }

    private void delete(Long id) {
        infoSearchService.deleteById(ArticleSearchVo.builder().id(id).classify(ConstantEnum.SEARCH_INFO_TYPE_NEWS.getValue()).build());
    }

}
