package com.wblog.search.api;

import com.wblog.common.constant.ApplicationNameConstants;
import com.wblog.search.vo.ArticleSearchVo;
import io.github.fallingsoulm.easy.archetype.framework.page.PageInfo;
import io.github.fallingsoulm.easy.archetype.framework.page.PageRequestParams;
import io.github.fallingsoulm.easy.archetype.framework.page.RespEntity;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 文章搜索的API
 *
 * @author luyanan
 * @since 2021/7/18
 **/
@FeignClient(ApplicationNameConstants.SEARCH)
public interface ArticleSearchApi {


    /**
     * 根据条件删除
     *
     * @return io.github.fallingsoulm.easy.archetype.framework.page.RespEntity
     * @since 2021/7/18
     */
    @PostMapping("search/info/delete")
    RespEntity delete(@RequestBody ArticleSearchVo articleSearchVo);


    /**
     * 分页查询
     *
     * @param pageRequestParams
     * @return io.github.fallingsoulm.easy.archetype.framework.page.RespEntity<io.github.fallingsoulm.easy.archetype.framework.page.PageInfo < com.wblog.search.vo.ArticleSearchVo>>
     * @since 2021/7/18
     */
    @PostMapping("search/info/find/page")
    RespEntity<PageInfo<ArticleSearchVo>> findByPage(@RequestBody PageRequestParams<ArticleSearchVo> pageRequestParams);


    /**
     * 根据id集合查询
     *
     * @param ids      id集合
     * @param classify 分类
     * @return io.github.fallingsoulm.easy.archetype.framework.page.RespEntity<com.wblog.search.vo.ArticleSearchVo>
     * @since 2021/7/27
     */
    @ApiOperation(value = "根据id集合查询")
    @PostMapping("search/info/find/ids")
    RespEntity<List<ArticleSearchVo>> findByIds(@RequestParam("ids") List<Long> ids,
                                                @RequestParam("classify") Integer classify);
}
