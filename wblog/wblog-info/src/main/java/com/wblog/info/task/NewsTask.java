package com.wblog.info.task;

import com.apes.hub.api.module.info.vo.NewsVo;

/**
 * @author luyanan
 * @since 2020/7/13
 * <p>资讯同步任务</p>
 **/
public interface NewsTask {


    /**
     * <p>数据同步</p>
     *
     * @author luyanan
     * @since 2020/7/13
     */
    void syn();


    /**
     * <p>最后一条记录</p>
     *
     * @author luyanan
     * @since 2020/7/13
     */
    NewsVo lastOffset();

}
