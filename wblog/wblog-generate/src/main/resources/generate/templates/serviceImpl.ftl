package  ${classPkg};

<#list   config.imports as import >
import ${import} ;
</#list>
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * ${entity.comment} serviceImpl
 * </p>
 *
 * @author ${author}
 * @since ${since}
*/
<#list  config.annotations as annotation>
${annotation}
</#list>
@Service
public class ${className}  implements ${serviceConfig.className} {


   @Autowired
   private ${manageConfig.className} ${manageConfig.className?uncap_first};

    @Override
    public PageInfo<${entityVoConfig.className}> listByPage(PageRequestParams<${entityVoConfig.className}> pageRequestParams) {
        PageRequestParams<${entityConfig.className}> requestParams = ConverUtils.converPageRequestParams(pageRequestParams, ${entityConfig.className}.class);
        PageInfo<${entityConfig.className}> pageInfo = ${manageConfig.className?uncap_first}.listByPage(requestParams);
        return ConverUtils.converPageInfo(pageInfo, ${entityVoConfig.className}.class);
    }

    @Override
    public List<${entityVoConfig.className}> list(${entityVoConfig.className} ${entityVoConfig.className?uncap_first}) {
        ${entityConfig.className} ${entityConfig.className?uncap_first} = ConverUtils.conver(${entityVoConfig.className?uncap_first}, ${entityConfig.className}.class);
        List<${entityConfig.className}> list = ${manageConfig.className?uncap_first}.list(${entityConfig.className?uncap_first});
        return ConverUtils.listConver(list, ${entityVoConfig.className}.class);
    }

    @Override
    public ${entityVoConfig.className} findById(Long id) {
        ${entityConfig.className} ${entityConfig.className?uncap_first} = ${manageConfig.className?uncap_first}.findById(id);
        return ConverUtils.conver(${entityConfig.className?uncap_first}, ${entityVoConfig.className}.class);
    }

    @Override
    public void insert(${entityVoConfig.className} ${entityVoConfig.className?uncap_first}) {
        ${entityConfig.className} ${entityConfig.className?uncap_first} = ConverUtils.conver(${entityVoConfig.className?uncap_first}, ${entityConfig.className}.class);
        ${manageConfig.className?uncap_first}.insert(${entityConfig.className?uncap_first});
    }

    @Override
    public void update(${entityVoConfig.className} ${entityVoConfig.className?uncap_first}) {
        ${entityConfig.className} ${entityConfig.className?uncap_first} = ConverUtils.conver(${entityVoConfig.className?uncap_first}, ${entityConfig.className}.class);
        ${manageConfig.className?uncap_first}.update(${entityConfig.className?uncap_first});
    }

    @Override
    public void deleteByIds(Collection<${config.idFieldType}> ids) {
        ${manageConfig.className?uncap_first}.deleteBatch(ids);
    }

}