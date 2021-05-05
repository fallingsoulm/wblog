package com.wblog.info.conver;

import com.apes.hub.api.module.info.vo.ArticleLabelVo;
import com.apes.hub.data.conver.AbstractBeanConver;
import com.apes.hub.info.entity.ArticleLabelEntity;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 文章与标签关联 manage
 * </p>
 *
 * @author luyanan
 * @since 2020-06-10
 */
@Component

public class ArticleLabelConver extends AbstractBeanConver<ArticleLabelEntity, ArticleLabelVo> {
    @Override
    protected Class<ArticleLabelEntity> sClass() {
        return ArticleLabelEntity.class;
    }

    @Override
    protected Class<ArticleLabelVo> dClass() {
        return ArticleLabelVo.class;
    }
}
