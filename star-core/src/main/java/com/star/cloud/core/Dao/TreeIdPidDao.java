package com.star.cloud.core.dao;

import com.star.cloud.core.entity.po.TreeIdPidPo;

import java.util.List;

/**
 * @PACKAGE_NAME: com.star.cloud.core.Dao
 * @CLASS_NAME: TreeIdPidDao
 * @Description: Tree Data CRUD
 * @Author : yang qianli
 * @Date: 2017-11-20 23:44
 * @Modified by:
 * @Date:
 */
public interface TreeIdPidDao<T extends TreeIdPidPo<T>> extends CrudDao<T> {


    /* @author: yang qianli
     * @date: 2017-12-09 19:16  
     * @param:  T
     * @return:   
     * @description:
     */
    public List<T> findByPidsLike(T entity);

   /* @author: yang qianli
    * @date: 2017-12-09 19:17  
    * @param:  T
    * @return:   
    * @description:
    */ 
    public int updatePids(T entity);




}
