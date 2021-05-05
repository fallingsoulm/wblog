package com.wblog.system.enums;

import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 枚举
 *
 * @author luyanan
 * @since 2021/2/12
 **/
public interface IEnums {


	/**
	 * 获取分类
	 *
	 * @return java.lang.String
	 * @since 2021/2/12
	 */
	IClassify getClassify();

	/**
	 * 获取编码
	 *
	 * @return java.lang.Object
	 * @since 2021/2/12
	 */
	Object getCode();

	/**
	 * 获取介绍
	 *
	 * @return java.lang.String
	 * @since 2021/2/12
	 */
	String getDesp();


	/**
	 * 转换成Vo类
	 *
	 * @return com.wblog.system.enums.IEnums.EnumVo
	 * @since 2021/2/12
	 */
	default EnumVo enumVo() {
		EnumVo enumVo = new EnumVo();
		enumVo.setClassify(this.getClassify().getCode());
		enumVo.setCode(this.getCode());
		enumVo.setDesp(this.getDesp());
		return enumVo;
	}


	/**
	 * 分类
	 *
	 * @since 2021/2/12
	 */

	public static interface IClassify {

		/**
		 * 获取编码
		 *
		 * @since 2021/2/12
		 */

		String getCode();
	}

	@Data
	public static class EnumVo {

		/**
		 * 分类
		 *
		 * @since 2021/2/12
		 */

		private String classify;

		/**
		 * 编码
		 *
		 * @since 2021/2/12
		 */

		private Object code;
		/**
		 * 介绍
		 *
		 * @since 2021/2/12
		 */

		private String desp;
	}
}
