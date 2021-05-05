package com.wblog.info.conver;

import com.apes.hub.api.module.info.vo.NewsVo;
import com.apes.hub.data.conver.AbstractBeanConver;
import com.apes.hub.info.entity.NewsEntity;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 热门资讯 manage
 * </p>
 *
 * @author luyanan
 * @since 2020-07-13
 */
@Component

public class NewsConver extends AbstractBeanConver<NewsEntity, NewsVo> {
    @Override
    protected Class<NewsEntity> sClass() {
        return NewsEntity.class;
    }

    @Override
    protected Class<NewsVo> dClass() {
        return NewsVo.class;
    }
}
