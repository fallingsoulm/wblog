package com.wblog.generate;



import io.github.fallingsoulm.easy.archetype.data.manage.IManage;
import io.github.fallingsoulm.easy.archetype.generate.config.TemplateConfig;
import io.github.fallingsoulm.easy.archetype.generate.core.TableInfoEntity;

import java.util.Map;

/**
 * manage模板
 *
 * @author luyanan
 * @since 2021/2/3
 **/
public class ManageTemplate extends io.github.fallingsoulm.easy.archetype.generate.ext.simple.ManageTemplate {

    /**
     * 实体类模板配置
     *
     * @since 2021/2/3
     */
    private TemplateConfig entityConfig;

    public ManageTemplate(TemplateConfig entityConfig) {
        this.entityConfig = entityConfig;
    }

    @Override
    public void before(TableInfoEntity tableInfoEntity, TemplateConfig config, Map<String, Object> data) {
        data.put("entityConfig", entityConfig);
        setImport(entityConfig.getFullPkg());
        setImport(IManage.class);
    }

    @Override
    public String templatePath() {
        return GenerateConstants.TEMPLATE_PATH + "manage.ftl";
    }

    @Override
    public String pkg() {
        return GenerateConstants.WEB + super.pkg();
    }
}
