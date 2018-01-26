package com.drp.vo;

import java.util.List;

public class ProductResultVO {

    private List<SpuSearchVO> productList;

    private Integer pageCount;

    private Integer count;

    private Integer pageTotal;

    private Integer pageSize;

    private Integer currentPage;

    private List<Integer> brandIds;

    private List<String> firstLetters;

    public List<SpuSearchVO> getProductList() {
        return productList;
    }

    public void setProductList(List<SpuSearchVO> productList) {
        this.productList = productList;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public List<Integer> getBrandIds() {
        return brandIds;
    }

    public void setBrandIds(List<Integer> brandIds) {
        this.brandIds = brandIds;
    }

    public List<String> getFirstLetters() {
        return firstLetters;
    }

    public void setFirstLetters(List<String> firstLetters) {
        this.firstLetters = firstLetters;
    }
}
