package com.wblog.info.mapper;

import com.wblog.info.entity.CommentEntity;
import io.github.fallingsoulm.easy.archetype.data.mybatisplus.BaseMapperPlus;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author luyanan
 * @since 2020/9/19
 * <p>评论</p>
 **/
@Mapper
public interface CommentMapper extends BaseMapperPlus<CommentEntity> {


}
