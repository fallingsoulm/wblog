package com.wblog.common.module.system.api;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.wblog.common.constant.ApplicationNameConstants;
import com.wblog.common.constant.Version;
import com.wblog.common.module.system.vo.SysUserVo;
import io.github.fallingsoulm.easy.archetype.framework.page.PageInfo;
import io.github.fallingsoulm.easy.archetype.framework.page.PageRequestParams;
import io.github.fallingsoulm.easy.archetype.framework.page.RespEntity;
import io.github.fallingsoulm.easy.archetype.framework.utils.BeanUtils;
import io.swagger.annotations.ApiOperation;
import javafx.scene.chart.PieChart;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author luyanan
 * @since 2020/9/16
 * <p>用户</p>
 **/

@FeignClient(value = ApplicationNameConstants.SYSTEM)
public interface SysUserApi {

    String prefix = ApplicationNameConstants.SYSTEM_PATH_PREFIX + "system/user/";


    /**
     * 根据id集合查询
     *
     * @param userIds
     * @return io.github.fallingsoulm.easy.archetype.framework.page.RespEntity<java.util.List < com.wblog.common.module.system.vo.SysUserVo>>
     * @since 2021/6/2
     */
    @PostMapping(prefix + "find/ids")
    RespEntity<List<SysUserVo>> findByIds(@RequestBody List<Long> userIds);

    /**
     * 根据用户id查询
     *
     * @param userId 用户id
     * @return io.github.fallingsoulm.easy.archetype.framework.page.RespEntity<com.wblog.common.module.system.vo.SysUserVo>
     * @since 2021/6/3
     */
    @GetMapping(prefix + "find/id/{userId}")
    RespEntity<SysUserVo> findById(@PathVariable("userId") Long userId);

    /**
     * 用户保存
     *
     * @param sysUserVo
     * @return io.github.fallingsoulm.easy.archetype.framework.page.RespEntity
     * @since 2021/6/3
     */
    @PostMapping(prefix)
    RespEntity save(@RequestBody SysUserVo sysUserVo);
}
