package com.wblog.info.admin;

import com.wblog.common.constant.Version;
import com.wblog.common.module.info.vo.NewsVo;
import com.wblog.info.service.INewsService;
import io.github.fallingsoulm.easy.archetype.framework.page.PageInfo;
import io.github.fallingsoulm.easy.archetype.framework.page.PageRequestParams;
import io.github.fallingsoulm.easy.archetype.framework.page.RespEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author luyanan
 * @since 2020/10/23
 * <p>资讯</p>
 **/
@Api(description = "资讯")
@RestController
@RequestMapping(Version.VERSION_1 + "news/admin/")
public class NewsAdminController {


    @Autowired
    private INewsService newsService;

    /**
     * <p>根据id查询</p>
     *
     * @param id
     * @return {@link NewsVo}
     * @author luyanan
     * @since 2020/9/17
     */
    @PreAuthorize("hasAnyAuthority('info:news:query')")
    @ApiOperation(value = "根据id查询")
    @GetMapping("find/id/{id}")
    public RespEntity<NewsVo> findById(@PathVariable("id") Long id) {
        NewsVo newsVo = newsService.findById(id);
        return RespEntity.success(newsVo);
    }

    @PreAuthorize("hasAnyAuthority('info:news:list')")
    @ApiOperation(value = "分页查询")
    @PostMapping("list")
    public RespEntity<PageInfo<NewsVo>> list(@RequestBody PageRequestParams<NewsVo> pageRequestParams) {
        PageInfo<NewsVo> pageInfo = newsService.findByPage(pageRequestParams);
        return RespEntity.success(pageInfo);
    }


    @PreAuthorize("hasAnyAuthority('info:news:remove')")
    @ApiOperation(value = "删除")
    @DeleteMapping("/{ids}")
    public RespEntity remove(@PathVariable("ids") Long[] ids) {
        newsService.deleteByIds(Arrays.stream(ids).collect(Collectors.toList()));
        return RespEntity.success();
    }


}

