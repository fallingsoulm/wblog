package com.wblog.info.service.impl;

import com.wblog.common.module.info.vo.GitSynDataVo;
import com.wblog.common.module.info.vo.GitSynHistoryVo;
import com.wblog.info.entity.GitSynHistoryEntity;
import com.wblog.info.manage.IGitSynHistoryManage;
import com.wblog.info.service.IGitSynDataService;
import com.wblog.info.service.IGitSynHistoryService;
import io.github.fallingsoulm.easy.archetype.data.mybatisplus.MybatisPlusUtils;
import io.github.fallingsoulm.easy.archetype.data.mybatisplus.PageInfoContentHandler;
import io.github.fallingsoulm.easy.archetype.framework.page.PageInfo;
import io.github.fallingsoulm.easy.archetype.framework.page.PageRequestParams;
import io.github.fallingsoulm.easy.archetype.framework.utils.BeanUtils;
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
    private MybatisPlusUtils plusUtils;

    @Autowired
    private IGitSynDataService gitSynDataService;

    @Override
    public PageInfo<GitSynHistoryVo> findByPage(PageRequestParams<GitSynHistoryVo> pageRequestParams) {
        PageRequestParams<GitSynHistoryEntity> params = plusUtils.convertPageRequestParams(pageRequestParams, GitSynHistoryEntity.class);
        PageInfo<GitSynHistoryEntity> entityPageInfo = iGitSynHistoryManage.listByPage(params);
        return plusUtils.convertPageInfo(entityPageInfo, GitSynHistoryVo.class, new PageInfoContentHandler<GitSynHistoryVo>() {
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
        return BeanUtils.copyProperties(gitSynHistoryEntity, GitSynHistoryVo.class);
    }

    @Override
    public List<GitSynHistoryVo> findList(GitSynHistoryVo gitSynHistoryVo) {
        GitSynHistoryEntity GitSynHistoryEntity = BeanUtils.copyProperties(gitSynHistoryVo, GitSynHistoryEntity.class);
        List<GitSynHistoryEntity> list = iGitSynHistoryManage.list(GitSynHistoryEntity);
        return BeanUtils.copyList(list, GitSynHistoryVo.class);
    }

    @Override
    public List<GitSynHistoryVo> findByIds(List<Long> ids) {
        List<GitSynHistoryEntity> entities = iGitSynHistoryManage.findByIds(ids);
        return BeanUtils.copyList(entities, GitSynHistoryVo.class);
    }

    @Override
    public Long save(GitSynHistoryVo gitSynHistoryVo) {
        GitSynHistoryEntity gitSynHistoryEntity = BeanUtils.copyProperties(gitSynHistoryVo, GitSynHistoryEntity.class);
        iGitSynHistoryManage.insert(gitSynHistoryEntity);
        return gitSynHistoryEntity.getId();
    }

    @Override
    public void update(GitSynHistoryVo gitSynHistoryVo) {
        GitSynHistoryEntity gitSynHistoryEntity = BeanUtils.copyProperties(gitSynHistoryVo, GitSynHistoryEntity.class);
        iGitSynHistoryManage.update(gitSynHistoryEntity);

    }

    @Override
    public void deleteByIds(List<Long> ids) {
        iGitSynHistoryManage.deleteBatch(ids);
    }

    @Override
    public void deleteById(Long id) {
        iGitSynHistoryManage.deleteById(GitSynHistoryEntity.builder().id(id).build());
    }

}
