package com.star.cloud.core.DBSource;

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

    //The data source writes the corresponding KEY
    private  static  final String WRITER = "writer";
    //The data source reads the corresponding KEY
    private  static  final String READER = "reader";
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
     * @date: 2017-11-21 1:22
     * @param:  * @param null
     * @return: void
     * @description: set WRITER mark when accessing the data source
     */
    public  static  void writerMark(){
        putDataSourceKey(WRITER);
    }

    /* @author: yang qianli
     * @date: 2017-11-21 1:25
     * @param:  * @param null
     * @return:   void
     * @description: set READER mark when accessing the data source
     */
    public  static  void readerMark(){
        putDataSourceKey(READER);
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
