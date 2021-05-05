package com.wblog.system.service;

import com.wblog.system.entity.SysDeptDo;
import com.wblog.system.vo.TreeSelectVo;

import java.util.List;

/**
 * <p>
 * 部门表 service
 * </p>
 *
 * @author luyanan
 * @since 2021-02-03
 */
public interface ISysDeptService {

	/**
	 * 构建部门下拉列表
	 *
	 * @param sysDeptDo
	 * @return java.util.List<com.wblog.system.vo.TreeSelectVo>
	 * @since 2021/2/11
	 */
	List<TreeSelectVo> treeselect(SysDeptDo sysDeptDo);

	/**
	 * 根据条件查询列表
	 *
	 * @param dept 实体条件
	 * @return java.util.List<com.wblog.system.entity.SysDeptDo>
	 * @since 2021/2/12
	 */
	List<SysDeptDo> list(SysDeptDo dept);

	/**
	 * 根据id查询详情
	 *
	 * @param deptId 部门id
	 * @return com.wblog.system.entity.SysDeptDo
	 * @since 2021/2/12
	 */
	SysDeptDo findById(Long deptId);

	/**
	 * 根据角色id查询关联的部门id
	 *
	 * @param roleId
	 * @return java.util.List<java.lang.Long>
	 * @since 2021/2/12
	 */
	List<Long> listByRoleId(Long roleId);

	/**
	 * 新增部门
	 *
	 * @param dept
	 * @return void
	 * @since 2021/2/12
	 */
	void insertDept(SysDeptDo dept);

	/**
	 * 修改部门
	 *
	 * @param dept
	 * @return void
	 * @since 2021/2/12
	 */
	void updateDept(SysDeptDo dept);

	/**
	 * 根据id删除部门
	 *
	 * @param deptId 部门id
	 * @return void
	 * @since 2021/2/12
	 */
	void deleteById(Long deptId);
}
