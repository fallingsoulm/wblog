package com.wblog.system;

import cn.hutool.core.io.FileUtil;
import com.wblog.common.exception.IMsgCode;

/**
 * 系统模块的异常编码
 *
 * @author luyanan
 * @since 2021/2/12
 **/
public interface SystemMsgCode extends IMsgCode {


    /***************** 用户模块编码 ******************/
    /**
     * 用户名/密码不正确
     *
     * @since 2021/1/30
     */
    int USER_PASSWORD_NOT_FOUND = 10001;

    /**
     * 验证码生成错误
     *
     * @since 2021/2/4
     */
    int CAPTCHA_GENERATE_FAIL = 10002;

    /**
     * 用户名已经存在
     *
     * @since 2021/2/12
     */
    int USERNAME_UNIQUE = 10003;

    /**
     * 手机号已经存在
     *
     * @since 2021/2/12
     */
    int PHONE_UNIQUE = 10004;


    /**
     * 邮箱已经存在
     *
     * @since 2021/2/12
     */
    int EMAIL_UNIQUE = 10005;

    /**
     * 没有权限操作
     *
     * @since 2021/2/12
     */

    int PERMISSION_DENIED = 10006;


    /**
     * 部门名称已经存在
     *
     * @since 2021/2/12
     */

    int DEPT_NAME_UNIQUE = 10007;


    /**
     * 字典不存在
     *
     * @since 2021/2/12
     */

    int DICT_NOT_FOUNT = 10008;


    /**
     * 部门停用
     *
     * @since 2021/2/12
     */

    int DEPT_STATUS_DISABLE = 10009;

    /**
     * 上级部门不能是自己
     *
     * @since 2021/2/12
     */

    int DEPT_PARENT_IS_NOT_ME = 10010;


    /**
     * 部门包含未停用的子部门
     *
     * @since 2021/2/12
     */

    int DEPT_HAV_DISABLE_CHILD_DEPT = 10011;

    /**
     * 存在下级部门
     *
     * @since 2021/2/12
     */

    int DEPT_HAS_CHILD = 10012;


    /**
     * 部门存在用户
     *
     * @since 2021/2/12
     */

    int DEPT_HAS_USER = 10013;


    /**
     * 字典类型重复
     *
     * @since 2021/2/12
     */

    int DICT_TYPE_UNIQUE = 10014;

    /**
     * 字典类型已经分类
     *
     * @since 2021/2/12
     */

    int DICT_TYPE_DISTRIBUTION = 10015;


    /**
     * 菜单名称唯一
     *
     * @since 2021/2/14
     */

    int MENU_NAME_UNIQUE = 10016;


    /**
     * 菜单路径必须以http/Https开头
     *
     * @since 2021/2/14
     */

    int MENU_PATH_START_HTTP = 10017;

    /**
     * 上级菜单不能选择自己
     *
     * @since 2021/2/14
     */

    int MENU_PARENT_IS_NOT_ME = 10018;


    /**
     * 岗位名称已经存在
     *
     * @since 2021/2/14
     */

    int POST_NAME_UNIQUE = 10019;

    /**
     * 岗位编码存在
     *
     * @since 2021/2/14
     */

    int POST_CODE_UNIQUE = 10020;

    /**
     * 旧密码错误
     *
     * @since 2021/2/15
     */

    int OLD_PASSWORD_ERROR = 10021;
    /**
     * 修改密码失败,新旧密码不能一致
     *
     * @since 2021/2/15
     */

    int OLD_AND_NEW_PASSWORDS_CANNOT_BE_THE_SAME = 10022;
    /**
     * 角色名称已经存在
     *
     * @since 2021/2/15
     */

    int ROLE_NAME_UNIQUE = 10023;
    /**
     * 角色权限已经存在
     *
     * @since 2021/2/15
     */

    int ROLE_KEY_UNIQUE = 10024;
    /**
     * 角色包含关联的用户,不能删除
     *
     * @since 2021/2/15
     */

    int ROLE_HAS_USER = 10025;
}
