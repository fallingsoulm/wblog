package com.wblog.common.datascope.annotation;

import java.lang.annotation.*;

/**
 * @author luyanan
 * @since 2020/11/3
 * <p>数据权限过滤注解</p>
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface GlobalDataScope {


}
