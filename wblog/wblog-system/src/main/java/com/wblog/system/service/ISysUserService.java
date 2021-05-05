package com.wblog.system.service;

import com.wblog.system.entity.SysMenuDo;
import com.wblog.system.entity.SysUserDo;
import com.wblog.system.vo.SysUserVo;
import io.github.fallingsoulm.easy.archetype.framework.page.PageInfo;
import io.github.fallingsoulm.easy.archetype.framework.page.PageRequestParams;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 用户信息表 service
 * </p>
 *
 * @author luyanan
 * @since 2021-02-03
 */
public interface ISysUserService {

    /**
     * 根据用户id查询
     *
     * @param userId 用户id
     * @return com.wblog.system.entity.SysUserDo
     * @since 2021/2/4
     */
    SysUserDo findById(Long userId);

    /**
     * 根据用户名查询用户
     *
     * @param username 用户名
     * @return com.wblog.system.entity.SysUserDo
     * @since 2021/2/10
     */
    SysUserDo findByUserName(String username);

    /**
     * 分页查询
     *
     * @param pageRequestParams 分页查询参数
     * @return com.wblog.framework.core.page.PageInfo<com.wblog.system.entity.SysUserDo>
     * @since 2021/2/11
     */
    PageInfo<SysUserDo> findByPage(PageRequestParams<SysUserDo> pageRequestParams);

    /**
     * 新增用户
     *
     * @param sysUserVo 用户实体
     * @return int 条数
     * @since 2021/2/12
     */
    int insertUser(SysUserVo sysUserVo);

    /**
     * 修改用户
     *
     * @param user 用户信息
     * @return int
     * @since 2021/2/12
     */
    int updateUser(SysUserVo user);

    /**
     * 根据id集合删除
     *
     * @param ids 用户id集合
     * @return void
     * @since 2021/2/12
     */
    void deleteByIds(List<Long> ids);

    /**
     * 重置密码
     *
     * @param user 用户信息
     * @return void
     * @since 2021/2/12
     */
    void resetPwd(SysUserVo user);

    /**
     * 状态修改
     *
     * @param user 用户信息
     * @return void
     * @since 2021/2/12
     */
    void changeStatus(SysUserVo user);


    /**
     * 统计
     *
     * @param sysUserDo 实体条件
     * @return java.lang.Integer
     * @since 2021/2/12
     */
    Integer count(SysUserDo sysUserDo);

    /**
     * 查询用户所属的角色组
     *
     * @param userId
     * @return java.lang.String
     * @since 2021/2/14
     */
    String selectUserRoleGroup(Long userId);


    /**
     * 查询用户所属的岗位组
     *
     * @param userId
     * @return java.lang.String
     * @since 2021/2/14
     */
    String selectUserPostGroup(Long userId);

    /**
     * 修改个人用户信息
     *
     * @param user
     * @return void
     * @since 2021/2/14
     */
    void updateUserProfile(SysUserVo user);

    /**
     * 重置密码
     *
     * @param userId      用户id
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @return void
     * @since 2021/2/14
     */
    void updatePwd(Long userId, String oldPassword, String newPassword);

    /**
     * 头像上传
     *
     * @param userId
     * @param file
     * @return void
     * @since 2021/2/15
     */
    void avatar(Long userId, MultipartFile file);

    /**
     * 根据用户id查询详情
     *
     * @param userId 用户id
     * @return com.wblog.system.vo.SysUserVo
     * @since 2021/2/15
     */
    SysUserVo info(Long userId);

    /**
     * 根据用户id查询菜单
     *
     * @param userId 用户id
     * @return java.util.List<com.wblog.system.entity.SysMenuDo>
     * @since 2021/4/22
     */
    List<SysMenuDo> findMeunByUserId(Long userId);

}
