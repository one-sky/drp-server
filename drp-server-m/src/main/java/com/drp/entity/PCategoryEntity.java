package com.drp.entity;

import java.sql.Timestamp;

public class PCategoryEntity {
    private int id;
    private int parentId;
    private String categoryName;
    private String categoryEgName;
    private int sortBy;
    private int isLeaf;
    private int status;
    private String level;
    private String categoryPic;
    private int createBy;
    private Timestamp createTime;
    private int lastUpdateBy;
    private Timestamp lastUpdateTime;
    private String categoryPath;
    private String categoryRoot;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryEgName() {
        return categoryEgName;
    }

    public void setCategoryEgName(String categoryEgName) {
        this.categoryEgName = categoryEgName;
    }

    public int getSortBy() {
        return sortBy;
    }

    public void setSortBy(int sortBy) {
        this.sortBy = sortBy;
    }

    public int getIsLeaf() {
        return isLeaf;
    }

    public void setIsLeaf(int isLeaf) {
        this.isLeaf = isLeaf;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getCategoryPic() {
        return categoryPic;
    }

    public void setCategoryPic(String categoryPic) {
        this.categoryPic = categoryPic;
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

    public String getCategoryPath() {
        return categoryPath;
    }

    public void setCategoryPath(String categoryPath) {
        this.categoryPath = categoryPath;
    }

    public String getCategoryRoot() {
        return categoryRoot;
    }

    public void setCategoryRoot(String categoryRoot) {
        this.categoryRoot = categoryRoot;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PCategoryEntity that = (PCategoryEntity) o;

        if (id != that.id) return false;
        if (parentId != that.parentId) return false;
        if (sortBy != that.sortBy) return false;
        if (isLeaf != that.isLeaf) return false;
        if (status != that.status) return false;
        if (createBy != that.createBy) return false;
        if (lastUpdateBy != that.lastUpdateBy) return false;
        if (categoryName != null ? !categoryName.equals(that.categoryName) : that.categoryName != null) return false;
        if (categoryEgName != null ? !categoryEgName.equals(that.categoryEgName) : that.categoryEgName != null)
            return false;
        if (level != null ? !level.equals(that.level) : that.level != null) return false;
        if (categoryPic != null ? !categoryPic.equals(that.categoryPic) : that.categoryPic != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (lastUpdateTime != null ? !lastUpdateTime.equals(that.lastUpdateTime) : that.lastUpdateTime != null)
            return false;
        if (categoryPath != null ? !categoryPath.equals(that.categoryPath) : that.categoryPath != null) return false;
        if (categoryRoot != null ? !categoryRoot.equals(that.categoryRoot) : that.categoryRoot != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + parentId;
        result = 31 * result + (categoryName != null ? categoryName.hashCode() : 0);
        result = 31 * result + (categoryEgName != null ? categoryEgName.hashCode() : 0);
        result = 31 * result + sortBy;
        result = 31 * result + isLeaf;
        result = 31 * result + status;
        result = 31 * result + (level != null ? level.hashCode() : 0);
        result = 31 * result + (categoryPic != null ? categoryPic.hashCode() : 0);
        result = 31 * result + createBy;
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + lastUpdateBy;
        result = 31 * result + (lastUpdateTime != null ? lastUpdateTime.hashCode() : 0);
        result = 31 * result + (categoryPath != null ? categoryPath.hashCode() : 0);
        result = 31 * result + (categoryRoot != null ? categoryRoot.hashCode() : 0);
        return result;
    }
}
