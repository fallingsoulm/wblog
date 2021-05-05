package com.wblog.system.controller;

import com.wblog.system.entity.SysMenuDo;
import com.wblog.system.service.ISysMenuService;
import com.wblog.system.vo.RouterVo;
import com.wblog.system.vo.TreeSelectVo;
import io.github.fallingsoulm.easy.archetype.framework.page.RespEntity;
import io.github.fallingsoulm.easy.archetype.security.core.LoginUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 菜单控制层
 *
 * @author luyanan
 * @since 2021/2/7
 **/
@Api(tags = "菜单")
@RestController
@RequestMapping("system/menu")
public class SysMenuController {

	@Autowired
	private LoginUserService loginUserService;

	@Autowired
	private ISysMenuService sysMenuService;

	/**
	 * 获取路由信息
	 *
	 * @return com.wblog.framework.core.page.RespEntity
	 * @since 2021/2/7
	 */
	@ApiOperation(value = "获取路由信息", response = RouterVo.class)
	@GetMapping("getRouters")
	public RespEntity getRouters() {
		List<RouterVo> routers = sysMenuService.getRouters(loginUserService.getUserId());
		return RespEntity.success(routers);
	}


	/**
	 * 获取菜单列表
	 *
	 * @param menu 条件
	 * @return com.wblog.framework.core.page.RespEntity<java.util.List < com.wblog.system.entity.SysMenuDo>>
	 * @since 2021/2/14
	 */
	@ApiOperation(value = "获取菜单列表")
	@PreAuthorize("@ss.hasPermi('system:menu:list')")
	@GetMapping("/list")
	public RespEntity<List<SysMenuDo>> list(SysMenuDo menu) {
//		Long userId = loginUserService.getUserId();
//		List<SysMenuDo> sysMenuDos = sysMenuService.listByUserId(menu, userId);
		List<SysMenuDo> sysMenuDos = sysMenuService.list(menu);
		return RespEntity.success(sysMenuDos);
	}

	/**
	 * 根据菜单id查询菜单详情
	 *
	 * @param menuId 菜单id
	 * @return AjaxResult
	 * @since 2021/2/14
	 */
	@ApiOperation(value = "根据菜单id查询菜单详情")
	@PreAuthorize("@ss.hasPermi('system:menu:query')")
	@GetMapping(value = "/{menuId}")
	public RespEntity<SysMenuDo> getInfo(@PathVariable Long menuId) {
		SysMenuDo sysMenuDo = sysMenuService.findById(menuId);
		return RespEntity.success(sysMenuDo);
	}

	/**
	 * 获取菜单下拉列表
	 *
	 * @param menu
	 * @return com.wblog.framework.core.page.RespEntity<java.util.List < com.wblog.system.vo.TreeSelectVo>>
	 * @since 2021/2/14
	 */
	@ApiOperation(value = "获取菜单下拉列表")
	@GetMapping("/treeselect")
	public RespEntity<List<TreeSelectVo>> treeselect(SysMenuDo menu) {

		Long userId = loginUserService.getUserId();
		List<TreeSelectVo> treeSelects = sysMenuService.treeselect(menu, userId);
		return RespEntity.success(treeSelects);
	}

	/**
	 * 加载对应角色菜单列表树
	 *
	 * @param roleId 角色id
	 * @return com.wblog.framework.core.page.RespEntity
	 * @since 2021/2/14
	 */
	@ApiOperation(value = "加载对应角色菜单列表树")
	@GetMapping(value = "/roleMenuTreeselect/{roleId}")
	public RespEntity roleMenuTreeselect(@PathVariable("roleId") Long roleId) {

		List<Long> checkedKeys = Optional.ofNullable(sysMenuService.listByRoleIds(Arrays.asList(roleId))).orElse(new ArrayList<>()).stream().map(a -> a.getMenuId()).distinct().collect(Collectors.toList());

		List<TreeSelectVo> treeselect = sysMenuService.treeselect(new SysMenuDo(), loginUserService.getUserId());
		return RespEntity.success(map -> {
			map.put("checkedKeys", checkedKeys);
			map.put("menus", treeselect);
		});
	}

	/**
	 * 新增菜单
	 *
	 * @param menu 菜单
	 * @return com.wblog.framework.core.page.RespEntity
	 * @since 2021/2/14
	 */
	@ApiOperation(value = "新增菜单")
	@PreAuthorize("@ss.hasPermi('system:menu:add')")
	@PostMapping
	public RespEntity add(@Validated @RequestBody SysMenuDo menu) {

		sysMenuService.insert(menu);
		return RespEntity.success();
	}

	/**
	 * 菜单修改
	 *
	 * @param menu
	 * @return com.wblog.framework.core.page.RespEntity
	 * @since 2021/2/14
	 */
	@ApiOperation(value = "菜单修改")
	@PreAuthorize("@ss.hasPermi('system:menu:edit')")
	@PutMapping
	public RespEntity edit(@Validated @RequestBody SysMenuDo menu) {
		sysMenuService.update(menu);
		return RespEntity.success();
	}

	/**
	 * 根据菜单id删除
	 *
	 * @param menuId 菜单id
	 * @return com.wblog.framework.core.page.RespEntity
	 * @since 2021/2/14
	 */
	@ApiOperation(value = "根据菜单id删除")
	@PreAuthorize("@ss.hasPermi('system:menu:remove')")
	@DeleteMapping("/{menuId}")
	public RespEntity remove(@PathVariable("menuId") Long menuId) {

		sysMenuService.deleteById(menuId);
		return RespEntity.success();
	}
}
