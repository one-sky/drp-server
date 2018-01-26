package com.drp.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class PPromoteProductEntity {
    private int id;
    private int skuId;
    private int promotionId;
    private int promotionStock;
    private int createBy;
    private Timestamp createTime;
    private int lastUpdateBy;
    private Timestamp lastUpdateTime;
    private int lowQuantity;
    private int highQuantity;
    private BigDecimal price;
    private int lockStock;

    private int changeStock;

    public int getChangeStock() {
        return changeStock;
    }

    public void setChangeStock(int changeStock) {
        this.changeStock = changeStock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSkuId() {
        return skuId;
    }

    public void setSkuId(int skuId) {
        this.skuId = skuId;
    }

    public int getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(int promotionId) {
        this.promotionId = promotionId;
    }

    public int getPromotionStock() {
        return promotionStock;
    }

    public void setPromotionStock(int promotionStock) {
        this.promotionStock = promotionStock;
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

    public int getLowQuantity() {
        return lowQuantity;
    }

    public void setLowQuantity(int lowQuantity) {
        this.lowQuantity = lowQuantity;
    }

    public int getHighQuantity() {
        return highQuantity;
    }

    public void setHighQuantity(int highQuantity) {
        this.highQuantity = highQuantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getLockStock() {
        return lockStock;
    }

    public void setLockStock(int lockStock) {
        this.lockStock = lockStock;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PPromoteProductEntity that = (PPromoteProductEntity) o;

        if (id != that.id) return false;
        if (skuId != that.skuId) return false;
        if (promotionId != that.promotionId) return false;
        if (promotionStock != that.promotionStock) return false;
        if (createBy != that.createBy) return false;
        if (lastUpdateBy != that.lastUpdateBy) return false;
        if (lowQuantity != that.lowQuantity) return false;
        if (highQuantity != that.highQuantity) return false;
        if (lockStock != that.lockStock) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (lastUpdateTime != null ? !lastUpdateTime.equals(that.lastUpdateTime) : that.lastUpdateTime != null)
            return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + skuId;
        result = 31 * result + promotionId;
        result = 31 * result + promotionStock;
        result = 31 * result + createBy;
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + lastUpdateBy;
        result = 31 * result + (lastUpdateTime != null ? lastUpdateTime.hashCode() : 0);
        result = 31 * result + lowQuantity;
        result = 31 * result + highQuantity;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + lockStock;
        return result;
    }
}
