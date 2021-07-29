package com.wblog.common.module.info.api;


import com.wblog.common.constant.ApplicationNameConstants;
import com.wblog.common.constant.Version;
import com.wblog.common.module.info.vo.ArticleLabelVo;
import com.wblog.common.module.info.vo.ArticleVo;
import io.github.fallingsoulm.easy.archetype.framework.page.PageInfo;
import io.github.fallingsoulm.easy.archetype.framework.page.PageRequestParams;
import io.github.fallingsoulm.easy.archetype.framework.page.RespEntity;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author luyanan
 * @since 2020/9/16
 * <p>文章的api</p>
 **/
@FeignClient(ApplicationNameConstants.INFO)
public interface ArticleApi {

    String prefix = ApplicationNameConstants.INFO_PATH_PREFIX + Version.VERSION_1 + "info/article/";

    /**
     * <p>文章详情</p>
     *
     * @param articleId
     * @return {@link ArticleVo}
     * @author luyanan
     * @since 2020/9/17
     */
    @ApiOperation(value = "文章详情")
    @GetMapping(prefix + "info/{articleId}")
    RespEntity<ArticleVo> info(@PathVariable("articleId") Long articleId);

    /**
     * <p>分页查询</p>
     *
     * @param pageRequestParams
     * @return {@link PageInfo< Object>}
     * @author luyanan
     * @since 2020/9/17
     */

    @ApiOperation(value = "分页查询", response = ArticleVo.class)
    @PostMapping(value = prefix + "find/page")
    RespEntity<PageInfo<ArticleVo>> findByPage(@RequestBody PageRequestParams<ArticleVo> pageRequestParams);


    /**
     * <p>根据标签id分页查询</p>
     *
     * @param requestParams
     * @return {@link PageInfo< ArticleVo>}
     * @author luyanan
     * @since 2020/9/17
     */
    @ApiOperation(value = "根据标签id分页查询")
    @PostMapping(prefix + "find/page/labelId")
    RespEntity<PageInfo<ArticleVo>> findPageByLabelId(@RequestBody PageRequestParams<ArticleLabelVo> requestParams);

    /**
     * <p>文章巡查</p>
     *
     * @return RespEntity
     * @author luyanan
     * @since 2020/9/21
     */
    @GetMapping(prefix + "patrol")
    RespEntity patrol();

    /**
     * 根据id查询
     *
     * @param id
     * @return io.github.fallingsoulm.easy.archetype.framework.page.RespEntity<com.wblog.common.module.info.vo.ArticleVo>
     * @since 2021/7/21
     */
    @GetMapping(prefix + "find/id/{id}")
    RespEntity<ArticleVo> findById(@PathVariable("id") Long id);

}
