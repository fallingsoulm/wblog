package com.wblog.info.task;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.apes.hub.api.enums.ConstantEnum;
import com.apes.hub.api.module.info.vo.NewsVo;
import com.apes.hub.data.http.UserAgent;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author luyanan
 * @since 2020/7/14
 * <p>博客园资讯抓取</p>
 **/
@Service("cnblogsNewsTask")
public class CnblogsNewsTask extends AbstractNewsTask {


    @Override
    protected List<NewsVo> parserHtml(String html) {
        List<NewsVo> newsVoList = new ArrayList<>();
        if (StrUtil.isBlank(html)) {
            return newsVoList;
        }
        Elements elements = Jsoup.parse(html).select(".post-item-body");
        if (null != elements && elements.size() > 0) {
            for (Element element : elements) {
                String title = element.select(".post-item-title").text();
                String url = element.select(".post-item-title").attr("href");
                String desp = element.select(".post-item-summary").text();
                Date publishTime = parserPublishTime(element.select("post-item-foot").text());
                newsVoList.add(buildNewVos().publishTime(publishTime).desp(desp).url(url).title(title).build());
            }
        }
        return newsVoList;
    }

    /**
     * <p>解析发布时间</p>
     *
     * @param post_item_foot
     * @return {@link Date}
     * @author luyanan
     * @since 2020/7/14
     */
    private Date parserPublishTime(String post_item_foot) {

        if (StrUtil.isBlank(post_item_foot)) {
            return new Date();
        }

        String s = ReUtil.get("[0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}", post_item_foot, 0);

        if (StrUtil.isBlank(s)) {
            return new Date();
        }

        return DateUtil.parse(s, "yyyy-MM-dd HH:mm").toJdkDate();
    }


    @Override
    protected String getHtmlContent(Integer page) {
        Map<String, Object> params = new HashMap<>();
        params.put("CategoryId", 808);
        params.put("CategoryType", "SiteHome");
        params.put("ItemListActionName", "AggSitePostList");
        params.put("PageIndex", page);
        params.put("ParentCategoryId", 0);
        params.put("TotalPostCount", 4000);
        return HttpUtil.createPost("https://www.cnblogs.com/AggSite/AggSitePostList").header("User-Agen", UserAgent.getUserAgent())
                .body(JSON.toJSONString(params)).execute().body();
    }

    @Override
    protected ConstantEnum source() {
        return ConstantEnum.NEWS_SOURCE_CNBLOGS;
    }
}
