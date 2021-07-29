package com.wblog.generate;

import cn.hutool.core.util.StrUtil;
import io.github.fallingsoulm.easy.archetype.generate.config.TemplateConfig;
import io.github.fallingsoulm.easy.archetype.generate.core.TableInfoEntity;
import io.github.fallingsoulm.easy.archetype.generate.template.AbstractTemplate;

import java.util.Map;

/**
 * 前端vue框架API的模板
 *
 * @author luyanan
 * @since 2021/2/27
 **/
public class VueApiTemplate extends AbstractTemplate {

    private TemplateConfig entityConfig;

    /**
     * 项目路径
     *
     * @since 2021/3/1
     */
    private String applicationContextPath;

    public VueApiTemplate(TemplateConfig entityConfig, String applicationContextPath) {
        this.entityConfig = entityConfig;
        this.applicationContextPath = applicationContextPath;
    }

    @Override
    public void before(TableInfoEntity tableInfoEntity, TemplateConfig config, Map<String, Object> data) {
        String requestMapping = tableInfoEntity.getTableName().replaceAll("_", "/").toLowerCase();
        data.put("pathPrefix", StrUtil.isNotBlank(applicationContextPath) ?
                (applicationContextPath + "/" + requestMapping).replace("/+", "/") : requestMapping);


    }

    @Override
    public String templatePath() {
        return GenerateConstants.TEMPLATE_PATH + "vueApi.ftl";
    }

    @Override
    public String fileNameFormat() {
        return "%s";
    }

    @Override
    public String fileNameSuffix() {
        return ".js";
    }

    @Override
    public String pkg() {
        return "vue" + "/" + applicationContextPath + "/" + tableInfoEntity.getEntityName();
    }
}
