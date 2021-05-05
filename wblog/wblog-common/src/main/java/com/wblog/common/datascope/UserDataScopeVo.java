package com.wblog.common.datascope;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author luyanan
 * @since 2020/11/4
 * <p>用户拥有的数据权限</p>
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDataScopeVo {


    /**
     * <p>可以查询的用户列表I</p>
     * <p>
     * 当为null的时候,不参与数据权限校验
     *
     * @author luyanan
     * @since 2020/11/4
     */
    private List<Long> userIds;

}
