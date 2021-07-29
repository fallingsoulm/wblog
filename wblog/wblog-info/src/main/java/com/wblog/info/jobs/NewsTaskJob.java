package com.wblog.info.jobs;

import com.wblog.common.enums.ConstantEnum;
import com.wblog.info.news.*;
import io.github.fallingsoulm.easy.archetype.job.annotation.Job;
import io.github.fallingsoulm.easy.archetype.job.invoke.bean.IJobBeanHandler;
import io.github.fallingsoulm.easy.archetype.job.invoke.bean.JobRespEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * @author luyanan
 * @since 2020/7/14
 * <p>工厂类</p>
 **/
@Slf4j
@Job(name = "资讯定时任务", group = "info", cron = "0 0 0/3 * * ? ")
public class NewsTaskJob implements IJobBeanHandler {


    @Autowired
    private OschinaNewsSpiderHandler oschinaNewsTask;


    @Autowired
    private CnblogsNewsSpiderHandler cnblogsNewsTask;


    @Autowired
    private JueJinNewsSpiderHandler jueJinNewsTask;

    @Autowired
    private CSDNNewsSpiderHandler csdnNewsTask;

    @Autowired
    private ImoocNewsSpiderHandler imoocNewsTask;

    @Autowired
    private SegmentfaultNewsSpiderHandler segmentfaultNewsTask;


    Map<Integer, NewsSpiderHandler> newsTaskMap = new HashMap<>();


    void init() {

        // 开源中国
        newsTaskMap.put(ConstantEnum.NEWS_SOURCE_OSCHINA.getValue(), oschinaNewsTask);

        newsTaskMap.put(ConstantEnum.NEWS_SOURCE_CNBLOGS.getValue(), cnblogsNewsTask);
        newsTaskMap.put(ConstantEnum.NEWS_SOURCE_JUEJIN.getValue(), jueJinNewsTask);
        newsTaskMap.put(ConstantEnum.NEWS_SOURCE_CSDN.getValue(), csdnNewsTask);
        newsTaskMap.put(ConstantEnum.NEWS_SOURCE_IMOOC.getValue(), imoocNewsTask);
        newsTaskMap.put(ConstantEnum.NEWS_SOURCE_SEGMENTFAULT.getValue(), segmentfaultNewsTask);

    }

    public void syn(Integer source) {


        init();
        if (null == source) {
            for (Map.Entry<Integer, NewsSpiderHandler> entry : newsTaskMap.entrySet()) {
                try {
                    entry.getValue().syn();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            try {
                NewsSpiderHandler newsSpiderHandler = newsTaskMap.get(source);
                newsSpiderHandler.syn();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public JobRespEntity execute(String params) throws Exception {
        syn(null);
        return JobRespEntity.success();
    }

    @Override
    public boolean async() {
        return true;
    }
}
