package com.wblog.info.mapper;

import com.wblog.info.entity.AlbumArticleEntity;
import io.github.fallingsoulm.easy.archetype.data.mybatisplus.BaseMapperPlus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 文章与专辑关联 mapper
 * </p>
 *
 * @author luyanan
 * @since 2020-07-15
 */
@Mapper
public interface AlbumArticleMapper extends BaseMapperPlus<AlbumArticleEntity> {


    /**
     * <p>最大的下标</p>
     *
     * @param albumId 专辑id
     * @return {@link Integer}
     * @author luyanan
     * @since 2020/7/20
     */


    @Select("SELECT order_num FROM b_album_article WHERE album_id = #{albumId}  ORDER BY order_num DESC  LIMIT 0,1")
    Integer maxOrderNum(@Param("albumId") Long albumId);


}
