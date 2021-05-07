package com.wblog.info.manage.impl;

import com.wblog.info.entity.ArticleInfoEntity;
import com.wblog.info.manage.IArticleInfoManage;
import com.wblog.info.mapper.ArticleInfoMapper;
import io.github.fallingsoulm.easy.archetype.data.manage.impl.CacheManageImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 文章详情表 manageImpl
 * </p>
 *
 * @author luyanan
 * @since 2020-06-10
 */
@Service
public class ArticleInfoManageImpl extends CacheManageImpl<ArticleInfoMapper, ArticleInfoEntity> implements IArticleInfoManage {

}
