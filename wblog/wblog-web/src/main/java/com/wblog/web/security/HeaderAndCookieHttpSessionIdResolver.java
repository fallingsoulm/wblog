package com.wblog.web.security;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.session.web.http.CookieHttpSessionIdResolver;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;
import org.springframework.session.web.http.HttpSessionIdResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 通过cookie和header 中获取session
 *
 * @author luyanan
 * @since 2021/1/30
 **/
public class HeaderAndCookieHttpSessionIdResolver implements HttpSessionIdResolver {

	private static final String WRITTEN_SESSION_ID_ATTR = CookieHttpSessionIdResolver.class.getName()
			.concat(".WRITTEN_SESSION_ID_ATTR");

	private final String sessionIdName;

	private CookieSerializer cookieSerializer = new DefaultCookieSerializer();

	public HeaderAndCookieHttpSessionIdResolver(String sessionIdName) {
		if (sessionIdName == null) {
			throw new IllegalArgumentException("sessionIdName cannot be null");
		}
		else {
			this.sessionIdName = sessionIdName;
		}
	}

	@Override
	public List<String> resolveSessionIds(HttpServletRequest httpServletRequest) {
		// 从header 中获取
		String session = httpServletRequest.getHeader(this.sessionIdName);
		if (StrUtil.isNotBlank(session)) {
			return Collections.singletonList(base64Decode(session));
		}

		// 从cookie中获取
		List<String> sessions = this.cookieSerializer.readCookieValues(httpServletRequest);
		if (CollectionUtil.isNotEmpty(sessions)) {
			return sessions;
		}
		// 从请求头中获取
		String[] parameterValues = httpServletRequest.getParameterValues(this.sessionIdName);
		return Arrays.stream(Optional.ofNullable(parameterValues).orElse(new String[] {})).collect(Collectors.toList());
	}

	@Override
	public void setSessionId(HttpServletRequest request, HttpServletResponse response, String sessionId) {
		// 写到请求头中
//		response.setHeader(this.sessionIdName, base64Encode(sessionId));

		// 再写到cookie中
		if (!sessionIdName.equals(request.getAttribute(WRITTEN_SESSION_ID_ATTR))) {
			request.setAttribute(WRITTEN_SESSION_ID_ATTR, sessionIdName);
			this.cookieSerializer.writeCookieValue(new CookieSerializer.CookieValue(request, response, sessionId));
		}
	}

	@Override
	public void expireSession(HttpServletRequest request, HttpServletResponse response) {
		this.cookieSerializer.writeCookieValue(new CookieSerializer.CookieValue(request, response, ""));
		response.setHeader(this.sessionIdName, "");
	}

	public void setCookieSerializer(CookieSerializer cookieSerializer) {
		if (cookieSerializer == null) {
			throw new IllegalArgumentException("cookieSerializer cannot be null");
		}
		else {
			this.cookieSerializer = cookieSerializer;
		}
	}

	public static String base64Decode(String base64Value) {
		try {
			byte[] decodedCookieBytes = Base64.getDecoder().decode(base64Value);
			return new String(decodedCookieBytes);
		}
		catch (Exception var3) {
			return null;
		}
	}

	public static String base64Encode(String value) {
		byte[] encodedCookieBytes = Base64.getEncoder().encode(value.getBytes());
		return new String(encodedCookieBytes);
	}

}
