package  ${classPkg};

<#list   config.imports as import >
    import ${import} ;
</#list>
import java.util.List;
import java.io.Serializable;
/**
 * <p>
 * ${entity.comment} manage
 * </p>
 *
 * @author ${author}
 * @since ${since}
*/
<#list  config.annotations as annotation>
${annotation}
</#list>
public interface ${className} extends IManage<${entityConfig.className}> {


}
