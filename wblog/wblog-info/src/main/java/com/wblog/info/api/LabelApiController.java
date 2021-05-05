package com.wblog.info.api;

import com.apes.hub.api.enums.ConstantEnum;
import com.apes.hub.api.module.info.vo.LabelVo;
import com.apes.hub.api.page.PageInfo;
import com.apes.hub.api.page.PageRequestParams;
import com.apes.hub.api.uitils.RespEntity;
import com.apes.hub.core.constant.Version;
import com.apes.hub.info.service.ILabelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author luyanan
 * @since 2020/9/17
 * <p>标签</p>
 **/
@Api(description = "标签")
@RestController
@RequestMapping(Version.VERSION_1 + "label")
public class LabelApiController {

    @Autowired
    private ILabelService labelService;

    /**
     * <p>分页查询标签并且统计数量</p>
     *
     * @param pageRequestParams 分页参数
     * @return {@link PageInfo<LabelVo>}
     * @author luyanan
     * @since 2020/6/15
     */
    @PostMapping("find/page/count")
    @ApiOperation(value = "分页查询标签并且统计数量")
    public RespEntity<PageInfo<LabelVo>> findByPageAndCount(@RequestBody PageRequestParams<LabelVo> pageRequestParams) {
        LabelVo params = pageRequestParams.getParams();
        if (params == null) {

            params = new LabelVo();
        }
        params.setArticleStatus(ConstantEnum.ARTICLE_STATUS_ENABLE.getValue());
        pageRequestParams.setParams(params);
        PageInfo<LabelVo> pageInfo = labelService.findByPageAndCount(pageRequestParams);
        return RespEntity.success(pageInfo);
    }

    /**
     * <p>根据id查询</p>
     *
     * @param labelId
     * @return {@link LabelVo}
     * @author luyanan
     * @since 2020/9/17
     */
    @ApiOperation(value = "根据id查询", response = LabelVo.class)
    @GetMapping("find/id/{labelId}")
    RespEntity<LabelVo> findById(@PathVariable("labelId") Long labelId) {
        LabelVo labelVo = labelService.findById(labelId);
        return RespEntity.success(labelVo);
    }

    @ApiOperation(value = "分页查询")
    @PostMapping("list")
    public RespEntity<PageInfo<LabelVo>> list(@RequestBody PageRequestParams<LabelVo> pageRequestParams) {

        PageInfo<LabelVo> pageInfo = labelService.findByPageAndCount(pageRequestParams);
        return RespEntity.success(pageInfo);
    }


    @ApiOperation(value = "新增")
    @PostMapping()
    public RespEntity save(@RequestBody LabelVo labelVo) {
        labelService.save(labelVo);
        return RespEntity.success();
    }


    @ApiOperation(value = "修改")
    @PutMapping
    public RespEntity edit(@RequestBody LabelVo labelVo) {
        labelService.update(labelVo);
        return RespEntity.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("/{ids}")
    public RespEntity remove(@PathVariable("ids") Long[] ids) {
        labelService.deleteByIds(Arrays.stream(ids).collect(Collectors.toList()));
        return RespEntity.success();
    }
}