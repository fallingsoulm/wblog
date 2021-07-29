package com.wblog.info.jobs;

import com.wblog.info.service.IArticleService;
import io.github.fallingsoulm.easy.archetype.framework.thread.BusinessThreadPoolTaskExecutor;
import io.github.fallingsoulm.easy.archetype.job.annotation.Job;
import io.github.fallingsoulm.easy.archetype.job.invoke.bean.IJobBeanHandler;
import io.github.fallingsoulm.easy.archetype.job.invoke.bean.JobRespEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 文章访问量同步
 *
 * @author luyanan
 * @since 2021/6/27
 **/
@Slf4j
@Job(name = "文章访问量同步", cron = "0 0 0/6 * * ? ")
public class ArticleSynViewJob implements IJobBeanHandler {
    @Autowired
    private IArticleService articleService;

    @Autowired
    private BusinessThreadPoolTaskExecutor businessThreadPoolTaskExecutor;

    @Override
    public boolean async() {
        return true;
    }

    @Override
    public JobRespEntity execute(String params) throws Exception {
        businessThreadPoolTaskExecutor.execute(() -> {
            log.info("开始执行文章访问量同步");
            articleService.synView();
            log.info("文章访问量同步结束");
        });

        return JobRespEntity.success();
    }
}
