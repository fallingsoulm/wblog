package com.wblog.info.service.impl;

import com.apes.hub.api.enums.ConstantEnum;
import com.apes.hub.api.module.info.vo.NewsVo;
import com.apes.hub.api.page.PageInfo;
import com.apes.hub.api.page.PageInfoContentHandler;
import com.apes.hub.api.page.PageRequestParams;
import com.apes.hub.core.page.MybatisPlusUtils;
import com.apes.hub.info.conver.NewsConver;
import com.apes.hub.info.entity.NewsEntity;
import com.apes.hub.info.event.EventSourceVo;
import com.apes.hub.info.event.NewsEvent;
import com.apes.hub.info.manage.INewsManage;
import com.apes.hub.info.service.INewsService;
import com.apes.hub.info.task.NewsTaskFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 热门资讯 serviceImpl
 * </p>
 *
 * @author luyanan
 * @since 2020-07-13
 */
@Service
@Slf4j
public class NewsServiceImpl implements INewsService {


    @Autowired
    private INewsManage iNewsManage;


    @Autowired
    private NewsConver newsConver;


    @Autowired
    private MybatisPlusUtils plusUtils;


    @Autowired
    private NewsTaskFactory newsTaskFactory;

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public PageInfo<NewsVo> findByPage(PageRequestParams<NewsVo> pageRequestParams) {
        PageRequestParams<NewsEntity> params = plusUtils.convertPageRequestParams(pageRequestParams, NewsEntity.class, newsConver);
        PageInfo<NewsEntity> entityPageInfo = iNewsManage.findByPage(params);
        return plusUtils.convertPageInfo(entityPageInfo, NewsVo.class, newsConver, new PageInfoContentHandler<NewsVo>() {
            @Override
            public void handler(List<NewsVo> contentList) {
                for (NewsVo newsVo : contentList) {
                    newsVo.setSourceStr(ConstantEnum.getByTypeAndValue(ConstantEnum.EnumType.NEWS_SOURCE, newsVo.getSource()).getDesp());
                }
            }
        });
    }

    @Override
    public NewsVo findById(Long id) {
        NewsEntity newsEntity = iNewsManage.findById(id);
        if (newsEntity == null) {
            return null;
        }
        return newsConver.map(newsEntity, NewsVo.class);
    }

    @Override
    public List<NewsVo> findList(NewsVo newsVo) {
        NewsEntity NewsEntity = newsConver.map(newsVo, NewsEntity.class);
        List<NewsEntity> list = iNewsManage.findList(NewsEntity);
        return newsConver.mapAsList(list, NewsVo.class);
    }

    @Override
    public List<NewsVo> findByIds(List<Long> ids) {
        List<NewsEntity> entities = iNewsManage.findByIds(ids);
        return newsConver.mapAsList(entities, NewsVo.class);
    }

    @Override
    public Long save(NewsVo newsVo) {
        // 去重
        NewsEntity one = iNewsManage.findOne(NewsEntity.builder().source(newsVo.getSource()).url(newsVo.getUrl()).build());
        if (null != one) {
            return null;
        }
        NewsEntity newsEntity = newsConver.map(newsVo, NewsEntity.class);
        iNewsManage.insert(newsEntity);
        // 发布资讯添加事件
        applicationContext.publishEvent(new NewsEvent(EventSourceVo
                .builder()
                .id(newsEntity.getId().toString())
                .type(EventSourceVo.Type.SAVE)
                .build()));
        return newsEntity.getId();
    }

    @Override
    public void update(NewsVo newsVo) {
        NewsEntity newsEntity = newsConver.map(newsVo, NewsEntity.class);
        iNewsManage.update(newsEntity);

    }

    @Override
    public void deleteByIds(List<Long> ids) {
        iNewsManage.deleteBatch(new NewsEntity(), ids);
    }

    @Override
    public void deleteById(Long id) {
        iNewsManage.deleteById(NewsEntity.builder().id(id).build());
    }

    @Override
    public NewsVo lastUrl(Integer source) {
        return iNewsManage.lastUrl(source);

    }

    @Override
    public void syn(Integer source) {
        newsTaskFactory.syn(source);
    }

}
