package com.wblog.info.jobs;

import cn.hutool.core.util.StrUtil;
import com.wblog.common.enums.ConstantEnum;
import com.wblog.common.module.info.vo.LabelVo;
import com.wblog.info.entity.ArticleEntity;
import com.wblog.info.entity.ArticleInfoEntity;
import com.wblog.info.manage.IArticleInfoManage;
import com.wblog.info.manage.IArticleManage;
import com.wblog.info.service.ILabelService;
import com.wblog.info.utils.ArticleSearchVoBuilder;
import com.wblog.search.api.ArticleSearchApi;
import com.wblog.search.service.InfoSearchService;
import com.wblog.search.vo.ArticleSearchVo;
import io.github.fallingsoulm.easy.archetype.data.mybatisplus.MybatisPlusUtils;
import io.github.fallingsoulm.easy.archetype.job.annotation.Job;
import io.github.fallingsoulm.easy.archetype.job.invoke.bean.IJobBeanHandler;
import io.github.fallingsoulm.easy.archetype.job.invoke.bean.JobRespEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 文章同步到es的任务
 *
 * @author luyanan
 * @since 2021/7/18
 **/
@Slf4j
@Job(name = "文章同步到es", cron = "0 0 0/6 * * ? ")
public class ArticleSynEsJob implements IJobBeanHandler {

    @Autowired
    private IArticleManage articleManage;

    @Autowired
    private IArticleInfoManage articleInfoManage;

    @Autowired
    private MybatisPlusUtils mybatisPlusUtils;

    @Autowired
    private InfoSearchService infoSearchService;

    @Autowired
    private ArticleSearchApi articleSearchApi;

    @Autowired
    private ILabelService labelService;


    @Override
    public JobRespEntity execute(String params) throws Exception {
        boolean cleanAll = (StrUtil.isNotBlank(params) && params.equals("true")) ? true : false;
        // 查询所有符合条件的数据
        if (cleanAll) {
            articleSearchApi.delete(ArticleSearchVo.builder().classify(ConstantEnum.SEARCH_INFO_TYPE_ARTICLE.getValue()).build());
        }

        ArticleEntity articleEntity = ArticleEntity.builder().status(ConstantEnum.ARTICLE_STATUS_ENABLE.getValue()).build();

        mybatisPlusUtils.bigDataList(articleEntity, articleManage, data -> {
            List<Long> ids = data.stream().map(ArticleEntity::getId).collect(Collectors.toList());
            infoSearchService.update(ids, ConstantEnum.SEARCH_INFO_TYPE_ARTICLE.getValue());
//            for (ArticleEntity entity : data) {
//                infoSearchService.update(ArticleSearchVoBuilder.build(entity,articleInfoManage,labelService));
//            }
        });
        return JobRespEntity.success();
    }
}
