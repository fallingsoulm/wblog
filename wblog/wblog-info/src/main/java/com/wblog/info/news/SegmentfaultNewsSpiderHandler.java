package com.wblog.info.news;

import cn.hutool.core.date.DateUtil;
import cn.hutool.http.HttpUtil;
import com.wblog.common.enums.ConstantEnum;
import com.wblog.common.module.info.vo.NewsVo;
import com.wblog.info.entity.NewsInfoEntity;
import com.wblog.info.utils.UserAgent;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author luyanan
 * @since 2020/7/24
 * <p>思否 资讯</p>
 **/
@Service("segmentfaultNewsTask")
public class SegmentfaultNewsSpiderHandler extends AbstractNewsSpiderHandler {


    @Override
    protected List<NewsVo> parserHtml(String html) {
        List<NewsVo> newsVoList = new ArrayList<>();
        Elements newsItems = Jsoup.parse(html).select("div.news-item");
        if (null != newsItems && newsItems.size() > 0) {
            for (Element newsItem : newsItems) {

                String title = newsItem.select("h4.news__item-title").text();
                String uul = newsItem.select("a.news-img").attr("href");
                String desp = newsItem.select("div.article-excerpt").text();
                String time = newsItem.select("div.news__item-meta").select("span.author").text();
                Date publishDate = parserTime(time.split("·")[1]);
                newsVoList.add(buildNewVos().title(title)
                        .publishTime(publishDate)
                        .desp(desp)
                        .url("https://segmentfault.com" + uul)
                        .build());

            }
        }
        return newsVoList;
    }

    public static Date parserTime(String time) {

        Date publishDate = null;
        if (time.startsWith("今天")) {
            time = time.replace("今天", "").trim();
            time = DateUtil.format(new Date(), "yyyy-MM-dd " + time);
            publishDate = DateUtil.parse(time, "yyyy-MM-dd HH:mm");
        } else if (time.endsWith("分钟前")) {
            time = time.replace("分钟前", "").trim();
            publishDate = DateUtil.offsetMinute(new Date(), Integer.valueOf("-" + time));
        } else if (time.startsWith("刚刚")) {
        } else {

            publishDate = DateUtil.parse(DateUtil.year(new Date()) + "年" + time, "yyyy年MM月dd日");
        }
        return publishDate;
    }

    private int newPage;

    @Override
    protected String getHtmlContent(Integer page) {
        newPage = page;
        return HttpUtil.createGet("https://segmentfault.com/newest").header("user-agent", UserAgent.getUserAgent())
                .header("upgrade-insecure-requests", "1")
                .header("sec-fetch-site", "same-origin")
                .header("sec-fetch-mode", "navigate")
                .header("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3")
                .header("cache-control", "max-age=0")
                .execute().body();
    }

    @Override
    protected String parserContent(String html) {
        Elements select = Jsoup.parse(html).select("article.article-content");
        if (null == select || select.size() == 0) {
            return null;
        }
        return select.html();
    }

    @Override
    protected boolean breakCondition() {
        return newPage < 2;
    }

    @Override
    public ConstantEnum source() {
        return ConstantEnum.NEWS_SOURCE_SEGMENTFAULT;
    }


}
