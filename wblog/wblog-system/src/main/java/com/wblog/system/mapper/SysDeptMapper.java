package com.wblog.system.mapper;

import com.wblog.system.entity.SysDeptDo;
import io.github.fallingsoulm.easy.archetype.data.mybatisplus.BaseMapperPlus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 部门表 mapper
 * </p>
 *
 * @author luyanan
 * @since 2021-02-03
 */
@Mapper
public interface SysDeptMapper extends BaseMapperPlus<SysDeptDo> {
	/**
	 * 根据角色id查询部门树信息
	 *
	 * @param roleId            角色id
	 * @param deptCheckStrictly 部门树选择项是否关联显示
	 * @return java.util.List<java.lang.Long> 选中的部门树列表
	 * @since 2021/2/12
	 */
	List<Long> listByRoleId(@Param("roleId") Long roleId, @Param("deptCheckStrictly") Boolean deptCheckStrictly);

	/**
	 * 根据id查询所有的子部门(正常状态)
	 *
	 * @param deptId 部门id
	 * @return int 子部门个数
	 * @since 2021/2/12
	 */
	int selectNormalChildrenDeptById(@Param("deptId") Long deptId);

	/**
	 * 根据部门id查询所有的子部门
	 *
	 * @param deptId 部门id
	 * @return java.util.List<com.wblog.system.entity.SysDeptDo>
	 * @since 2021/2/12
	 */
	List<SysDeptDo> selectChildrenDeptById(@Param("deptId") Long deptId);

	/**
	 * 修改子元素关系
	 *
	 * @param depts
	 * @return void
	 * @since 2021/2/12
	 */
	void updateDeptChildren(@Param("depts") List<SysDeptDo> depts);

	/**
	 * 修改部门所有的父级部门的状态
	 *
	 * @param dept 当前部门
	 * @return void
	 * @since 2021/2/12
	 */
	void updateDeptStatus(SysDeptDo dept);
}
