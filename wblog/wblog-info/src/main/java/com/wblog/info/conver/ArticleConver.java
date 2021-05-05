package com.wblog.info.conver;

import com.apes.hub.api.module.info.vo.ArticleVo;
import com.apes.hub.data.conver.AbstractBeanConver;
import com.apes.hub.info.entity.ArticleEntity;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 文章表 manage
 * </p>
 *
 * @author luyanan
 * @since 2020-06-10
 */
@Component
public class ArticleConver extends AbstractBeanConver<ArticleEntity, ArticleVo> {
    @Override
    protected Class<ArticleEntity> sClass() {
        return ArticleEntity.class;
    }

    @Override
    protected Class<ArticleVo> dClass() {
        return ArticleVo.class;
    }
}
