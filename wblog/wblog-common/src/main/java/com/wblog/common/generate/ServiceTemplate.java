package com.wblog.common.generate;




import io.github.fallingsoulm.easy.archetype.framework.page.PageInfo;
import io.github.fallingsoulm.easy.archetype.framework.page.PageRequestParams;
import io.github.fallingsoulm.easy.archetype.generate.config.TemplateConfig;
import io.github.fallingsoulm.easy.archetype.generate.core.TableInfoEntity;
import io.github.fallingsoulm.easy.archetype.generate.template.AbstractTemplate;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * service层模板
 *
 * @author luyanan
 * @since 2021/2/28
 **/
public class ServiceTemplate extends AbstractTemplate {

	private TemplateConfig entityVoTemplateConfig;

	public ServiceTemplate(TemplateConfig entityVoTemplateConfig) {
		this.entityVoTemplateConfig = entityVoTemplateConfig;
	}

	@Override
	public void before(TableInfoEntity tableInfoEntity, TemplateConfig config, Map<String, Object> data) {

		data.put("entityVoConfig", entityVoTemplateConfig);
		setImport(entityVoTemplateConfig.getFullPkg());
		setImport(PageInfo.class, PageRequestParams.class, List.class, Collection.class);
	}

	@Override
	public String templatePath() {
		return GenerateConstants.TEMPLATE_PATH + "service.ftl";
	}

	@Override
	public String fileNameFormat() {
		return "I%sService";
	}

	@Override
	public String pkg() {
		return GenerateConstants.WEB +"service";
	}
}
