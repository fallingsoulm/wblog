package com.wblog.generate;


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
    public String module() {
        return "common.module." + super.module();
    }

    @Override
    public String pkg() {
        return "vo";
    }
}
