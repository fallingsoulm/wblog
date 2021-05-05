package  ${classPkg};

<#list  config.imports as import >
import ${import} ;
</#list>
/**
 * <p>
 * ${entity.comment} mapper
 * </p>
 *
 * @author ${author}
 * @since ${since}
*/
<#list  config.annotations as annotation>
${annotation}
</#list>
<#if  mybatisPlus ==  true>
public interface ${className} extends BaseMapperPlus<${entityConfig.className}> {
<#else >
public interface ${className}{
</#if>


}
