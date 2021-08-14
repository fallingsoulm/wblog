package com.wblog.search.sitemap;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class BaiduSubmitSitemapTest {

    @Test
    public void test() {

        String url = "http://data.zz.baidu.com/urls?site=https://www.luyanan.com&token=up4m0Kt41DY2PoJT";
        List<String> lists = new ArrayList<>();
        lists.add("https://www.luyanan.com/news/info/21760229680705.html");
        System.out.println(HttpUtil.createPost(url).body(lists.stream().collect(Collectors.joining("\n")), "text/plain").execute().body());

    }

    @Test
    public void bing() {
        String url = "https://www.bing.com/webmaster/api.svc/json/SubmitUrlbatch?apikey=da267dbba02345bba615c5e5860f17c3";

        List<String> lists = new ArrayList<>();
        lists.add("https://www.luyanan.com/news/info/21760229680705.html");
        Map<String, Object> map = new HashMap<>();
        map.put("siteUrl", "https://luyanan.com");
        map.put("urlList", lists);
        String body = HttpUtil.createGet(url).body(JSON.toJSONString(map)).execute().body();
        System.out.println(body);
    }
}