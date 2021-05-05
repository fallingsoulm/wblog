package com.wblog.info.api;

import com.apes.hub.api.enums.ConstantEnum;
import com.apes.hub.api.module.info.vo.AlbumVo;
import com.apes.hub.api.page.PageInfo;
import com.apes.hub.api.page.PageRequestParams;
import com.apes.hub.api.uitils.RespEntity;
import com.apes.hub.core.constant.Version;
import com.apes.hub.info.service.IAlbumService;
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
