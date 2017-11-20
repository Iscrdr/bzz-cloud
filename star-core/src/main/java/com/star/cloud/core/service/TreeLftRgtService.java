package com.star.cloud.core.service;

import com.star.cloud.core.Dao.TreeLftRgtDao;
import com.star.cloud.core.entity.po.TreeLftRgtPo;
import org.springframework.transaction.annotation.Transactional;

/**
 * @PACKAGE_NAME: com.star.cloud.core.service
 * @CLASS_NAME: TreeIdPidService
 * @Description:
 * @Author : yang qianli 
 * @Date: 2017-11-21 0:17
 * @Modified by: 
 * @Date:
 */
@Transactional(readOnly = true)
public abstract class TreeLftRgtService<D extends TreeLftRgtDao<T>, T extends TreeLftRgtPo<T>> extends CrudService<D, T> {
}
