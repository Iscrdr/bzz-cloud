package ${package_name}.service;

import com.bzz.cloud.core.service.CrudService;
import ${package_name}.entity.${table_first_upper_name};;
import ${package_name}.dao.${table_first_upper_name}Dao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @PACKAGE_NAME: ${package_name}.service
 * @CLASS_NAME: ${table_first_upper_name}Service
 * @Description:
 * @Author : ${author}
 * @Date: ${date}
 * @Modified by:
 * @Date:
 */
@Service
@Transactional
public class ${table_first_upper_name}Service extends CrudService<${table_first_upper_name}Dao,${table_first_upper_name}> {


}