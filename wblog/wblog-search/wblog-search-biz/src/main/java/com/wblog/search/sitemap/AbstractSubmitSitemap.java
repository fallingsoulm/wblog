package com.wblog.search.sitemap;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.wblog.common.enums.ConstantEnum;
import com.wblog.common.redis.RedisKeyEnums;
import com.wblog.search.service.InfoElasticsearchService;
import com.wblog.search.vo.ArticleSearchVo;
import io.github.fallingsoulm.easy.archetype.data.redis.RedisKeyGenerator;
import io.github.fallingsoulm.easy.archetype.job.invoke.bean.IJobBeanHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author luyanan
 * @since 2021/8/14
 **/
@Slf4j
public abstract class AbstractSubmitSitemap implements SubmitSitemap {

    @Autowired
    protected RedisTemplate redisTemplate;

    @Autowired
    protected RedisKeyGenerator redisKeyGenerator;

    @Autowired
    protected InfoElasticsearchService infoElasticsearchService;
    protected Long remain = remain();


    @Override
    public void submit() {
        // 1.  获取上次提交的最后一个id

        // 上次提交的最后一个id
        Long lastId = null;
        String redisKey = redisKeyGenerator.generate(RedisKeyEnums.SUBMIT_SITEMAP);
        if (redisTemplate.opsForHash().hasKey(redisKey, type())) {
            lastId = (Long) redisTemplate.opsForHash().get(redisKey, type());
        }
        int size = 10;
        while (this.remain > size) {

            List<ArticleSearchVo> articleSearchVos = infoElasticsearchService.findByPage(lastId, size);
            if (CollectionUtil.isEmpty(articleSearchVos)) {
                break;
            }
            List<String> collect = articleSearchVos.stream().map(a -> {
                if (a.getClassify().equals(ConstantEnum.SEARCH_INFO_TYPE_NEWS.getValue())) {
                    return "https://www.luyanan.com/news/info/" + a.getId() + ".html";
                } else {
                    return "https://www.luyanan.com/article/info/" + a.getId() + ".html";
                }
            }).collect(Collectors.toList());
            doSubmit(collect);
            log.info("推送到{}:sitemap的数据有{}条,剩余可推送{}条", type(), collect.size(), remain);
            lastId = articleSearchVos.get(articleSearchVos.size() - 1).getId();
            redisTemplate.opsForHash().put(redisKey, type(), lastId);
        }
    }

    protected abstract void doSubmit(List<String> submitUrls);
}
