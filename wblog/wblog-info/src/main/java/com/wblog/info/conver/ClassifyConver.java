package com.wblog.info.conver;

import com.apes.hub.api.module.info.vo.ClassifyVo;
import com.apes.hub.data.conver.AbstractBeanConver;
import com.apes.hub.info.entity.ClassifyEntity;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 文章分类 manage
 * </p>
 *
 * @author luyanan
 * @since 2020-06-10
 */
@Component

public class ClassifyConver extends AbstractBeanConver<ClassifyEntity, ClassifyVo> {
    @Override
    protected Class<ClassifyEntity> sClass() {
        return ClassifyEntity.class;
    }

    @Override
    protected Class<ClassifyVo> dClass() {
        return ClassifyVo.class;
    }
}
