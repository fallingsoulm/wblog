package com.wblog.common.datascope;

/**
 * @author luyanan
 * @since 2020/11/3
 * <p>数据权限枚举</p>
 **/
public enum DataScopeEnums {


    /**
     * <p>全部数据权限</p>
     *
     * @author luyanan
     * @since 2020/11/3
     */
    DATA_SCOPE_ALL("1"),
    /**
     * <p>自定义数据权限</p>
     *
     * @author luyanan
     * @since 2020/11/3
     */
    DATA_SCOPE_CUSTOM("2"),

    /**
     * <p>部门数据权限</p>
     *
     * @author luyanan
     * @since 2020/11/3
     */
    DATA_SCOPE_DEPT("3"),

    /**
     * <p>部门以及以下数据权限</p>
     *
     * @author luyanan
     * @since 2020/11/3
     */
    DATA_SCOPE_DEPT_AND_CHILD("4"),

    /**
     * <p>仅本人数据权限</p>
     *
     * @author luyanan
     * @since 2020/11/3
     */
    DATA_SCOPE_SELF("5");;


    private String code;

    public String getCode() {
        return code;
    }

    DataScopeEnums(String code) {
        this.code = code;
    }
}
