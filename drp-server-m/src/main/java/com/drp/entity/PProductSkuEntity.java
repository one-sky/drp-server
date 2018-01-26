package com.drp.entity;

import java.math.BigDecimal;

public class PProductSkuEntity {
    private int id;
    private int productId;
    private BigDecimal retailPrice;
    private String skuImg;
    private String skuAttr;
    private String skuCode;
    private int salesNum;
    private BigDecimal minPrice;
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

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public BigDecimal getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(BigDecimal retailPrice) {
        this.retailPrice = retailPrice;
    }

    public String getSkuImg() {
        return skuImg;
    }

    public void setSkuImg(String skuImg) {
        this.skuImg = skuImg;
    }

    public String getSkuAttr() {
        return skuAttr;
    }

    public void setSkuAttr(String skuAttr) {
        this.skuAttr = skuAttr;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public int getSalesNum() {
        return salesNum;
    }

    public void setSalesNum(int salesNum) {
        this.salesNum = salesNum;
    }

    public BigDecimal getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
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

        PProductSkuEntity skuEntity = (PProductSkuEntity) o;

        if (id != skuEntity.id) return false;
        if (productId != skuEntity.productId) return false;
        if (salesNum != skuEntity.salesNum) return false;
        if (stock != skuEntity.stock) return false;
        if (lockStock != skuEntity.lockStock) return false;
        if (retailPrice != null ? !retailPrice.equals(skuEntity.retailPrice) : skuEntity.retailPrice != null)
            return false;
        if (skuImg != null ? !skuImg.equals(skuEntity.skuImg) : skuEntity.skuImg != null) return false;
        if (skuAttr != null ? !skuAttr.equals(skuEntity.skuAttr) : skuEntity.skuAttr != null) return false;
        if (skuCode != null ? !skuCode.equals(skuEntity.skuCode) : skuEntity.skuCode != null) return false;
        if (minPrice != null ? !minPrice.equals(skuEntity.minPrice) : skuEntity.minPrice != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + productId;
        result = 31 * result + (retailPrice != null ? retailPrice.hashCode() : 0);
        result = 31 * result + (skuImg != null ? skuImg.hashCode() : 0);
        result = 31 * result + (skuAttr != null ? skuAttr.hashCode() : 0);
        result = 31 * result + (skuCode != null ? skuCode.hashCode() : 0);
        result = 31 * result + salesNum;
        result = 31 * result + (minPrice != null ? minPrice.hashCode() : 0);
        result = 31 * result + stock;
        result = 31 * result + lockStock;
        return result;
    }
}
