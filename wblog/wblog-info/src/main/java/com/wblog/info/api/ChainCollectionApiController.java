package com.wblog.info.api;

import com.apes.hub.api.module.info.vo.ChainCollectionVo;
import com.apes.hub.api.uitils.RespEntity;
import com.apes.hub.core.constant.Version;
import com.apes.hub.info.service.IChainCollectionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author luyanan
 * @since 2020/9/17
 * <p>链接收藏</p>
 **/
@Api(description = "链藏")
@RestController
@RequestMapping(Version.VERSION_1 + "chain/collection")
public class ChainCollectionApiController {

    @Autowired
    private IChainCollectionService chainCollectionService;

    @GetMapping("list/map/{classifyId}")
    @ApiOperation(value = "根据父类id查询下面的所有链藏")
    public RespEntity<Map<String, List<ChainCollectionVo>>> listyByMap(@PathVariable("classifyId") Long classifyId) {
        Map<String, List<ChainCollectionVo>> map = chainCollectionService.listyByMap(classifyId);
        return RespEntity.success(map);
    }
}
