package com.wblog.system.enums;

import java.text.MessageFormat;
import java.time.Duration;

/**
 * redis的key的枚举
 *
 * @author luyanan
 * @since 2021/2/4
 **/
public enum SystemRedisKeyEnums {

	/**
	 * 验证码的key
	 *
	 * @since 2021/2/4
	 */
	CAPTCHA_CODES("captcha_codes:{0}", Duration.ofMinutes(2), "验证码");

	/**
	 * key
	 *
	 * @since 2021/2/4
	 */
	private String key;

	/**
	 * 介绍
	 *
	 * @since 2021/2/4
	 */
	private String desp;

	/**
	 * 失效时间
	 *
	 * @since 2021/2/4
	 */
	private Duration expire;

	SystemRedisKeyEnums(String key, Duration expire, String desp) {
		this.key = key;
		this.desp = desp;
		this.expire = expire;
	}

	public String getKey(String... params) {
		return MessageFormat.format(this.key, params);
	}

	public String getDesp() {
		return desp;
	}

	public Duration getExpire() {
		return expire;
	}

}
