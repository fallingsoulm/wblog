package com.wblog.front.controller;

import com.wblog.common.module.info.api.ArticleApi;
import com.wblog.common.module.info.api.LabelApi;
import com.wblog.common.module.info.vo.ArticleVo;
import com.wblog.common.module.info.vo.LabelVo;
import com.wblog.common.module.system.api.SysUserApi;
import com.wblog.common.module.system.vo.SysUserVo;
import com.wblog.front.utils.PageUtils;
import io.github.fallingsoulm.easy.archetype.framework.page.PageInfo;
import io.github.fallingsoulm.easy.archetype.framework.page.PageRequestParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author luyanan
 * @since 2020/6/29
 * <p>用户控制层</p>
 **/
@Controller
@RequestMapping("user")
public class UserController {


    @Autowired
    private ArticleApi articleApi;

    @Autowired
    private LabelApi labelApi;

    @Autowired
    private SysUserApi sysUserApi;


    @ApiOperation(value = "查看用户下的文章列表")
    @GetMapping({"article/{userId}", "article/{userId}.html"})
    public String article(@PathVariable("userId") Long userId, Integer page, Model model) {

        SysUserVo sysUserVo = sysUserApi.findById(userId).getData();
        model.addAttribute("user", sysUserVo);
        PageRequestParams<ArticleVo> requestParams = PageUtils.build(ArticleVo.builder().userId(userId).build(), page);
        PageInfo<ArticleVo> pageInfo =
                articleApi.findByPage(requestParams).getData();
        model.addAttribute("articlePageInfo", pageInfo);

        PageRequestParams<LabelVo> pageRequestParams = new PageRequestParams<>();
        pageRequestParams.setPageNum(1);
        pageRequestParams.setPageSize(30);
        List<LabelVo> labelVoLists = labelApi.findByPageAndCount(pageRequestParams).getData().getContent();
        model.addAttribute("labelVoLists", labelVoLists);
        return "userArticle";
    }

}
