package com.wblog.info.api;

import com.wblog.common.constant.Version;
import com.wblog.common.enums.ConstantEnum;
import com.wblog.common.module.info.vo.AlbumVo;
import com.wblog.info.service.IAlbumService;
import io.github.fallingsoulm.easy.archetype.framework.page.PageInfo;
import io.github.fallingsoulm.easy.archetype.framework.page.PageRequestParams;
import io.github.fallingsoulm.easy.archetype.framework.page.RespEntity;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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


    @GetMapping("find/id/{albumId}")
    public RespEntity<AlbumVo> findById(@PathVariable("albumId") Long albumId) {
        return RespEntity.success(albumService.findById(albumId));
    }
}
