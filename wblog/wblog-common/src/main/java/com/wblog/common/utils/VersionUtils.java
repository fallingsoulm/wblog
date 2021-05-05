package com.wblog.common.utils;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.IoUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 版本工具类
 *
 * @author luyanan
 * @since 2021/2/9
 **/
@Slf4j
public class VersionUtils {

	public static void main(String[] args) {
		System.out.println(readGitProperties());
	}

	public static Map<String, Object> readGitProperties() {
		InputStream inputStream = null;
		try {
			ClassLoader classLoader = VersionUtils.class.getClassLoader();
			inputStream = classLoader.getResourceAsStream("git.properties");
			// 读取文件内容，自定义一个方法实现即可
			String versionJson = IoUtil.read(inputStream, "utf-8");
			JSONObject jsonObject = JSON.parseObject(versionJson);
			Set<Map.Entry<String, Object>> entrySet = jsonObject.entrySet();
			if (CollectionUtil.isNotEmpty(entrySet)) {
				return entrySet.stream()
						.collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue(), (o, n) -> n));
			}
		} catch (Exception e) {
			log.error("get git version info fail", e);
		} finally {
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (Exception e) {
				log.error("close inputstream fail", e);
			}
		}
		return new HashMap<>(0);
	}
}
