package com.wblog.info.conver;

import com.apes.hub.api.module.info.vo.LabelVo;
import com.apes.hub.data.conver.AbstractBeanConver;
import com.apes.hub.info.entity.LabelEntity;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 标签 manage
 * </p>
 *
 * @author luyanan
 * @since 2020-06-10
 */
@Component

public class LabelConver extends AbstractBeanConver<LabelEntity, LabelVo> {
    @Override
    protected Class<LabelEntity> sClass() {
        return LabelEntity.class;
    }

    @Override
    protected Class<LabelVo> dClass() {
        return LabelVo.class;
    }
}
