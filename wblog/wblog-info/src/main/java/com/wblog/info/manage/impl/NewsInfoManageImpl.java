package com.wblog.info.manage.impl;

import com.wblog.info.entity.NewsInfoEntity;
import com.wblog.info.manage.INewsInfoManage;
import com.wblog.info.mapper.NewsInfoMapper;
import io.github.fallingsoulm.easy.archetype.data.manage.impl.CacheManageImpl;
import org.springframework.stereotype.Service;

/**
 * 资讯内容
 *
 * @author luyanan
 * @since 2021/7/17
 **/
@Service
public class NewsInfoManageImpl extends CacheManageImpl<NewsInfoMapper, NewsInfoEntity> implements INewsInfoManage {
}
