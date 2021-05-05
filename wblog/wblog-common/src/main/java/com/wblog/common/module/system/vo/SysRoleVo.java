package com.wblog.common.module.system.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author luyanan
 * @since 2020/11/3
 * <p>角色</p>
 **/
@Data
public class SysRoleVo implements Serializable {
    private static final long serialVersionUID = -7556596540069781977L;
    /**
     * 角色ID
     */

    private Long roleId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色权限
     */
    private String roleKey;

    /**
     * 角色排序
     */
    private String roleSort;

    /**
     * 数据范围（1：所有数据权限；2：自定义数据权限；3：本部门数据权限；4：本部门及以下数据权限）
     */
    private String dataScope;

    /**
     * 角色状态（0正常 1停用）
     */
    private String status;
}
