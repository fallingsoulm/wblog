package com.wblog.common.module.info.api;


import com.wblog.common.constant.ApplicationNameConstants;
import com.wblog.common.constant.Version;
import com.wblog.common.module.info.vo.ArticleVo;
import io.github.fallingsoulm.easy.archetype.framework.page.RespEntity;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author luyanan
 * @since 2020/9/17
 * <p>专辑文章关联</p>
 **/
@FeignClient(ApplicationNameConstants.INFO)
public interface AlbumArticleApi {

    String prefix = ApplicationNameConstants.INFO_PATH_PREFIX + Version.VERSION_1 + "album/article/";

    /**
     * <p>查询文章列表</p>
     *
     * @param albumId
     * @return {@link List<   ArticleVo  >}
     * @author luyanan
     * @since 2020/9/17
     */
    @ApiOperation(value = "查询文章列表")
    @GetMapping(prefix + "find/article/{albumId}")
    public RespEntity<List<ArticleVo>> findArticle(@PathVariable("albumId") Long albumId);

}
