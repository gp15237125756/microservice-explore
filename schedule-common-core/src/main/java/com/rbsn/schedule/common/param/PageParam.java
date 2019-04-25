package com.rbsn.schedule.common.pojo.param;

import com.rbsn.schedule.common.util.PropertiesUtil;
import org.apache.commons.lang.StringUtils;

import java.io.Serializable;

/**
 * 分页参数类
 */
public class PageParam implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 默认为第一页.
     */
    public static final int DEFAULT_PAGE_NUM = 1;

    /**
     * 默认每页记录数(20).
     */
    public static final int DEFAULT_NUM_PER_PAGE = PropertiesUtil.getInteger("page.size");

    /**
     * 最大每页记录数(100).
     */
    public static final int MAX_PAGE_SIZE = 10000;

    /**
     * 当前页数
     */
    private Integer pageNum = DEFAULT_PAGE_NUM;

    /**
     * 每页记录数
     */
    private Integer pageSize; // 每页记录数

    /**
     * 排序
     */
    private String orderBy;

    /**
     * 默认构造函数
     */
    public PageParam(){}

    /**
     * 带参数的构造函数
     * @param pageNum
     * @param pageSize
     */
    public PageParam(int pageNum , int pageSize){
    	this.pageNum = pageNum;
    	this.pageSize = pageSize;
    }

    public PageParam(int pageNum , int pageSize, String orderBy){
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.orderBy = orderBy;
    }

    public Integer getPageNum() {
        return pageNum !=null && pageNum > 0 ?pageNum : DEFAULT_PAGE_NUM;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize != null && pageSize > 0 ? pageSize : DEFAULT_NUM_PER_PAGE;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrderBy() {
        return StringUtils.isNotBlank(orderBy) ? orderBy : null;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    @Override
    public String toString() {
        return "PageParam{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", orderBy='" + orderBy + '\'' +
                '}';
    }
}
