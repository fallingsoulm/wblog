package com.wblog.search.sitemap;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.wblog.common.enums.ConstantEnum;
import com.wblog.common.module.info.vo.ArticleVo;
import com.wblog.common.redis.RedisKeyEnums;
import com.wblog.search.service.InfoElasticsearchService;
import com.wblog.search.vo.ArticleSearchVo;
import io.github.fallingsoulm.easy.archetype.data.redis.RedisKeyGenerator;
import io.github.fallingsoulm.easy.archetype.framework.page.PageInfo;
import io.github.fallingsoulm.easy.archetype.job.annotation.Job;
import io.github.fallingsoulm.easy.archetype.job.invoke.bean.IJobBeanHandler;
import io.github.fallingsoulm.easy.archetype.job.invoke.bean.JobRespEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import javax.jws.Oneway;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 提交sitemap到百度
 *
 * @author luyanan
 * @since 2021/8/13
 **/
@Slf4j
@Job(name = "提交sitemap到百度", cron = "0 0 0/6 * * ? ")
public class BaiduSubmitSitemap extends AbstractSubmitSitemap implements IJobBeanHandler {


    @Override
    public String type() {
        return "baidu";
    }


//    @Override
//    public void submit() {
//
//        // 1.  获取上次提交的最后一个id
//
//        // 上次提交的最后一个id
//        Long lastId = null;
//        String redisKey = redisKeyGenerator.generate(RedisKeyEnums.SUBMIT_SITEMAP);
//        if (redisTemplate.opsForHash().hasKey(redisKey, type())) {
//            lastId = (Long) redisTemplate.opsForHash().get(redisKey, type());
//        }
//        int size = 10;
//        while (this.remain > size) {
//
//            List<ArticleSearchVo> articleSearchVos = infoElasticsearchService.findByPage(lastId, size);
//            if (CollectionUtil.isEmpty(articleSearchVos)) {
//                break;
//            }
//
//
//            String body = articleSearchVos.stream().map(a -> {
//                if (a.getClassify().equals(ConstantEnum.SEARCH_INFO_TYPE_NEWS.getValue())) {
//                    return "https://www.luyanan.com/news/info/" + a.getId() + ".html";
//                } else {
//                    return "https://www.luyanan.com/article/info/" + a.getId() + ".html";
//                }
//            }).collect(Collectors.joining("\n"));
//
//            lastId = articleSearchVos.get(articleSearchVos.size() - 1).getId();
//            redisTemplate.opsForHash().put(redisKey, type(), lastId);
//        }
//
//    }

    @Override
    public Long remain() {
        return 100000L;
    }

    @Override
    protected void doSubmit(List<String> submitUrls) {
        // 提交到百度
        String url = "http://data.zz.baidu.com/urls?site=https://www.luyanan.com&token=up4m0Kt41DY2PoJT";
        String result = HttpUtil.createPost(url).body(submitUrls.stream().collect(Collectors.joining("\n")), "text/plain").execute().body();
        if (StrUtil.isNotBlank(result)) {
            Long remain = JSON.parseObject(result).
                    getLong("remain");
            this.remain = remain;

        }
    }


    @Override
    public JobRespEntity execute(String params) throws Exception {
        submit();
        return JobRespEntity.success();
    }
}
