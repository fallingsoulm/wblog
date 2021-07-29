package com.wblog.info.jobs;

import cn.hutool.core.collection.CollectionUtil;
import com.wblog.common.enums.ConstantEnum;
import com.wblog.common.module.info.vo.NewsVo;
import com.wblog.info.component.FileTemplatePlus;
import com.wblog.info.entity.NewsEntity;
import com.wblog.info.entity.NewsInfoEntity;
import com.wblog.info.manage.INewsInfoManage;
import com.wblog.info.manage.INewsManage;
import com.wblog.info.mq.service.ILabelMqService;
import com.wblog.info.news.NewsSpiderHandler;
import com.wblog.info.news.NewsSpiderHandlerFactory;
import io.github.fallingsoulm.easy.archetype.data.mybatisplus.MybatisPlusUtils;
import io.github.fallingsoulm.easy.archetype.framework.utils.BeanUtils;
import io.github.fallingsoulm.easy.archetype.job.annotation.Job;
import io.github.fallingsoulm.easy.archetype.job.invoke.bean.IJobBeanHandler;
import io.github.fallingsoulm.easy.archetype.job.invoke.bean.JobRespEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

/**
 * 资讯信息同步
 *
 * @author luyanan
 * @since 2021/7/27
 **/
@Slf4j
@Job(name = "资讯内容同步", cron = "0 0 0/3 * * ? ")
public class NewsInfoSynJobs implements IJobBeanHandler {



    @Autowired
    private INewsManage newsManage;

    @Autowired
    private INewsInfoManage newsInfoManage;


    @Autowired
    private NewsSpiderHandlerFactory newsSpiderHandlerFactory;

    @Autowired
    private FileTemplatePlus fileTemplatePlus;

    @Autowired
    private ILabelMqService labelMqService;


    @Override
    public JobRespEntity execute(String params) throws Exception {


        List<NewsEntity> ids = newsManage.findWithoutContent();
        if (CollectionUtil.isNotEmpty(ids)) {
            log.info("发现没有内容的资讯{}条", ids.size());
        }
        for (NewsEntity news : ids) {
            try {
                // 修复头图
                String image = fileTemplatePlus.removeHost(fileTemplatePlus.randomImage());
                newsManage.update(NewsEntity.builder().id(news.getId()).image(image).build());
                NewsSpiderHandler handler = newsSpiderHandlerFactory.getHandler(news.getSource());
                NewsInfoEntity content = handler.getContent(BeanUtils.copyProperties(news, NewsVo.class));
                if (null == content) {
                    continue;
                }
                newsInfoManage.insert(content);
                labelMqService.articleAddLabel(news.getId(), ConstantEnum.SEARCH_INFO_TYPE_NEWS.getValue());
                log.info("修复资讯,id:{},image:{},content:{}", news.getId(), image, content.getContent());
            } catch (Exception e) {
                e.printStackTrace();
            }


        }


        return JobRespEntity.success();
    }
}
