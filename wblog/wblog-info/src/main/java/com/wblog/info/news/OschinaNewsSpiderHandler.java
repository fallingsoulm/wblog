package com.wblog.info.news;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.wblog.common.enums.ConstantEnum;
import com.wblog.common.module.info.vo.NewsVo;
import com.wblog.info.utils.UserAgent;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author luyanan
 * @since 2020/7/13
 * <p>开源中国最近文章抓取</p>
 **/
@Service("oschinaNewsTask")
public class OschinaNewsSpiderHandler extends AbstractNewsSpiderHandler {


    /**
     * <p>解析发布时间</p>
     *
     * @param time 发布时间
     * @return {@link Date}
     * @author luyanan
     * @since 2020/7/13
     */
    public Date parserPublishTime(String time) {

        if (StrUtil.isBlank(time) || time.equals("刚刚")) {
            return new Date();
        }

        if (time.endsWith("分钟前")) {
            String regEx = "[^0-9]";
            Pattern p = Pattern.compile(regEx);
            Matcher m = p.matcher(time);
            String t = m.replaceAll("").trim();
            if (StrUtil.isBlank(t) || Integer.valueOf(t) == 0) {
                return new Date();
            }
            return DateUtil.date(new Date()).offset(DateField.MINUTE, Integer.valueOf("-" + t)).toJdkDate();
        }

        if (time.startsWith("今天")) {
            time = time.replace("今天", "").trim();
            return DateUtil.parse(DateUtil.format(new Date(), "yyyy-MM-dd ") + time, "yyyy-MM-dd HH:mm").toJdkDate();

        } else {
            try {
                return DateUtil.parse(DateUtil.format(new Date(), "yyyy") + "/" + time, "yyyy/MM/dd HH:mm").toJdkDate();
            } catch (Exception e) {
                return new Date();
            }
        }

    }


    /**
     * <p>获取页面内容</p>
     *
     * @param page
     * @return {@link String}
     * @author luyanan
     * @since 2020/7/13
     */
    @Override
    protected String getHtmlContent(Integer page) {
        String url = "https://www.oschina.net/blog/widgets/_blog_index_newest_list?classification=0&p=" + page + "&type=ajax";
        HttpRequest header = HttpUtil.createGet(url)
                .cookie().header("User-Agent", UserAgent.getUserAgent());
        HttpResponse execute = header.execute();
        return execute.body();

    }

    @Override
    protected String parserContent(String html) {
        Elements elements = Jsoup.parse(html).select("div.content");
        if (null == elements || elements.size() == 0) {
            return null;
        }
        return elements.html();
    }

    @Override
    public ConstantEnum source() {
        return ConstantEnum.NEWS_SOURCE_OSCHINA;
    }

    @Override
    public NewsVo lastOffset() {

        return newsService.lastUrl(ConstantEnum.NEWS_SOURCE_OSCHINA.getValue());
    }

    @Override
    protected List<NewsVo> parserHtml(String html) {

        List<NewsVo> newsVoList = new ArrayList<>();
        if (StrUtil.isBlank(html)) {
            return newsVoList;
        }

        Elements blogitems = Jsoup.parse(html).select(".blog-item");

        if (blogitems.size() > 0) {
            for (Element blogitem : blogitems) {
                String title = blogitem.select(".header").attr("title");
                if (StrUtil.isBlank(title)) {
                    continue;
                }
                String url = blogitem.select(".header").attr("href");
                String desp = blogitem.select(".description").text();
                String time = blogitem.select(".item").get(2).text();
                Date publishTime = parserPublishTime(time);
                NewsVo newsVo = buildNewVos()
                        .title(title)
                        .desp(desp)
                        .url(url)
                        .publishTime(publishTime)
                        .build();
                newsVoList.add(newsVo);
            }
        }
        return newsVoList;
    }


}
