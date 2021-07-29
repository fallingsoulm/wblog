package com.wblog.info.news;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wblog.common.enums.ConstantEnum;
import com.wblog.common.module.info.vo.NewsVo;
import com.wblog.info.entity.NewsInfoEntity;
import com.wblog.info.utils.UserAgent;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luyanan
 * @since 2020/7/21
 * <p>csdn的资讯收集</p>
 **/
@Service("cSDNNewsTask")
public class CSDNNewsSpiderHandler extends AbstractNewsSpiderHandler {


    private String shown_offset;

    @Override
    protected List<NewsVo> parserHtml(String html) {

        if (StrUtil.isBlank(html)) {
            return new ArrayList<>();
        }
        if (html.startsWith("<!DOCTYPE html")) {
            return htmlParser(html);
        } else {
            return jsonParser(html);
        }
    }

    /**
     * <p>解析返回结果为json的内容</p>
     *
     * @param html
     * @return {@link List< NewsVo>}
     * @author luyanan
     * @since 2020/7/21
     */
    private List<NewsVo> jsonParser(String html) {
        List<NewsVo> newsVoList = new ArrayList<>();
        JSONObject jsonObject = JSON.parseObject(html);
        shown_offset = jsonObject.getString("shown_offset");
        JSONArray articles = jsonObject.getJSONArray("articles");
        if (null != articles && articles.size() > 0) {
            for (int i = 0; i < articles.size(); i++) {

                JSONObject article = articles.getJSONObject(i);

                String title = article.getString("title");
                String desp = article.getString("summary");
                String id = article.getString("id");
                String userName = article.getString("user_name");
                String url = getUrl(id, userName);
                String time = article.getString("shown_time");

                NewsVo newsVo = buildNewVos().url(url).publishTime(DateUtil.date(Long.valueOf(time + "000")))
                        .title(title)
                        .desp(desp)
                        .build();
                newsVoList.add(newsVo);

            }
        }
        return newsVoList;
    }

    /**
     * <p>解析html</p>
     *
     * @param html
     * @return {@link List< NewsVo>}
     * @author luyanan
     * @since 2020/7/21
     */
    private List<NewsVo> htmlParser(String html) {
        List<NewsVo> newsVoList = new ArrayList<>();
        Document document = Jsoup.parse(html);
        shown_offset = document.select("#feedlist_id").attr("shown-offset");
        Elements elements = document.select("#feedlist_id").select("li.clearfix");
        if (null != elements && elements.size() > 0) {
            for (Element element : elements) {
                String dataType = element.attr("data-type");
                if (!dataType.equals("blog")) {
                    continue;
                }
                String title = element.select("div.title").text();
                String id = element.attr("data-id");
                String time = element.attr("shown-time");

                String userName = element.select("dl.list_userbar img").attr("username");
                String url = getUrl(id, userName);
                NewsVo newsVo = buildNewVos().title(title)
                        .desp(title)
                        .url(url)
                        .publishTime(DateUtil.date(Long.valueOf(time + "000")))
                        .build();
                newsVoList.add(newsVo);
            }
        }

        return newsVoList;

    }

    private String getUrl(String id, String userName) {
        return "https://blog.csdn.net/" + userName + "/article/details/" + id;
    }

    @Override
    protected String getHtmlContent(Integer page) {
        if (StrUtil.isBlank(shown_offset)) {
            // 当初次获取的时候, 从https://www.csdn.net/nav/newarticles 中获取
            return HttpUtil.createGet("https://www.csdn.net/nav/newarticles")
                    .execute().body();
        } else {
            // 从 https://www.csdn.net/api/articles?type=more&category=newarticles&shown_offset=1595305716000000 中获取
            return HttpUtil.createGet("https://www.csdn.net/api/articles?type=more&category=newarticles&shown_offset=" + shown_offset)
                    .header("User-Agent", UserAgent.getUserAgent())
                    .header("accept", "application/json, text/javascript, */*; q=0.01")
                    .header("accept-language", "zh-CN,zh;q=0.9")
                    .execute().body();
        }
    }

    @Override
    public ConstantEnum source() {
        return ConstantEnum.NEWS_SOURCE_CSDN;
    }


    @Override
    protected void finishProcessor() {
        shown_offset = null;
    }

    @Override
    protected String parserContent(String html) {
        Element content_views = Jsoup.parse(html).getElementById("content_views");
        if (null == content_views) {
            return null;
        }
        return content_views.html();
    }


}
