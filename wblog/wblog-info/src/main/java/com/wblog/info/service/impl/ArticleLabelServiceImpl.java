package com.wblog.info.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.wblog.common.module.info.vo.ArticleLabelVo;
import com.wblog.common.module.info.vo.LabelVo;
import com.wblog.info.entity.ArticleLabelEntity;
import com.wblog.info.manage.IArticleLabelManage;
import com.wblog.info.service.IArticleLabelService;
import io.github.fallingsoulm.easy.archetype.data.mybatisplus.MybatisPlusUtils;
import io.github.fallingsoulm.easy.archetype.framework.page.PageInfo;
import io.github.fallingsoulm.easy.archetype.framework.page.PageRequestParams;
import io.github.fallingsoulm.easy.archetype.framework.utils.BeanUtils;
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
    private MybatisPlusUtils plusUtils;


    @Override
    public PageInfo<ArticleLabelVo> findByPage(PageRequestParams<ArticleLabelVo> pageRequestParams) {
        PageRequestParams<ArticleLabelEntity> params = plusUtils.convertPageRequestParams(pageRequestParams, ArticleLabelEntity.class);
        PageInfo<ArticleLabelEntity> entityPageInfo = iArticleLabelManage.listByPage(params);
        return plusUtils.convertPageInfo(entityPageInfo, ArticleLabelVo.class);
    }

    @Override
    public ArticleLabelVo findById(Long id) {
        ArticleLabelEntity articleLabelEntity = iArticleLabelManage.findById(id);
        if (articleLabelEntity == null) {
            return null;
        }
        return BeanUtils.copyProperties(articleLabelEntity, ArticleLabelVo.class);
    }

    @Override
    public List<ArticleLabelVo> findList(ArticleLabelVo articleLabelVo) {
        ArticleLabelEntity ArticleLabelEntity = BeanUtils.copyProperties(articleLabelVo, ArticleLabelEntity.class);
        List<ArticleLabelEntity> list = iArticleLabelManage.list(ArticleLabelEntity);
        return BeanUtils.copyList(list, ArticleLabelVo.class);
    }

    @Override
    public List<ArticleLabelVo> findByIds(List<Long> ids) {
        List<ArticleLabelEntity> entities = iArticleLabelManage.findByIds(ids);
        return BeanUtils.copyList(entities, ArticleLabelVo.class);
    }

    @Override
    public Long save(ArticleLabelVo articleLabelVo) {
        ArticleLabelEntity articleLabelEntity = BeanUtils.copyProperties(articleLabelVo, ArticleLabelEntity.class);
        iArticleLabelManage.insert(articleLabelEntity);
        return articleLabelEntity.getId();
    }

    @Override
    public void update(ArticleLabelVo articleLabelVo) {
        ArticleLabelEntity articleLabelEntity = BeanUtils.copyProperties(articleLabelVo, ArticleLabelEntity.class);
        iArticleLabelManage.update(articleLabelEntity);

    }

    @Override
    public void deleteByIds(List<Long> ids) {
        iArticleLabelManage.deleteBatch(ids);
    }

    @Override
    public void deleteById(Long id) {
        iArticleLabelManage.deleteById(ArticleLabelEntity.builder().id(id).build());
    }

    @Override
    public void delete(ArticleLabelVo articleLabelVo) {
        iArticleLabelManage.delete(BeanUtils.copyProperties(articleLabelVo, ArticleLabelEntity.class));
    }

    @Override
    public PageInfo<LabelVo> findByPageAndCount(PageRequestParams<LabelVo> pageRequestParams) {
        Integer count = iArticleLabelManage.findAndCount(pageRequestParams.getParams());
        List<LabelVo> labelVos = iArticleLabelManage.listAndCount(pageRequestParams);

        return new PageInfo<LabelVo>(labelVos, count.longValue(), pageRequestParams);
    }

    @Override
    public Integer count(ArticleLabelVo articleLabelVo) {
        ArticleLabelEntity entity = BeanUtils.copyProperties(articleLabelVo, ArticleLabelEntity.class);
        return iArticleLabelManage.count(entity);
    }

    @Override
    public void saveBatch(List<ArticleLabelVo> articleLabelVoList) {


        if (CollectionUtil.isEmpty(articleLabelVoList)) {
            return;
        }

        List<ArticleLabelEntity> articleLabelEntities = BeanUtils.copyList(articleLabelVoList, ArticleLabelEntity.class);
        this.iArticleLabelManage.insertBatch(articleLabelEntities);

    }


}
