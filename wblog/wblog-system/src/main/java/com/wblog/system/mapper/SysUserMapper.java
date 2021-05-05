package com.wblog.system.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wblog.system.entity.SysUserDo;
import io.github.fallingsoulm.easy.archetype.data.mybatisplus.BaseMapperPlus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户信息表 mapper
 * </p>
 *
 * @author luyanan
 * @since 2021-02-03
 */
@Mapper
public interface SysUserMapper extends BaseMapperPlus<SysUserDo> {

	/**
	 * 分页查询
	 *
	 * @param page
	 * @param params
	 * @return com.baomidou.mybatisplus.core.metadata.IPage<com.wblog.system.entity.SysUserDo>
	 * @since 2021/2/15
	 */
	IPage<SysUserDo> listByPage(IPage<SysUserDo> page, @Param("params") SysUserDo params);
}
