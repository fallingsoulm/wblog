package  ${classPkg};

<#list   config.imports as import >
import ${import} ;
</#list>
/**
 *
 * ${entity.comment} service
 *
 *
 * @author ${author}
 * @since ${since}
*/
<#list  config.annotations as annotation>
${annotation}
</#list>
public interface ${className}  {

/**
* 分页查询
*
* @param pageRequestParams 分页参数
* @return PageInfo<${entityVoConfig.className}>
 * @since ${since}
 */
 PageInfo<${entityVoConfig.className}> listByPage(PageRequestParams<${entityVoConfig.className}> pageRequestParams);


   /**
   * 根据条件查询
   *
   * @param ${entityVoConfig.className?uncap_first} 根据条件查询
   * @return java.util.List<${entityVoConfig.className}>
    * @since ${since}
    */
    List<${entityVoConfig.className}> list(${entityVoConfig.className} ${entityVoConfig.className?uncap_first});

     /**
     * 根据id查询
     *
     * @param id id
     * @return ${entityVoConfig.className}
     * @since ${since}
     */
     ${entityVoConfig.className} findById(Long id);

     /**
     * 添加
     *
     * @param ${entityVoConfig.className?uncap_first} 实体
     * @return void
     * @since ${since}
     */
     void insert(${entityVoConfig.className} ${entityVoConfig.className?uncap_first});


     /**
     * 根据id修改
     *
     * @param ${entityVoConfig.className?uncap_first} 需要修改的对象
     * @return void
     * @since ${since}
     */
     void update(${entityVoConfig.className} ${entityVoConfig.className?uncap_first});


     /**
     * 根据id集合删除
     *
     * @param ids id集合
     * @return void
     * @since ${since}
     */
     void deleteByIds(Collection<${config.idFieldType}> ids);

}