package  ${classPkg};

<#list   config.imports as import >
import ${import} ;
</#list>
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
/**
 * <p>
 * ${entity.comment} controller
 * </p>
 *
 * @author ${author}
 * @since ${since}
*/
<#list  config.annotations as annotation>
 ${annotation}
</#list>
<#if swagger ==  true>
@Api(tags = "${entity.comment}")
</#if>
@RestController
@RequestMapping("${requestMapping}")
public class ${className} {
    /**
    * 权限前缀
    *
    * @since ${since}
    */
   private static final String PERMISS_PREFIX = "${requestMapping}";

    @Autowired
    private ${serviceConfig.className} ${serviceConfig.className?uncap_first};

    /**
    * 分页查询
    *
    * @param pageRequestParams 分页参数
    * @return RespEntity<PageInfo<${entityVoConfig.className}>>
    * @since ${since}
    */
    @ApiOperation(value = "分页查询", response = ${entityVoConfig.className}.class)
    @PreAuthorize("@ss.hasPermi('" + PERMISS_PREFIX + "list')")
    @PostMapping("/list")
    public RespEntity<PageInfo<${entityVoConfig.className}>> listByPage(@RequestBody PageRequestParams<${entityVoConfig.className}> pageRequestParams) {
        PageInfo<${entityVoConfig.className}> pageInfo = ${serviceConfig.className?uncap_first}.listByPage(pageRequestParams);
        return RespEntity.success(pageInfo);
    }

    /**
    * 根据id查询详情
    *
    * @param id id
    * @return RespEntity<${entityVoConfig.className}>
    * @since ${since}
    */
    @ApiOperation(value = "根据id查询详情", response = ${entityVoConfig.className}.class)
    @PreAuthorize("@ss.hasPermi('" + PERMISS_PREFIX + "query')")
    @GetMapping(value = "/{id}")
    public RespEntity<${entityVoConfig.className}> findById(@PathVariable("id") Long id) {
        ${entityVoConfig.className} ${entityVoConfig.className?uncap_first} = ${serviceConfig.className?uncap_first}.findById(id);
        return RespEntity.success(${entityVoConfig.className?uncap_first});
    }

    /**
    * 新增
    *
    * @param ${entityVoConfig.className?uncap_first} ${entityVoConfig.className?uncap_first}
    * @return RespEntity
    * @since ${since}
    */
    @ApiOperation(value = "新增")
    @PreAuthorize("@ss.hasPermi('" + PERMISS_PREFIX + "add')")
    @PostMapping()
    public RespEntity add(@Validated @RequestBody ${entityVoConfig.className} ${entityVoConfig.className?uncap_first}) {
        ${serviceConfig.className?uncap_first}.insert(${entityVoConfig.className?uncap_first});
        return RespEntity.success();
    }

    /**
    * 修改
    *
    * @param ${entityVoConfig.className?uncap_first} ${entityVoConfig.className?uncap_first}
    * @return RespEntity
    * @since ${since}
    */
    @ApiOperation(value = "修改")
    @PreAuthorize("@ss.hasPermi('" + PERMISS_PREFIX + "edit')")
    @PutMapping
    public RespEntity edit(@Validated @RequestBody ${entityVoConfig.className} ${entityVoConfig.className?uncap_first}) {
        ${serviceConfig.className?uncap_first}.update(${entityVoConfig.className?uncap_first});
        return RespEntity.success();
    }

    /**
    * 根据id集合删除
    *
    * @param ids id删除
    * @return RespEntity
    * @since ${since}
    */
    @ApiOperation(value = "删除")
    @PreAuthorize("@ss.hasPermi('" + PERMISS_PREFIX + "remove')")
    @DeleteMapping("/{ids}")
    public RespEntity remove(@PathVariable("ids") ${config.idFieldType}[] ids) {
        ${serviceConfig.className?uncap_first}.deleteByIds(Arrays.asList(ids));
        return RespEntity.success();
    }
}
