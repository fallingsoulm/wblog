package com.wblog.system.manage;

import com.wblog.system.entity.SysDeptDo;
import io.github.fallingsoulm.easy.archetype.data.manage.IManage;

import java.util.List;

/**
 * <p>
 * 部门表 manage
 * </p>
 *
 * @author luyanan
 * @since 2021-02-03
 */
public interface ISysDeptManage extends IManage<SysDeptDo> {

	/**
	 * 根据角色id查询部门树信息
	 *
	 * @param roleId            角色id
	 * @param deptCheckStrictly 部门树选择项是否关联显示
	 * @return java.util.List<java.lang.Long> 选中的部门树列表
	 * @since 2021/2/12
	 */
	List<Long> listByRoleId(Long roleId, Boolean deptCheckStrictly);

	/**
	 * 查询所有的子部门(正常状态)
	 *
	 * @param deptId 部门id
	 * @return int 子部门个数
	 * @since 2021/2/12
	 */
	int selectNormalChildrenDeptById(Long deptId);

	/**
	 * 根据id查询所有的子部门
	 *
	 * @param deptId 部门id
	 * @return java.util.List<com.wblog.system.entity.SysDeptDo>
	 * @since 2021/2/12
	 */
	List<SysDeptDo> selectChildrenDeptById(Long deptId);

	/**
	 * 修改子元素关系
	 *
	 * @param children 子元素
	 * @return void
	 * @since 2021/2/12
	 */
	void updateDeptChildren(List<SysDeptDo> children);

	/**
	 * 修改所在部门的父级部门状态
	 *
	 * @param dept 当前部门
	 * @return void
	 * @since 2021/2/12
	 */
	void updateDeptStatus(SysDeptDo dept);

}
