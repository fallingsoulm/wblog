package com.wblog.info.event;

import com.wblog.common.enums.ConstantEnum;
import com.wblog.common.module.info.vo.LabelVo;
import com.wblog.common.module.info.vo.NewsVo;
import com.wblog.info.entity.NewsInfoEntity;
import com.wblog.info.manage.INewsInfoManage;
import com.wblog.info.mq.service.ILabelMqService;
import com.wblog.info.news.NewsSpiderHandlerFactory;
import com.wblog.info.service.ILabelService;
import com.wblog.info.service.INewsService;
import com.wblog.search.service.InfoSearchService;
import com.wblog.search.vo.ArticleSearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * @author luyanan
 * @since 2020/12/22
 * <p>资讯事件监听</p>
 **/
@Component
public class NewsEventListener implements ApplicationListener<ArticleEvent> {


    @Autowired
    private INewsService newsService;

    @Autowired
    private NewsSpiderHandlerFactory newsSpiderHandlerFactory;

    @Autowired
    private INewsInfoManage newsInfoManage;

    @Autowired
    private ILabelService labelService;

    @Autowired
    private InfoSearchService infoSearchService;
    @Autowired
    private ILabelMqService labelMqService;

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
        // 添加资讯内容


        NewsInfoEntity content = newsSpiderHandlerFactory.getHandler(newsVo.getSource()).getContent(newsVo);

        if (null == content) {
            return;
        }


        newsVo.setContent(content.getContent());
        newsVo.setType(content.getType());
        // 添加
        newsInfoManage.insert(NewsInfoEntity.builder().newsId(newsVo.getId())
                .content(content.getContent())
                .type(content.getType()).build());

        // 添加标签
        labelMqService.articleAddLabel(content.getNewsId(), ConstantEnum.SEARCH_INFO_TYPE_NEWS.getValue());
        infoSearchService.update(Arrays.asList(Long.valueOf(id)), ConstantEnum.SEARCH_INFO_TYPE_NEWS.getValue());
//        List<LabelVo> labelVoList =
//                labelService.findByArticleId(newsVo.getId());
//
//
//        infoSearchService.update(ArticleSearchVo.builder().classify(ConstantEnum.SEARCH_INFO_TYPE_NEWS.getValue())
//                .id(newsVo.getId())
//                .title(newsVo.getTitle())
//                .image(newsVo.getImage())
//                .introduction(newsVo.getDesp())
//                .content(content.getContent())
//                .createTime(newsVo.getCreateTime())
//                .labelVos(labelVoList)
//                .build());
    }
}
