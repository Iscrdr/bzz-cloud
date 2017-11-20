package com.star.cloud.core.service;

import com.star.cloud.core.Dao.TreeIdPidDao;
import com.star.cloud.core.entity.po.TreeIdPidPo;
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
public abstract class TreeIdPidService<D extends TreeIdPidDao<T>, T extends TreeIdPidPo<T>> extends CrudService<D, T> {
}