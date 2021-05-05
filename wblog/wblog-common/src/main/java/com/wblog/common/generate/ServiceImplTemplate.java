package com.wblog.common.generate;


import com.wblog.common.utils.ConverUtils;
import io.github.fallingsoulm.easy.archetype.framework.page.PageInfo;
import io.github.fallingsoulm.easy.archetype.framework.page.PageRequestParams;
import io.github.fallingsoulm.easy.archetype.generate.config.TemplateConfig;
import io.github.fallingsoulm.easy.archetype.generate.core.TableInfoEntity;
import io.github.fallingsoulm.easy.archetype.generate.template.AbstractTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * service实现层模板
 *
 * @author luyanan
 * @since 2021/2/28
 **/
public class ServiceImplTemplate extends AbstractTemplate {

    private TemplateConfig entityTemplateConfig;

    private TemplateConfig entityVoTemplateConfig;
    private TemplateConfig serviceTemplateConfig;

    private TemplateConfig manageTemplateConfig;

    public ServiceImplTemplate(TemplateConfig entityTemplateConfig,
                               TemplateConfig entityVoTemplateConfig,
                               TemplateConfig serviceTemplateConfig,
                               TemplateConfig manageTemplateConfig) {
        this.entityTemplateConfig = entityTemplateConfig;
        this.entityVoTemplateConfig = entityVoTemplateConfig;
        this.serviceTemplateConfig = serviceTemplateConfig;
        this.manageTemplateConfig = manageTemplateConfig;
    }

    @Override
    public void before(TableInfoEntity tableInfoEntity, TemplateConfig config, Map<String, Object> data) {
        data.put("entityConfig", entityTemplateConfig);
        data.put("entityVoConfig", entityVoTemplateConfig);
        data.put("serviceConfig", serviceTemplateConfig);
        data.put("manageConfig", manageTemplateConfig);
        setImport(entityTemplateConfig.getFullPkg(),
                serviceTemplateConfig.getFullPkg(),
                entityVoTemplateConfig.getFullPkg(),
                manageTemplateConfig.getFullPkg());
        setImport(PageInfo.class,
                PageRequestParams.class,
                List.class,
                Collection.class,
                ConverUtils.class,
                Autowired.class,
                Service.class);
    }

    @Override
    public String templatePath() {
        return GenerateConstants.TEMPLATE_PATH + "serviceImpl.ftl";
    }

    @Override
    public String fileNameFormat() {
        return "%sServiceImpl";
    }

    @Override
    public String pkg() {
        return GenerateConstants.WEB + "service.impl";
    }
}
