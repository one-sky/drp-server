package com.drp.Util;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PageModel<T> {

    private Integer pageNum;

    private Integer pages;

    private Integer total;

    private Integer pageSize;


    //构造方法中将pageNum，pageSize，total获得
    public PageModel(List<T> data, Integer pageNum, Integer pageSize) {
        setPageNum(pageNum);
        setPageSize(pageSize);
        setTotal(data.size());
        //pages 总页数
        if (total % pageSize == 0) {
            //说明整除，正好每页显示pageSize条数据，没有多余一页要显示少于pageSize条数据的
            setPages(total / pageSize);
        } else {
            //不整除，就要在加一页，来显示多余的数据。
            setPages(total / pageSize + 1);
        }
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

}
