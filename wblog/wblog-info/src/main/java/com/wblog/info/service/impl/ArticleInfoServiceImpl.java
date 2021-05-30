package com.wblog.info.service.impl;

import com.wblog.common.module.info.vo.ArticleInfoVo;
import com.wblog.info.entity.ArticleInfoEntity;
import com.wblog.info.manage.IArticleInfoManage;
import com.wblog.info.service.IArticleInfoService;
import com.wblog.info.service.IGitSynService;
import io.github.fallingsoulm.easy.archetype.data.mybatisplus.MybatisPlusUtils;
import io.github.fallingsoulm.easy.archetype.framework.page.PageInfo;
import io.github.fallingsoulm.easy.archetype.framework.page.PageRequestParams;
import io.github.fallingsoulm.easy.archetype.framework.utils.BeanUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 文章详情表 serviceImpl
 * </p>
 *
 * @author luyanan
 * @since 2020-06-10
 */
@Service
@Slf4j
public class ArticleInfoServiceImpl implements IArticleInfoService {


    @Autowired
    private IArticleInfoManage iArticleInfoManage;


    @Autowired
    private MybatisPlusUtils plusUtils;

    @Autowired
    private IGitSynService gitSynService;

    @Override
    public PageInfo<ArticleInfoVo> findByPage(PageRequestParams<ArticleInfoVo> pageRequestParams) {
        PageRequestParams<ArticleInfoEntity> params = plusUtils.convertPageRequestParams(pageRequestParams, ArticleInfoEntity.class);
        PageInfo<ArticleInfoEntity> entityPageInfo = iArticleInfoManage.listByPage(params);
        return plusUtils.convertPageInfo(entityPageInfo, ArticleInfoVo.class);
    }

    @Override
    public ArticleInfoVo findById(Long articleId) {
        ArticleInfoEntity articleInfoEntity = iArticleInfoManage.findById(articleId);
        if (articleInfoEntity == null) {
            return null;
        }
        return BeanUtils.copyProperties(articleInfoEntity, ArticleInfoVo.class);
    }

    @Override
    public Map<Long, ArticleInfoVo> findByIdsToMap(List<Long> articleIds) {
        Map<Long, ArticleInfoVo> map = new HashMap<>();
        this.findByIds(articleIds).stream().forEach(a -> {
            map.put(a.getArticleId(), a);
        });
        return map;
    }

    @Override
    public List<ArticleInfoVo> findList(ArticleInfoVo articleInfoVo) {
        ArticleInfoEntity ArticleInfoEntity = BeanUtils.copyProperties(articleInfoVo, ArticleInfoEntity.class);
        List<ArticleInfoEntity> list = iArticleInfoManage.list(ArticleInfoEntity);
        return BeanUtils.copyList(list, ArticleInfoVo.class);
    }

    @Override
    public List<ArticleInfoVo> findByIds(List<Long> articleIds) {
        List<ArticleInfoEntity> entities = iArticleInfoManage.findByIds(articleIds);
        return BeanUtils.copyList(entities, ArticleInfoVo.class);
    }

    @Override
    public Long save(ArticleInfoVo articleInfoVo) {
        ArticleInfoEntity articleInfoEntity = BeanUtils.copyProperties(articleInfoVo, ArticleInfoEntity.class);
        iArticleInfoManage.insert(articleInfoEntity);
        return articleInfoEntity.getArticleId();
    }

    @Override
    public void update(ArticleInfoVo articleInfoVo) {
        ArticleInfoEntity articleInfoEntity = BeanUtils.copyProperties(articleInfoVo, ArticleInfoEntity.class);
        iArticleInfoManage.update(articleInfoEntity);
    }

    @Override
    public void deleteByIds(List<Long> articleIds) {
        iArticleInfoManage.deleteBatch(articleIds);
    }

    @Override
    public void deleteById(Long articleId) {
        iArticleInfoManage.deleteById(ArticleInfoEntity.builder().articleId(articleId).build());
    }


}
