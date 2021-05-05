package com.wblog.system.enums;

import com.wblog.common.exception.BusinessException;
import com.wblog.system.SystemMsgCode;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 系统模块枚举
 *
 * @author luyanan
 * @since 2021/2/12
 **/
public enum SystemEnums implements IEnums {

    /**
     * 目录
     *
     * @since 2021/2/10
     */
    MENU_TYPE_DIR(DictClassify.MENU_TYPE, "M", "目录"),

    /**
     * 菜单
     *
     * @since 2021/2/10
     */
    MENU_TYPE_MENU(DictClassify.MENU_TYPE, "C", "菜单"),
    /**
     * 按钮
     *
     * @since 2021/2/10
     */
    MENU_TYPE_BUTTON(DictClassify.MENU_TYPE, "F", "按钮"),

    /**
     * 显示
     *
     * @since 2021/2/10
     */
    STATUS_NORMAL(0, "正常"),
    /**
     * 状态-停用
     *
     * @since 2021/2/10
     */
    STATUS_DISABLE(1, "停用"),

    /**
     * 是
     *
     * @since 2021/2/10
     */
    MENU_YES_FRAME(0, "是"),

    /**
     * 不是
     *
     * @since 2021/2/10
     */
    MENU_NO_FRAME(1, "否"),

    /**
     * Layout组件标识
     *
     * @since 2021/2/10
     */
    MENU_LAYOUT("Layout", "Layout组件标识"),

    /**
     * ParentView组件标识
     *
     * @since 2021/2/10
     */
    MENU_PARENT_VIEW("ParentView", "ParentView组件标识"),

    /**
     * 是否为菜单外链(是)
     *
     * @since 2021/2/14
     */

    MENU_FRAME_YES(DictClassify.MENU_FRAME, "0", "是"),

    /**
     * 是否为菜单外链(否)
     *
     * @since 2021/2/14
     */

    MENU_FRAME_NO(DictClassify.MENU_FRAME, "1", "否"),

    ;;


    /**
     * 分类
     *
     * @since 2021/2/12
     */

    private DictClassify classify;
    /**
     * 编码
     *
     * @since 2021/2/12
     */

    private Object code;

    /**
     * 介绍
     *
     * @since 2021/2/12
     */

    private String desp;


    SystemEnums(Object code, String desp) {
        this.classify = DictClassify.DEFAULT;
        this.code = code;
        this.desp = desp;
    }


    SystemEnums(DictClassify classify, Object code, String desp) {
        this.classify = classify;
        this.code = code;
        this.desp = desp;
    }

    /**
     * code对比
     *
     * @param code code
     * @return boolean
     * @since 2021/2/14
     */
    public boolean equalsCode(Object code) {
        return this.getCode().equals(code);
    }

    @Override
    public DictClassify getClassify() {
        return classify;
    }

    @Override
    public Object getCode() {
        return code;
    }

    @Override
    public String getDesp() {
        return desp;
    }


    /**
     * 根据分类获取该分类下的枚举列表
     *
     * @param classify 分类
     * @return java.util.List<com.wblog.system.enums.IEnums>
     * @since 2021/2/12
     */
    public static List<IEnums> getByClassify(DictClassify classify) {
        return Arrays.stream(SystemEnums.values()).filter(a -> a.classify == classify).collect(Collectors.toList());
    }


    public static List<EnumVo> getVoByClassify(DictClassify classify) {
        return getByClassify(classify).stream().map(a -> a.enumVo()).collect(Collectors.toList());
    }


    public static IEnums getByClassify(DictClassify classify, Object code) {
        return Arrays
                .stream(SystemEnums.values())
                .filter(a -> a.classify == classify && a.getCode().equals(code)).findAny()
                .orElseThrow(() -> new BusinessException(SystemMsgCode.DICT_NOT_FOUNT));
    }


    public static IEnums getByClassify(Object code) {
        return getByClassify(DictClassify.DEFAULT, code);
    }

    public static EnumVo getVoByClassify(DictClassify classify, Object code) {
        return getByClassify(classify, code).enumVo();
    }


    public static enum DictClassify implements IClassify {


        /**
         * 默认
         *
         * @since 2021/2/12
         */

        DEFAULT("default"),
        /**
         * 菜单类型
         *
         * @since 2021/2/12
         */

        MENU_TYPE("menu_type"),
        /**
         * 菜单外链
         *
         * @since 2021/2/14
         */

        MENU_FRAME("MENU_FRAME"),
        ;


        private String code;


        @Override
        public String getCode() {
            return code;
        }

        DictClassify(String code) {
            this.code = code;
        }
    }

}
