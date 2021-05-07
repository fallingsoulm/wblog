package com.wblog.info.mapper;

import com.wblog.info.entity.ArticleEntity;
import io.github.fallingsoulm.easy.archetype.data.mybatisplus.BaseMapperPlus;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 文章表 mapper
 * </p>
 *
 * @author luyanan
 * @since 2020-06-10
 */
@Mapper
public interface ArticleMapper extends BaseMapperPlus<ArticleEntity> {


}
