package com.wblog.info.task;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.apes.hub.api.enums.ConstantEnum;
import com.apes.hub.api.module.info.vo.NewsVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author luyanan
 * @since 2020/7/13
 * <p>掘金资讯同步</p>
 **/
@Slf4j
@Service("jueJinNewsTask")
public class JueJinNewsTask extends AbstractNewsTask {


    private static String cursor = "0";


//    @Override
//    protected List<NewsVo> parserHtml(String html) {
//
//        List<NewsVo> newsVoList = new ArrayList<>();
//        if (StrUtil.isBlank(html)) {
//            return newsVoList;
//        }
//        JSONObject items = JSON.parseObject(html).getJSONObject("data").getJSONObject("articleFeed").getJSONObject("items");
//        //下一页游标
//        after = items.getJSONObject("pageInfo").getString("endCursor");
//        // 是否包含下一页
//        hasNextPage = items.getJSONObject("pageInfo").getBoolean("hasNextPage");
//        JSONArray edges = items.getJSONArray("edges");
//        if (null != edges && edges.size() > 0) {
//            for (int i = 0; i < edges.size(); i++) {
//                JSONObject edge = edges.getJSONObject(i);
//                JSONObject node = edge.getJSONObject("node");
//                String title = node.getString("title");
//                String url = node.getString("originalUrl");
//                String desp = node.getString("content");
//                Date publishTime = DateUtil.parse(node.getString("updatedAt"), "yyyy-MM-dd'T'HH:mm:ss");
//                newsVoList.add(buildNewVos().title(title).url(url).desp(desp).publishTime(publishTime).build());
//            }
//        }
//        return newsVoList;
//    }

    /**
     * <p>掘金接口改版修改</p>
     *
     * @param page
     * @return {@link String}
     * @author luyanan
     * @since 2020/8/5
     */
    @Override
    protected String getHtmlContent(Integer page) {
        Map<String, Object> params = new HashMap<>();
        params.put("client_type", 2608);
        params.put("cursor", cursor);
        params.put("id_type", 2);
        params.put("limit", 20);
        params.put("sort_type", 300);
        String body = HttpUtil.createPost("https://apinew.juejin.im/recommend_api/v1/article/recommend_all_feed")
                .header("Origin", "https://juejin.im")
                .header("Referer", "https://juejin.im/?sort=newest")
                .header("Sec-Fetch-Mode", "cors")
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/76.0.3809.132 Safari/537.36")
                .body(JSON.toJSONString(params))
                .execute().body();

        return body;
    }

    @Override
    protected void finishProcessor() {
        cursor = "0";
    }


    /**
     * <p>接口改版</p>
     *
     * @param html
     * @return {@link List< NewsVo>}
     * @author luyanan
     * @since 2020/8/5
     */
    @Override
    protected List<NewsVo> parserHtml(String html) {

        List<NewsVo> newsVoList = new ArrayList<>();
        JSONObject jsonObject = JSON.parseObject(html);
        Integer err_no = jsonObject.getInteger("err_no");
        if (!err_no.equals(0)) {

            log.error("采集掘金出现异常:{}", html);
            return newsVoList;
        }
        cursor = jsonObject.getString("cursor");
        JSONArray data = jsonObject.getJSONArray("data");

        if (null != data && data.size() > 0) {
            for (int i = 0; i < data.size(); i++) {
                JSONObject d = data.getJSONObject(i);
                JSONObject item_info = d.getJSONObject("item_info");
                Integer item_type = d.getInteger("item_type");
                if (!item_type.equals(2)) {

                    continue;
                }
                JSONObject article_info = item_info.getJSONObject("article_info");
                String title = article_info.getString("title");
                String desp = article_info.getString("brief_content");
                String id = article_info.getString("article_id");
                Long ctime = article_info.getLong("ctime");

                newsVoList.add(buildNewVos().title(title)
                        .desp(desp)
                        .url("https://juejin.im/post/" + id)
                        .publishTime(new Date(ctime * 1000))
                        .build());
            }
        }
        return newsVoList;
    }

//    @Override
//    protected String getHtmlContent(Integer page) {
//        Map<String, Object> params = new HashMap<>();
//        params.put("operationName", "");
//        params.put("query", "");
//        Map<String, Object> variables = new HashMap<>();
//        variables.put("first", "20");
//        variables.put("after", after);
//        variables.put("order", "POPULAR");
//        params.put("variables", variables);
//        Map<String, Object> extensions = new HashMap<>();
//        Map<String, Object> query = new HashMap<>();
//        query.put("id", "21207e9ddb1de777adeaca7a2fb38030");
//        extensions.put("query", query);
//        params.put("extensions", extensions);
//        return HttpUtil.createPost("https://web-api.juejin.im/query").body(JSON.toJSONString(params)).header("X-Agent", "Juejin/Web")
//                .header("User-Agent", UserAgent.getUserAgent()).execute().body();
//    }


    @Override
    protected ConstantEnum source() {
        return ConstantEnum.NEWS_SOURCE_JUEJIN;
    }

    public static void main(String[] args) {

        Map<String, Object> params = new HashMap<>();
        params.put("client_type", 2608);
        params.put("cursor", cursor);
        params.put("id_type", 2);
        params.put("limit", 20);
        params.put("sort_type", 300);
        String body = HttpUtil.createPost("https://apinew.juejin.im/recommend_api/v1/article/recommend_all_feed")
                .header("Origin", "https://juejin.im")
                .header("Referer", "https://juejin.im/?sort=newest")
                .header("Sec-Fetch-Mode", "cors")
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/76.0.3809.132 Safari/537.36")
                .body(JSON.toJSONString(params))
                .execute().body();
        System.out.println(body);
    }
}
