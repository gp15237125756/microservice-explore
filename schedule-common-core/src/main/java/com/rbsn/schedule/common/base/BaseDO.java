package com.rbsn.schedule.common.base;

import java.io.Serializable;
import java.util.Date;

/**
 * 基础实体类
 */
public class BaseDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 删除标记（0：正常；1：删除；2：审核；）
     */
    public static final String DEL_FLAG_NORMAL = "0";
    public static final String DEL_FLAG_DELETE = "1";

    private String id;
    private String remark;	// 备注
    private Date createDate;	// 创建日期
    private Date updateDate;	// 更新日期
    private String delFlag; 	// 删除标记（0：正常；1：删除）

    private String orderBy;   //排序字段名称

    public BaseDO() {
        this.delFlag = DEL_FLAG_NORMAL;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    @Override
    public String toString() {
        return "id='" + id + '\'' +
                ", remarks='" + remark + '\'' +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                ", delFlag='" + delFlag + '\'' +
                ", orderBy='" + orderBy + '\'';
    }
}
