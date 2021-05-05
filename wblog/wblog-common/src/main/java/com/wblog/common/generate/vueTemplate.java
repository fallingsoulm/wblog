package com.wblog.common.generate;



import io.github.fallingsoulm.easy.archetype.generate.config.TemplateConfig;
import io.github.fallingsoulm.easy.archetype.generate.core.TableInfoEntity;
import io.github.fallingsoulm.easy.archetype.generate.template.AbstractTemplate;

import java.util.Map;

/**
 * vue页面
 *
 * @author luyanan
 * @since 2021/3/2
 **/
public class vueTemplate extends AbstractTemplate {

    private TemplateConfig entityConfig;

    private String applicationName;

    public vueTemplate(TemplateConfig entityConfig, String applicationName) {
        this.entityConfig = entityConfig;
        this.applicationName = applicationName;
    }

    @Override
    public void before(TableInfoEntity tableInfoEntity, TemplateConfig config, Map<String, Object> data) {
        String permissPrefix = tableInfoEntity.getTableName().replaceAll("_", ":").toLowerCase() + ":";
        data.put("permissPrefix", permissPrefix);
        String apiPath = applicationName + "/" + tableInfoEntity.getEntityName() + "/" + tableInfoEntity.getEntityName();
        data.put("apiPath", apiPath);
    }

    @Override
    public String templatePath() {
        return GenerateConstants.TEMPLATE_PATH + "vue.ftl";
    }

    @Override
    public String fileNameFormat() {
        return "%s";
    }

    @Override
    public String fileNameSuffix() {
        return ".vue";
    }

    @Override
    public String pkg() {
        return "vue" + "/" + applicationName + "/" + tableInfoEntity.getEntityName();
    }
}
