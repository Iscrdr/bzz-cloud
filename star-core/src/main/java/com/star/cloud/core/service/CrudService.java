package com.star.cloud.core.service;

import com.star.cloud.core.Dao.CrudDao;
import com.star.cloud.core.entity.po.CommonPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @PACKAGE_NAME: com.star.cloud.core.service
 * @CLASS_NAME: CrudService
 * @Author : yang qianli
 * @Date: 2017-11-19 21:49
 * @Modified by:
 * @Date:
 *
 * @Description:
 */
@Transactional(readOnly = true)
public class CrudService <D extends CrudDao< T>, T extends CommonPo<T>> extends BaseService {


    /* @author: yang qianli
     * @date: 2017-11-19 21:50
     * @description: Persistent layer object
     */
    @Autowired
    protected D dao;


    /* @author: yang qianli
     * @date: 2017-11-19 21:42
     * @param:  String id
     * @return:   T
     * @description: Querying a data through  T.id
     */
    public T get(String id) {
        return dao.get(id);
    }

    /* @author: yang qianli
     * @date: 2017-11-19 21:42
     * @param:  T
     * @return:   T
     * @description: Querying a data
     */
    public T get(T entity) {
        return dao.get(entity);
    }

    /* @author: yang qianli
     * @date: 2017-11-19 21:44
     * @param:  T
     * @return:  List<T>
     * @description: Query a set of data through an object
     */
    public List<T> findList(T po) {
        return dao.findList(po);
    }


    /*public Page<T> findPage(Page<T> page, T entity) {
        entity.setPage(page);
        page.setList(dao.findList(entity));
        return page;
    }*/


    /* @author: yang qianli
     * @date: 2017-11-19 21:45
     * @param:  * @param null
     * @return:   void
     * @description: insert or update an object,set Transactional(readOnly = false)
     */
    @Transactional(readOnly = false)
    public  int save(T po) {
        int result ;
        if (po.isNew()){
            po.preInsert();
            result = dao.insert(po);
        }else{
            po.preUpdate();
            result =dao.update(po);
        }
        return result;
    }

    /* @author: yang qianli
     * @date: 2017-11-19 21:47
     * @param:  * @param null
     * @return:   void
     * @description: delete an object,set Transactional(readOnly = false)
     */
    @Transactional(readOnly = false)
    public int delete(T po) {
        return dao.delete(po);
    }

}
