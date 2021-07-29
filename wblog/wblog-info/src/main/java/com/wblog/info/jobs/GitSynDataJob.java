package com.wblog.info.jobs;

import com.wblog.info.service.IGitSynDataService;
import io.github.fallingsoulm.easy.archetype.job.annotation.Job;
import io.github.fallingsoulm.easy.archetype.job.invoke.bean.IJobBeanHandler;
import io.github.fallingsoulm.easy.archetype.job.invoke.bean.JobRespEntity;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 文章源同步
 *
 * @author luyanan
 * @since 2021/6/27
 **/
@Job(name = "文章源", cron = "0 0 2 * * ? ")
public class GitSynDataJob implements IJobBeanHandler {
    @Autowired
    private IGitSynDataService gitSynDataService;

    @Override
    public JobRespEntity execute(String params) throws Exception {
        gitSynDataService.synAll();
        return JobRespEntity.success();
    }

    @Override
    public boolean async() {
        return true;
    }
}
