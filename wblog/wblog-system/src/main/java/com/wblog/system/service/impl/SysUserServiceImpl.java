package com.wblog.system.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.wblog.common.exception.BusinessException;
import com.wblog.system.SystemMsgCode;
import com.wblog.system.entity.*;
import com.wblog.system.manage.ISysUserManage;
import com.wblog.system.service.*;
import com.wblog.system.vo.SysUserVo;
import io.github.fallingsoulm.easy.archetype.framework.page.PageInfo;
import io.github.fallingsoulm.easy.archetype.framework.page.PageRequestParams;
import io.github.fallingsoulm.easy.archetype.framework.utils.BeanUtils;
import io.github.fallingsoulm.easy.archetype.security.core.LoginUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户信息表 serviceImpl
 * </p>
 *
 * @author luyanan
 * @since 2021-02-03
 */
@Service
public class SysUserServiceImpl implements ISysUserService {

    @Autowired
    private ISysUserManage iSysUserManage;

    @Autowired
    private ISysUserPostService sysUserPostService;

    @Autowired
    private ISysUserRoleService sysUserRoleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private LoginUserService loginUserService;

    @Autowired
    private ISysRoleService sysRoleService;

    @Autowired
    private ISysPostService sysPostService;

    @Autowired
    private ISysDeptService sysDeptService;

    @Autowired
    private ISysMenuService sysMenuService;

    @Override
    public SysUserDo findById(Long userId) {
        return Optional.ofNullable(iSysUserManage.findById(userId)).map(a -> {
            // 不返回密码
            a.setPassword(null);
            return a;
        }).orElse(null);
    }

    @Override
    public SysUserDo findByUserName(String username) {
        return Optional.ofNullable(iSysUserManage.findOne(SysUserDo.builder().userName(username).build())).orElse(null);
    }

    @Override
    public PageInfo<SysUserDo> findByPage(PageRequestParams<SysUserDo> pageRequestParams) {
        return iSysUserManage.listByPage(pageRequestParams);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int insertUser(SysUserVo sysUserVo) {
        checkUnique(sysUserVo);
        // 保存用户信息
        SysUserDo sysUserDo = BeanUtils.copyProperties(sysUserVo, SysUserDo.class);
        // 密码加密
        sysUserDo.setPassword(passwordEncoder.encode(sysUserDo.getPassword()));
        int rows = this.iSysUserManage.insert(sysUserDo);
        sysUserVo.setUserId(sysUserDo.getUserId());
        // 保存用户与岗位关联
        sysUserPostService.insertBatch(Arrays.asList(sysUserVo.getPostIds()), sysUserDo.getUserId());
        // 保存用户与角色关联
        sysUserRoleService.insertBatch(Arrays.asList(sysUserVo.getRoleIds()), sysUserDo.getUserId());
        return rows;

    }

    /**
     * 检验唯一
     *
     * @param sysUserVo
     * @return void
     * @since 2021/2/12
     */
    private void checkUnique(SysUserVo sysUserVo) {
        Long userId = sysUserVo.getUserId();
        SysUserDo oldSysUser = null == userId ? null : iSysUserManage.findById(userId);
        // 校验用户名唯一
        if (isUserNameUnique(sysUserVo, oldSysUser)) {
            throw new BusinessException(SystemMsgCode.USERNAME_UNIQUE);
        }
        // 校验手机号
        if (isPhoneUnique(sysUserVo, oldSysUser)) {
            throw new BusinessException(SystemMsgCode.PHONE_UNIQUE);
        }
        // 校验邮箱
        if (isEmailUnique(sysUserVo, oldSysUser)) {
            throw new BusinessException(SystemMsgCode.EMAIL_UNIQUE);
        }
    }

    /**
     * 校验邮箱是否唯一
     *
     * @param sysUserVo  新提交的值
     * @param oldSysUser 旧的值
     * @return boolean
     * @since 2021/2/12
     */
    private boolean isEmailUnique(SysUserVo sysUserVo, SysUserDo oldSysUser) {
        return (null != oldSysUser && !oldSysUser.getEmail().equals(sysUserVo.getEmail())) ||
                CollectionUtil.isNotEmpty(iSysUserManage.list(SysUserDo.builder().email(sysUserVo.getEmail()).build()));
    }

    /**
     * 校验手机号是否重复
     *
     * @param sysUserVo  提交的新的用户信息
     * @param oldSysUser 旧的用户信息
     * @return boolean
     * @since 2021/2/12
     */
    private boolean isPhoneUnique(SysUserVo sysUserVo, SysUserDo oldSysUser) {
        return (null != oldSysUser && !oldSysUser.getPhonenumber().equals(sysUserVo.getPhonenumber())) ||
                CollectionUtil.isNotEmpty(iSysUserManage.list(SysUserDo.builder().phonenumber(sysUserVo.getPhonenumber()).build()));
    }

    /**
     * 校验用户名是否唯一
     *
     * @param sysUserVo  添加或者修改的用户信息
     * @param oldSysUser 数据库中可能存在的旧数据
     * @return boolean
     * @since 2021/2/12
     */
    private boolean isUserNameUnique(SysUserVo sysUserVo, SysUserDo oldSysUser) {
        return (null != oldSysUser && !oldSysUser.getUserName().equals(sysUserVo.getUserName())) ||
                CollectionUtil.isNotEmpty(iSysUserManage.list(SysUserDo.builder().userName(sysUserVo.getUserName()).build()));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int updateUser(SysUserVo user) {
        // 检验用户是否有修改的权限
        // 只有超级管理员权限的角色才有权利修改超级管理员权限的用户
        checkUserAllowed(user);
        // 校验用户名/手机号/邮箱唯一
        checkUnique(user);
        // 删除用户与岗位关联
        sysUserPostService.delete(SysUserPostDo.builder().userId(user.getUserId()).build());
        // 删除用户与角色关联
        sysUserRoleService.delete(SysUserRoleDo.builder().userId(user.getUserId()).build());

        // 保存用户与岗位关联
        sysUserPostService.insertBatch(Arrays.asList(user.getPostIds()), user.getUserId());
        // 保存用户与角色关联
        sysUserRoleService.insertBatch(Arrays.asList(user.getRoleIds()), user.getUserId());
        iSysUserManage.update(BeanUtils.copyProperties(user, SysUserDo.class));

        return 1;
    }

    @Override
    public void deleteByIds(List<Long> ids) {
        iSysUserManage.deleteBatch(ids);
    }

    @Override
    public void resetPwd(SysUserVo user) {
        checkUserAllowed(user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        iSysUserManage.update(SysUserDo.builder().userId(user.getUserId()).password(user.getPassword()).build());
    }

    @Override
    public void changeStatus(SysUserVo user) {
        checkUserAllowed(user);
        iSysUserManage.update(SysUserDo.builder().userId(user.getUserId()).status(user.getStatus()).build());
    }

    @Override
    public Integer count(SysUserDo sysUserDo) {


        return this.iSysUserManage.count(sysUserDo);

    }

    @Override
    public String selectUserRoleGroup(Long userId) {
        return sysRoleService.listByUserId(userId).stream().map(SysRoleDo::getRoleName).collect(Collectors.joining(","));
    }

    @Override
    public String selectUserPostGroup(Long userId) {
        return sysPostService.listByUserId(userId).stream().map(SysPostDo::getPostName).collect(Collectors.joining(","));
    }

    @Override
    public void updateUserProfile(SysUserVo user) {
        iSysUserManage.update(BeanUtils.copyProperties(user, SysUserDo.class));
    }

    @Override
    public void updatePwd(Long userId, String oldPassword, String newPassword) {

        SysUserDo sysUserDo = iSysUserManage.findById(userId);
        // 匹配原密码
        if (!passwordEncoder.matches(oldPassword, sysUserDo.getPassword())) {

            throw new BusinessException(SystemMsgCode.OLD_PASSWORD_ERROR);
        }
        // 新旧密码是否一致
        if (passwordEncoder.matches(oldPassword, newPassword)) {
            throw new BusinessException(SystemMsgCode.OLD_AND_NEW_PASSWORDS_CANNOT_BE_THE_SAME);
        }
        iSysUserManage.update(SysUserDo.builder().userId(userId).password(passwordEncoder.encode(newPassword)).build());
    }

    @Override
    public void avatar(Long userId, MultipartFile file) {

    }

    @Override
    public SysUserVo info(Long userId) {
        SysUserDo userDo = this.iSysUserManage.findById(userId);
        SysUserVo sysUserVo = BeanUtils.copyProperties(userDo, SysUserVo.class);
        sysUserVo.setDept(sysDeptService.findById(sysUserVo.getDeptId()));
        return sysUserVo;
    }

    @Override
    public List<SysMenuDo> findMeunByUserId(Long userId) {
        // 角色集合
        List<SysRoleDo> sysRoleDos = sysRoleService.listByUserId(userId);
        Set<String> roles = new HashSet<>();
        for (SysRoleDo sysRoleDo : sysRoleDos) {
            roles.addAll(Arrays.asList(sysRoleDo.getRoleKey().split(",")));
        }
        // 菜单集合
        List<SysMenuDo> sysMenuDos = sysMenuService
                .listByRoleIds(sysRoleDos.stream().map(a -> a.getRoleId()).distinct().collect(Collectors.toList()));
        return sysMenuDos;
    }

    /**
     * 检验用户是否有操作的权限
     *
     * @param user
     * @return void
     * @since 2021/2/12
     */
    private void checkUserAllowed(SysUserVo user) {
        // 只有登录的用户为超级管理员的角色的时候才有权限才做超级管理员角色下的用户
        Long loginUserId = loginUserService.getUserId();
        List<SysUserRoleDo> list = sysUserRoleService.list(SysUserRoleDo.builder().userId(user.getUserId()).build());
        // 判断是否为admin用户
        boolean isAdmin = list.stream().filter(a -> a.getRoleId().equals(1L)).findFirst().isPresent();

        if (isAdmin && !loginUserService.getUser().getRoles().contains("admin")) {
            throw new BusinessException(SystemMsgCode.PERMISSION_DENIED);
        }
    }
}
