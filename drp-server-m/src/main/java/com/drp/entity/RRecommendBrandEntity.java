package com.drp.entity;

import java.sql.Timestamp;

public class RRecommendBrandEntity {
    private int id;
    private int brandId;
    private String sortBy;
    private int createBy;
    private Timestamp createTime;
    private int lastUpdateBy;
    private Timestamp lastUpdateTime;

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

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RRecommendBrandEntity that = (RRecommendBrandEntity) o;

        if (id != that.id) return false;
        if (brandId != that.brandId) return false;
        if (createBy != that.createBy) return false;
        if (lastUpdateBy != that.lastUpdateBy) return false;
        if (sortBy != null ? !sortBy.equals(that.sortBy) : that.sortBy != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (lastUpdateTime != null ? !lastUpdateTime.equals(that.lastUpdateTime) : that.lastUpdateTime != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + brandId;
        result = 31 * result + (sortBy != null ? sortBy.hashCode() : 0);
        result = 31 * result + createBy;
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + lastUpdateBy;
        result = 31 * result + (lastUpdateTime != null ? lastUpdateTime.hashCode() : 0);
        return result;
    }
}
