package com.wblog.info.mapper;

import com.wblog.common.module.info.vo.NewsVo;
import com.wblog.info.entity.NewsEntity;
import io.github.fallingsoulm.easy.archetype.data.mybatisplus.BaseMapperPlus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

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
    @Select("SELECT *  FROM b_news  WHERE source = #{source} AND  del_flag = 0  ORDER BY create_time DESC  LIMIT 0,1 ")
    NewsVo lastUrl(@Param("source") Integer source);

    /**
     * 查询没有资讯内容的id
     *
     * @return java.util.List<java.lang.Long>
     * @since 2021/7/27
     */
    @Select("SELECT b_news.* FROM b_news LEFT JOIN b_news_info ON b_news.id = b_news_info.news_id  WHERE  b_news.del_flag = 0  AND  b_news_info.content IS NULL  ORDER BY create_time DESC   LIMIT 0,1000")
    List<NewsEntity> findWithoutContent();
}
