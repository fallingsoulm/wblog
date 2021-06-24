package com.wblog.common.exception;

import cn.hutool.http.HttpStatus;
import io.github.fallingsoulm.easy.archetype.data.logger.LoggerEvent;
import io.github.fallingsoulm.easy.archetype.data.logger.LoggerVo;
import io.github.fallingsoulm.easy.archetype.framework.page.RespEntity;
import io.github.fallingsoulm.easy.archetype.framework.spring.SpringContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 异常拦截抽象类
 *
 * @author luyanan
 * @since 2021/6/12
 **/
@Slf4j
public class AbstractExceptionHandler {

    /**
     * 请求方法不支持
     *
     * @param e
     * @return com.wblog.framework.core.page.RespEntity
     * @since 2021/1/24
     */
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public RespEntity handleException(HttpRequestMethodNotSupportedException e) {
        log.error(e.getMessage(), e);
        return RespEntity.error(IMsgCode.HTTP_NOT_FOUND, e.getMethod());
    }

    /**
     * 账号失效或者过期
     *
     * @param accountExpiredException
     * @return io.github.fallingsoulm.easy.archetype.framework.page.RespEntity
     * @since 2021/6/12
     */
    @ExceptionHandler(AccountExpiredException.class)
    public RespEntity accountExpiredException(AccountExpiredException accountExpiredException) {
        return RespEntity.error(IMsgCode.HTTP_UNAUTHORIZED);
    }

    /**
     * 访问拒绝
     *
     * @param e
     * @return io.github.fallingsoulm.easy.archetype.framework.page.RespEntity
     * @since 2021/6/12
     */
    @ExceptionHandler(AccessDeniedException.class)
    public RespEntity accessDeniedException(AccessDeniedException e, HttpServletRequest request, HandlerMethod handlerMethod) {

        log.error(e.getMessage(), e);
        printLogger(request, handlerMethod, e);
        return RespEntity.error(IMsgCode.PERMISSION_DENIED);
    }


    /**
     * 日志打印
     *
     * @param request
     * @param handlerMethod
     * @return void
     * @since 2021/6/13
     */
    protected void printLogger(HttpServletRequest request, HandlerMethod handlerMethod, Exception e) {

        // 发布事件
        SpringContextHolder.publishEvent(new LoggerEvent(LoggerVo.builder()
                .endTime(System.currentTimeMillis()).exception(e)
                .request(request)
                .request(SpringContextHolder.getRequest())
                .method(handlerMethod.getMethod())
                .build()));
    }

    /**
     * 自定义校验异常
     *
     * @param e 异常
     * @return com.wblog.framework.core.page.RespEntity
     * @since 2021/1/24
     */
    @ExceptionHandler(BindException.class)
    public RespEntity validatedBindException(BindException e) {
        log.error(e.getMessage(), e);
        String message = e.getAllErrors().get(0).getDefaultMessage();
        return RespEntity.error(HttpStatus.HTTP_BAD_REQUEST, message);
    }


    /**
     * 参数校验异常
     *
     * @param e
     * @return io.github.fallingsoulm.easy.archetype.framework.page.RespEntity
     * @since 2021/6/12
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public RespEntity methodArgumentNotValidException(MethodArgumentNotValidException e) {
        e.printStackTrace();
        String defaultMessage = e.getBindingResult().getFieldError().getDefaultMessage();
//        String message = e.getAllErrors().get(0).getDefaultMessage();
        return RespEntity.error(HttpStatus.HTTP_BAD_REQUEST, defaultMessage);
    }


    /**
     * 自定义异常
     *
     * @param request
     * @param handlerMethod
     * @param e
     * @return java.lang.Object
     * @since 2021/1/24
     */
    @ExceptionHandler(BusinessException.class)
    public Object customerException(HttpServletRequest request, HandlerMethod handlerMethod, BusinessException e) {
        e.printStackTrace();
        if (SpringContextHolder.isBody(handlerMethod)) {
            // json请求
            return RespEntity.error(e.getCode(), e.getMessage());
        } else {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("errorMessage", e.getMessage());
            modelAndView.setViewName("error/business");
            return modelAndView;
        }
    }

    /**
     * 拦截未知异常
     *
     * @param e 异常信息
     * @return com.wblog.framework.core.page.RespEntity
     * @since 2021/1/24
     */
    @ExceptionHandler(Exception.class)
    public RespEntity handleException(RuntimeException e) {
        log.error(e.getMessage(), e);
        e.printStackTrace();
        return RespEntity.error(IMsgCode.INTERNAL_SERVER_ERROR, e.getMessage());
    }
}
