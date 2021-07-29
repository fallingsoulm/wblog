package com.wblog.search.api;

import com.wblog.search.service.InfoElasticsearchService;
import com.wblog.search.vo.ArticleSearchVo;
import io.github.fallingsoulm.easy.archetype.framework.page.PageInfo;
import io.github.fallingsoulm.easy.archetype.framework.page.PageRequestParams;
import io.github.fallingsoulm.easy.archetype.framework.page.RespEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 信息es的操作接口类
 *
 * @author luyanan
 * @since 2021/7/18
 **/
@Api(description = "信息es操作")
@RestController
@RequestMapping("search/info")
public class InfoElasticsearchController {


    @Autowired
    private InfoElasticsearchService infoElasticsearchService;


    /**
     * 清空索引下的所有数据
     *
     * @return io.github.fallingsoulm.easy.archetype.framework.page.RespEntity
     * @since 2021/7/18
     */
    @ApiOperation(value = "删除")
    @PostMapping("delete")
    public RespEntity delete(@RequestBody ArticleSearchVo articleSearchVo) {
        infoElasticsearchService.delete(articleSearchVo);
        return RespEntity.success();
    }


    /**
     * 分页查询
     *
     * @param pageRequestParams
     * @return io.github.fallingsoulm.easy.archetype.framework.page.RespEntity<io.github.fallingsoulm.easy.archetype.framework.page.PageInfo < com.wblog.search.vo.ArticleSearchVo>>
     * @since 2021/7/18
     */
    @ApiOperation(value = "分页查询")
    @PostMapping("find/page")
    public RespEntity<PageInfo<ArticleSearchVo>> findByPage(@RequestBody PageRequestParams<ArticleSearchVo> pageRequestParams) {

        PageInfo<ArticleSearchVo> pageInfo = infoElasticsearchService.findByPage(pageRequestParams);
        return RespEntity.success(pageInfo);
    }


    /**
     * 根据id集合查询
     *
     * @param ids      id集合
     * @param classify 分类
     * @return io.github.fallingsoulm.easy.archetype.framework.page.RespEntity<com.wblog.search.vo.ArticleSearchVo>
     * @since 2021/7/27
     */
    @ApiOperation(value = "根据id集合查询")
    @PostMapping("find/ids")
    public RespEntity<List<ArticleSearchVo>> findByIds(@RequestParam("ids") List<Long> ids,
                                                       @RequestParam("classify") Integer classify) {
        List<ArticleSearchVo> articleSearchVos = infoElasticsearchService.findByIds(ids, classify);
        return RespEntity.success(articleSearchVos);
    }

}
