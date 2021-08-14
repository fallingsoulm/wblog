package com.wblog.info.service.impl;

import cn.hutool.core.util.StrUtil;
import com.wblog.common.enums.ConstantEnum;
import com.wblog.common.exception.BusinessException;
import com.wblog.common.module.info.api.ArticleViewComponent;
import com.wblog.common.module.info.vo.LabelVo;
import com.wblog.common.module.info.vo.NewsVo;
import com.wblog.info.component.FileTemplatePlus;
import com.wblog.info.entity.NewsEntity;
import com.wblog.info.entity.NewsInfoEntity;
import com.wblog.info.event.EventSourceVo;
import com.wblog.info.event.NewsEvent;
import com.wblog.info.manage.INewsInfoManage;
import com.wblog.info.manage.INewsManage;
import com.wblog.info.news.NewsSpiderHandlerFactory;
import com.wblog.info.service.ILabelService;
import com.wblog.info.service.INewsService;
import io.github.fallingsoulm.easy.archetype.data.mybatisplus.MybatisPlusUtils;
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
    private ArticleViewComponent articleViewComponent;
    @Autowired
    private INewsManage iNewsManage;


    @Autowired
    private MybatisPlusUtils plusUtils;


    @Autowired
    private FileTemplatePlus fileTemplatePlus;


    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private INewsInfoManage newsInfoManage;

    @Autowired
    private ILabelService labelService;

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
        NewsVo newsVo = BeanUtils.copyProperties(newsEntity, NewsVo.class);
        NewsInfoEntity infoEntity = newsInfoManage.findById(id);
        if (null != infoEntity) {
            newsVo.setContent(infoEntity.getContent());
            newsVo.setType(infoEntity.getType());
        }

        return newsVo;
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
        //
        NewsEntity newsEntity = BeanUtils.copyProperties(newsVo, NewsEntity.class);
        if (StrUtil.isBlank(newsEntity.getImage())) {
            // 随机的头图
            newsEntity.setImage(fileTemplatePlus.removeHost(fileTemplatePlus.randomImage()));
        }
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
    public NewsVo info(Long id) {
        NewsEntity entity = iNewsManage.findById(id);
        if (null == entity) {
            throw new BusinessException(id + ":资讯不存在");
        }
        NewsInfoEntity infoEntity = newsInfoManage.findById(id);
        if (null == infoEntity) {
            throw new BusinessException("资讯详情不存在");
        }
        NewsVo newsVo = BeanUtils.copyProperties(entity, NewsVo.class);
        newsVo.setContent(infoEntity.getContent());
        newsVo.setType(infoEntity.getType());
        // 访问量
        newsVo.setView(articleViewComponent.incrementView(ConstantEnum.SEARCH_INFO_TYPE_NEWS.getValue(), id, entity.getView()));
        // 标签

        List<LabelVo> labelVos = labelService.findByArticleId(newsVo.getId());
        newsVo.setLabelVos(labelVos);
        return newsVo;
    }


}
