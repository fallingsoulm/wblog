package com.wblog.info.manage;

import com.apes.hub.core.manage.IManage;
import com.apes.hub.info.entity.AlbumArticleEntity;

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
     * @return {@link Integer}
     * @author luyanan
     * @since 2020/7/20
     * @param albumId
     */
    Integer maxOrderNum(Long albumId);


}
