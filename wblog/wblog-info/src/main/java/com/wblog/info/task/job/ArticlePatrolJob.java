package com.wblog.info.task.job;

import com.wblog.info.service.IArticleService;
import io.github.fallingsoulm.easy.archetype.job.annotation.Job;
import io.github.fallingsoulm.easy.archetype.job.invoke.bean.IJobBeanHandler;
import io.github.fallingsoulm.easy.archetype.job.invoke.bean.JobRespEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 文章巡查
 *
 * @author luyanan
 * @since 2021/6/27
 **/
@Slf4j
@Job(name = "文章巡查", cron = "0 0 0/6 * * ? ")
public class ArticlePatrolJob implements IJobBeanHandler {
    @Autowired
    private IArticleService articleService;

    @Override
    public JobRespEntity execute(String params) throws Exception {
        articleService.patrol();
        return JobRespEntity.success();
    }

    @Override
    public boolean async() {
        return true;
    }
}
