package ${package_name}.entity;
import com.bzz.cloud.core.entity.po.CommonPo;
import lombok.*;



/**
 * @CLASS_NAME: ${table_name}
 * @PACKAGE_NAME: ${package_name}.entity
 * @Author : ${author}
 * @Date: ${date}
 * @Modified by:
 * @Date:
 * @Description:
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ${table_name} extends CommonPo<${table_name}> {

<#if entity_column?exists>
    <#list entity_column as entity>
    /**
    *${entity.columnComment!}
    */
        <#if entity.columnType = 'date' || entity.columnType = 'datatime' || entity.columnType = 'timestamp'>
    private Date ${entity.reflectedColumnName};
        </#if>

        <#if (entity.columnType = 'varchar' || entity.columnType = 'char' || entity.columnType = 'text')>
    private String ${entity.reflectedColumnName};
        </#if>
        <#if (entity.columnType = 'int') >
    private Integer ${entity.reflectedColumnName};
    </#if>
        <#if (entity.columnType = 'float') >
    private Float ${entity.reflectedColumnName};
        </#if>
        <#if (entity.columnType = 'double') >
    private Double ${entity.reflectedColumnName};
        </#if>
    </#list>
</#if>



}