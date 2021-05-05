package com.wblog.system.mapper;

import com.wblog.system.entity.SysRoleMenuDo;
import io.github.fallingsoulm.easy.archetype.data.mybatisplus.BaseMapperPlus;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 角色和菜单关联表 mapper
 * </p>
 *
 * @author luyanan
 * @since 2021-02-03
 */
@Mapper
public interface SysRoleMenuMapper extends BaseMapperPlus<SysRoleMenuDo> {

}
