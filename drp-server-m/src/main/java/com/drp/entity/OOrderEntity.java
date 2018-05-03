package com.drp.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class OOrderEntity {
    private int id;
    private String orderCode;
    private int distributorId;
    private Long accountId;
    private int type;
    private BigDecimal totalAmount;
    private BigDecimal paidAmount;
    private int status;
    private BigDecimal shippingCost;
    private String trancationId;
    private Integer paymentStatus;
    private Timestamp paymentTime;
    private String paymentChannel;
    private int orderItemQuantity;
    private BigDecimal itemSubtoal;
    private Timestamp orderTime;
    private Byte cancelType;
    private String cancelReason;
    private Timestamp cancelTime;
    private String buyerMessage;
    private Timestamp deliveryTime;
    private Timestamp finishTime;
    private int createBy;
    private Timestamp createTime;
    private int lastUpdateBy;
    private Timestamp lastUpdateTime;
    private String receiverName;
    private String receiverPhone;
    private String province;
    private String city;
    private String area;
    private String detailAddress;
    private Timestamp expireTime;
    private String deliveryName;
    private String deliveryCode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public int getDistributorId() {
        return distributorId;
    }

    public void setDistributorId(int distributorId) {
        this.distributorId = distributorId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(BigDecimal paidAmount) {
        this.paidAmount = paidAmount;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public BigDecimal getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(BigDecimal shippingCost) {
        this.shippingCost = shippingCost;
    }

    public String getTrancationId() {
        return trancationId;
    }

    public void setTrancationId(String trancationId) {
        this.trancationId = trancationId;
    }

    public Integer getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(Integer paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Timestamp getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(Timestamp paymentTime) {
        this.paymentTime = paymentTime;
    }

    public String getPaymentChannel() {
        return paymentChannel;
    }

    public void setPaymentChannel(String paymentChannel) {
        this.paymentChannel = paymentChannel;
    }

    public int getOrderItemQuantity() {
        return orderItemQuantity;
    }

    public void setOrderItemQuantity(int orderItemQuantity) {
        this.orderItemQuantity = orderItemQuantity;
    }

    public BigDecimal getItemSubtoal() {
        return itemSubtoal;
    }

    public void setItemSubtoal(BigDecimal itemSubtoal) {
        this.itemSubtoal = itemSubtoal;
    }

    public Timestamp getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Timestamp orderTime) {
        this.orderTime = orderTime;
    }

    public Byte getCancelType() {
        return cancelType;
    }

    public void setCancelType(Byte cancelType) {
        this.cancelType = cancelType;
    }

    public String getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
    }

    public Timestamp getCancelTime() {
        return cancelTime;
    }

    public void setCancelTime(Timestamp cancelTime) {
        this.cancelTime = cancelTime;
    }

    public String getBuyerMessage() {
        return buyerMessage;
    }

    public void setBuyerMessage(String buyerMessage) {
        this.buyerMessage = buyerMessage;
    }

    public Timestamp getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Timestamp deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public Timestamp getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Timestamp finishTime) {
        this.finishTime = finishTime;
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

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public Timestamp getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Timestamp expireTime) {
        this.expireTime = expireTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OOrderEntity that = (OOrderEntity) o;

        if (id != that.id) return false;
        if (distributorId != that.distributorId) return false;
        if (type != that.type) return false;
        if (status != that.status) return false;
        if (orderItemQuantity != that.orderItemQuantity) return false;
        if (createBy != that.createBy) return false;
        if (lastUpdateBy != that.lastUpdateBy) return false;
        if (orderCode != null ? !orderCode.equals(that.orderCode) : that.orderCode != null) return false;
        if (accountId != null ? !accountId.equals(that.accountId) : that.accountId != null) return false;
        if (totalAmount != null ? !totalAmount.equals(that.totalAmount) : that.totalAmount != null) return false;
        if (paidAmount != null ? !paidAmount.equals(that.paidAmount) : that.paidAmount != null) return false;
        if (shippingCost != null ? !shippingCost.equals(that.shippingCost) : that.shippingCost != null) return false;
        if (trancationId != null ? !trancationId.equals(that.trancationId) : that.trancationId != null) return false;
        if (paymentStatus != null ? !paymentStatus.equals(that.paymentStatus) : that.paymentStatus != null)
            return false;
        if (paymentTime != null ? !paymentTime.equals(that.paymentTime) : that.paymentTime != null) return false;
        if (paymentChannel != null ? !paymentChannel.equals(that.paymentChannel) : that.paymentChannel != null)
            return false;
        if (itemSubtoal != null ? !itemSubtoal.equals(that.itemSubtoal) : that.itemSubtoal != null) return false;
        if (orderTime != null ? !orderTime.equals(that.orderTime) : that.orderTime != null) return false;
        if (cancelType != null ? !cancelType.equals(that.cancelType) : that.cancelType != null) return false;
        if (cancelReason != null ? !cancelReason.equals(that.cancelReason) : that.cancelReason != null) return false;
        if (cancelTime != null ? !cancelTime.equals(that.cancelTime) : that.cancelTime != null) return false;
        if (buyerMessage != null ? !buyerMessage.equals(that.buyerMessage) : that.buyerMessage != null) return false;
        if (deliveryTime != null ? !deliveryTime.equals(that.deliveryTime) : that.deliveryTime != null) return false;
        if (finishTime != null ? !finishTime.equals(that.finishTime) : that.finishTime != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (lastUpdateTime != null ? !lastUpdateTime.equals(that.lastUpdateTime) : that.lastUpdateTime != null)
            return false;
        if (receiverName != null ? !receiverName.equals(that.receiverName) : that.receiverName != null) return false;
        if (receiverPhone != null ? !receiverPhone.equals(that.receiverPhone) : that.receiverPhone != null)
            return false;
        if (province != null ? !province.equals(that.province) : that.province != null) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (area != null ? !area.equals(that.area) : that.area != null) return false;
        if (detailAddress != null ? !detailAddress.equals(that.detailAddress) : that.detailAddress != null)
            return false;
        if (expireTime != null ? !expireTime.equals(that.expireTime) : that.expireTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (orderCode != null ? orderCode.hashCode() : 0);
        result = 31 * result + distributorId;
        result = 31 * result + (accountId != null ? accountId.hashCode() : 0);
        result = 31 * result + type;
        result = 31 * result + (totalAmount != null ? totalAmount.hashCode() : 0);
        result = 31 * result + (paidAmount != null ? paidAmount.hashCode() : 0);
        result = 31 * result + status;
        result = 31 * result + (shippingCost != null ? shippingCost.hashCode() : 0);
        result = 31 * result + (trancationId != null ? trancationId.hashCode() : 0);
        result = 31 * result + (paymentStatus != null ? paymentStatus.hashCode() : 0);
        result = 31 * result + (paymentTime != null ? paymentTime.hashCode() : 0);
        result = 31 * result + (paymentChannel != null ? paymentChannel.hashCode() : 0);
        result = 31 * result + orderItemQuantity;
        result = 31 * result + (itemSubtoal != null ? itemSubtoal.hashCode() : 0);
        result = 31 * result + (orderTime != null ? orderTime.hashCode() : 0);
        result = 31 * result + (cancelType != null ? cancelType.hashCode() : 0);
        result = 31 * result + (cancelReason != null ? cancelReason.hashCode() : 0);
        result = 31 * result + (cancelTime != null ? cancelTime.hashCode() : 0);
        result = 31 * result + (buyerMessage != null ? buyerMessage.hashCode() : 0);
        result = 31 * result + (deliveryTime != null ? deliveryTime.hashCode() : 0);
        result = 31 * result + (finishTime != null ? finishTime.hashCode() : 0);
        result = 31 * result + createBy;
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + lastUpdateBy;
        result = 31 * result + (lastUpdateTime != null ? lastUpdateTime.hashCode() : 0);
        result = 31 * result + (receiverName != null ? receiverName.hashCode() : 0);
        result = 31 * result + (receiverPhone != null ? receiverPhone.hashCode() : 0);
        result = 31 * result + (province != null ? province.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (area != null ? area.hashCode() : 0);
        result = 31 * result + (detailAddress != null ? detailAddress.hashCode() : 0);
        result = 31 * result + (expireTime != null ? expireTime.hashCode() : 0);
        return result;
    }

    public String getDeliveryName() {
        return deliveryName;
    }

    public void setDeliveryName(String deliveryName) {
        this.deliveryName = deliveryName;
    }

    public String getDeliveryCode() {
        return deliveryCode;
    }

    public void setDeliveryCode(String deliveryCode) {
        this.deliveryCode = deliveryCode;
    }
}
