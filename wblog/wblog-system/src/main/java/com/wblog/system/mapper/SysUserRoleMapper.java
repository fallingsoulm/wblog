package com.wblog.system.mapper;

import com.wblog.system.entity.SysUserRoleDo;
import io.github.fallingsoulm.easy.archetype.data.mybatisplus.BaseMapperPlus;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户和角色关联表 mapper
 * </p>
 *
 * @author luyanan
 * @since 2021-02-03
 */
@Mapper
public interface SysUserRoleMapper extends BaseMapperPlus<SysUserRoleDo> {

}
