package com.wblog.search.sitemap;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.wblog.search.vo.ArticleSearchVo;
import io.github.fallingsoulm.easy.archetype.job.annotation.Job;
import io.github.fallingsoulm.easy.archetype.job.invoke.bean.IJobBeanHandler;
import io.github.fallingsoulm.easy.archetype.job.invoke.bean.JobRespEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 提交sitemap到bing
 *
 * @author luyanan
 * @since 2021/8/14
 **/
@Job(name = "提交sitemap到bing", cron = "0 0 0/6 * * ? ")
public class BinigSubmitSitemap extends AbstractSubmitSitemap implements IJobBeanHandler {
    @Override
    protected void doSubmit(List<String> submitUrls) {
        String url = "https://www.bing.com/webmaster/api.svc/json/SubmitUrlbatch?apikey=da267dbba02345bba615c5e5860f17c3";

        Map<String, Object> map = new HashMap<>();
        map.put("siteUrl", "https://luyanan.com");
        map.put("urlList", submitUrls);
        String body = HttpUtil.createGet(url).body(JSON.toJSONString(map)).execute().body();
        this.remain = this.remain - submitUrls.size();

    }

    @Override
    public String type() {
        return "bing";
    }

    @Override
    public Long remain() {
        return 10000L;
    }

    @Override
    public JobRespEntity execute(String params) throws Exception {
        submit();
        return JobRespEntity.success();
    }
}
