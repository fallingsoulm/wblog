package com.wblog.info.service.impl;

import com.wblog.common.enums.ConstantEnum;
import com.wblog.common.module.info.vo.NewsVo;
import com.wblog.info.entity.NewsEntity;
import com.wblog.info.event.EventSourceVo;
import com.wblog.info.event.NewsEvent;
import com.wblog.info.manage.INewsManage;
import com.wblog.info.service.INewsService;
import com.wblog.info.task.NewsTaskFactory;
import io.github.fallingsoulm.easy.archetype.data.mybatisplus.MybatisPlusUtils;
import io.github.fallingsoulm.easy.archetype.data.mybatisplus.PageInfoContentHandler;
import io.github.fallingsoulm.easy.archetype.framework.page.PageInfo;
import io.github.fallingsoulm.easy.archetype.framework.page.PageRequestParams;
import io.github.fallingsoulm.easy.archetype.framework.utils.BeanUtils;
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
    private MybatisPlusUtils plusUtils;


    @Autowired
    private NewsTaskFactory newsTaskFactory;

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public PageInfo<NewsVo> findByPage(PageRequestParams<NewsVo> pageRequestParams) {
        PageRequestParams<NewsEntity> params = plusUtils.convertPageRequestParams(pageRequestParams, NewsEntity.class);
        PageInfo<NewsEntity> entityPageInfo = iNewsManage.listByPage(params);
        return plusUtils.convertPageInfo(entityPageInfo, NewsVo.class, contentList -> {
            for (NewsVo newsVo : contentList) {
                newsVo.setSourceStr(ConstantEnum.getByTypeAndValue(ConstantEnum.EnumType.NEWS_SOURCE, newsVo.getSource()).getDesp());
            }
        });
    }

    @Override
    public NewsVo findById(Long id) {
        NewsEntity newsEntity = iNewsManage.findById(id);
        if (newsEntity == null) {
            return null;
        }
        return BeanUtils.copyProperties(newsEntity, NewsVo.class);
    }

    @Override
    public List<NewsVo> findList(NewsVo newsVo) {
        NewsEntity NewsEntity = BeanUtils.copyProperties(newsVo, NewsEntity.class);
        List<NewsEntity> list = iNewsManage.list(NewsEntity);
        return BeanUtils.copyList(list, NewsVo.class);
    }

    @Override
    public List<NewsVo> findByIds(List<Long> ids) {
        List<NewsEntity> entities = iNewsManage.findByIds(ids);
        return BeanUtils.copyList(entities, NewsVo.class);
    }

    @Override
    public Long save(NewsVo newsVo) {
        // 去重
        NewsEntity one = iNewsManage.findOne(NewsEntity.builder().source(newsVo.getSource()).url(newsVo.getUrl()).build());
        if (null != one) {
            return null;
        }
        NewsEntity newsEntity = BeanUtils.copyProperties(newsVo, NewsEntity.class);
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
        NewsEntity newsEntity = BeanUtils.copyProperties(newsVo, NewsEntity.class);
        iNewsManage.update(newsEntity);

    }

    @Override
    public void deleteByIds(List<Long> ids) {
        iNewsManage.deleteBatch(ids);
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
