package com.drp.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class OOrderItemEntity {
    private int id;
    private int orderId;
    private int skuId;
    private BigDecimal skuPrice;
    private BigDecimal orginalPrice;
    private int skuQuantity;
    private String mftProdCode;
    private String thirdOrderItemId;
    private int createBy;
    private Timestamp createTime;
    private int lastUpdateBy;
    private Timestamp lastUpdateTime;
    private String status;
    private String deliverName;
    private String deliverCode;
    private Integer deliverStatusCode;
    private String trackingNumber;
    private String deliverStatus;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getSkuId() {
        return skuId;
    }

    public void setSkuId(int skuId) {
        this.skuId = skuId;
    }

    public BigDecimal getSkuPrice() {
        return skuPrice;
    }

    public void setSkuPrice(BigDecimal skuPrice) {
        this.skuPrice = skuPrice;
    }

    public BigDecimal getOrginalPrice() {
        return orginalPrice;
    }

    public void setOrginalPrice(BigDecimal orginalPrice) {
        this.orginalPrice = orginalPrice;
    }

    public int getSkuQuantity() {
        return skuQuantity;
    }

    public void setSkuQuantity(int skuQuantity) {
        this.skuQuantity = skuQuantity;
    }

    public String getMftProdCode() {
        return mftProdCode;
    }

    public void setMftProdCode(String mftProdCode) {
        this.mftProdCode = mftProdCode;
    }

    public String getThirdOrderItemId() {
        return thirdOrderItemId;
    }

    public void setThirdOrderItemId(String thirdOrderItemId) {
        this.thirdOrderItemId = thirdOrderItemId;
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

    public String getDeliverName() {
        return deliverName;
    }

    public void setDeliverName(String deliverName) {
        this.deliverName = deliverName;
    }

    public String getDeliverCode() {
        return deliverCode;
    }

    public void setDeliverCode(String deliverCode) {
        this.deliverCode = deliverCode;
    }

    public Integer getDeliverStatusCode() {
        return deliverStatusCode;
    }

    public void setDeliverStatusCode(Integer deliverStatusCode) {
        this.deliverStatusCode = deliverStatusCode;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public String getDeliverStatus() {
        return deliverStatus;
    }

    public void setDeliverStatus(String deliverStatus) {
        this.deliverStatus = deliverStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OOrderItemEntity that = (OOrderItemEntity) o;

        if (id != that.id) return false;
        if (orderId != that.orderId) return false;
        if (skuId != that.skuId) return false;
        if (skuQuantity != that.skuQuantity) return false;
        if (createBy != that.createBy) return false;
        if (lastUpdateBy != that.lastUpdateBy) return false;
        if (skuPrice != null ? !skuPrice.equals(that.skuPrice) : that.skuPrice != null) return false;
        if (orginalPrice != null ? !orginalPrice.equals(that.orginalPrice) : that.orginalPrice != null) return false;
        if (mftProdCode != null ? !mftProdCode.equals(that.mftProdCode) : that.mftProdCode != null) return false;
        if (thirdOrderItemId != null ? !thirdOrderItemId.equals(that.thirdOrderItemId) : that.thirdOrderItemId != null)
            return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (lastUpdateTime != null ? !lastUpdateTime.equals(that.lastUpdateTime) : that.lastUpdateTime != null)
            return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (deliverName != null ? !deliverName.equals(that.deliverName) : that.deliverName != null) return false;
        if (deliverCode != null ? !deliverCode.equals(that.deliverCode) : that.deliverCode != null) return false;
        if (deliverStatusCode != null ? !deliverStatusCode.equals(that.deliverStatusCode) : that.deliverStatusCode != null)
            return false;
        if (trackingNumber != null ? !trackingNumber.equals(that.trackingNumber) : that.trackingNumber != null)
            return false;
        if (deliverStatus != null ? !deliverStatus.equals(that.deliverStatus) : that.deliverStatus != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + orderId;
        result = 31 * result + skuId;
        result = 31 * result + (skuPrice != null ? skuPrice.hashCode() : 0);
        result = 31 * result + (orginalPrice != null ? orginalPrice.hashCode() : 0);
        result = 31 * result + skuQuantity;
        result = 31 * result + (mftProdCode != null ? mftProdCode.hashCode() : 0);
        result = 31 * result + (thirdOrderItemId != null ? thirdOrderItemId.hashCode() : 0);
        result = 31 * result + createBy;
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + lastUpdateBy;
        result = 31 * result + (lastUpdateTime != null ? lastUpdateTime.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (deliverName != null ? deliverName.hashCode() : 0);
        result = 31 * result + (deliverCode != null ? deliverCode.hashCode() : 0);
        result = 31 * result + (deliverStatusCode != null ? deliverStatusCode.hashCode() : 0);
        result = 31 * result + (trackingNumber != null ? trackingNumber.hashCode() : 0);
        result = 31 * result + (deliverStatus != null ? deliverStatus.hashCode() : 0);
        return result;
    }
}
