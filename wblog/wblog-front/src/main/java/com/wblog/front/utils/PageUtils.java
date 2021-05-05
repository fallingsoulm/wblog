package com.wblog.front.utils;


import io.github.fallingsoulm.easy.archetype.framework.page.PageRequestParams;

/**
 * @author luyanan
 * @since 2020/6/13
 * <p>分页工具类</p>
 **/
public class PageUtils {

    private static Integer DEFAULT_PAGE_SIZE = 20;

    /**
     * <p>PageRequestParams 构建</p>
     *
     * @param page
     * @param entity 条件
     * @return {@link PageRequestParams<T>}
     * @author luyanan
     * @since 2020/6/13
     */
    public static <T> PageRequestParams<T> build(T entity, Integer page, Integer size) {
        page = (page == null) ? 1 : page;
        PageRequestParams<T> pageRequestParams = new PageRequestParams<>();
        pageRequestParams.setPageSize(size);
        pageRequestParams.setPageNum(page);
        pageRequestParams.setParams(entity);
        return pageRequestParams;
    }


    public static <T> PageRequestParams<T> build(T entity, Integer page) {
        return build(entity, page, DEFAULT_PAGE_SIZE);
    }

    /**
     * <p>PageRequestParams 构建</p>
     *
     * @param page
     * @return {@link PageRequestParams<T>}
     * @author luyanan
     * @since 2020/6/13
     */
    public static <T> PageRequestParams<T> build(Integer page, Integer size) {
        return build(null, page, size);
    }

    /**
     * <p>PageRequestParams 构建</p>
     *
     * @param page
     * @return {@link PageRequestParams<T>}
     * @author luyanan
     * @since 2020/6/13
     */
    public static <T> PageRequestParams<T> build(Integer page) {
        return build(null, page, DEFAULT_PAGE_SIZE);
    }
}
