package com.drp.entity;

import java.sql.Timestamp;

public class PProductCategoryEntity {
    private int id;
    private int brandId;
    private int categoryId;
    private String productName;
    private int createBy;
    private Timestamp createTime;
    private int lastUpdateBy;
    private Timestamp lastUpdateTime;
    private String categoryName;
    private String thumbnailImage;
    private String online;
    private String spuAttr;
    private String size;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getCreateBy() {
        return createBy;
    }

    public void setCreateBy(int createBy) {
        this.createBy = createBy;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public int getLastUpdateBy() {
        return lastUpdateBy;
    }

    public void setLastUpdateBy(int lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    public Timestamp getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Timestamp lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getThumbnailImage() {
        return thumbnailImage;
    }

    public void setThumbnailImage(String thumbnailImage) {
        this.thumbnailImage = thumbnailImage;
    }

    public String getOnline() {
        return online;
    }

    public void setOnline(String online) {
        this.online = online;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PProductCategoryEntity that = (PProductCategoryEntity) o;

        if (id != that.id) return false;
        if (brandId != that.brandId) return false;
        if (categoryId != that.categoryId) return false;
        if (createBy != that.createBy) return false;
        if (lastUpdateBy != that.lastUpdateBy) return false;
        if (productName != null ? !productName.equals(that.productName) : that.productName != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (lastUpdateTime != null ? !lastUpdateTime.equals(that.lastUpdateTime) : that.lastUpdateTime != null)
            return false;
        if (categoryName != null ? !categoryName.equals(that.categoryName) : that.categoryName != null) return false;
        if (thumbnailImage != null ? !thumbnailImage.equals(that.thumbnailImage) : that.thumbnailImage != null)
            return false;
        if (online != null ? !online.equals(that.online) : that.online != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + brandId;
        result = 31 * result + categoryId;
        result = 31 * result + (productName != null ? productName.hashCode() : 0);
        result = 31 * result + createBy;
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + lastUpdateBy;
        result = 31 * result + (lastUpdateTime != null ? lastUpdateTime.hashCode() : 0);
        result = 31 * result + (categoryName != null ? categoryName.hashCode() : 0);
        result = 31 * result + (thumbnailImage != null ? thumbnailImage.hashCode() : 0);
        result = 31 * result + (online != null ? online.hashCode() : 0);
        return result;
    }

    public String getSpuAttr() {
        return spuAttr;
    }

    public void setSpuAttr(String spuAttr) {
        this.spuAttr = spuAttr;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
