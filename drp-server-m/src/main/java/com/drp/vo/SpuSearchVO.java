package com.drp.vo;

import java.math.BigDecimal;
import java.util.List;

public class SpuSearchVO {

    private Integer userType;  // 查询字段

    private Integer productId;

    private List<Integer> productIds;  // 查询字段

    private String productName;  // 查询字段

    private Integer brandId;

    private List<Integer> brandIds;  // 查询字段

    private String brandName;  // 查询字段

    private Integer categoryId;

    private List<Integer> categoryIds;  // 查询字段

    private String categoryName;  // 查询字段

    private String thumbnailImage;

    private BigDecimal retailPrice;

    private String priceRange;

    private String salesNum;

    private String online;  // 查询字段

    private String spuAttr;

    private Integer page;

    private Integer pageSize;

    private Integer startIndex;

    private String orderField; // 排序字段，可选值：productId,retailPrice,salesNum,lastUpdateTime 分别对应：默认排序，按价格排序，按销量排序和按新品排序

    private Integer orderDirection;// 排序方向，1为从大到小，2为从小到大。不填的话默认是从大到小


    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getProductId() {
        return productId;
    }

    public List<Integer> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<Integer> productIds) {
        this.productIds = productIds;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public List<Integer> getBrandIds() {
        return brandIds;
    }

    public void setBrandIds(List<Integer> brandIds) {
        this.brandIds = brandIds;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getThumbnailImage() {
        return thumbnailImage;
    }

    public void setThumbnailImage(String thumbnailImage) {
        this.thumbnailImage = thumbnailImage;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<Integer> getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(List<Integer> categoryIds) {
        this.categoryIds = categoryIds;
    }


    public BigDecimal getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(BigDecimal retailPrice) {
        this.retailPrice = retailPrice;
    }

    public String getPriceRange() {
        return priceRange;
    }

    public void setPriceRange(String priceRange) {
        this.priceRange = priceRange;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }

    public String getOrderField() {
        return orderField;
    }

    public void setOrderField(String orderField) {
        this.orderField = orderField;
    }

    public Integer getOrderDirection() {
        return orderDirection;
    }

    public void setOrderDirection(Integer orderDirection) {
        this.orderDirection = orderDirection;
    }


    public String getSalesNum() {
        return salesNum;
    }

    public void setSalesNum(String salesNum) {
        this.salesNum = salesNum;
    }

    public String getOnline() {
        return online;
    }

    public void setOnline(String online) {
        this.online = online;
    }

    public String getSpuAttr() {
        return spuAttr;
    }

    public void setSpuAttr(String spuAttr) {
        this.spuAttr = spuAttr;
    }
}
