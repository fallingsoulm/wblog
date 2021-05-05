package com.wblog.info.task;

import cn.hutool.core.date.DateUtil;
import cn.hutool.http.HttpUtil;
import com.apes.hub.api.enums.ConstantEnum;
import com.apes.hub.api.module.info.vo.NewsVo;
import com.apes.hub.data.http.UserAgent;
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
 * <p>慕课网</p>
 **/
@Service("imoocNewsTask")
public class ImoocNewsTask extends AbstractNewsTask {


    @Override
    protected List<NewsVo> parserHtml(String html) {
        List<NewsVo> newsVoList = new ArrayList<>();
        Elements articleKwraps = Jsoup.parse(html).select("div.article-lwrap");
        if (null == articleKwraps || articleKwraps.size() == 0) {
            return newsVoList;
        }

        for (Element articleKwrap : articleKwraps) {
            String url = "https://www.imooc.com" + articleKwrap.select("div.list-content a").attr("href");
            String title = articleKwrap.select("div.list-content a").text();
            String createTime = articleKwrap.select("div.createTime").text();
            Date publishTime = DateUtil.parse(DateUtil.year(new Date()) + " " + createTime.trim(), "yyyy MM.dd");

            NewsVo build = buildNewVos().desp(title)
                    .publishTime(publishTime)
                    .url(url)
                    .title(title)
                    .build();
            newsVoList.add(build);
        }

        return newsVoList;
    }

    @Override
    protected String getHtmlContent(Integer page) {

        return HttpUtil.createGet("https://www.imooc.com/article/getarticlelist?type=0&marking=new&page=" + page)
                .header("User-Agent", UserAgent.getUserAgent())
                .header("Upgrade-Insecure-Requests", "1")
                .header("Sec-Fetch-Site", "none")
                .header("Sec-Fetch-Mode", "navigate")
                .header("Host", "www.imooc.com")
                .execute().body();
    }

    @Override
    protected ConstantEnum source() {
        return ConstantEnum.NEWS_SOURCE_IMOOC;
    }
}
