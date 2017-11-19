package com.star.cloud.core.entity.po;

import com.star.common.Utils.IdUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

/**
 * @PACKAGE_NAME: com.star.cloud.core.entity.po
 * @CLASS_NAME: CommonPo
 * @Description:
 * @Author : yang qianli 
 * @Date: 2017-11-19 20:55
 * @Modified by: 
 * @Date:
 */

public abstract class CommonPo <T> extends BasePo<T> {

    protected T createUser;	// 创建人
    protected Date createDate;	// 创建日期
    protected T updateUser;	// 最后更新人
    protected Date updateDate;	// 最后更新日期
    protected Integer delFlag; 	// 删除标记（0：正常；1：删除；2：审核）
    protected String remarks;	// 备注


    public CommonPo() {
        this.delFlag = DEL_FLAG_TRUE ;
    }

    public CommonPo(String id) {
        super(id);
    }

    public T getCreateUser() {
        return createUser;
    }

    public void setCreateUser(T createUser) {
        this.createUser = createUser;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public T getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(T updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }


    @Override
    public void preInsert() {
       /* // 不限制ID为UUID，调用setIsNewRecord()使用自定义ID
        if (!this.isNew()){
            if(StringUtils.isBlank(this.id)){
                setId(IdUtils.uuid());
            }
        }
       // User user = UserUtils.getUser();
        if (StringUtils.isNotBlank(user.getId())){
            this.updateBy = user;
            this.createBy = user;
        }
        this.updateDate = new Date();
        this.createDate = this.updateDate;*/
        this.createUser = null;
        this.updateUser = null;

        this.updateDate = new Date();
        this.createDate = this.updateDate;
    }

    @Override
    public void preUpdate() {
        this.updateDate = new Date();
        this.createDate = this.updateDate;

    }
}
