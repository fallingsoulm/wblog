package com.wblog.info.conver;

import com.apes.hub.api.module.info.vo.GitSynHistoryVo;
import com.apes.hub.data.conver.AbstractBeanConver;
import com.apes.hub.info.entity.GitSynHistoryEntity;
import org.springframework.stereotype.Component;

/**
 * <p>
 * git同步历史 manage
 * </p>
 *
 * @author luyanan
 * @since 2020-06-10
 */
@Component
public class GitSynHistoryConver extends AbstractBeanConver<GitSynHistoryEntity, GitSynHistoryVo> {
    @Override
    protected Class<GitSynHistoryEntity> sClass() {
        return GitSynHistoryEntity.class;
    }

    @Override
    protected Class<GitSynHistoryVo> dClass() {
        return GitSynHistoryVo.class;
    }
}
