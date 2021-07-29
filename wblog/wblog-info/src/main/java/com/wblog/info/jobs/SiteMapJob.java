package com.wblog.info.jobs;

import com.wblog.info.service.ISiteMapService;
import io.github.fallingsoulm.easy.archetype.job.annotation.Job;
import io.github.fallingsoulm.easy.archetype.job.invoke.bean.IJobBeanHandler;
import io.github.fallingsoulm.easy.archetype.job.invoke.bean.JobRespEntity;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * sitemap生成
 *
 * @author luyanan
 * @since 2021/6/27
 **/

@Job(name = "sitemap生成", cron = "0 0 0 * * ? ")
public class SiteMapJob implements IJobBeanHandler {

    @Autowired
    private ISiteMapService siteMapService;

    @Override
    public JobRespEntity execute(String params) throws Exception {
        siteMapService.siteMap();
        return JobRespEntity.success();
    }

    @Override
    public boolean async() {
        return true;
    }
}
