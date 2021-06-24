package com.wblog.info.api;

import com.wblog.common.constant.Version;
import com.wblog.common.datascope.annotation.GlobalDataScope;
import com.wblog.common.module.info.vo.NewsVo;
import com.wblog.info.service.INewsService;
import io.github.fallingsoulm.easy.archetype.framework.page.PageInfo;
import io.github.fallingsoulm.easy.archetype.framework.page.PageRequestParams;
import io.github.fallingsoulm.easy.archetype.framework.page.RespEntity;
import io.github.fallingsoulm.easy.archetype.framework.thread.BusinessThreadPoolTaskExecutor;
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
 * <p>资讯</p>
 **/
@Api(description = "资讯")
@RestController
@RequestMapping(Version.VERSION_1 + "news")
public class NewsApiController {

    @Autowired
    private BusinessThreadPoolTaskExecutor businessThreadPoolTaskExecutor;

    @Autowired
    private INewsService newsService;
    private static final String authorPrefix = "info:news:";


    /**
     * <p>分页查询</p>
     *
     * @param pageRequestParams
     * @return {@link PageInfo< NewsVo>}
     * @author luyanan
     * @since 2020/9/17
     */
    @PreAuthorize("hasAnyAuthority('" + authorPrefix + "list')")
    @ApiOperation(value = "分页查询")
    @PostMapping("find/page")
    public RespEntity<PageInfo<NewsVo>> findByPage(@RequestBody PageRequestParams<NewsVo> pageRequestParams) {
        PageInfo<NewsVo> pageInfo = newsService.findByPage(pageRequestParams);
        return RespEntity.success(pageInfo);
    }


    /**
     * <p>根据id查询</p>
     *
     * @param id
     * @return {@link NewsVo}
     * @author luyanan
     * @since 2020/9/17
     */
    @ApiOperation(value = "根据id查询")
    @GetMapping("find/id/{id}")
    public RespEntity<NewsVo> findById(@PathVariable("id") Long id) {
        NewsVo newsVo = newsService.findById(id);
        return RespEntity.success(newsVo);
    }


    @ApiOperation(value = "分页查询")
    @PostMapping("list")
    public RespEntity<PageInfo<NewsVo>> list(@RequestBody PageRequestParams<NewsVo> pageRequestParams) {
        PageInfo<NewsVo> pageInfo = newsService.findByPage(pageRequestParams);
        return RespEntity.success(pageInfo);
    }

    @PreAuthorize("hasAnyAuthority('" + authorPrefix + "remove')")
    @ApiOperation(value = "删除")
    @DeleteMapping("/{ids}")
    public RespEntity remove(@PathVariable("ids") Long[] ids) {
        newsService.deleteByIds(Arrays.stream(ids).collect(Collectors.toList()));
        return RespEntity.success();
    }

    /**
     * <p>同步</p>
     *
     * @author luyanan
     * @since 2020/9/21
     */
    @GetMapping("news/syn")
    public RespEntity newsSyn() {

        businessThreadPoolTaskExecutor.execute(new Runnable() {
            @Override
            public void run() {
                newsService.syn(null);
            }
        });

        return RespEntity.success();
    }


}
