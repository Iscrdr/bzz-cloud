package ${package_name}.entity;
import com.star.cloud.core.entity.po.CommonPo;
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
        <#if (entity.columnType?lower_case)  == 'date' || (entity.columnType?lower_case)  == 'datetime' || (entity.columnType?lower_case)  == 'timestamp'>
    private Date ${entity.reflectedColumnName?uncap_first};
        </#if>
        <#if ((entity.columnType?lower_case) == 'varchar' || (entity.columnType?lower_case)  == 'char' || (entity.columnType?lower_case)  == 'text')>
    private String ${entity.reflectedColumnName?uncap_first};
        </#if>
        <#if ((entity.columnType?lower_case)  == 'int') >
    private Integer ${entity.reflectedColumnName?uncap_first};
        </#if>
        <#if ((entity.columnType?lower_case)  == 'float') >
    private Float ${entity.reflectedColumnName?uncap_first};
        </#if>
        <#if ((entity.columnType?lower_case)  == 'double') >
    private Double ${entity.reflectedColumnName?uncap_first};

        </#if>

    </#list>
</#if>



}