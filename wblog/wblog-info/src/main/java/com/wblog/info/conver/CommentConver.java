package com.wblog.info.conver;

import com.apes.hub.api.module.info.vo.CommentVo;
import com.apes.hub.data.conver.AbstractBeanConver;
import com.apes.hub.info.entity.CommentEntity;
import org.springframework.stereotype.Component;

/**
 * @author luyanan
 * @since 2020/9/19
 * <p>评论类型转换</p>
 **/
@Component
public class CommentConver extends AbstractBeanConver<CommentEntity, CommentVo> {


    @Override
    protected Class<CommentEntity> sClass() {
        return CommentEntity.class;
    }

    @Override
    protected Class<CommentVo> dClass() {
        return CommentVo.class;
    }
}
