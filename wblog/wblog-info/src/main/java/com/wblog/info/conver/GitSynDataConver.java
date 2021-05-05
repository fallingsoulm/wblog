package com.wblog.info.conver;

import com.apes.hub.api.module.info.vo.GitSynDataVo;
import com.apes.hub.data.conver.AbstractBeanConver;
import com.apes.hub.info.entity.GitSynDataEntity;
import org.springframework.stereotype.Component;

/**
 * <p>
 * GitSynData Bean转换
 * </p>
 *
 * @author luyanan
 * @since 2020-05-28
 */
@Component
public class GitSynDataConver extends AbstractBeanConver<GitSynDataEntity, GitSynDataVo> {
    @Override
    protected Class<GitSynDataEntity> sClass() {
        return GitSynDataEntity.class;
    }

    @Override
    protected Class<GitSynDataVo> dClass() {
        return GitSynDataVo.class;
    }
}
