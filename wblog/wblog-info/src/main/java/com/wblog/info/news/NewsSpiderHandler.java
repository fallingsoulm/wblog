package com.wblog.info.news;

import com.wblog.common.enums.ConstantEnum;
import com.wblog.common.module.info.vo.NewsVo;
import com.wblog.info.entity.NewsInfoEntity;

/**
 * @author luyanan
 * @since 2020/7/13
 * <p>资讯同步任务</p>
 **/
public interface NewsSpiderHandler {

    /**
     * 支持的平台
     *
     * @author luyanan
     * @since 2021/7/13
     */
    ConstantEnum source();

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
     * @return com.wblog.common.module.info.vo.NewsVo
     * @since 2021/7/13
     */
    NewsVo lastOffset();


    /**
     * 获取资讯内容
     *
     * @param newsVo
     * @return com.wblog.info.entity.NewsInfoEntity
     * @since 2021/7/13
     */
    NewsInfoEntity getContent(NewsVo newsVo);

}
