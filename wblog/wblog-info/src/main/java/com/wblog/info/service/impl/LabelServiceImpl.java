package com.wblog.info.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.apes.hub.api.exception.CustomException;
import com.apes.hub.api.module.file.api.FileApi;
import com.apes.hub.api.module.info.vo.ArticleLabelVo;
import com.apes.hub.api.module.info.vo.LabelVo;
import com.apes.hub.api.page.PageInfo;
import com.apes.hub.api.page.PageInfoContentHandler;
import com.apes.hub.api.page.PageRequestParams;
import com.apes.hub.core.page.MybatisPlusUtils;
import com.apes.hub.info.conver.LabelConver;
import com.apes.hub.info.entity.LabelEntity;
import com.apes.hub.info.manage.ILabelManage;
import com.apes.hub.info.mq.service.ILabelMqService;
import com.apes.hub.info.service.IArticleLabelService;
import com.apes.hub.info.service.ILabelService;
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
    private LabelConver labelConver;


    @Autowired
    private MybatisPlusUtils plusUtils;

    @Autowired
    private IArticleLabelService articleLabelService;

    @Autowired
    private FileApi fileApi;

    @Autowired
    private ILabelMqService labelMqService;

    @Override
    public PageInfo<LabelVo> findByPage(PageRequestParams<LabelVo> pageRequestParams) {
        PageRequestParams<LabelEntity> params = plusUtils.convertPageRequestParams(pageRequestParams, LabelEntity.class, labelConver);
        PageInfo<LabelEntity> entityPageInfo = iLabelManage.findByPage(params);
        return plusUtils.convertPageInfo(entityPageInfo, LabelVo.class, labelConver, new PageInfoContentHandler<LabelVo>() {
            @Override
            public void handler(List<LabelVo> contentList) {
                List<String> hosts = contentList.stream().map(LabelVo::getIcon).distinct().collect(Collectors.toList());
                Map<String, String> addHosts = fileApi.addHosts(hosts);
                for (LabelVo labelVo : contentList) {
                    labelVo.setIcon(addHosts.get(labelVo.getIcon()));
                    Integer count = articleLabelService.count(ArticleLabelVo.builder().labelId(labelVo.getId()).build());
                    labelVo.setNum(count);
                    //图片处理

                }
            }
        });
    }

    @Override
    public LabelVo findById(Long id) {
        LabelEntity labelEntity = iLabelManage.findById(id);
        if (labelEntity == null) {
            return null;
        }
        LabelVo labelVo = labelConver.map(labelEntity, LabelVo.class);
        labelVo.setIcon(fileApi.addHost(labelVo.getIcon()));
        return labelVo;
    }

    @Override
    public List<LabelVo> findList(LabelVo labelVo) {
        LabelEntity LabelEntity = labelConver.map(labelVo, LabelEntity.class);
        List<LabelEntity> list = iLabelManage.findList(LabelEntity);
        return labelConver.mapAsList(list, LabelVo.class);
    }

    @Override
    public List<LabelVo> findByIds(List<Long> ids) {
        List<LabelEntity> entities = iLabelManage.findByIds(ids);
        return labelConver.mapAsList(entities, LabelVo.class);
    }

    @Override
    public Long save(LabelVo labelVo) {
        LabelEntity one = this.iLabelManage.findOne(LabelEntity.builder().name(labelVo.getName()).build());
        if (null != one) {
            throw new CustomException("标签名称不允许重复");
        }
        LabelEntity labelEntity = labelConver.map(labelVo, LabelEntity.class);
        labelEntity.setIcon(fileApi.spiltHost(labelEntity.getIcon()));
        iLabelManage.insert(labelEntity);
        labelMqService.addLabel(labelEntity.getId());

        return labelEntity.getId();
    }


    @Override
    public void update(LabelVo labelVo) {
        LabelEntity labelEntity = labelConver.map(labelVo, LabelEntity.class);
        labelEntity.setIcon(fileApi.spiltHost(labelEntity.getIcon()));
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
        iLabelManage.deleteBatch(new LabelEntity(), ids);
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
            Map<String, String> hosts = fileApi.addHosts(content.stream().map(LabelVo::getIcon).distinct().collect(Collectors.toList()));
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
