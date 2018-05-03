package com.drp.vo;

import com.drp.entity.PAttrValueEntity;

import java.sql.Timestamp;
import java.util.List;

public class AttrVO {
    private int id;
    private int categoryId;
    private List<Integer> categoryIds;
    private String categoryName;
    private String attrName;
    private int createBy;
    private Timestamp createTime;
    private int lastUpdateBy;
    private Timestamp lastUpdateTime;
    private int status;
    private List<PAttrValueEntity> attrValueEntityList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public List<Integer> getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(List<Integer> categoryIds) {
        this.categoryIds = categoryIds;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<PAttrValueEntity> getAttrValueEntityList() {
        return attrValueEntityList;
    }

    public void setAttrValueEntityList(List<PAttrValueEntity> attrValueEntityList) {
        this.attrValueEntityList = attrValueEntityList;
    }
}
