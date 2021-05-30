package com.wblog.info.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.wblog.common.exception.BusinessException;
import com.wblog.common.module.info.vo.ArticleLabelVo;
import com.wblog.common.module.info.vo.LabelVo;
import com.wblog.info.component.FileTemplatePlus;
import com.wblog.info.entity.LabelEntity;
import com.wblog.info.manage.ILabelManage;
import com.wblog.info.mq.service.ILabelMqService;
import com.wblog.info.service.IArticleLabelService;
import com.wblog.info.service.ILabelService;
import io.github.fallingsoulm.easy.archetype.data.file.FileTemplate;
import io.github.fallingsoulm.easy.archetype.data.mybatisplus.MybatisPlusUtils;
import io.github.fallingsoulm.easy.archetype.data.mybatisplus.PageInfoContentHandler;
import io.github.fallingsoulm.easy.archetype.framework.page.PageInfo;
import io.github.fallingsoulm.easy.archetype.framework.page.PageRequestParams;
import io.github.fallingsoulm.easy.archetype.framework.utils.BeanUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 标签 serviceImpl
 * </p>
 *
 * @author luyanan
 * @since 2020-06-10
 */
@Service
@Slf4j
public class LabelServiceImpl implements ILabelService {


    @Autowired
    private ILabelManage iLabelManage;


    @Autowired
    private MybatisPlusUtils plusUtils;

    @Autowired
    private IArticleLabelService articleLabelService;

    @Autowired
    private FileTemplate fileTemplate;

    @Autowired
    private ILabelMqService labelMqService;

    @Override
    public PageInfo<LabelVo> findByPage(PageRequestParams<LabelVo> pageRequestParams) {
        PageRequestParams<LabelEntity> params = plusUtils.convertPageRequestParams(pageRequestParams, LabelEntity.class);
        PageInfo<LabelEntity> entityPageInfo = iLabelManage.listByPage(params);
        return plusUtils.convertPageInfo(entityPageInfo, LabelVo.class, contentList -> {
            List<String> hosts = contentList.stream().map(LabelVo::getIcon).distinct().collect(Collectors.toList());
            Map<String, String> addHosts = fileTemplate.addHost(hosts);
            for (LabelVo labelVo : contentList) {
                labelVo.setIcon(addHosts.get(labelVo.getIcon()));
                Integer count = articleLabelService.count(ArticleLabelVo.builder().labelId(labelVo.getId()).build());
                labelVo.setNum(count);
                //图片处理

            }
        });
    }

    @Override
    public LabelVo findById(Long id) {
        LabelEntity labelEntity = iLabelManage.findById(id);
        if (labelEntity == null) {
            return null;
        }
        LabelVo labelVo = BeanUtils.copyProperties(labelEntity, LabelVo.class);
        labelVo.setIcon(fileTemplate.addHost(labelVo.getIcon()));
        return labelVo;
    }

    @Override
    public List<LabelVo> findList(LabelVo labelVo) {
        LabelEntity LabelEntity = BeanUtils.copyProperties(labelVo, LabelEntity.class);
        List<LabelEntity> list = iLabelManage.list(LabelEntity);
        return BeanUtils.copyList(list, LabelVo.class);
    }

    @Override
    public List<LabelVo> findByIds(List<Long> ids) {
        List<LabelEntity> entities = iLabelManage.findByIds(ids);
        return BeanUtils.copyList(entities, LabelVo.class);
    }

    @Override
    public Long save(LabelVo labelVo) {
        LabelEntity one = this.iLabelManage.findOne(LabelEntity.builder().name(labelVo.getName()).build());
        if (null != one) {
            throw new BusinessException("标签名称不允许重复");
        }
        LabelEntity labelEntity = BeanUtils.copyProperties(labelVo, LabelEntity.class);
        labelEntity.setIcon(fileTemplate.removeHost(labelEntity.getIcon()));
        iLabelManage.insert(labelEntity);
        labelMqService.addLabel(labelEntity.getId());

        return labelEntity.getId();
    }


    @Override
    public void update(LabelVo labelVo) {
        LabelEntity labelEntity = BeanUtils.copyProperties(labelVo, LabelEntity.class);
        labelEntity.setIcon(fileTemplate.removeHost(labelEntity.getIcon()));
        iLabelManage.update(labelEntity);

        labelMqService.addLabel(labelEntity.getId());
    }

    @Override
    public void deleteByIds(List<Long> ids) {

        if (CollectionUtil.isEmpty(ids)) {
            return;
        }
        for (Long id : ids) {
            articleLabelService.delete(ArticleLabelVo.builder().labelId(id).build());
        }
        iLabelManage.deleteBatch(ids);
    }

    @Override
    public void deleteById(Long id) {
        articleLabelService.delete(ArticleLabelVo.builder().labelId(id).build());
        iLabelManage.deleteById(LabelEntity.builder().id(id).build());
    }

    @Override
    public void updateLabel(Long articleId) {
//        this.articleAddLabel(articleId);
        labelMqService.articleAddLabel(articleId);
    }


    @Override
    public PageInfo<LabelVo> findByPageAndCount(PageRequestParams<LabelVo> pageRequestParams) {

        PageInfo<LabelVo> pageInfo = articleLabelService.findByPageAndCount(pageRequestParams);

        List<LabelVo> content = pageInfo.getContent();
        if (!content.isEmpty()) {
            Map<String, String> hosts = fileTemplate.removeHost(content.stream().map(LabelVo::getIcon).distinct().collect(Collectors.toList()));
            for (LabelVo labelVo : content) {
                labelVo.setIcon(hosts.get(labelVo.getIcon()));
            }
        }
        pageInfo.setContent(content);

        return pageInfo;
    }

    @Override
    public List<LabelVo> findByArticleId(Long id, Integer limit) {


        List<Long> labelIds = articleLabelService.findList(ArticleLabelVo
                .builder()
                .articleId(id)
                .build())
                .stream()
                .map(ArticleLabelVo::getLabelId)
                .distinct()
                .collect(Collectors.toList());

        if (null != limit) {
            labelIds = labelIds.subList(0, (labelIds.size() >= limit) ? limit : labelIds.size());
        }
        List<LabelVo> voList = this.findByIds(labelIds);
        return voList;
    }


}
