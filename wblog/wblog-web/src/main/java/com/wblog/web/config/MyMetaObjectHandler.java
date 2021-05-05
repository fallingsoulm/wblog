package com.wblog.web.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import io.github.fallingsoulm.easy.archetype.security.core.LoginUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * MybatsPlus 字段填充
 *
 * @author luyanan
 * @since 2021/2/15
 **/
@Component
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Autowired
    private LoginUserService loginUserService;

    @Override
    public void insertFill(MetaObject metaObject) {

        log.debug("插入字段填充:{}", metaObject.getOriginalObject());
        Long userId = loginUserService.getUserId();
        this.strictInsertFill(metaObject, "createTime", Date.class, new Date());
        this.strictInsertFill(metaObject, "createBy", Long.class, userId);
        this.strictInsertFill(metaObject, "updateTime", Date.class, new Date());
        this.strictInsertFill(metaObject, "updateBy", Long.class, userId);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.debug("修改字段填充:{}", metaObject.getOriginalObject());
        Long userId = loginUserService.getUserId();
        this.strictUpdateFill(metaObject, "updateTime", Date.class, new Date());
        this.strictUpdateFill(metaObject, "updateBy", Long.class, userId);
    }
}
