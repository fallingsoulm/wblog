package com.wblog.front.controller;

import com.apes.hub.api.module.info.api.ArticleApi;
import com.apes.hub.api.module.info.api.LabelApi;
import com.apes.hub.api.module.info.vo.ArticleLabelVo;
import com.apes.hub.api.module.info.vo.ArticleVo;
import com.apes.hub.api.module.info.vo.LabelVo;
import com.apes.hub.api.page.PageInfo;
import com.apes.hub.api.page.PageRequestParams;
import com.apes.hub.gateway.info.utils.PageUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author luyanan
 * @since 2020/6/15
 * <p>标签控制层</p>
 **/
@Api(value = "标签")
@Controller
//@RequestMapping("label")
public class LabelController {

    @Autowired
    private LabelApi labelApi;

    @Autowired
    private ArticleApi articleApi;

    @GetMapping({"label", "label.html"})
    public String findByPage(Integer page, Model model) {
        PageRequestParams<LabelVo> pageRequestParams = PageUtils.build(page, 20);
        PageInfo<LabelVo> pageInfo = labelApi.findByPageAndCount(pageRequestParams).getData();

        List<ArticleVo> articleVos = articleApi.findByPage(PageUtils.build(1, 10))
                .getData().getContent();
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("articleVos", articleVos);
        return "label";
    }


    @ApiOperation(value = "根据标签id查询")
    @GetMapping({"label/{labelId}", "label/{labelId}.html"})
    public String info(@PathVariable("labelId") Long labelId, Integer page, Model model) {

        LabelVo labelVo = labelApi.findById(labelId).getData();
        model.addAttribute("labelVo", labelVo);

        PageRequestParams<ArticleLabelVo> requestParams = PageUtils.build(ArticleLabelVo.builder().labelId(labelId).build(), page, 10);
        PageInfo<ArticleVo> articleVoIPageInfo = articleApi.findPageByLabelId(requestParams).getData();
        PageRequestParams<LabelVo> pageRequestParams = new PageRequestParams<>();
        pageRequestParams.setPageIndex(1);
        pageRequestParams.setPageSize(30);
        List<LabelVo> labelVoLists = labelApi.findByPageAndCount(pageRequestParams).getData().getContent();
        model.addAttribute("articlePageInfo", articleVoIPageInfo);
        model.addAttribute("labelVoLists", labelVoLists);
        return "labelArticle";
    }

}