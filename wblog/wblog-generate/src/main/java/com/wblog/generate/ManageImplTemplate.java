package com.wblog.generate;


import io.github.fallingsoulm.easy.archetype.data.manage.impl.ManageImpl;
import io.github.fallingsoulm.easy.archetype.generate.config.TemplateConfig;
import io.github.fallingsoulm.easy.archetype.generate.core.TableInfoEntity;

import java.util.Map;

/**
 * Manage实现类模板
 *
 * @author luyanan
 * @since 2021/2/3
 **/

public class ManageImplTemplate extends io.github.fallingsoulm.easy.archetype.generate.ext.simple.ManageImplTemplate {

    public ManageImplTemplate(boolean mybatisPlus, TemplateConfig entityConfig, TemplateConfig mapperConfig,
                              TemplateConfig manageConfig) {
        super(mybatisPlus, entityConfig, mapperConfig, manageConfig);
    }

    @Override
    public void before(TableInfoEntity tableInfoEntity, TemplateConfig config, Map<String, Object> data) {
        super.before(tableInfoEntity, config, data);
        setImport(ManageImpl.class);
    }

    @Override
    public String templatePath() {
        return GenerateConstants.TEMPLATE_PATH + "manageImpl.ftl";
    }

    @Override
    public String pkg() {
        return GenerateConstants.WEB + super.pkg();
    }
}
