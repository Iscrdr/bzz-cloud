package ${package_name}.web;


import ${package_name}.entity.${table_first_upper_name};
import ${package_name}.service.${table_first_upper_name}Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;



/**
 * @PACKAGE_NAME: ${package_name}.web
 * @CLASS_NAME: ${table_first_upper_name}Controller
 * @Description:
 * @Author : ${author}
 * @Date: ${date}
 * @Modified by:
 * @Date:
 */
@RestController
public class ${table_first_upper_name}Controller {

    @Resource
    private ${table_first_upper_name}Service ${table_first_lower_name}Service;

    @GetMapping(value = "/${table_lower_case_name}/list")
    public String list(){
        return ${table_first_lower_name}Service.findAllList();
    }


}