package com.wblog.common.module.system.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author luyanan
 * @since 2020/9/12
 * <p>登录用户的Vo</p>
 **/
@ApiModel(value = "登录的用户")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginUserVo {

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "用户id")
    private Long userId;
    @ApiModelProperty(value = "部门id")
    private Long deptId;

    /**
     * <p>是否记住我</p>
     *
     * @author luyanan
     * @since 2020/10/13
     */
    private Boolean rememberMe;

    /**
     * <p>回调url</p>
     *
     * @author luyanan
     * @since 2020/10/19
     */
    private String redirectUri;

    /**
     * 是否为管理员
     *
     * @return 结果
     */
    public boolean isAdmin() {
        return userId != null && 1L == userId;
    }

}
