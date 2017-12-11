package com.star.cloud.core.entity.po;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * @PACKAGE_NAME: com.star.cloud.core.entity.po
 * @CLASS_NAME: BasePo
 * @Description: JavaBean base class
 * @Author : yang qianli
 * @Date: 2017-11-19 17:21
 * @Modified by: 
 */
public abstract class BasePo <T> implements Serializable {


    private static final long serialVersionUID = 2829748089686572974L;
    /*
     * PO id Bean (unique)
     */
    private String id;
    /*
     * Is it the newly inserted data
     * default:false
     * The force is set to the newly inserted data : setIsNew(true)
     */
    private boolean isNew = false;

    /*
     * Is this data available?
     * 0：yes；1：no；
     */
    public static final Integer DEL_FLAG_TRUE = 0;
    public static final Integer DEL_FLAG_FLASE = 1;



    public BasePo() {

    }

    public BasePo(String id) {
        this();
        this.id = id;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }


    public boolean isNew() {
        return isNew || StringUtils.isBlank(getId());
    }
    /*
     * isNew = true , the force is set to the newly inserted data.
     * Using a custom Id,ID is not automatically generated.
     * It needs to be manually transmitted.
     *
     * @param isNew
     */
    public void setNew(boolean newIs) {
        isNew = newIs;
    }


    /* @author: yang qianli
     * @date: 2017-11-19 17:49
     * @param:  * @param
     * @return: void
     * @description: Subclass inheritance, executed before insertion
     */
    public abstract void preInsert();


   /* @author: yang qianli
    * @date: 2017-11-19 17:49
    * @param:  * @param
    * @return: void
    * @description:Subclass inheritance, executed before the update
    */
    public abstract void preUpdate();
}
