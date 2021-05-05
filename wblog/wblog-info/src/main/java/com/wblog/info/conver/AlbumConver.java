package com.wblog.info.conver;

import com.apes.hub.api.module.info.vo.AlbumVo;
import com.apes.hub.data.conver.AbstractBeanConver;
import com.apes.hub.info.entity.AlbumEntity;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 文章专辑 manage
 * </p>
 *
 * @author luyanan
 * @since 2020-07-15
 */
@Component
public class AlbumConver extends AbstractBeanConver<AlbumEntity, AlbumVo> {
    @Override
    protected Class<AlbumEntity> sClass() {
        return AlbumEntity.class;
    }

    @Override
    protected Class<AlbumVo> dClass() {
        return AlbumVo.class;
    }
}
