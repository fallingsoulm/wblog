package com.wblog.info.service.impl;

import com.apes.hub.api.module.info.vo.ArticleInfoVo;
import com.apes.hub.api.page.PageInfo;
import com.apes.hub.api.page.PageRequestParams;
import com.apes.hub.core.page.MybatisPlusUtils;
import com.apes.hub.info.conver.ArticleInfoConver;
import com.apes.hub.info.entity.ArticleInfoEntity;
import com.apes.hub.info.manage.IArticleInfoManage;
import com.apes.hub.info.service.IArticleInfoService;
import com.apes.hub.info.service.IGitSynService;
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
    private ArticleInfoConver articleInfoConver;


    @Autowired
    private MybatisPlusUtils plusUtils;

    @Autowired
    private IGitSynService gitSynService;

    @Override
    public PageInfo<ArticleInfoVo> findByPage(PageRequestParams<ArticleInfoVo> pageRequestParams) {
        PageRequestParams<ArticleInfoEntity> params = plusUtils.convertPageRequestParams(pageRequestParams, ArticleInfoEntity.class, articleInfoConver);
        PageInfo<ArticleInfoEntity> entityPageInfo = iArticleInfoManage.findByPage(params);
        return plusUtils.convertPageInfo(entityPageInfo, ArticleInfoVo.class, articleInfoConver);
    }

    @Override
    public ArticleInfoVo findById(Long articleId) {
        ArticleInfoEntity articleInfoEntity = iArticleInfoManage.findById(articleId);
        if (articleInfoEntity == null) {
            return null;
        }
        return articleInfoConver.map(articleInfoEntity, ArticleInfoVo.class);
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
        ArticleInfoEntity ArticleInfoEntity = articleInfoConver.map(articleInfoVo, ArticleInfoEntity.class);
        List<ArticleInfoEntity> list = iArticleInfoManage.findList(ArticleInfoEntity);
        return articleInfoConver.mapAsList(list, ArticleInfoVo.class);
    }

    @Override
    public List<ArticleInfoVo> findByIds(List<Long> articleIds) {
        List<ArticleInfoEntity> entities = iArticleInfoManage.findByIds(articleIds);
        return articleInfoConver.mapAsList(entities, ArticleInfoVo.class);
    }

    @Override
    public Long save(ArticleInfoVo articleInfoVo) {
        ArticleInfoEntity articleInfoEntity = articleInfoConver.map(articleInfoVo, ArticleInfoEntity.class);
        iArticleInfoManage.insert(articleInfoEntity);
        return articleInfoEntity.getArticleId();
    }

    @Override
    public void update(ArticleInfoVo articleInfoVo) {
        ArticleInfoEntity articleInfoEntity = articleInfoConver.map(articleInfoVo, ArticleInfoEntity.class);
        iArticleInfoManage.update(articleInfoEntity);
    }

    @Override
    public void deleteByIds(List<Long> articleIds) {
        iArticleInfoManage.deleteBatch(new ArticleInfoEntity(), articleIds);
    }

    @Override
    public void deleteById(Long articleId) {
        iArticleInfoManage.deleteById(ArticleInfoEntity.builder().articleId(articleId).build());
    }


}
