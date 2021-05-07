package com.wblog.info.manage;


import com.wblog.info.entity.AlbumArticleEntity;
import io.github.fallingsoulm.easy.archetype.data.manage.IManage;

/**
 * <p>
 * 文章与专辑关联 manage
 * </p>
 *
 * @author luyanan
 * @since 2020-07-15
 */
public interface IAlbumArticleManage extends IManage<AlbumArticleEntity> {


    /**
     * <p>查询最大的排序</p>
     *
     * @param albumId
     * @return {@link Integer}
     * @author luyanan
     * @since 2020/7/20
     */
    Integer maxOrderNum(Long albumId);


}
