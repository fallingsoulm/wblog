package com.wblog.front.controller;

import com.apes.hub.api.module.info.api.ArticleApi;
import com.apes.hub.api.module.info.api.ChainCollectionApi;
import com.apes.hub.api.module.info.vo.ArticleVo;
import com.apes.hub.api.module.info.vo.ChainCollectionVo;
import com.apes.hub.gateway.info.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

/**
 * @author luyanan
 * @since 2020/6/27
 * <p>常用工具控制层</p>
 **/
@Controller
//@RequestMapping("tools")
public class ToolsController {

    @Autowired
    private ChainCollectionApi chainCollectionApi;
    @Autowired
    private ArticleApi articleApi;

    @GetMapping({"tools", "tools.html"})
    public String tools(Model model) {

        Map<String, List<ChainCollectionVo>> map = chainCollectionApi.listyByMap(4L).getData();
        List<ArticleVo> articleVos = articleApi.findByPage(PageUtils.build(1, 10)).getData().getContent();
        model.addAttribute("toolMap", map);
        model.addAttribute("articleVos", articleVos);
        return "tools";
    }

}
