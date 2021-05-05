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
	String USER_PASSWORD_NOT_FOUND = "S_001";

	/**
	 * 验证码生成错误
	 *
	 * @since 2021/2/4
	 */
	String CAPTCHA_GENERATE_FAIL = "S_002";

	/**
	 * 用户名已经存在
	 *
	 * @since 2021/2/12
	 */
	String USERNAME_UNIQUE = "S_003";

	/**
	 * 手机号已经存在
	 *
	 * @since 2021/2/12
	 */
	String PHONE_UNIQUE = "S_004";


	/**
	 * 邮箱已经存在
	 *
	 * @since 2021/2/12
	 */
	String EMAIL_UNIQUE = "S_005";

	/**
	 * 没有权限操作
	 *
	 * @since 2021/2/12
	 */

	String PERMISSION_DENIED = "S_006";


	/**
	 * 部门名称已经存在
	 *
	 * @since 2021/2/12
	 */

	String DEPT_NAME_UNIQUE = "S_007";


	/**
	 * 字典不存在
	 *
	 * @since 2021/2/12
	 */

	String DICT_NOT_FOUNT = "S_008";


	/**
	 * 部门停用
	 *
	 * @since 2021/2/12
	 */

	String DEPT_STATUS_DISABLE = "S_009";

	/**
	 * 上级部门不能是自己
	 *
	 * @since 2021/2/12
	 */

	String DEPT_PARENT_IS_NOT_ME = "S_010";


	/**
	 * 部门包含未停用的子部门
	 *
	 * @since 2021/2/12
	 */

	String DEPT_HAV_DISABLE_CHILD_DEPT = "S_011";

	/**
	 * 存在下级部门
	 *
	 * @since 2021/2/12
	 */

	String DEPT_HAS_CHILD = "S_012";


	/**
	 * 部门存在用户
	 *
	 * @since 2021/2/12
	 */

	String DEPT_HAS_USER = "S_013";


	/**
	 * 字典类型重复
	 *
	 * @since 2021/2/12
	 */

	String DICT_TYPE_UNIQUE = "S_014";

	/**
	 * 字典类型已经分类
	 *
	 * @since 2021/2/12
	 */

	String DICT_TYPE_DISTRIBUTION = "S_015";


	/**
	 * 菜单名称唯一
	 *
	 * @since 2021/2/14
	 */

	String MENU_NAME_UNIQUE = "S_016";


	/**
	 * 菜单路径必须以http/Https开头
	 *
	 * @since 2021/2/14
	 */

	String MENU_PATH_START_HTTP = "S_017";

	/**
	 * 上级菜单不能选择自己
	 *
	 * @since 2021/2/14
	 */

	String MENU_PARENT_IS_NOT_ME = "S_018";


	/**
	 * 岗位名称已经存在
	 *
	 * @since 2021/2/14
	 */

	String POST_NAME_UNIQUE = "S_019";

	/**
	 * 岗位编码存在
	 *
	 * @since 2021/2/14
	 */

	String POST_CODE_UNIQUE = "S_020";

	/**
	 * 旧密码错误
	 *
	 * @since 2021/2/15
	 */

	String OLD_PASSWORD_ERROR = "S_021";
	/**
	 * 修改密码失败,新旧密码不能一致
	 *
	 * @since 2021/2/15
	 */

	String OLD_AND_NEW_PASSWORDS_CANNOT_BE_THE_SAME = "S_022";
	/**
	 * 角色名称已经存在
	 *
	 * @since 2021/2/15
	 */

	String ROLE_NAME_UNIQUE = "S_023";
	/**
	 * 角色权限已经存在
	 *
	 * @since 2021/2/15
	 */

	String ROLE_KEY_UNIQUE = "S_024";
	/**
	 * 角色包含关联的用户,不能删除
	 *
	 * @since 2021/2/15
	 */

	String ROLE_HAS_USER = "S_025";
}
