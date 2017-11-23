package com.star.cloud.framework.DBSource;

/**
 * @PACKAGE_NAME: com.star.cloud.core.DBSource
 * @CLASS_NAME: StarDynamicDataSourceHolder
 * @Description:
 * @Author : yang qianli
 * @Date: 2017-11-21 1:15
 * @Modified by:
 * @Date:
 */
public class StarDynamicDataSourceHolder {


    //Use ThreadLocal to record the data source key of the current thread
    private  static  final ThreadLocal<String> holder = new ThreadLocal<String>();

    /* @author: yang qianli
     * @date: 2017-11-21 1:19
     * @param:  * @param null
     * @return:   void
     * @description: Set key when accessing the data source
     */
    public  static  void putDataSourceKey(String key) {
        holder.set(key);
    }

    /* @author: yang qianli
     * @date: 2017-11-21 1:21
     * @param:  * @param null
     * @return:   String
     * @description: Gets access to the data source
     */
    public  static String getDataSourceKey() {
        return  holder.get();
    }


    /* @author: yang qianli
     * @date: 2017-11-21 1:44
     * @param:  * @param null
     * @return:
     * @description: clear holder
     */
    public static void clear() {
        holder.remove();
    }


}
