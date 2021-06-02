package com.wblog.common.module.info.api;

import com.wblog.common.constant.ApplicationNameConstants;
import com.wblog.common.constant.Version;
import com.wblog.common.module.info.vo.ChainCollectionVo;
import io.github.fallingsoulm.easy.archetype.framework.page.RespEntity;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

/**
 * @author luyanan
 * @since 2020/9/17
 * <p>链接收藏</p>
 **/
@FeignClient(ApplicationNameConstants.INFO)
public interface ChainCollectionApi {
    String prefix = ApplicationNameConstants.INFO_PATH_PREFIX + Version.VERSION_1 + "chain/collection/";

    @GetMapping(prefix + "list/map/{classifyId}")
    @ApiOperation(value = "根据父类id查询下面的所有链藏")
    RespEntity<Map<String, List<ChainCollectionVo>>> listyByMap(@PathVariable("classifyId") Long classifyId);
}
