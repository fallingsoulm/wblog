package com.wblog.front.controller;

import com.wblog.common.module.info.api.ArticleApi;
import com.wblog.common.module.info.api.ChainCollectionApi;
import com.wblog.common.module.info.vo.ArticleVo;
import com.wblog.common.module.info.vo.ChainCollectionVo;
import com.wblog.front.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

/**
 * @author luyanan
 * @since 2020/6/27
 * <p>常用文档控制层</p>
 **/
@Controller
//@RequestMapping("document")
public class DocumentController {

    @Autowired
    private ChainCollectionApi chainCollectionApi;
    @Autowired
    private ArticleApi articleApi;

    @GetMapping({"document", "document.html"})
    public String tools(Model model) {

        Map<String, List<ChainCollectionVo>> map = chainCollectionApi.listyByMap(2L).getData();
        List<ArticleVo> articleVos = articleApi.findByPage(PageUtils.build(1, 10)).getData().getContent();
        model.addAttribute("documentMap", map);
        model.addAttribute("articleVos", articleVos);
        return "document";
    }

}
