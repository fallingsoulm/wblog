package com.wblog.system.mapper;

import io.github.fallingsoulm.easy.archetype.data.mybatisplus.BaseMapperPlus;
import org.apache.ibatis.annotations.Mapper;
import com.wblog.system.entity.SysUserPostDo;

/**
 * <p>
 * 用户与岗位关联表 mapper
 * </p>
 *
 * @author luyanan
 * @since 2021-02-03
 */
@Mapper
public interface SysUserPostMapper extends BaseMapperPlus<SysUserPostDo> {

}
