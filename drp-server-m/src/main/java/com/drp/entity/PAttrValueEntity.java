package com.drp.entity;

import java.sql.Timestamp;

public class PAttrValueEntity {
    private int id;
    private int attrId;
    private String attrValue;
    private int createBy;
    private Timestamp createTime;
    private int lastUpdateBy;
    private Timestamp lastUpdateTime;
    private String status;
    private String spuIds;
    private int sortBy;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAttrId() {
        return attrId;
    }

    public void setAttrId(int attrId) {
        this.attrId = attrId;
    }

    public String getAttrValue() {
        return attrValue;
    }

    public void setAttrValue(String attrValue) {
        this.attrValue = attrValue;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSpuIds() {
        return spuIds;
    }

    public void setSpuIds(String spuIds) {
        this.spuIds = spuIds;
    }

    public int getSortBy() {
        return sortBy;
    }

    public void setSortBy(int sortBy) {
        this.sortBy = sortBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PAttrValueEntity that = (PAttrValueEntity) o;

        if (id != that.id) return false;
        if (attrId != that.attrId) return false;
        if (createBy != that.createBy) return false;
        if (lastUpdateBy != that.lastUpdateBy) return false;
        if (sortBy != that.sortBy) return false;
        if (attrValue != null ? !attrValue.equals(that.attrValue) : that.attrValue != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (lastUpdateTime != null ? !lastUpdateTime.equals(that.lastUpdateTime) : that.lastUpdateTime != null)
            return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (spuIds != null ? !spuIds.equals(that.spuIds) : that.spuIds != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + attrId;
        result = 31 * result + (attrValue != null ? attrValue.hashCode() : 0);
        result = 31 * result + createBy;
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + lastUpdateBy;
        result = 31 * result + (lastUpdateTime != null ? lastUpdateTime.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (spuIds != null ? spuIds.hashCode() : 0);
        result = 31 * result + sortBy;
        return result;
    }
}
