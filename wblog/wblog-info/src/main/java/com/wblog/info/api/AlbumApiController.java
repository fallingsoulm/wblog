package com.wblog.info.api;

import com.wblog.common.constant.Version;
import com.wblog.common.datascope.annotation.GlobalDataScope;
import com.wblog.common.enums.ConstantEnum;
import com.wblog.common.module.info.vo.AlbumArticleVo;
import com.wblog.common.module.info.vo.AlbumVo;
import com.wblog.common.module.info.vo.ArticleVo;
import com.wblog.info.service.IAlbumArticleService;
import com.wblog.info.service.IAlbumService;
import io.github.fallingsoulm.easy.archetype.framework.page.PageInfo;
import io.github.fallingsoulm.easy.archetype.framework.page.PageRequestParams;
import io.github.fallingsoulm.easy.archetype.framework.page.RespEntity;
import io.github.fallingsoulm.easy.archetype.framework.validation.AddGroup;
import io.github.fallingsoulm.easy.archetype.security.core.LoginUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author luyanan
 * @since 2020/9/17
 * <p>专辑</p>
 **/
@Api(description = "专辑")
@RequestMapping(Version.VERSION_1 + "album")
@RestController
public class AlbumApiController {

    @Autowired
    private IAlbumService albumService;
    @Autowired
    private LoginUserService loginUserService;

    @Autowired
    private IAlbumArticleService albumArticleService;


    private static final String authorPrefix = "info:album:";

    @GlobalDataScope
    @PreAuthorize("hasAnyAuthority('" + authorPrefix + "query')")
    @ApiOperation(value = "分页查询")
    @PostMapping("list")
    public RespEntity<PageInfo<AlbumVo>> list(@RequestBody PageRequestParams<AlbumVo> pageRequestParams) {
        PageInfo<AlbumVo> pageInfo = albumService.findByPage(pageRequestParams);
        return RespEntity.success(pageInfo);
    }


    @PreAuthorize("hasAnyAuthority('" + authorPrefix + "add')")
    @ApiOperation(value = "新增")
    @PostMapping()
    public RespEntity save(@RequestBody @Validated(AddGroup.class) AlbumVo albumVo) {
        albumVo.setUserId(loginUserService.getUserId());
        albumVo.setStatus(ConstantEnum.ALBUM_STATUS_STOP.getValue());
        albumService.save(albumVo);
        return RespEntity.success();
    }


    @PreAuthorize("hasAnyAuthority('" + authorPrefix + "edit')")
    @ApiOperation(value = "修改")
    @PutMapping
    public RespEntity edit(@RequestBody AlbumVo albumVo) {
        albumService.update(albumVo);
        return RespEntity.success();
    }

    @PreAuthorize("hasAnyAuthority('" + authorPrefix + "query')")
    @ApiOperation(value = "根据id查询")
    @GetMapping("/{id}")
    public RespEntity<AlbumVo> findById(@PathVariable("id") Long id) {

        AlbumVo albumVo = albumService.findById(id);
        return RespEntity.success(albumVo);
    }

    @PreAuthorize("hasAnyAuthority('" + authorPrefix + "remove')")
    @ApiOperation(value = "删除")
    @DeleteMapping("/{ids}")
    public RespEntity remove(@PathVariable("ids") Long[] ids) {
        albumService.deleteByIds(Arrays.stream(ids).collect(Collectors.toList()));
        return RespEntity.success();
    }


    @PreAuthorize("hasAnyAuthority('" + authorPrefix + "query')")
    @ApiOperation(value = "绑定的文章列表")
    @PostMapping("bind/article")
    public RespEntity bindArticle(@RequestBody PageRequestParams<AlbumArticleVo> pageRequestParams) {
        PageInfo<AlbumArticleVo> pageInfo = albumArticleService.findByPage(pageRequestParams);
        return RespEntity.success(pageInfo);
    }

    @PreAuthorize("hasAnyAuthority('" + authorPrefix + "remove')")
    @ApiOperation(value = "删除绑定的文章")
    @DeleteMapping("/delete/bind/{ids}")
    public RespEntity deleteBind(@PathVariable("ids") Long[] ids) {
        albumArticleService.deleteByIds(Arrays.stream(ids).collect(Collectors.toList()));
        return RespEntity.success();
    }

    @PreAuthorize("hasAnyAuthority('" + authorPrefix + "query')")
    @PostMapping("no/bind/article")
    @ApiOperation(value = "没有绑定的文章列表")
    public RespEntity noBindArticle(@RequestBody PageRequestParams<AlbumArticleVo> pageRequestParams) {
        PageInfo<ArticleVo> pageInfo = albumArticleService.notAssociatedArticles(pageRequestParams);
        return RespEntity.success(pageInfo);
    }

    @PreAuthorize("hasAnyAuthority('" + authorPrefix + "add')")
    @ApiOperation(value = "绑定")
    @PostMapping("bind/{albumId}")
    public RespEntity bind(@PathVariable("albumId") Long albumId, @RequestBody List<Long> articles) {
        albumArticleService.saveBatch(articles, albumId);
        return RespEntity.success();
    }


    @PreAuthorize("hasAnyAuthority('" + authorPrefix + "edit')")
    @ApiOperation(value = "修改排序")
    @GetMapping("update/bind/sort/{id}/{sort}")
    public RespEntity updateBindSort(@PathVariable("id") Long id,
                                     @PathVariable("sort") Integer sort) {

        albumArticleService.update(AlbumArticleVo.builder().id(id).orderNum(sort).build());
        return RespEntity.success();
    }

    @PostMapping(value = "find/page", consumes = MediaType.APPLICATION_JSON_VALUE)
    public RespEntity<PageInfo<AlbumVo>> findByPage(@RequestBody PageRequestParams<AlbumVo> requestParams) {
        if (null == requestParams.getParams()) {
            AlbumVo albumVo = new AlbumVo();
            albumVo.setStatus(ConstantEnum.ALBUM_STATUS_ENABLE.getValue());
            requestParams.setParams(albumVo);
        }
        PageInfo<AlbumVo> pageInfo = albumService.findByPage(requestParams);
        return RespEntity.success(pageInfo);
    }

}
