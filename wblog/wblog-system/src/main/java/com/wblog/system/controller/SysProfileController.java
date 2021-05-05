package com.wblog.system.controller;

import com.wblog.system.service.ISysUserService;
import com.wblog.system.vo.SysUserVo;
import io.github.fallingsoulm.easy.archetype.framework.page.RespEntity;
import io.github.fallingsoulm.easy.archetype.security.core.LoginUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 个人信息
 *
 * @author luyanan
 * @since 2021/2/14
 **/
@Api(tags = "个人信息")
@RestController
@RequestMapping("/system/user/profile")
public class SysProfileController {

    @Autowired
    private LoginUserService loginUserService;

    @Autowired
    private ISysUserService sysUserService;

    /**
     * 个人信息
     *
     * @return com.wblog.framework.core.page.RespEntity
     * @since 2021/2/14
     */
    @ApiOperation(value = "个人信息")
    @GetMapping
    public RespEntity profile() {


        Long userId = loginUserService.getUserId();
        return RespEntity.success(map -> {
            map.put("roleGroup", sysUserService.selectUserRoleGroup(userId));
            map.put("postGroup", sysUserService.selectUserPostGroup(userId));
            map.put("user", sysUserService.info(userId));
        });
    }

    /**
     * 修改个人信息
     *
     * @param user
     * @return com.wblog.framework.core.page.RespEntity
     * @since 2021/2/14
     */
    @ApiOperation(value = "修改个人信息")
    @PutMapping
    public RespEntity updateProfile(@RequestBody SysUserVo user) {
        sysUserService.updateUserProfile(user);
        return RespEntity.success();
    }

    /**
     * 重置密码
     *
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @return RespEntity
     * @since 2021/2/14
     */
    @ApiOperation(value = "重置密码")
    @PutMapping("/updatePwd")
    public RespEntity updatePwd(String oldPassword, String newPassword) {
        sysUserService.updatePwd(loginUserService.getUserId(), oldPassword, newPassword);
        return RespEntity.success();
    }


    /**
     * 头像上传
     *
     * @param file
     * @return com.wblog.framework.core.page.RespEntity
     * @since 2021/2/14
     */
    @ApiOperation(value = "头像上传")
    @PostMapping("/avatar")
    public RespEntity avatar(@RequestParam("avatarfile") MultipartFile file) throws IOException {

        sysUserService.avatar(loginUserService.getUserId(), file);
        return RespEntity.success();
    }
}
