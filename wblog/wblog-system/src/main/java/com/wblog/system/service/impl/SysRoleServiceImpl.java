package com.wblog.system.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.wblog.common.exception.BusinessException;
import com.wblog.system.SystemMsgCode;
import com.wblog.system.entity.SysRoleDeptDo;
import com.wblog.system.entity.SysRoleDo;
import com.wblog.system.entity.SysRoleMenuDo;
import com.wblog.system.entity.SysUserRoleDo;
import com.wblog.system.manage.ISysRoleManage;
import com.wblog.system.service.ISysRoleDeptService;
import com.wblog.system.service.ISysRoleMenuService;
import com.wblog.system.service.ISysRoleService;
import com.wblog.system.service.ISysUserRoleService;
import com.wblog.system.vo.SysRoleVo;
import io.github.fallingsoulm.easy.archetype.framework.constant.NumberConstants;
import io.github.fallingsoulm.easy.archetype.framework.page.PageInfo;
import io.github.fallingsoulm.easy.archetype.framework.page.PageRequestParams;
import io.github.fallingsoulm.easy.archetype.framework.utils.BeanUtils;
import io.github.fallingsoulm.easy.archetype.security.core.LoginUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色信息表 serviceImpl
 * </p>
 *
 * @author luyanan
 * @since 2021-02-03
 */
@Service
public class SysRoleServiceImpl implements ISysRoleService {

    @Autowired
    private ISysRoleManage sysRoleManage;

    @Autowired
    private ISysUserRoleService sysUserRoleService;

    @Autowired
    private ISysRoleDeptService sysRoleDeptService;

    @Autowired
    private ISysRoleMenuService sysRoleMenuService;

    @Autowired
    private LoginUserService loginUserService;

    @Override
    public List<SysRoleDo> listByUserId(Long userId) {
        // 根据用户id查询关联的角色id
        List<SysUserRoleDo> sysUserRoleDos = sysUserRoleService.list(SysUserRoleDo.builder().userId(userId).build());
        return Optional
                .ofNullable(sysRoleManage.findByIds(
                        sysUserRoleDos.stream().map(SysUserRoleDo::getRoleId).distinct().collect(Collectors.toList())))
                .orElse(new ArrayList<>());
    }

    @Override
    public List<SysRoleDo> findByIds(Collection<Long> ids) {
        if (CollectionUtil.isEmpty(ids)) {
            return new ArrayList<>();
        }
        return sysRoleManage.findByIds(ids);
    }

    @Override
    public List<SysRoleDo> list(SysRoleDo sysRoleDo) {
        return sysRoleManage.list(sysRoleDo);

    }

    @Override
    public SysRoleDo findById(Long roleId) {
        return sysRoleManage.findById(roleId);
    }

    @Override
    public PageInfo<SysRoleDo> findByPage(PageRequestParams<SysRoleDo> pageRequestParams) {
        return sysRoleManage.listByPage(pageRequestParams);
    }

    @Override
    public void insert(SysRoleVo role) {
        // 校验角色名称唯一

        if (sysRoleManage.count(SysRoleDo.builder().roleName(role.getRoleName()).build()) > 0) {
            throw new BusinessException(SystemMsgCode.ROLE_NAME_UNIQUE);
        }
        // 校验角色权限唯一
        if (sysRoleManage.count(SysRoleDo.builder().roleKey(role.getRoleKey()).build()) > 0) {
            throw new BusinessException(SystemMsgCode.ROLE_KEY_UNIQUE);
        }
        SysRoleDo sysRoleDo = BeanUtils.copyProperties(role, SysRoleDo.class);
        sysRoleManage.insert(sysRoleDo);
        // 保存角色
        sysRoleMenuService.insertBatch(sysRoleDo.getRoleId(), Arrays.asList(role.getMenuIds()));
    }

    @Override
    public void update(SysRoleVo role) {
        checkRoleAllowed(role, loginUserService.getUserId());
        SysRoleDo oldSysRole = sysRoleManage.findById(role.getRoleId());
        if (!oldSysRole.getRoleName().equals(role.getRoleName()) &&
                sysRoleManage.count(SysRoleDo.builder().roleName(role.getRoleName()).build()) > 0) {
            throw new BusinessException(SystemMsgCode.ROLE_NAME_UNIQUE);
        }
        // 校验角色权限唯一
        if (!oldSysRole.getRoleKey().equals(role.getRoleKey()) &&
                sysRoleManage.count(SysRoleDo.builder().roleKey(role.getRoleKey()).build()) > 0) {
            throw new BusinessException(SystemMsgCode.ROLE_KEY_UNIQUE);
        }
        sysRoleManage.update(BeanUtils.copyProperties(role, SysRoleDo.class));
        // 删除角色与菜单关联
        sysRoleMenuService.delete(SysRoleMenuDo.builder().roleId(role.getRoleId()).build());
        sysRoleMenuService.insertBatch(role.getRoleId(), Arrays.asList(role.getMenuIds()));
    }

    @Override
    public void dataScope(SysRoleVo role) {
        SysRoleDo sysRoleDo = BeanUtils.copyProperties(role, SysRoleDo.class);
        checkRoleAllowed(role, loginUserService.getUserId());
        // 修改角色信息
        sysRoleManage.update(sysRoleDo);
        // 删除角色与部门关联
        sysRoleDeptService.delete(SysRoleDeptDo.builder().roleId(role.getRoleId()).build());
        //新增角色和部门信息
        sysRoleDeptService.insertBatch(role.getDeptIds(), role.getRoleId());

    }

    @Override
    public void changeStatus(SysRoleDo role) {
        checkRoleAllowed(BeanUtils.copyProperties(role, SysRoleVo.class), loginUserService.getUserId());
        sysRoleManage.update(role);
    }

    @Override
    public void deleteByIds(Long[] roleIds) {
        Arrays.stream(roleIds).forEach(roleId -> {
            // 判断角色权限
            checkRoleAllowed(SysRoleVo.builder().roleId(roleId).build(), loginUserService.getUserId());
            // 判断角色是否已经分配

            if (sysUserRoleService.count(SysUserRoleDo.builder().roleId(roleId).build()) > 0) {
                throw new BusinessException(SystemMsgCode.ROLE_HAS_USER);
            }

        });
        // 删除角色与菜单的关联
        sysUserRoleService.deleteByRoleIds(roleIds);
        // 删除角色与部门的关联
        sysRoleDeptService.deleteByRoleIds(roleIds);
        // 删除角色
        sysRoleManage.deleteBatch(Arrays.asList(roleIds));
    }

    /**
     * 校验角色是否有权限操作
     * 只有当前登陆人是超级管理员才有权限编辑超级管理员的角色
     *
     * @param role
     * @param loginUserId 登陆人的用户id
     * @return void
     * @since 2021/2/15
     */
    private void checkRoleAllowed(SysRoleVo role, Long loginUserId) {
        // 登陆人角色id集合
        List<Long> loginUserRoleIds = sysUserRoleService.list(SysUserRoleDo.builder().userId(loginUserId).build()).stream().map(SysUserRoleDo::getRoleId).distinct().collect(Collectors.toList());
        if (null != role && role.getRoleId().intValue() == NumberConstants.ONE &&
                loginUserRoleIds.stream().filter(a -> a.intValue() != NumberConstants.ONE).findFirst().isPresent()) {
            throw new BusinessException(SystemMsgCode.PERMISSION_DENIED);
        }
    }


}
