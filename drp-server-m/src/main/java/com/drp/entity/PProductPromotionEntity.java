package com.drp.entity;

import java.sql.Timestamp;

public class PProductPromotionEntity {
    private int id;
    private String promotionName;
    private Timestamp salesEffStart;
    private Timestamp salesEffEnd;
    private String coverImg;
    private String status;
    private String discription;
    private String content;
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

    public String getPromotionName() {
        return promotionName;
    }

    public void setPromotionName(String promotionName) {
        this.promotionName = promotionName;
    }

    public Timestamp getSalesEffStart() {
        return salesEffStart;
    }

    public void setSalesEffStart(Timestamp salesEffStart) {
        this.salesEffStart = salesEffStart;
    }

    public Timestamp getSalesEffEnd() {
        return salesEffEnd;
    }

    public void setSalesEffEnd(Timestamp salesEffEnd) {
        this.salesEffEnd = salesEffEnd;
    }

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

        PProductPromotionEntity that = (PProductPromotionEntity) o;

        if (id != that.id) return false;
        if (createBy != that.createBy) return false;
        if (lastUpdateBy != that.lastUpdateBy) return false;
        if (promotionName != null ? !promotionName.equals(that.promotionName) : that.promotionName != null)
            return false;
        if (salesEffStart != null ? !salesEffStart.equals(that.salesEffStart) : that.salesEffStart != null)
            return false;
        if (salesEffEnd != null ? !salesEffEnd.equals(that.salesEffEnd) : that.salesEffEnd != null) return false;
        if (coverImg != null ? !coverImg.equals(that.coverImg) : that.coverImg != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (discription != null ? !discription.equals(that.discription) : that.discription != null) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (lastUpdateTime != null ? !lastUpdateTime.equals(that.lastUpdateTime) : that.lastUpdateTime != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (promotionName != null ? promotionName.hashCode() : 0);
        result = 31 * result + (salesEffStart != null ? salesEffStart.hashCode() : 0);
        result = 31 * result + (salesEffEnd != null ? salesEffEnd.hashCode() : 0);
        result = 31 * result + (coverImg != null ? coverImg.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (discription != null ? discription.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + createBy;
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + lastUpdateBy;
        result = 31 * result + (lastUpdateTime != null ? lastUpdateTime.hashCode() : 0);
        return result;
    }
}
