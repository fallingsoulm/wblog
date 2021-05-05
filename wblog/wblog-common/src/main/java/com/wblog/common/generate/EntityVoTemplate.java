package com.wblog.common.generate;


import io.github.fallingsoulm.easy.archetype.generate.ext.simple.EntityTemplate;

/**
 * Vo类模板
 *
 * @author luyanan
 * @since 2021/2/28
 **/
public class EntityVoTemplate extends EntityTemplate {

    public EntityVoTemplate(boolean swagger) {
        super(swagger, false);
    }


    @Override
    public String fileNameFormat() {
        return "%sVo";
    }

    @Override
    public String pkg() {
        return "api.vo";
    }
}
