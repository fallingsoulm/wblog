package com.wblog.info.manage.impl;

import com.apes.hub.core.manage.MybatisPlusCacheManageImpl;
import com.apes.hub.info.entity.ArticleInfoEntity;
import com.apes.hub.info.manage.IArticleInfoManage;
import com.apes.hub.info.mapper.ArticleInfoMapper;
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
public class ArticleInfoManageImpl extends MybatisPlusCacheManageImpl<ArticleInfoMapper, ArticleInfoEntity> implements IArticleInfoManage {

}
