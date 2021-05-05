package com.wblog.front.controller;


import com.wblog.common.enums.ConstantEnum;
import com.wblog.common.module.info.api.ArticleApi;
import com.wblog.common.module.info.api.LabelApi;
import com.wblog.common.module.info.vo.ArticleVo;
import com.wblog.common.module.info.vo.LabelVo;
import com.wblog.front.utils.PageUtils;
import io.github.fallingsoulm.easy.archetype.framework.page.PageInfo;
import io.github.fallingsoulm.easy.archetype.framework.page.PageRequestParams;
import io.github.fallingsoulm.easy.archetype.framework.page.RespEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author luyanan
 * @since 2020/6/13
 * <p>首页的controller</p>
 **/

@Api(description = "首页")
@Controller
public class IndexController {


    @Autowired
    private ArticleApi articleApi;

    @Autowired
    private LabelApi labelApi;

    @ApiOperation(value = "首页-文章列表")
    @GetMapping({"", "index.html"})
    public String index(Integer page, Model model) {


        PageRequestParams<ArticleVo> requestParams = PageUtils
                .build(ArticleVo.builder().status(ConstantEnum.ARTICLE_STATUS_ENABLE.getValue()).build(), page, 10);
        RespEntity<PageInfo<ArticleVo>> articleApiByPage = articleApi.findByPage(requestParams);


        PageRequestParams<LabelVo> pageRequestParams = new PageRequestParams<>();
        pageRequestParams.setPageNum(1);
        pageRequestParams.setPageSize(30);
        List<LabelVo> labelVoLists = labelApi.findByPageAndCount(pageRequestParams).getData().getContent();
        model.addAttribute("articlePageInfo", articleApiByPage.getData());
        model.addAttribute("labelVoLists", labelVoLists);
        return "index";
    }


    @ApiOperation(value = "搜索接口")
    @GetMapping({"search", "search.html"})
    public String search(String search, Integer page, Model model) {

        PageRequestParams<ArticleVo> requestParams = PageUtils
                .build(ArticleVo.builder().introduction(search).status(ConstantEnum.ARTICLE_STATUS_ENABLE.getValue()).build(), page, 10);
        PageInfo<ArticleVo> articleVoIPageInfo = articleApi.findByPage(requestParams).getData();
        model.addAttribute("articlePageInfo", articleVoIPageInfo);
        model.addAttribute("search", search);
        return "search";
    }


}
