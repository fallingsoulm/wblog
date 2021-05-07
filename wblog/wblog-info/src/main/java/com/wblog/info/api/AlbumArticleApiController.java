package com.wblog.info.api;

import com.wblog.common.constant.Version;
import com.wblog.common.module.info.vo.ArticleVo;
import com.wblog.info.service.IAlbumArticleService;
import io.github.fallingsoulm.easy.archetype.framework.page.RespEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author luyanan
 * @since 2020/9/17
 * <p>专辑文章关联</p>
 **/
@Api(description = "专辑文章关联")
@RequestMapping(Version.VERSION_1 + "album/article")
@RestController
public class AlbumArticleApiController {


    @Autowired
    private IAlbumArticleService albumArticleService;

    /**
     * <p>查询文章列表</p>
     *
     * @param albumId
     * @return {@link List< ArticleVo>}
     * @author luyanan
     * @since 2020/9/17
     */
    @ApiOperation(value = "查询文章列表")
    @GetMapping("find/article/{albumId}")
    public RespEntity<List<ArticleVo>> findArticle(@PathVariable("albumId") Long albumId) {
        List<ArticleVo> article = albumArticleService.findArticle(albumId);
        return RespEntity.success(article);
    }

}
