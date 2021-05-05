package com.wblog.info.conver;

import com.apes.hub.api.module.info.vo.GitSynVo;
import com.apes.hub.data.conver.AbstractBeanConver;
import com.apes.hub.info.entity.GitSynEntity;
import org.springframework.stereotype.Component;

/**
* <p>
* 同步表 manage
* </p>
*
* @author luyanan
* @since 2020-06-10
*/
    @Component

public class GitSynConver extends AbstractBeanConver<GitSynEntity, GitSynVo> {
@Override
protected Class<GitSynEntity> sClass() {
    return GitSynEntity.class;
    }

    @Override
    protected Class<GitSynVo> dClass() {
        return GitSynVo.class;
    }
}
