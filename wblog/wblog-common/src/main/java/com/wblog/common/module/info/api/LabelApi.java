package com.wblog.common.module.info.api;

import com.wblog.common.constant.ApplicationNameConstants;
import com.wblog.common.constant.Version;
import com.wblog.common.module.info.vo.LabelVo;
import io.github.fallingsoulm.easy.archetype.framework.page.PageInfo;
import io.github.fallingsoulm.easy.archetype.framework.page.PageRequestParams;
import io.github.fallingsoulm.easy.archetype.framework.page.RespEntity;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author luyanan
 * @since 2020/9/17
 * <p>标签</p>
 **/
@FeignClient(ApplicationNameConstants.INFO)
public interface LabelApi {

    String prefix = ApplicationNameConstants.INFO_PATH_PREFIX + Version.VERSION_1 + "label/";

    /**
     * <p>分页查询标签并且统计数量</p>
     *
     * @param pageRequestParams 分页参数
     * @return {@link PageInfo< LabelVo>}
     * @author luyanan
     * @since 2020/6/15
     */
    @PostMapping(prefix + "find/page/count")
    @ApiOperation(value = "分页查询标签并且统计数量")
    RespEntity<PageInfo<LabelVo>> findByPageAndCount(@RequestBody PageRequestParams<LabelVo> pageRequestParams);

    /**
     * <p>根据id查询</p>
     *
     * @param labelId
     * @return {@link LabelVo}
     * @author luyanan
     * @since 2020/9/17
     */
    @ApiOperation(value = "根据id查询", response = LabelVo.class)
    @GetMapping(prefix + "find/id/{labelId}")
    RespEntity<LabelVo> findById(@PathVariable("labelId") Long labelId);


    /**
     * 根据信息id查询
     *
     * @param articleId 文章id
     * @param num       数量,当为-1的时候,查询所有
     * @return io.github.fallingsoulm.easy.archetype.framework.page.RespEntity<java.util.List < com.wblog.common.module.info.vo.LabelVo>>
     * @since 2021/7/26
     */
    @ApiOperation(value = "根据信息id查询")
    @GetMapping(prefix + "ids/articleId")
    RespEntity<List<LabelVo>> findByArticleId(@RequestParam("articleId") Long articleId, @RequestParam("num") Integer num);
}
