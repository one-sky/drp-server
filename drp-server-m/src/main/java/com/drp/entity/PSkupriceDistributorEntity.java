package com.drp.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class PSkupriceDistributorEntity {
    private int id;
    private int distributorId;
    private int skuId;
    private String attributes;
    private BigDecimal price;
    private Integer createBy;
    private Timestamp createTime;
    private int lastUpdateBy;
    private Timestamp lastUpdateTime;
    private int lowQuantity;
    private int highQuantity;
    private int stock;
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

    public int getDistributorId() {
        return distributorId;
    }

    public void setDistributorId(int distributorId) {
        this.distributorId = distributorId;
    }

    public int getSkuId() {
        return skuId;
    }

    public void setSkuId(int skuId) {
        this.skuId = skuId;
    }

    public String getAttributes() {
        return attributes;
    }

    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
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

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
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

        PSkupriceDistributorEntity that = (PSkupriceDistributorEntity) o;

        if (id != that.id) return false;
        if (distributorId != that.distributorId) return false;
        if (skuId != that.skuId) return false;
        if (lastUpdateBy != that.lastUpdateBy) return false;
        if (lowQuantity != that.lowQuantity) return false;
        if (highQuantity != that.highQuantity) return false;
        if (stock != that.stock) return false;
        if (lockStock != that.lockStock) return false;
        if (attributes != null ? !attributes.equals(that.attributes) : that.attributes != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        if (createBy != null ? !createBy.equals(that.createBy) : that.createBy != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (lastUpdateTime != null ? !lastUpdateTime.equals(that.lastUpdateTime) : that.lastUpdateTime != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + distributorId;
        result = 31 * result + skuId;
        result = 31 * result + (attributes != null ? attributes.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (createBy != null ? createBy.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + lastUpdateBy;
        result = 31 * result + (lastUpdateTime != null ? lastUpdateTime.hashCode() : 0);
        result = 31 * result + lowQuantity;
        result = 31 * result + highQuantity;
        result = 31 * result + stock;
        result = 31 * result + lockStock;
        return result;
    }
}
