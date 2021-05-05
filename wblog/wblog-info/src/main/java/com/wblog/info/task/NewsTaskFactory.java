package com.wblog.info.task;

import com.apes.hub.api.enums.ConstantEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author luyanan
 * @since 2020/7/14
 * <p>工厂类</p>
 **/
@Slf4j
@Component
public class NewsTaskFactory {


    @Autowired
    private OschinaNewsTask oschinaNewsTask;


    @Autowired
    private CnblogsNewsTask cnblogsNewsTask;


    @Autowired
    private JueJinNewsTask jueJinNewsTask;

    @Autowired
    private CSDNNewsTask csdnNewsTask;

    @Autowired
    private ImoocNewsTask imoocNewsTask;

    @Autowired
    private SegmentfaultNewsTask segmentfaultNewsTask;


    Map<Integer, NewsTask> newsTaskMap = new HashMap<>();


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
            for (Map.Entry<Integer, NewsTask> entry : newsTaskMap.entrySet()) {
                try {
                    entry.getValue().syn();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            try {
                NewsTask newsTask = newsTaskMap.get(source);
                newsTask.syn();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
