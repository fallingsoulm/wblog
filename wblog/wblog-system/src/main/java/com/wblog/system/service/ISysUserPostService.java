package com.wblog.system.service;

import com.wblog.system.entity.SysUserPostDo;

import java.util.List;

/**
 * <p>
 * 用户与岗位关联表 service
 * </p>
 *
 * @author luyanan
 * @since 2021-02-03
 */
public interface ISysUserPostService {

	/**
	 * 根据条件查询
	 *
	 * @param sysUserPostDo 条件
	 * @return java.util.List<com.wblog.system.entity.SysUserPostDo>
	 * @since 2021/2/12
	 */
	List<SysUserPostDo> list(SysUserPostDo sysUserPostDo);

	/**
	 * 批量插入
	 *
	 * @param userPostDoList
	 * @return void
	 * @since 2021/2/12
	 */
	void insertBatch(List<SysUserPostDo> userPostDoList);


	/**
	 * 批量插入
	 *
	 * @param postIds 岗位id集合
	 * @param userId  用户id
	 * @return void
	 * @since 2021/2/12
	 */
	void insertBatch(List<Long> postIds, Long userId);

	/**
	 * 根据条件删除
	 *
	 * @param sysUserPostDo 实体条件
	 * @return void
	 * @since 2021/2/12
	 */
	void delete(SysUserPostDo sysUserPostDo);
}
