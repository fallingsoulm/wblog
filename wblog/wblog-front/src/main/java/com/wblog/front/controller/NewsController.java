package com.wblog.front.controller;

import com.wblog.common.module.info.api.NewsApi;
import com.wblog.common.module.info.vo.NewsVo;
import com.wblog.front.utils.PageUtils;
import io.github.fallingsoulm.easy.archetype.framework.page.PageInfo;
import io.github.fallingsoulm.easy.archetype.framework.page.PageRequestParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;

/**
 * @author luyanan
 * @since 2020/7/14
 * <p>资讯页面</p>
 **/
//@RequestMapping("news")
@Controller
public class NewsController {

    @Autowired
    private NewsApi newsApi;


    @GetMapping({"news", "news.html"})
    public String findByPage(Integer page, Model model) {
        PageRequestParams<NewsVo> pageRequestParams = PageUtils.build(page, 20);
        PageInfo<NewsVo> pageInfo = newsApi.findByPage(pageRequestParams).getData();

        model.addAttribute("pageInfo", pageInfo);

        return "news";
    }


//    //    @ApiOperation(value = "查看详情")
//    @GetMapping("info/{id}")
//    public void info(@PathVariable("id") Long id, HttpServletResponse response) throws IOException {
//        NewsVo newsVo = newsApi.findById(id).getDataOrThrow(new TypeReference<NewsVo>() {
//        });
//        response.sendRedirect(newsVo.getUrl());
//    }

    //    @ApiOperation(value = "查看详情")
    @GetMapping({"news/info/{id}", "news/info/{id}.html"})
    public String info(@PathVariable("id") Long id, Model model) throws IOException {
        NewsVo newsVo = newsApi.findById(id).getData();
        model.addAttribute("newsVo", newsVo);
//        response.sendRedirect(newsVo.getUrl());
        return "newsInfo";
    }

}
