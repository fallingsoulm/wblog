package com.wblog.common.generate;


import io.github.fallingsoulm.easy.archetype.data.mybatisplus.BaseMapperPlus;
import io.github.fallingsoulm.easy.archetype.generate.config.TemplateConfig;
import io.github.fallingsoulm.easy.archetype.generate.core.TableInfoEntity;

import java.util.Map;

/**
 * Mapper文件模板
 *
 * @author luyanan
 * @since 2021/2/3
 **/
public class MapperTemplate extends io.github.fallingsoulm.easy.archetype.generate.ext.simple.MapperTemplate {

    public MapperTemplate(TemplateConfig entityConfig, boolean mybatisPlus) {
        super(entityConfig, mybatisPlus);
    }

    public MapperTemplate(TemplateConfig entityConfig) {
        super(entityConfig);
    }

    @Override
    public void before(TableInfoEntity tableInfoEntity, TemplateConfig config, Map<String, Object> data) {
        super.before(tableInfoEntity, config, data);
        setImport(BaseMapperPlus.class);
    }

    @Override
    public String templatePath() {
        return GenerateConstants.TEMPLATE_PATH + "mapper.ftl";
    }

    @Override
    public String pkg() {
        return GenerateConstants.WEB + super.pkg();
    }
}
