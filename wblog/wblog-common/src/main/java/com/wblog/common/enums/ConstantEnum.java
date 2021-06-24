package com.wblog.common.enums;


import com.wblog.common.exception.BusinessException;
import com.wblog.common.exception.IMsgCode;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author luyanan
 * @since 2020/9/15
 * <p>全局枚举</p>
 **/
public enum ConstantEnum {


    /************************git管理**********************************************/

    /**
     * <p>正常</p>
     *
     * @author luyanan
     * @since 2020/6/8
     */
    GIT_SYN_DATA_STATUS_ENABLE(EnumType.GIT_SYN_DATA_STATUS, 100, "上架", false, ListClass.primary),

    GIT_SYN_DATA_STATUS_STOP(EnumType.GIT_SYN_DATA_STATUS, 101, "下架", false, ListClass.danger),

    GIT_SYN_DATA_STATUS_CHECKING(EnumType.GIT_SYN_DATA_STATUS, 102, "审核中", true, ListClass.warning),


    GIT_SYN_DATA_STATUS_DISCARD(EnumType.GIT_SYN_DATA_STATUS, 103, "废弃", true, ListClass.warning),


    /************************用户类型****************************/
    USER_TYPE_SYSTEM(EnumType.USER_TYPE, 0, "系统用户", false),
    USER_TYPE_REGISTER(EnumType.USER_TYPE, 1, "注册用户", true),


    /*************************文章来源******************************/
    ARTICLE_SOURCE_ORIGINAL(EnumType.ARTICLE_SOURCE_TYPE, 100, "原创", true),
    ARTICLE_SOURCE_REPRINT(EnumType.ARTICLE_SOURCE_TYPE, 101, "转载", false),
    ARTICLE_SOURCE_TRANSLATE(EnumType.ARTICLE_SOURCE_TYPE, 102, "翻译", false),


    /*****************************标签状态*********************************/
    LABEL_STATUS_ENABLE(EnumType.LABEL_STATUS, 100, "上架", false),
    LABEL_STATUS_STOP(EnumType.LABEL_STATUS, 101, "下架", true),


    /**********************************文章状态***************************************/
    ARTICLE_STATUS_ENABLE(EnumType.ARTICLE_STATUS, 100, "上架", false, ListClass.primary),

    ARTICLE_STATUS_STOP(EnumType.ARTICLE_STATUS, 101, "下架", false, ListClass.danger),

    ARTICLE_STATUS_CHECKING(EnumType.ARTICLE_STATUS, 102, "审核中", true, ListClass.warning),
    ARTICLE_STATUS_DISCARD(EnumType.ARTICLE_STATUS, 103, "废弃", false, ListClass.warning),
    /*******************************文章来源********************************/
    GIT_SYN_DATA_SOURCE_GITEE(EnumType.GIT_SYN_DATA_SOURCE, 100, "gitee"),
    GIT_SYN_DATA_SOURCE_GITHUB(EnumType.GIT_SYN_DATA_SOURCE, 101, "github"),
    GIT_SYN_DATA_SOURCE_UNKNOWN(EnumType.GIT_SYN_DATA_SOURCE, 102, "未知"),
    GIT_SYN_DATA_SOURCE_SYSTEM(EnumType.GIT_SYN_DATA_SOURCE, 103, "系统"),
    /*************************************链藏分类***************************/
    CHAIN_COLLECTION_TYPE_SYSTEM(EnumType.CHAIN_COLLECTION_TYPE, 100, "系统"),
    CHAIN_COLLECTION_TYPE_CUSTOM(EnumType.CHAIN_COLLECTION_TYPE, 101, "自定义"),
    /***********************************链藏状态*************************************/
    CHAIN_COLLECTION_STATUS_ENABLE(EnumType.CHAIN_COLLECTION_STATUS, 100, "上架"),
    CHAIN_COLLECTION_STATUS_STOP(EnumType.CHAIN_COLLECTION_STATUS, 101, "下架"),
    /*************************************资讯来源*****************************************/
    NEWS_SOURCE_OSCHINA(EnumType.NEWS_SOURCE, 100, "oschina"),
    NEWS_SOURCE_JUEJIN(EnumType.NEWS_SOURCE, 101, "juejin"),
    NEWS_SOURCE_SEGMENTFAULT(EnumType.NEWS_SOURCE, 102, "segmentfault"),
    NEWS_SOURCE_CNBLOGS(EnumType.NEWS_SOURCE, 103, "cnblogs"),
    NEWS_SOURCE_CSDN(EnumType.NEWS_SOURCE, 104, "CSDN"),
    NEWS_SOURCE_IMOOC(EnumType.NEWS_SOURCE, 105, "imooc"),


    /************************************专辑状态*********************************/
    ALBUM_STATUS_ENABLE(EnumType.ALBUM_STATUS, 100, "上架"),
    ALBUM_STATUS_STOP(EnumType.ALBUM_STATUS, 101, "下架"),

    /**************************文章排序方式************************************/
    ARTICLE_ORDER_BY_NEWEST(EnumType.ARTICLE_ORDER_BY, 100, "最新"),
    ARTICLE_ORDER_BY_VIEW(EnumType.ARTICLE_ORDER_BY, 101, "访问量"),


    /************************日志**********************************/

    LOGGER_STATUS_SUCCESS(EnumType.LOGGER_STATUS, 200, "成功"),

    LOGGER_STATUS_ERROR(EnumType.LOGGER_STATUS, 100, "失败"),


    /*************************搜索******************************************/
    SEARCH_BELONG_ARTICLE(EnumType.SEARCH_BELONG, 100, "文章"),
    SEARCH_BELONG_NEWS(EnumType.SEARCH_BELONG, 101, "资讯"),
    ;

    /**
     * <p>枚举类型</p>
     *
     * @author luyanan
     * @since 2020/6/8
     */
    private EnumType enumType;

    /**
     * <p>类型</p>
     *
     * @author luyanan
     * @since 2020/6/8
     */
    private Integer value;

    /**
     * <p>介绍</p>
     *
     * @author luyanan
     * @since 2020/6/8
     */
    private String desp;

    /**
     * <p>是否为默认值</p>
     *
     * @author luyanan
     * @since 2020/6/8
     */
    private boolean defaults;

    /**
     * <p>表格回显样式</p>
     *
     * @return {@link }
     * @author luyanan
     * @since 2020/6/9
     */
    private ListClass listClass;


    /**
     * <p>根据type和value 值获取</p>
     *
     * @param enumType 枚举类型
     * @param value    value
     * @return {@link ConstantEnum}
     * @author luyanan
     * @since 2020/6/8
     */
    public static ConstantEnum getByTypeAndValue(EnumType enumType, Integer value) {
        return Arrays.stream(ConstantEnum.values())
                .filter(a -> a.getEnumType().equals(enumType) && a.getValue().equals(value))
                .findFirst().orElseThrow(() -> new BusinessException(IMsgCode.INTERNAL_SERVER_ERROR, "未知的枚举类型:" + value));
    }

    /**
     * <p>对比value值是否相等</p>
     *
     * @param value
     * @return {@link boolean}
     * @author luyanan
     * @since 2020/12/22
     */
    public boolean equalsValue(Integer value) {
        return this.getValue().equals(value);
    }


    /**
     * <p>根据类型查找</p>
     *
     * @param enumType 枚举类型
     * @return {@link List < ConstantEnum>}
     * @author luyanan
     * @since 2020/6/8
     */
    public static List<ConstantEnum> getByType(EnumType enumType) {
        return Arrays.stream(ConstantEnum.values())
                .filter(a -> a.getEnumType().equals(enumType)).collect(Collectors.toList());
    }

    /**
     * <p>根据类型查找</p>
     *
     * @param enumType 枚举类型
     * @return {@link List < ConstantEnum>}
     * @author luyanan
     * @since 2020/6/8
     */
    public static List<ConstantEnum> getByType(String enumType) {
        return Arrays.stream(ConstantEnum.values())
                .filter(a -> a.getEnumType().name.equals(enumType)).collect(Collectors.toList());
    }

    ConstantEnum(EnumType enumType, Integer type, String desp) {
        this.enumType = enumType;
        this.value = type;
        this.desp = desp;
        this.defaults = false;
        this.listClass = ListClass.primary;
    }

    ConstantEnum(Integer value, String desp) {
        this.enumType = EnumType.DEFAULT;
        this.value = value;
        this.desp = desp;
        this.defaults = false;
        this.listClass = ListClass.primary;
    }


    ConstantEnum(EnumType enumType, Integer value, String desp, boolean defaults, ListClass listClass) {
        this.enumType = enumType;
        this.value = value;
        this.desp = desp;
        this.defaults = defaults;
        this.listClass = listClass;
    }

    public EnumType getEnumType() {
        return enumType;
    }

    public Integer getValue() {
        return value;
    }

    public String getDesp() {
        return desp;
    }

    public boolean isDefault() {
        return defaults;
    }

    public ListClass getListClass() {
        return listClass;
    }

    ConstantEnum(EnumType enumType, Integer value, String desp, boolean defaults) {
        this.enumType = enumType;
        this.value = value;
        this.desp = desp;
        this.defaults = defaults;
    }

    /**
     * <p>比较状态</p>
     *
     * @param status
     * @return {@link boolean}
     * @author luyanan
     * @since 2020/9/22
     */
    public boolean compareStatus(Integer status) {
        return this.value.equals(status);
    }

    /**
     * <p>枚举类型</p>
     *
     * @author luyanan
     * @since 2020/6/8
     */
    public enum EnumType {
        DEFAULT("DEFAULT"),

        GIT_SYN_DATA_STATUS("GIT_SYN_DATA_STATUS"),


        /**
         * <p>用户类型</p>
         *
         * @author luyanan
         * @since 2020/6/9
         */
        USER_TYPE("USER_TYPE"),

        /**
         * <p>文章来源</p>
         *
         * @author luyanan
         * @since 2020/6/10
         */
        ARTICLE_SOURCE_TYPE("ARTICLE_SOURCE_TYPE"),

        /**
         * <p>标签的状态</p>
         *
         * @author luyanan
         * @since 2020/6/10
         */
        LABEL_STATUS("LABEL_STATUS"),


        /**
         * <p>文章状态</p>
         *
         * @author luyanan
         * @since 2020/6/12
         */
        ARTICLE_STATUS("ARTICLE_STATUS"),


        /**
         * <p>来源</p>
         *
         * @author luyanan
         * @since 2020/6/22
         */
        GIT_SYN_DATA_SOURCE("GIT_SYN_DATA_SOURCE"),
        /**
         * <p>链藏分类</p>
         *
         * @author luyanan
         * @since 2020/6/24
         */
        CHAIN_COLLECTION_TYPE("CHAIN_COLLECTION_TYPE"),


        /**
         * <p>链藏状态</p>
         *
         * @author luyanan
         * @since 2020/6/26
         */
        CHAIN_COLLECTION_STATUS("CHAIN_COLLECTION_STATUS"),


        /**
         * <p>资讯来源</p>
         *
         * @author luyanan
         * @since 2020/7/13
         */
        NEWS_SOURCE("NEWS_SOURCE"),


        /**
         * <p>专辑的状态</p>
         *
         * @author luyanan
         * @since 2020/7/15
         */
        ALBUM_STATUS("ALBUM_STATUS"),

        /**
         * 排序方式
         *
         * @author luyanan
         * @since 2020/11/24
         */

        ARTICLE_ORDER_BY("ARTICLE_ORDER_BY"),


        /**
         * 日志状态
         *
         * @author luyanan
         * @since 2020/11/24
         */
        LOGGER_STATUS("LOGGER_STATUS"),


        /**
         * <p>搜索来源</p>
         *
         * @author luyanan
         * @since 2020/12/16
         */
        SEARCH_BELONG("SEARCH_BELONG");

        /**
         * <p>名称</p>
         *
         * @author luyanan
         * @since 2020/6/8
         */
        private String name;

        EnumType(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    /**
     * <p>列表回显样式</p>
     *
     * @author luyanan
     * @since 2020/6/9
     */
    public enum ListClass {

        /**
         * <p>正常</p>
         *
         * @author luyanan
         * @since 2020/6/9
         */
        primary("primary"),
        /**
         * <p>隐藏</p>
         *
         * @author luyanan
         * @since 2020/6/9
         */
        danger("danger"),

        warning("warning"),
        success("success"),
        ;


        private String name;

        ListClass(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }


}
