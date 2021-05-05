package com.wblog.info.conver;

import com.apes.hub.api.module.info.vo.ChainCollectionClassifyVo;
import com.apes.hub.data.conver.AbstractBeanConver;
import com.apes.hub.info.entity.ChainCollectionClassifyEntity;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 链藏分类表 manage
 * </p>
 *
 * @author luyanan
 * @since 2020-06-23
 */
@Component

public class ChainCollectionClassifyConver extends AbstractBeanConver<ChainCollectionClassifyEntity, ChainCollectionClassifyVo> {
    @Override
    protected Class<ChainCollectionClassifyEntity> sClass() {
        return ChainCollectionClassifyEntity.class;
    }

    @Override
    protected Class<ChainCollectionClassifyVo> dClass() {
        return ChainCollectionClassifyVo.class;
    }
}
