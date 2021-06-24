package com.wblog.common.exception;


import cn.hutool.core.util.StrUtil;
import io.github.fallingsoulm.easy.archetype.framework.spring.message.MessageUtils;

/**
 * <p>
 * 自定义异常
 * </p>
 *
 * @author luyanan
 * @since 2021/1/12
 **/
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 1L;


    /**
     * 异常码
     *
     * @since 2021/2/9
     */
    private final Integer code;
    private Object[] args;
    private String msg;

    public BusinessException(Integer code) {
        this.code = code;
    }

    public BusinessException(String msg) {
        this.code = IMsgCode.INTERNAL_SERVER_ERROR;

        this.msg = msg;
    }

    public BusinessException(Integer code, Object... args) {
        this.code = code;
        this.args = args;
    }

    public BusinessException(Integer code, Throwable e) {
        super(code + "", e);
        this.code = code;
    }

    @Override
    public String getMessage() {
        if (null != this.code && StrUtil.isBlank(this.msg)) {
            return this.code + ":" + MessageUtils.getMessage(code + "", args);
        }
        return this.msg;
    }

    public Integer getCode() {
        return code;
    }

}
