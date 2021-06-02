package com.wblog.common.module.system.api;

import com.wblog.common.constant.ApplicationNameConstants;
import com.wblog.common.constant.Version;
import com.wblog.common.module.system.vo.SysUserVo;
import io.github.fallingsoulm.easy.archetype.framework.page.RespEntity;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author luyanan
 * @since 2020/9/16
 * <p>用户</p>
 **/

@FeignClient(value = ApplicationNameConstants.SYSTEM, fallback = SysUserApiFallBack.class)
public interface SysUserApi {

    String prefix = ApplicationNameConstants.SYSTEM_PATH_PREFIX + Version.VERSION_1 + "system/user/";

    @PostMapping(prefix + "find/ids")
    RespEntity<List<SysUserVo>> findByIds(@RequestBody List<Long> ids);


    /**
     * <p>根据id查询</p>
     *
     * @param id
     * @return {@link RespEntity< SysUserVo>}
     * @author luyanan
     * @since 2020/9/16
     */
    @GetMapping(prefix + "find/id/{id}")
    RespEntity<SysUserVo> findById(@PathVariable("id") Long id);

    /**
     * <p>添加</p>
     *
     * @param userVo
     * @return {@link RespEntity< Long>}
     * @author luyanan
     * @since 2020/9/16
     */

    @PostMapping(prefix + "save")
    RespEntity<Long> save(@RequestBody SysUserVo userVo);


    @ApiOperation(value = "获取数据权限")
    @GetMapping(prefix + "/data/scope/{userId}")
    RespEntity<List<String>> getDataScope(@PathVariable("userId") Long userId);

    @ApiOperation(value = "根据用户id获取所在部门以及部门以下的所有用户id")
    @GetMapping(prefix + "/deptAndChild/userId/{userId}")
    RespEntity<List<Long>> getDeptAndChildUserIdsByDeptUserId(@PathVariable("userId") Long userId);

    @ApiOperation(value = "根据用户id获取所在部门下的所有用户id")
    @GetMapping(prefix + "/dept/userId/{userId}")
    RespEntity<List<Long>> getUserIdsByDeptUserId(@PathVariable("userId") Long userId);


    /**
     * <p>根据部门id集合查询用户id集合</p>
     *
     * @param deptIds
     * @return {@link RespEntity<List<Long>>}
     * @author luyanan
     * @since 2020/11/4
     */
    @ApiOperation(value = "根据部门id集合查询用户id")
    @PostMapping(prefix + "/list/deptIds")
    RespEntity<List<Long>> listByDeptIds(@RequestBody List<Long> deptIds);
}
