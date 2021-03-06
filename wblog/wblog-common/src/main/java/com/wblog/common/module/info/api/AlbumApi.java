package com.wblog.common.module.info.api;

import com.wblog.common.constant.ApplicationNameConstants;
import com.wblog.common.constant.Version;
import com.wblog.common.module.info.vo.AlbumVo;
import io.github.fallingsoulm.easy.archetype.framework.page.PageInfo;
import io.github.fallingsoulm.easy.archetype.framework.page.PageRequestParams;
import io.github.fallingsoulm.easy.archetype.framework.page.RespEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author luyanan
 * @since 2020/9/17
 * <p>专辑</p>
 **/
@FeignClient(ApplicationNameConstants.INFO)
public interface AlbumApi {

    String prefix = ApplicationNameConstants.INFO_PATH_PREFIX + Version.VERSION_1 + "album/";

    /**
     * <p>分页查询</p>
     *
     * @param requestParams
     * @return {@link PageInfo<  AlbumVo >}
     * @author luyanan
     * @since 2020/9/17
     */
    @PostMapping(value = prefix + "find/page")
    RespEntity<PageInfo<AlbumVo>> findByPage(@RequestBody PageRequestParams<AlbumVo> requestParams);


    /**
     * <p>根据id查询</p>
     *
     * @param albumId
     * @return {@link AlbumVo}
     * @author luyanan
     * @since 2020/9/17
     */
    @GetMapping(prefix + "{id}")
    public RespEntity<AlbumVo> findById(@PathVariable("id") Long albumId);

}
