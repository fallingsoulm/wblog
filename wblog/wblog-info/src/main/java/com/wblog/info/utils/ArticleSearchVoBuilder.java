package com.wblog.info.utils;

import com.wblog.common.enums.ConstantEnum;
import com.wblog.common.module.info.vo.LabelVo;
import com.wblog.info.entity.ArticleEntity;
import com.wblog.info.entity.ArticleInfoEntity;
import com.wblog.info.manage.IArticleInfoManage;
import com.wblog.info.service.IArticleInfoService;
import com.wblog.info.service.ILabelService;
import com.wblog.search.vo.ArticleSearchVo;

import java.util.List;

/**
 * ArticleSearchVo 构建
 *
 * @author luyanan
 * @since 2021/7/18
 **/

public class ArticleSearchVoBuilder {


    public static ArticleSearchVo build(ArticleEntity entity, IArticleInfoManage articleInfoManage, ILabelService labelService) {
        ArticleInfoEntity infoEntity = articleInfoManage.findById(entity.getId());
        if (null == infoEntity) {
            return null;
        }
        // 查询标签
        List<LabelVo> labelVos = labelService.findByArticleId(entity.getId());
        ArticleSearchVo build = ArticleSearchVo.builder().id(entity.getId())
                .title(entity.getTitle())
                .introduction(entity.getIntroduction())
                .image(entity.getImage())
                .labelVos(labelVos)
                .content(infoEntity.getContent())
                .classify(ConstantEnum.SEARCH_INFO_TYPE_ARTICLE.getValue())
                .createTime(entity.getCreateTime())
                .userId(entity.getUserId())
                .build();
        return build;
    }


}
