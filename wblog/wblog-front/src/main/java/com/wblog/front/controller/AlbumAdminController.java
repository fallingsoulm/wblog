package com.wblog.front.controller;

import cn.hutool.core.collection.CollectionUtil;
import com.apes.hub.api.module.info.api.AlbumApi;
import com.apes.hub.api.module.info.api.AlbumArticleApi;
import com.apes.hub.api.module.info.api.ArticleApi;
import com.apes.hub.api.module.info.api.LabelApi;
import com.apes.hub.api.module.info.vo.AlbumVo;
import com.apes.hub.api.module.info.vo.ArticleVo;
import com.apes.hub.api.module.info.vo.LabelVo;
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
 * @since 2020/7/14
 * <p>专辑</p>
 **/
//@RequestMapping("album")
@Api(description = "专辑")
@Controller
public class AlbumAdminController {


    @Autowired
    private ArticleApi articleApi;

    @Autowired
    private LabelApi labelApi;

    @Autowired
    private AlbumApi albumApi;

    @Autowired
    private AlbumArticleApi albumArticleApi;


    @ApiOperation(value = "首页-文章列表")
    @GetMapping({"album", "album.html"})
    public String index(Integer page, Model model) {
//        AlbumVo albumEntityVo = new AlbumVo();
//        albumEntityVo.setStatus(ConstantEnum.ARTICLE_STATUS_ENABLE.getValue());
        PageRequestParams<AlbumVo> requestParams = PageUtils
                .build(null, page, 10);
        Object albumVoIPageInfo = albumApi.findByPage(requestParams).getData();

//        RespEntity<AlbumVo> respEntity = albumApi.findById(1L);
        PageRequestParams<LabelVo> pageRequestParams = new PageRequestParams<>();
        pageRequestParams.setPageIndex(1);
        pageRequestParams.setPageSize(30);
        List<LabelVo> labelVoLists = labelApi.findByPageAndCount(pageRequestParams).getData().getContent();
        model.addAttribute("albumVoIPageInfo", albumVoIPageInfo);
        model.addAttribute("labelVoLists", labelVoLists);
//        model.addAttribute("article", articleService.info(19576484412160L));
        return "album";
    }


    @ApiOperation(value = "专辑的文章")
    @GetMapping({"album/info/{albumId}/{articleId}", "album/info/{albumId}/{articleId}.html"})
    public String albumArticle(@PathVariable("albumId") Long albumId,
                               @PathVariable(value = "articleId") Long articleId, Model model) {

        AlbumVo albumVo = albumApi.findById(albumId).getData();
        model.addAttribute("albumVo", albumVo);
        // 根据id查询专辑列表
        List<ArticleVo> articleVos = albumArticleApi.findArticle(albumId).getData();
        model.addAttribute("articleVos", articleVos);
        if (null == articleId) {
            articleId = articleVos.get(0).getId();
        }
        checkArticle(articleVos, articleId);
        ArticleVo articleVo = articleApi.info(articleId).getData();
        model.addAttribute("articleVo", articleVo);
        return "albumArticle";
    }

    @ApiOperation(value = "专辑的文章")
    @GetMapping({"album/info/{albumId}", "album/info/{albumId}.html"})
    public String albumArticle(@PathVariable("albumId") Long albumId,
                               Model model) {

        Object albumVo = albumApi.findById(albumId).getData();
        model.addAttribute("albumVo", albumVo);
        // 根据id查询专辑列表
        List<ArticleVo> articleVos = albumArticleApi.findArticle(albumId).getData();
        model.addAttribute("articleVos", articleVos);
        if (CollectionUtil.isNotEmpty(articleVos)) {
            Long articleId = articleVos.get(0).getId();

            checkArticle(articleVos, articleId);

            ArticleVo articleVo = articleApi.info(articleId).getData();
            model.addAttribute("articleVo", articleVo);
        }

        return "albumArticle";
    }

    private void checkArticle(List<ArticleVo> articleVos, Long articleId) {

        if (CollectionUtil.isEmpty(articleVos)) {
            return;
        }
        for (ArticleVo articleVo : articleVos) {
            if (articleId.equals(articleVo.getId())) {
                articleVo.setCheck(true);
            } else {
                articleVo.setCheck(false);
            }
        }
    }

}
