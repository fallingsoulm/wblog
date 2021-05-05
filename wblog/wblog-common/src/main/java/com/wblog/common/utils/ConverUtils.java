package com.wblog.common.utils;

import cn.hutool.core.collection.CollectionUtil;
import io.github.fallingsoulm.easy.archetype.framework.page.PageInfo;
import io.github.fallingsoulm.easy.archetype.framework.page.PageRequestParams;
import io.github.fallingsoulm.easy.archetype.framework.utils.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 类转换Vo工具类
 *
 * @author luyanan
 * @since 2021/2/27
 **/
public class ConverUtils {


    /**
     * 类型转换
     *
     * @param pageRequestParams 来源分页参数
     * @param targetClass       目标分页参数
     * @param converHandler     转换处理类
     * @return com.wblog.framework.core.page.PageRequestParams<T>
     * @since 2021/2/27
     */
    public static <T, S> PageRequestParams<T> converPageRequestParams(PageRequestParams<S> pageRequestParams,
                                                                      Class<? extends T> targetClass, ConverHandler converHandler) {
        S source = pageRequestParams.getParams();
        T target = null;
        if (null != source && null != converHandler) {
            target = (T) converHandler.conver(source, targetClass);
        }
        return PageRequestParams.build(target, pageRequestParams.getOffset(), pageRequestParams.getPageSize());
    }

    /**
     * 类型转换
     *
     * @param pageRequestParams 来源分页参数
     * @param targetClass       目标分页参数
     * @return com.wblog.framework.core.page.PageRequestParams<T>
     * @since 2021/2/27
     */
    public static <T, S> PageRequestParams<T> converPageRequestParams(PageRequestParams<S> pageRequestParams,
                                                                      Class<? extends T> targetClass) {
        return converPageRequestParams(pageRequestParams, targetClass, new ConverHandler() {
        });
    }

    /**
     * 分页返回结果转换
     *
     * @param pageInfo    分页结果
     * @param targetClass 目标类的class
     * @return com.wblog.framework.core.page.PageInfo<T>
     * @since 2021/2/27
     */
    public static <T, S> PageInfo<T> converPageInfo(PageInfo<S> pageInfo,
                                                    Class<? extends T> targetClass
    ) {
        return converPageInfo(pageInfo, targetClass, new ListConverHandler<S, T>() {
        }, new ConverHandler<S, T>() {
        });
    }

    /**
     * 分页返回结果转换
     *
     * @param pageInfo              分页结果
     * @param targetClass           目标类的class
     * @param pageInfoConverHandler 分页结果转换
     * @param converHandler         单个元素转换
     * @return com.wblog.framework.core.page.PageInfo<T>
     * @since 2021/2/27
     */
    public static <T, S> PageInfo<T> converPageInfo(PageInfo<S> pageInfo,
                                                    Class<? extends T> targetClass,
                                                    ListConverHandler<S, T> pageInfoConverHandler,
                                                    ConverHandler<S, T> converHandler) {
        List<S> content = pageInfo.getContent();
        List<T> targets = pageInfoConverHandler.content(content, targetClass, converHandler);

        PageInfo<T> info = new PageInfo<>();
        info.setContent(targets);
        info.setPageNum(pageInfo.getPageNum());
        info.setPageSize(pageInfo.getPageSize());
        info.setTotalElements(pageInfo.getTotalElements());
        return info;
    }


    /**
     * 分页返回结果转换
     *
     * @param pageInfo          分页结果
     * @param targetClass       目标类的class
     * @param listConverHandler 分页结果转换
     * @return com.wblog.framework.core.page.PageInfo<T>
     * @since 2021/2/27
     */
    public static <T, S> PageInfo<T> converPageInfo(PageInfo<S> pageInfo,
                                                    Class<? extends T> targetClass,
                                                    ListConverHandler<S, T> listConverHandler
    ) {

        return converPageInfo(pageInfo, targetClass, listConverHandler, new ConverHandler<S, T>() {
        });
    }


    /**
     * 数据转换
     *
     * @param source        来源实体
     * @param targetClass   目标实体的class
     * @param converHandler 转换处理类
     * @return T
     * @since 2021/2/27
     */
    public static <T, S> T conver(S source, Class<? extends T> targetClass, ConverHandler<S, T> converHandler) {
        if (null == source) {
            return null;
        }
        return converHandler.conver(source, targetClass);
    }

    /**
     * 数据转换
     *
     * @param source      来源实体
     * @param targetClass 目标实体的class
     * @return T
     * @since 2021/2/27
     */
    public static <T, S> T conver(S source, Class<? extends T> targetClass) {
        return conver(source, targetClass, new ConverHandler<S, T>() {
        });
    }


    /**
     * 列表转换
     *
     * @param sources           来源集合
     * @param targetClass       目标类class
     * @param listConverHandler 集合转换
     * @param converHandler     元素转换
     * @return java.util.List<T>
     * @since 2021/2/27
     */
    public static <T, S> List<T> listConver(List<S> sources, Class<? extends T> targetClass, ListConverHandler<S, T> listConverHandler, ConverHandler<S, T> converHandler
    ) {
        if (CollectionUtil.isEmpty(sources)) {
            return new ArrayList<>();
        }
        return listConverHandler.content(sources, targetClass, converHandler);
    }

    /**
     * 列表转换
     *
     * @param sources           来源集合
     * @param targetClass       目标类class
     * @param listConverHandler 集合转换
     * @return java.util.List<T>
     * @since 2021/2/27
     */
    public static <T, S> List<T> listConver(List<S> sources, Class<? extends T> targetClass,
                                            ListConverHandler<S, T> listConverHandler
    ) {
        if (CollectionUtil.isEmpty(sources)) {
            return new ArrayList<>();
        }
        return listConverHandler.content(sources, targetClass, new ConverHandler<S, T>() {
        });
    }

    /**
     * 列表转换
     *
     * @param sources     来源集合
     * @param targetClass 目标类class
     * @return java.util.List<T>
     * @since 2021/2/27
     */
    public static <T, S> List<T> listConver(List<S> sources, Class<? extends T> targetClass
    ) {
        if (CollectionUtil.isEmpty(sources)) {
            return new ArrayList<>();
        }
        return new ListConverHandler<S, T>() {
        }.content(sources, targetClass, new ConverHandler<S, T>() {
        });
    }


    /**
     * 分页结果转换
     *
     * @since 2021/2/27
     */
    public interface ListConverHandler<S, T> {

        default List<T> content(List<S> sources, Class<? extends T> targetClass, ConverHandler<S, T> converHandler) {
            if (CollectionUtil.isEmpty(sources)) {
                return new ArrayList<>();
            }
            List<T> targets = sources.stream().map(s -> converHandler.conver(s, targetClass)).collect(Collectors.toList());
            contentHandler(targets);
            return targets;
        }


        default void contentHandler(List<T> targets) {

        }

    }


    /**
     * 转换处理类
     *
     * @author luyanan
     * @since 2021/2/27
     */
    public interface ConverHandler<S, T> {


        /**
         * 类型转换
         *
         * @param source 来源类
         * @param tClass 目标类的class
         * @return T  目标类
         * @since 2021/2/27
         */
        default T conver(S source, Class<? extends T> tClass) {
            if (null == source) {
                return null;
            }
            T target = BeanUtils.copyProperties(source, tClass);
            converHandler(source, target);
            return target;
        }


        /**
         * source转target的处理
         *
         * @param source 来源类
         * @param target 目标类
         * @return void
         * @since 2021/2/27
         */
        default void converHandler(S source, T target) {

        }

    }


}
