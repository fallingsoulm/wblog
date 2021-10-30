package com.wblog.info.jobs;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wblog.common.enums.ConstantEnum;
import com.wblog.common.module.info.vo.LabelVo;
import com.wblog.info.entity.NewsEntity;
import com.wblog.info.entity.NewsInfoEntity;
import com.wblog.info.manage.INewsInfoManage;
import com.wblog.info.manage.INewsManage;
import com.wblog.info.mapper.NewsInfoMapper;
import com.wblog.info.mapper.NewsMapper;
import com.wblog.info.service.ILabelService;
import com.wblog.search.api.ArticleSearchApi;
import com.wblog.search.service.InfoSearchService;
import com.wblog.search.vo.ArticleSearchVo;
import io.github.fallingsoulm.easy.archetype.data.mybatisplus.MybatisPlusUtils;
import io.github.fallingsoulm.easy.archetype.job.annotation.Job;
import io.github.fallingsoulm.easy.archetype.job.invoke.bean.IJobBeanHandler;
import io.github.fallingsoulm.easy.archetype.job.invoke.bean.JobRespEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 资讯同步到es的定时任务
 *
 * @author luyanan
 * @since 2021/7/18
 **/
@Slf4j
@Job(name = "资讯同步到es", cron = "0 0 0/3 * * ? ")
public class NewsSynEsJob implements IJobBeanHandler {

    @Autowired
    private NewsMapper newsMapper;

    @Autowired
    private NewsInfoMapper newsInfoMapper;


    @Autowired
    private InfoSearchService infoSearchService;

    @Autowired
    private ArticleSearchApi articleSearchApi;

    @Override
    public JobRespEntity execute(String params) throws Exception {

        boolean cleanAll = false;

        // 默认3天前
        if (StrUtil.isBlank(params)) {
            params = "3";
        }

        Long beforeId = null;
        if (StrUtil.isNotBlank(params) && params.equals("-1")) {
            cleanAll = true;

        }
        if (StrUtil.isNotBlank(params) && !params.equals("-1")) {
            Date beforeDay = DateUtil.offsetDay(DateUtil.parse(DateUtil.date(new Date()).toString("yyyy-MM-dd"), "yyyy-MM-dd"), Integer.valueOf("-" + params)).toJdkDate();
            List<NewsEntity> newsEntities = newsMapper.selectList(new LambdaQueryWrapper<NewsEntity>()
                    .select(NewsEntity::getId)
                    .gt(NewsEntity::getCreateTime, beforeDay)
                    .orderByAsc(NewsEntity::getId).last(" limit 0,1"));
            if (CollectionUtil.isNotEmpty(newsEntities)) {
                beforeId = newsEntities.get(0).getId();
            } else {
                // 当没有符合条件的时候,给出一个不存在的最大的值
                beforeId = Long.MAX_VALUE;
            }

        }


        // 查询所有符合条件的数据
        if (cleanAll) {
            articleSearchApi.delete(ArticleSearchVo.builder().classify(ConstantEnum.SEARCH_INFO_TYPE_NEWS.getValue()).build());
        }
        Integer offset = 0;
        Integer size = 100;
        while (true) {
            try {
                LambdaQueryWrapper<NewsInfoEntity> queryWrapper = new LambdaQueryWrapper<NewsInfoEntity>()
                        .select(NewsInfoEntity::getNewsId).orderByDesc(NewsInfoEntity::getNewsId)
                        .last(true, " limit " + offset + "," + size);
                queryWrapper.ge(null != beforeId, NewsInfoEntity::getNewsId, beforeId);

                List<NewsInfoEntity> newsInfoEntities = newsInfoMapper.selectList(queryWrapper);
                if (CollectionUtil.isEmpty(newsInfoEntities)) {
                    break;
                }
                offset = offset + size;
                List<Long> ids = newsInfoEntities.stream().map(NewsInfoEntity::getNewsId).collect(Collectors.toList());

                Map<Long, ArticleSearchVo> searchVoMap = articleSearchApi.findByIds(ids, ConstantEnum.SEARCH_INFO_TYPE_NEWS.getValue()).getData()
                        .stream().collect(Collectors.toMap(k -> k.getId(), v -> v));
                if (!cleanAll) {
                    ids = ids.stream().filter(a -> {
                        return !searchVoMap.keySet().contains(a);
                    }).collect(Collectors.toList());
                }
                if (CollectionUtil.isNotEmpty(ids)) {
                    infoSearchService.update(ids, ConstantEnum.SEARCH_INFO_TYPE_NEWS.getValue());
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        return JobRespEntity.success();
    }
}
