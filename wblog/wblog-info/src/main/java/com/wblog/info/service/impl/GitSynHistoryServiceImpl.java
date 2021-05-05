package com.wblog.info.service.impl;

import com.apes.hub.api.module.info.vo.GitSynDataVo;
import com.apes.hub.api.module.info.vo.GitSynHistoryVo;
import com.apes.hub.api.page.PageInfo;
import com.apes.hub.api.page.PageInfoContentHandler;
import com.apes.hub.api.page.PageRequestParams;
import com.apes.hub.core.page.MybatisPlusUtils;
import com.apes.hub.info.conver.GitSynHistoryConver;
import com.apes.hub.info.entity.GitSynHistoryEntity;
import com.apes.hub.info.manage.IGitSynHistoryManage;
import com.apes.hub.info.service.IGitSynDataService;
import com.apes.hub.info.service.IGitSynHistoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * git同步历史 serviceImpl
 * </p>
 *
 * @author luyanan
 * @since 2020-06-10
 */
@Service
@Slf4j
public class GitSynHistoryServiceImpl implements IGitSynHistoryService {


    @Autowired
    private IGitSynHistoryManage iGitSynHistoryManage;


    @Autowired
    private GitSynHistoryConver gitSynHistoryConver;


    @Autowired
    private MybatisPlusUtils plusUtils;

    @Autowired
    private IGitSynDataService gitSynDataService;

    @Override
    public PageInfo<GitSynHistoryVo> findByPage(PageRequestParams<GitSynHistoryVo> pageRequestParams) {
        PageRequestParams<GitSynHistoryEntity> params = plusUtils.convertPageRequestParams(pageRequestParams, GitSynHistoryEntity.class, gitSynHistoryConver);
        PageInfo<GitSynHistoryEntity> entityPageInfo = iGitSynHistoryManage.findByPage(params);
        return plusUtils.convertPageInfo(entityPageInfo, GitSynHistoryVo.class, gitSynHistoryConver, new PageInfoContentHandler<GitSynHistoryVo>() {
            @Override
            public void handler(List<GitSynHistoryVo> contentList) {
                if (contentList.isEmpty()) {
                    return;
                }
                List<Long> gitIds = contentList.stream().map(GitSynHistoryVo::getGitId).distinct().collect(Collectors.toList());
                List<GitSynDataVo> gitSynDataVos = gitSynDataService.findByIds(gitIds);
                for (GitSynHistoryVo gitSynHistoryVo : contentList) {
                    for (GitSynDataVo gitSynDataVo : gitSynDataVos) {
                        if (gitSynHistoryVo.getGitId().equals(gitSynDataVo.getId())) {
                            gitSynHistoryVo.setProjectName(gitSynDataVo.getProjectName());
                        }
                    }
                }
            }
        });
    }

    @Override
    public GitSynHistoryVo findById(Long id) {
        GitSynHistoryEntity gitSynHistoryEntity = iGitSynHistoryManage.findById(id);
        if (gitSynHistoryEntity == null) {
            return null;
        }
        return gitSynHistoryConver.map(gitSynHistoryEntity, GitSynHistoryVo.class);
    }

    @Override
    public List<GitSynHistoryVo> findList(GitSynHistoryVo gitSynHistoryVo) {
        GitSynHistoryEntity GitSynHistoryEntity = gitSynHistoryConver.map(gitSynHistoryVo, GitSynHistoryEntity.class);
        List<GitSynHistoryEntity> list = iGitSynHistoryManage.findList(GitSynHistoryEntity);
        return gitSynHistoryConver.mapAsList(list, GitSynHistoryVo.class);
    }

    @Override
    public List<GitSynHistoryVo> findByIds(List<Long> ids) {
        List<GitSynHistoryEntity> entities = iGitSynHistoryManage.findByIds(ids);
        return gitSynHistoryConver.mapAsList(entities, GitSynHistoryVo.class);
    }

    @Override
    public Long save(GitSynHistoryVo gitSynHistoryVo) {
        GitSynHistoryEntity gitSynHistoryEntity = gitSynHistoryConver.map(gitSynHistoryVo, GitSynHistoryEntity.class);
        iGitSynHistoryManage.insert(gitSynHistoryEntity);
        return gitSynHistoryEntity.getId();
    }

    @Override
    public void update(GitSynHistoryVo gitSynHistoryVo) {
        GitSynHistoryEntity gitSynHistoryEntity = gitSynHistoryConver.map(gitSynHistoryVo, GitSynHistoryEntity.class);
        iGitSynHistoryManage.update(gitSynHistoryEntity);

    }

    @Override
    public void deleteByIds(List<Long> ids) {
        iGitSynHistoryManage.deleteBatch(new GitSynHistoryEntity(), ids);
    }

    @Override
    public void deleteById(Long id) {
        iGitSynHistoryManage.deleteById(GitSynHistoryEntity.builder().id(id).build());
    }

}
