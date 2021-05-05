package com.wblog.system.mapper;

import io.github.fallingsoulm.easy.archetype.data.mybatisplus.BaseMapperPlus;
import org.apache.ibatis.annotations.Mapper;
import com.wblog.system.entity.SysRoleDo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 角色信息表 mapper
 * </p>
 *
 * @author luyanan
 * @since 2021-02-03
 */
@Mapper
public interface SysRoleMapper extends BaseMapperPlus<SysRoleDo> {

	/**
	 * 根据用户id查询角色
	 * @param userId 用户id
	 * @return java.util.List<com.wblog.system.entity.SysRoleDo>
	 * @since 2021/2/4
	 */
	List<SysRoleDo> selectRolePermissionByUserId(@Param("userId") Long userId);

}
