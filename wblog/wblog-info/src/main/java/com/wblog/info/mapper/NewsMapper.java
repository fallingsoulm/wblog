package com.wblog.info.mapper;

import com.apes.hub.api.module.info.vo.NewsVo;
import com.apes.hub.data.mp3.BaseMapperPlus;
import com.apes.hub.info.entity.NewsEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 热门资讯 mapper
 * </p>
 *
 * @author luyanan
 * @since 2020-07-13
 */
@Mapper
public interface NewsMapper extends BaseMapperPlus<NewsEntity> {


    /**
     * <p>查询最新的一条记录</p>
     *
     * @param source 来源
     * @return {@link String}
     * @author luyanan
     * @since 2020/7/13
     */
    @Select("SELECT *  FROM b_news  WHERE source = #{source} ORDER BY create_time DESC  LIMIT 0,1 ")
    NewsVo lastUrl(@Param("source") Integer source);

}
