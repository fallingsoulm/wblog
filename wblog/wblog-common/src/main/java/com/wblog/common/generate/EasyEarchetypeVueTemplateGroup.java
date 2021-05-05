package com.wblog.common.generate;


import io.github.fallingsoulm.easy.archetype.generate.config.TemplateConfig;
import io.github.fallingsoulm.easy.archetype.generate.ext.simple.EntityTemplate;
import io.github.fallingsoulm.easy.archetype.generate.ext.simple.MapperXmlTemplate;
import io.github.fallingsoulm.easy.archetype.generate.template.AbstractTemplateGroup;
import io.github.fallingsoulm.easy.archetype.generate.template.ITemplate;
import io.github.fallingsoulm.easy.archetype.generate.template.TemplateBuilder;

import java.util.List;

/**
 * 前后端分离的模板组
 *
 * @author luyanan
 * @since 2021/2/3
 **/
public class EasyEarchetypeVueTemplateGroup extends AbstractTemplateGroup {

    /**
     * swagger开关
     *
     * @since 2021/2/2
     */
    private boolean swagger = false;

    /**
     * mybatis plus 开关
     *
     * @since 2021/2/2
     */
    private boolean mybatisPlus = false;

    public EasyEarchetypeVueTemplateGroup(boolean swagger, boolean mybatisPlus) {
        this.swagger = swagger;
        this.mybatisPlus = mybatisPlus;
    }

    @Override
    public String name() {
        return this.getClass().getName();
    }

    @Override
    public void registryTemplateList(List<ITemplate> templates, TemplateBuilder templateBuilder) {

        String applicationName = "wblog";
        ITemplate entityTemplate = templateBuilder.build(new EntityTemplate(swagger, mybatisPlus) {
            @Override
            public String pkg() {
                return GenerateConstants.WEB + super.pkg();
            }
        });
        TemplateConfig entityConfig = entityTemplate.config();
        templates.add(entityTemplate);

        // vo类
        ITemplate entityVoTemplate = templateBuilder.build(new EntityVoTemplate(swagger));
        TemplateConfig entityVoConfig = entityVoTemplate.config();
        templates.add(entityVoTemplate);
        // Mapper文件
        ITemplate mapperTemplate = templateBuilder.build(new MapperTemplate(entityConfig, mybatisPlus));
        templates.add(mapperTemplate);
        TemplateConfig mapperConfig = mapperTemplate.config();
        // mapper xml
        ITemplate mapperXmlTemplate = templateBuilder.build(new MapperXmlTemplate(entityConfig, mapperConfig));
        templates.add(mapperXmlTemplate);
        // manage层 模板
        ITemplate manageTemplate = templateBuilder.build(new ManageTemplate(entityConfig));
        TemplateConfig manageConfig = manageTemplate.config();
        templates.add(manageTemplate);
        ITemplate manageImplTemplate = templateBuilder
                .build(new ManageImplTemplate(mybatisPlus, entityConfig, mapperConfig, manageConfig));
        templates.add(manageImplTemplate);
        // service层
        ITemplate serviceTemplate = templateBuilder.build(new ServiceTemplate(entityVoConfig));
        TemplateConfig serviceConfig = serviceTemplate.config();
        templates.add(serviceTemplate);
        // ServiceImpl实现层
        ITemplate serviceImplTemplate = templateBuilder.build(new ServiceImplTemplate(entityConfig, entityVoConfig,
                serviceConfig, manageConfig));
        templates.add(serviceImplTemplate);
        // controller层
        ITemplate controllerTemplate = templateBuilder.build(new ControllerTemplate(serviceConfig, entityVoConfig, swagger));
        templates.add(controllerTemplate);

        // vue
        ITemplate vueApiTemplate = templateBuilder.build(new VueApiTemplate(entityConfig, applicationName));
        templates.add(vueApiTemplate);

        ITemplate vueTemplate = templateBuilder.build(new vueTemplate(entityConfig, applicationName));
        templates.add(vueTemplate);
    }

}
