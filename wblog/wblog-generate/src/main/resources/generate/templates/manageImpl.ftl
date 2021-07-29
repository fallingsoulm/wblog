package  ${classPkg};

<#list   config.imports as import >
import ${import} ;
</#list>
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * ${entity.comment} manageImpl
 * </p>
 *
 * @author ${author}
 * @since ${since}
*/
<#list  config.annotations as annotation>
${annotation}
</#list>
@Service
<#if  mybatisPlus ==  true>
public class ${className} extends ManageImpl<${mapperConfig.className}, ${entityConfig.className}> implements ${manageConfig.className} {
<#else >
public class ${className}  implements ${manageConfig.className}{
</#if>

<#if  mybatisPlus ==  false>
    @Autowired
    private ${mapperConfig.className} ${mapperConfig.className?uncap_first};
</#if>
}
