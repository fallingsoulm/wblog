package com.wblog.common.module.info.api;

import com.wblog.common.constant.ApplicationNameConstants;
import com.wblog.common.constant.Version;
import com.wblog.common.module.info.vo.NewsVo;
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
 * @since 2020/9/17
 * <p>资讯</p>
 **/
@FeignClient(ApplicationNameConstants.INFO)
public interface NewsApi {
    String prefix = ApplicationNameConstants.INFO_PATH_PREFIX + Version.VERSION_1 + "news/";

    /**
     * <p>分页查询</p>
     *
     * @param pageRequestParams
     * @return {@link PageInfo< NewsVo>}
     * @author luyanan
     * @since 2020/9/17
     */
    @ApiOperation(value = "分页查询")
    @PostMapping(prefix + "find/page")
    public RespEntity<PageInfo<NewsVo>> findByPage(@RequestBody PageRequestParams<NewsVo> pageRequestParams);


    /**
     * <p>根据id查询</p>
     *
     * @param id
     * @return {@link NewsVo}
     * @author luyanan
     * @since 2020/9/17
     */
    @ApiOperation(value = "根据id查询")
    @GetMapping(prefix + "find/id/{id}")
    public RespEntity<NewsVo> findById(@PathVariable("id") Long id);

    /**
     * <p>同步</p>
     *
     * @author luyanan
     * @since 2020/9/21
     */
    @ApiOperation(value = "同步")
    @GetMapping(prefix + "news/syn")
    RespEntity newsSyn();

}
