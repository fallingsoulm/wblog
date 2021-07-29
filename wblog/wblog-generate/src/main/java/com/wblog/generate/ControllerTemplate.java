package com.wblog.generate;



import io.github.fallingsoulm.easy.archetype.framework.page.PageInfo;
import io.github.fallingsoulm.easy.archetype.framework.page.PageRequestParams;
import io.github.fallingsoulm.easy.archetype.framework.page.RespEntity;
import io.github.fallingsoulm.easy.archetype.generate.config.TemplateConfig;
import io.github.fallingsoulm.easy.archetype.generate.core.TableInfoEntity;
import io.github.fallingsoulm.easy.archetype.generate.template.AbstractTemplate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;

import java.util.Arrays;
import java.util.Map;

/**
 * controller层模板
 *
 * @author luyanan
 * @since 2021/2/28
 **/
public class ControllerTemplate extends AbstractTemplate {

    private TemplateConfig serviceTemplateConfig;

    private TemplateConfig entityVoConfig;

    private boolean swagger;


    public ControllerTemplate(TemplateConfig serviceTemplateConfig,
                              TemplateConfig entityVoConfig,
                              boolean swagger) {
        this.serviceTemplateConfig = serviceTemplateConfig;
        this.entityVoConfig = entityVoConfig;
        this.swagger = swagger;
    }

    @Override
    public void before(TableInfoEntity tableInfoEntity, TemplateConfig config, Map<String, Object> data) {
        data.put("serviceConfig", serviceTemplateConfig);
        data.put("entityVoConfig", entityVoConfig);
        data.put("swagger", swagger);
        if (swagger) {
            setImport(Api.class);
        }
        setImport(serviceTemplateConfig.getFullPkg(),
                entityVoConfig.getFullPkg());
        setImport(PageInfo.class,
                PageRequestParams.class,
                RespEntity.class,
                ApiOperation.class,
                Autowired.class,
                PreAuthorize.class,
                Validated.class,
                Arrays.class);
        String requestMapping = tableInfoEntity.getTableName().replaceAll("_", "/").toLowerCase();
        data.put("requestMapping", requestMapping);

        String permissPrefix = tableInfoEntity.getTableName().replaceAll("_", ":").toLowerCase() + ":";
        data.put("permissPrefix", permissPrefix);


    }

    @Override
    public String templatePath() {
        return GenerateConstants.TEMPLATE_PATH + "controller.ftl";
    }

    @Override
    public String fileNameFormat() {
        return "%sController";
    }

    @Override
    public String pkg() {
        return GenerateConstants.WEB + "controller";
    }
}
