package com.wblog.info.api;

import com.apes.hub.api.enums.ConstantEnum;
import com.apes.hub.api.module.info.vo.ArticleLabelVo;
import com.apes.hub.api.module.info.vo.ArticleVo;
import com.apes.hub.api.page.PageInfo;
import com.apes.hub.api.page.PageRequestParams;
import com.apes.hub.api.uitils.RespEntity;
import com.apes.hub.core.constant.Version;
import com.apes.hub.core.datascope.annotation.GlobalDataScope;
import com.apes.hub.data.thread.BusinessThreadPoolTaskExecutor;
import com.apes.hub.info.service.IArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author luyanan
 * @since 2020/9/17
 * <p>文章的接口</p>
 **/
@Api(description = "文章")
@RestController
@RequestMapping(Version.VERSION_1 + "info/article")
public class ArticleApiController {

    @Autowired
    private IArticleService articleService;

    @Autowired
    BusinessThreadPoolTaskExecutor businessThreadPoolTaskExecutor;


    @ApiOperation(value = "文章详情")
    @GetMapping("info/{articleId}")
    RespEntity<ArticleVo> info(@PathVariable("articleId") Long articleId) {
        return RespEntity.success(articleService.info(articleId));
    }


    @ApiOperation(value = "分页查询", response = ArticleVo.class)
    @PostMapping(value = "find/page")
    public RespEntity<PageInfo<ArticleVo>> findByPage(@RequestBody PageRequestParams<ArticleVo> pageRequestParams) {
        ArticleVo params = pageRequestParams.getParams();
        if (null == params) {
            params = new ArticleVo();
        }
        params.setStatus(ConstantEnum.ARTICLE_STATUS_ENABLE.getValue());
        pageRequestParams.setParams(params);
        PageInfo<ArticleVo> pageInfo = articleService.findByPage(pageRequestParams);
        return RespEntity.success(pageInfo);
    }

    @ApiOperation(value = "根据标签id分页查询")
    @PostMapping("find/page/labelId")
    public RespEntity<PageInfo<ArticleVo>> findPageByLabelId(@RequestBody PageRequestParams<ArticleLabelVo> requestParams) {
        PageInfo<ArticleVo> pageInfo = articleService.findPageByLabelId(requestParams);
        return RespEntity.success(pageInfo);
    }

    @ApiOperation(value = "文章巡查")
    @GetMapping("patrol")
    public RespEntity patrol() {
        articleService.patrol();
        return RespEntity.success();
    }

    @GlobalDataScope
    @PreAuthorize("hasAnyAuthority('info:article:list')")
    @PostMapping(value = "list")
    @ApiOperation(value = "分页查询")
    public RespEntity<PageInfo<ArticleVo>> list(@RequestBody PageRequestParams<ArticleVo> pageRequestParams) {
        PageInfo<ArticleVo> pageInfo = articleService.findByPageToManage(pageRequestParams);
        return RespEntity.success(pageInfo);
    }


    /**
     * <p>修改状态</p>
     *
     * @param ids
     * @return {@link RespEntity}
     * @author luyanan
     * @since 2020/9/21
     */
    @ApiOperation(value = "修改状态")
    @GetMapping("update/status/{ids}/{status}")
    public RespEntity updateStatus(@PathVariable Long[] ids, @PathVariable("status") Integer status) {
        articleService.updateStatus(Arrays.stream(ids).collect(Collectors.toList()), status);
        return RespEntity.success();
    }


    @ApiOperation(value = "删除")
    @DeleteMapping("/{ids}")
    public RespEntity delete(@PathVariable("ids") Long[] ids) {
        articleService.deleteByIds(Arrays.stream(ids).collect(Collectors.toList()));
        return RespEntity.success();
    }


    @ApiOperation(value = "重命名")
    @GetMapping("rename/{id}")
    public RespEntity rename(@PathVariable("id") Long[] ids) {
        for (Long id : ids) {
            articleService.rename(id);
        }
        return RespEntity.success();
    }

}
