package com.wblog.info.jobs;

import com.wblog.info.service.IGitSynDataService;
import io.github.fallingsoulm.easy.archetype.job.annotation.Job;
import io.github.fallingsoulm.easy.archetype.job.invoke.bean.IJobBeanHandler;
import io.github.fallingsoulm.easy.archetype.job.invoke.bean.JobRespEntity;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 从github上拉取文章源
 *
 * @author luyanan
 * @since 2021/6/27
 **/
@Job(name = "github文章源", cron = "0 0 2 * * ? ")
public class GitUrlFromGithubJob implements IJobBeanHandler {

    @Autowired
    private IGitSynDataService gitSynDataService;

    @Override
    public JobRespEntity execute(String params) throws Exception {
        gitSynDataService.getGitUrlFromGithub();
        return JobRespEntity.success();
    }
}
