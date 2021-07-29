package com.wblog.info.mapper;

import com.wblog.info.entity.NewsInfoEntity;
import io.github.fallingsoulm.easy.archetype.data.mybatisplus.BaseMapperPlus;
import org.apache.ibatis.annotations.Mapper;

/**
 * 资讯内容
 *
 * @author luyanan
 * @since 2021/7/17
 **/
@Mapper
public interface NewsInfoMapper extends BaseMapperPlus<NewsInfoEntity> {
}
