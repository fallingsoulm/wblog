package com.wblog.info.service.impl;

import com.apes.hub.api.module.info.vo.GitSynVo;
import com.apes.hub.api.page.PageInfo;
import com.apes.hub.api.page.PageRequestParams;
import com.apes.hub.core.page.MybatisPlusUtils;
import com.apes.hub.info.conver.GitSynConver;
import com.apes.hub.info.entity.GitSynEntity;
import com.apes.hub.info.manage.IGitSynManage;
import com.apes.hub.info.service.IGitSynService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 同步表 serviceImpl
 * </p>
 *
 * @author luyanan
 * @since 2020-06-10
 */
@Service
@Slf4j
public class GitSynServiceImpl implements IGitSynService {


    @Autowired
    private IGitSynManage iGitSynManage;


    @Autowired
    private GitSynConver gitSynConver;


    @Autowired
    private MybatisPlusUtils plusUtils;


    @Override
    public PageInfo<GitSynVo> findByPage(PageRequestParams<GitSynVo> pageRequestParams) {
        PageRequestParams<GitSynEntity> params = plusUtils.convertPageRequestParams(pageRequestParams, GitSynEntity.class, gitSynConver);
        PageInfo<GitSynEntity> entityPageInfo = iGitSynManage.findByPage(params);
        return plusUtils.convertPageInfo(entityPageInfo, GitSynVo.class, gitSynConver);
    }

    @Override
    public GitSynVo findById(Long id) {
        GitSynEntity gitSynEntity = iGitSynManage.findById(id);
        if (gitSynEntity == null) {
            return null;
        }
        return gitSynConver.map(gitSynEntity, GitSynVo.class);
    }

    @Override
    public List<GitSynVo> findList(GitSynVo gitSynVo) {
        GitSynEntity GitSynEntity = gitSynConver.map(gitSynVo, GitSynEntity.class);
        List<GitSynEntity> list = iGitSynManage.findList(GitSynEntity);
        return gitSynConver.mapAsList(list, GitSynVo.class);
    }

    @Override
    public List<GitSynVo> findByIds(List<Long> ids) {
        List<GitSynEntity> entities = iGitSynManage.findByIds(ids);
        return gitSynConver.mapAsList(entities, GitSynVo.class);
    }

    @Override
    public Long save(GitSynVo gitSynVo) {
        GitSynEntity gitSynEntity = gitSynConver.map(gitSynVo, GitSynEntity.class);
        iGitSynManage.insert(gitSynEntity);
        return gitSynEntity.getId();
    }

    @Override
    public void update(GitSynVo gitSynVo) {
        GitSynEntity gitSynEntity = gitSynConver.map(gitSynVo, GitSynEntity.class);
        iGitSynManage.update(gitSynEntity);

    }

    @Override
    public void deleteByIds(List<Long> ids) {
        iGitSynManage.deleteBatch(new GitSynEntity(), ids);
    }

    @Override
    public void deleteById(Long id) {
        iGitSynManage.deleteById(GitSynEntity.builder().id(id).build());
    }

    @Override
    public void saveBatch(List<GitSynVo> gitSynVos) {
        if (gitSynVos.isEmpty()) {
            return;
        }

        for (GitSynVo gitSynVo : gitSynVos) {
            gitSynVo.setUpdateTime(new Date());
        }
        this.iGitSynManage.insertBatch(gitSynConver.mapAsList(gitSynVos, GitSynEntity.class));

    }

    @Override
    public void deleteGitId(Long gitId) {

        this.iGitSynManage.delete(GitSynEntity.builder().gitId(gitId).build());


    }

    @Override
    public GitSynVo findOne(GitSynVo gitSynVo) {
        GitSynEntity entity = gitSynConver.map(gitSynVo, GitSynEntity.class);
        GitSynEntity synEntity = iGitSynManage.findOne(entity);
        if (null == synEntity) {
            return null;
        }
        return gitSynConver.map(synEntity, GitSynVo.class);
    }

    @Override
    public List<GitSynVo> findByArticleIds(List<Long> articleIds) {


        if (CollectionUtils.isEmpty(articleIds)) {

            return new ArrayList<>();
        }

        List<GitSynEntity> gitSynEntities = iGitSynManage.findByArticleIds(articleIds);
        return gitSynConver.mapAsList(gitSynEntities, GitSynVo.class);

    }

}
