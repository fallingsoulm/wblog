package com.wblog.info.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.apes.hub.api.module.info.vo.ArticleLabelVo;
import com.apes.hub.api.module.info.vo.LabelVo;
import com.apes.hub.api.page.PageInfo;
import com.apes.hub.api.page.PageRequestParams;
import com.apes.hub.core.page.MybatisPlusUtils;
import com.apes.hub.info.conver.ArticleLabelConver;
import com.apes.hub.info.entity.ArticleLabelEntity;
import com.apes.hub.info.manage.IArticleLabelManage;
import com.apes.hub.info.service.IArticleLabelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 文章与标签关联 serviceImpl
 * </p>
 *
 * @author luyanan
 * @since 2020-06-10
 */
@Service
@Slf4j
public class ArticleLabelServiceImpl implements IArticleLabelService {


    @Autowired
    private IArticleLabelManage iArticleLabelManage;


    @Autowired
    private ArticleLabelConver articleLabelConver;


    @Autowired
    private MybatisPlusUtils plusUtils;


    @Override
    public PageInfo<ArticleLabelVo> findByPage(PageRequestParams<ArticleLabelVo> pageRequestParams) {
        PageRequestParams<ArticleLabelEntity> params = plusUtils.convertPageRequestParams(pageRequestParams, ArticleLabelEntity.class, articleLabelConver);
        PageInfo<ArticleLabelEntity> entityPageInfo = iArticleLabelManage.findByPage(params);
        return plusUtils.convertPageInfo(entityPageInfo, ArticleLabelVo.class, articleLabelConver);
    }

    @Override
    public ArticleLabelVo findById(Long id) {
        ArticleLabelEntity articleLabelEntity = iArticleLabelManage.findById(id);
        if (articleLabelEntity == null) {
            return null;
        }
        return articleLabelConver.map(articleLabelEntity, ArticleLabelVo.class);
    }

    @Override
    public List<ArticleLabelVo> findList(ArticleLabelVo articleLabelVo) {
        ArticleLabelEntity ArticleLabelEntity = articleLabelConver.map(articleLabelVo, ArticleLabelEntity.class);
        List<ArticleLabelEntity> list = iArticleLabelManage.findList(ArticleLabelEntity);
        return articleLabelConver.mapAsList(list, ArticleLabelVo.class);
    }

    @Override
    public List<ArticleLabelVo> findByIds(List<Long> ids) {
        List<ArticleLabelEntity> entities = iArticleLabelManage.findByIds(ids);
        return articleLabelConver.mapAsList(entities, ArticleLabelVo.class);
    }

    @Override
    public Long save(ArticleLabelVo articleLabelVo) {
        ArticleLabelEntity articleLabelEntity = articleLabelConver.map(articleLabelVo, ArticleLabelEntity.class);
        iArticleLabelManage.insert(articleLabelEntity);
        return articleLabelEntity.getId();
    }

    @Override
    public void update(ArticleLabelVo articleLabelVo) {
        ArticleLabelEntity articleLabelEntity = articleLabelConver.map(articleLabelVo, ArticleLabelEntity.class);
        iArticleLabelManage.update(articleLabelEntity);

    }

    @Override
    public void deleteByIds(List<Long> ids) {
        iArticleLabelManage.deleteBatch(new ArticleLabelEntity(), ids);
    }

    @Override
    public void deleteById(Long id) {
        iArticleLabelManage.deleteById(ArticleLabelEntity.builder().id(id).build());
    }

    @Override
    public void delete(ArticleLabelVo articleLabelVo) {
        iArticleLabelManage.delete(articleLabelConver.map(articleLabelVo, ArticleLabelEntity.class));
    }

    @Override
    public PageInfo<LabelVo> findByPageAndCount(PageRequestParams<LabelVo> pageRequestParams) {
        Integer count = iArticleLabelManage.findAndCount(pageRequestParams.getParams());
        List<LabelVo> labelVos = iArticleLabelManage.listAndCount(pageRequestParams);

        return new PageInfo<LabelVo>(labelVos, count, pageRequestParams);
    }

    @Override
    public Integer count(ArticleLabelVo articleLabelVo) {
        ArticleLabelEntity entity = articleLabelConver.map(articleLabelVo, ArticleLabelEntity.class);
        return iArticleLabelManage.count(entity);
    }

    @Override
    public void saveBatch(List<ArticleLabelVo> articleLabelVoList) {


        if (CollectionUtil.isEmpty(articleLabelVoList)) {
            return;
        }

        List<ArticleLabelEntity> articleLabelEntities = articleLabelConver.mapAsList(articleLabelVoList, ArticleLabelEntity.class);
        this.iArticleLabelManage.insertBatch(articleLabelEntities);

    }


}
