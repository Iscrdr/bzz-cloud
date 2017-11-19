package com.star.cloud.core.Dao;


import java.util.List;

/**
 * @CLASS_NAME: $CLASS_NAME$
 * @PACKAGE_NAME: com.star.cloud.core.Dao$
 * @Description:
 * @Author : yang qian li
 * @Date: 2017-11-19 21:17
 * @Modified by:
 * @Date:
 */

public interface CrudDao<T> extends BaseDao {


    /* @author: yang qianli
     * @date: 2017-11-19 21:19
     * @param:  * @param id
     * @return: T
     * @description: Get a data
     */
    public T get(String id);


    /* @author: yang qianli
     * @date: 2017-11-19 21:18
     * @param:  * @param Po
     * @return: T
     * @description: Get a data through an object
     */
    public T get(T po);


    /* @author: yang qianli
     * @date: 2017-11-19 21:20
     * @param:  po
     * @return: List<T>
     * @description: Gets a set of data through an object.
     * If you have pages, set the page objects,po.setPage(ew Page<T>());
     */
    public List<T> findList(T po);

    /* @author: yang qianli
     * @date: 2017-11-19 21:22
     * @param:  po
     * @return: List<T>
     * @description: Querying all the data of an object
     */
    public List<T> findAllList(T po);

   /* @author: yang qianli
    * @date: 2017-11-19 21:25
    * @param:  * @param null
    * @return: List<T>
    * @description: Querying all the data
    */
    public List<T> findAllList();

    /* @author: yang qianli
     * @date: 2017-11-19 21:25
     * @param:  po
     * @return: int
     * @description: Insert a data
     */
    public int insert(T po);

    /* @author: yang qianli
     * @date: 2017-11-19 21:25
     * @param:  po
     * @return: int
     * @description: update a data
     */
    public int update(T po);

    /* @author: yang qianli
     * @date: 2017-11-19 21:25
     * @param:  String id
     * @return: int
     * @description: delete a data by id
     */
    public int delete(String id);

    /* @author: yang qianli
     * @date: 2017-11-19 21:25
     * @param:  po
     * @return: int
     * @description: delete a data by po
     */
    public int delete(T po);

}
