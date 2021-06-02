package com.wblog.common.module.system.api;

import com.wblog.common.constant.ApplicationNameConstants;
import com.wblog.common.constant.Version;
import io.github.fallingsoulm.easy.archetype.framework.page.RespEntity;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author luyanan
 * @since 2020/11/4
 * <p>部门接口</p>
 **/
@FeignClient(ApplicationNameConstants.SYSTEM)
public interface SysDeptApi {
    String prefix = ApplicationNameConstants.SYSTEM_PATH_PREFIX + Version.VERSION_1 + "system/dept/";


    /**
     * <p>根据部门id获取该部门和部门以下所有部门的id</p>
     *
     * @param deptId
     * @return {@link RespEntity <List<Long>>}
     * @author luyanan
     * @since 2020/11/4
     */
    @ApiOperation(value = "根据部门id获取该部门和部门以下所有部门的id")
    @GetMapping(prefix + "/dept/child/dept/{deptId}")
    RespEntity<List<Long>> getDeptAndChildByDeptId(@PathVariable("deptId") Long deptId);


    /**
     * <p>查询该用户的角色关联的部门id</p>
     *
     * @param userId
     * @return {@link RespEntity<List<Long>>}
     * @author luyanan
     * @since 2020/11/4
     */
    @ApiOperation(value = "查询该用户的角色关联的部门id")
    @GetMapping(prefix + "/list/deptId/userId/{userId}")
    RespEntity<List<Long>> listDeptIdByUserId(@PathVariable("userId") Long userId);
}
