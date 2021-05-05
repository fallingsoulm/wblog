package com.wblog.info.conver;

import com.apes.hub.api.module.info.vo.ChainCollectionVo;
import com.apes.hub.data.conver.AbstractBeanConver;
import com.apes.hub.info.entity.ChainCollectionEntity;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 链藏 manage
 * </p>
 *
 * @author luyanan
 * @since 2020-06-23
 */
@Component

public class ChainCollectionConver extends AbstractBeanConver<ChainCollectionEntity, ChainCollectionVo> {
    @Override
    protected Class<ChainCollectionEntity> sClass() {
        return ChainCollectionEntity.class;
    }

    @Override
    protected Class<ChainCollectionVo> dClass() {
        return ChainCollectionVo.class;
    }
}
