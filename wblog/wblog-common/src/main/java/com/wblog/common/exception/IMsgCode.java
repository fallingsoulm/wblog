package com.wblog.common.exception;

/**
 * <p>
 * 消息编码的接口类
 * </p>
 *
 * @author luyanan
 * @since 2021/1/17
 **/
public interface IMsgCode {


	/**
	 * 成功
	 *
	 * @since 2021/1/24
	 */
	String SUCCESS = "200";

	/**
	 * 未登录
	 *
	 * @since 2021/2/9
	 */
	String HTTP_UNAUTHORIZED = "401";
	/**
	 * 没有权限
	 *
	 * @since 2021/2/21
	 */

	String PERMISSION_DENIED = "403";
	/**
	 * 不支持的请求类型
	 *
	 * @since 2021/1/24
	 */
	String HTTP_NOT_FOUND = "405";

	/**
	 * 服务端错误
	 *
	 * @since 2021/1/24
	 */
	String INTERNAL_SERVER_ERROR = "500";


	String CONFIG_NOT_FOUND = "1000";
}
