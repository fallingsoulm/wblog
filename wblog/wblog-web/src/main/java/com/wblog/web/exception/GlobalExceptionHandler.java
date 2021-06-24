package com.wblog.web.exception;

import com.wblog.common.exception.AbstractExceptionHandler;
import com.wblog.common.exception.BusinessException;
import com.wblog.common.exception.IMsgCode;
import com.wblog.system.SystemMsgCode;
import io.github.fallingsoulm.easy.archetype.framework.page.RespEntity;
import io.github.fallingsoulm.easy.archetype.framework.spring.SpringContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常拦截
 *
 * @author luyanan
 * @since 2021/1/24
 **/
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends AbstractExceptionHandler {


}
