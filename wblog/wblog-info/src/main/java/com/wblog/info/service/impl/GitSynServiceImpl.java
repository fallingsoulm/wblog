package com.wblog.info.service.impl;

import com.wblog.common.module.info.vo.GitSynVo;
import com.wblog.info.entity.GitSynEntity;
import com.wblog.info.manage.IGitSynManage;
import com.wblog.info.service.IGitSynService;
import io.github.fallingsoulm.easy.archetype.data.mybatisplus.MybatisPlusUtils;
import io.github.fallingsoulm.easy.archetype.framework.page.PageInfo;
import io.github.fallingsoulm.easy.archetype.framework.page.PageRequestParams;
import io.github.fallingsoulm.easy.archetype.framework.utils.BeanUtils;
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
    private MybatisPlusUtils plusUtils;


    @Override
    public PageInfo<GitSynVo> findByPage(PageRequestParams<GitSynVo> pageRequestParams) {
        PageRequestParams<GitSynEntity> params = plusUtils.convertPageRequestParams(pageRequestParams, GitSynEntity.class);
        PageInfo<GitSynEntity> entityPageInfo = iGitSynManage.listByPage(params);
        return plusUtils.convertPageInfo(entityPageInfo, GitSynVo.class);
    }

    @Override
    public GitSynVo findById(Long id) {
        GitSynEntity gitSynEntity = iGitSynManage.findById(id);
        if (gitSynEntity == null) {
            return null;
        }
        return BeanUtils.copyProperties(gitSynEntity, GitSynVo.class);
    }

    @Override
    public List<GitSynVo> findList(GitSynVo gitSynVo) {
        GitSynEntity GitSynEntity = BeanUtils.copyProperties(gitSynVo, GitSynEntity.class);
        List<GitSynEntity> list = iGitSynManage.list(GitSynEntity);
        return BeanUtils.copyList(list, GitSynVo.class);
    }

    @Override
    public List<GitSynVo> findByIds(List<Long> ids) {
        List<GitSynEntity> entities = iGitSynManage.findByIds(ids);
        return BeanUtils.copyList(entities, GitSynVo.class);
    }

    @Override
    public Long save(GitSynVo gitSynVo) {
        GitSynEntity gitSynEntity = BeanUtils.copyProperties(gitSynVo, GitSynEntity.class);
        iGitSynManage.insert(gitSynEntity);
        return gitSynEntity.getId();
    }

    @Override
    public void update(GitSynVo gitSynVo) {
        GitSynEntity gitSynEntity = BeanUtils.copyProperties(gitSynVo, GitSynEntity.class);
        iGitSynManage.update(gitSynEntity);

    }

    @Override
    public void deleteByIds(List<Long> ids) {
        iGitSynManage.deleteBatch(ids);
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
        this.iGitSynManage.insertBatch(BeanUtils.copyList(gitSynVos, GitSynEntity.class));

    }

    @Override
    public void deleteGitId(Long gitId) {

        this.iGitSynManage.delete(GitSynEntity.builder().gitId(gitId).build());


    }

    @Override
    public GitSynVo findOne(GitSynVo gitSynVo) {
        GitSynEntity entity = BeanUtils.copyProperties(gitSynVo, GitSynEntity.class);
        GitSynEntity synEntity = iGitSynManage.findOne(entity);
        if (null == synEntity) {
            return null;
        }
        return BeanUtils.copyProperties(synEntity, GitSynVo.class);
    }

    @Override
    public List<GitSynVo> findByArticleIds(List<Long> articleIds) {


        if (CollectionUtils.isEmpty(articleIds)) {

            return new ArrayList<>();
        }

        List<GitSynEntity> gitSynEntities = iGitSynManage.findByArticleIds(articleIds);
        return BeanUtils.copyList(gitSynEntities, GitSynVo.class);

    }

}
