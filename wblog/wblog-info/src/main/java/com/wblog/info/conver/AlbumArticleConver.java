package com.wblog.info.conver;

import com.apes.hub.api.module.info.vo.AlbumArticleVo;
import com.apes.hub.data.conver.AbstractBeanConver;
import com.apes.hub.info.entity.AlbumArticleEntity;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 文章与专辑关联 manage
 * </p>
 *
 * @author luyanan
 * @since 2020-07-15
 */
@Service("albumArticleConver")
public class AlbumArticleConver extends AbstractBeanConver<AlbumArticleEntity, AlbumArticleVo> {
    @Override
    protected Class<AlbumArticleEntity> sClass() {
        return AlbumArticleEntity.class;
    }

    @Override
    protected Class<AlbumArticleVo> dClass() {
        return AlbumArticleVo.class;
    }
}
