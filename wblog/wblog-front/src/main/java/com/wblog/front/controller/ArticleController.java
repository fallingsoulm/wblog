package com.wblog.front.controller;

import com.wblog.common.module.info.api.ArticleApi;
import com.wblog.common.module.info.vo.ArticleVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author luyanan
 * @since 2020/6/15
 * <p>文章控制层</p>
 **/
@Api(value = "文章控制层")
@Controller()
@RequestMapping("article")
public class ArticleController {

    @Autowired
    private ArticleApi articleApi;

    @ApiOperation(value = "文章详情")
    @GetMapping({"info/{id}", "info/{id}.html"})
    public String info(@PathVariable("id") Long id, Model model) {

        ArticleVo articleVo = articleApi.info(id).getData();
        model.addAttribute("article", articleVo);
        return "blog";
    }

}
