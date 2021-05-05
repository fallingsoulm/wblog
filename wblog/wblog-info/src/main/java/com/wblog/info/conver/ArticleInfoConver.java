package com.wblog.info.conver;

import com.apes.hub.api.module.info.vo.ArticleInfoVo;
import com.apes.hub.data.conver.AbstractBeanConver;
import com.apes.hub.info.entity.ArticleInfoEntity;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 文章详情表 manage
 * </p>
 *
 * @author luyanan
 * @since 2020-06-10
 */
@Component

public class ArticleInfoConver extends AbstractBeanConver<ArticleInfoEntity, ArticleInfoVo> {
    @Override
    protected Class<ArticleInfoEntity> sClass() {
        return ArticleInfoEntity.class;
    }

    @Override
    protected Class<ArticleInfoVo> dClass() {
        return ArticleInfoVo.class;
    }
}
