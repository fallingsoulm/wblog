package com.wblog.info.manage;


import com.wblog.info.entity.GitSynEntity;
import io.github.fallingsoulm.easy.archetype.data.manage.IManage;

import java.util.List;

/**
 * <p>
 * 同步表 manage
 * </p>
 *
 * @author luyanan
 * @since 2020-06-10
 */
public interface IGitSynManage extends IManage<GitSynEntity> {


    /**
     * <p>根据文章id集合查询</p>
     *
     * @param articleIds 文章id集合
     * @return {@link List< GitSynEntity>}
     * @author luyanan
     * @since 2020/7/11
     */
    List<GitSynEntity> findByArticleIds(List<Long> articleIds);

}
