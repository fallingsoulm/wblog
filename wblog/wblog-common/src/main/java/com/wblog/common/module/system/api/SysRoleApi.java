package com.wblog.common.module.system.api;

import com.wblog.common.constant.Version;
import com.wblog.common.module.system.vo.SysRoleVo;
import io.github.fallingsoulm.easy.archetype.framework.page.RespEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author luyanan
 * @since 2020/11/3
 * <p>系统角色</p>
 **/
@FeignClient("apes-hub-uaa-web")
public interface SysRoleApi {
    String prefix = "uaa/" + Version.VERSION_1 + "system/role/";


    /**
     * <p>根据用户id查询角色列表</p>
     *
     * @param userId
     * @return {@link RespEntity < List < SysRoleVo >>}
     * @author luyanan
     * @since 2020/11/3
     */
    @GetMapping(prefix + "list/userId/{userId}")
    RespEntity<List<SysRoleVo>> listByUserId(@PathVariable("userId") Long userId);
}
