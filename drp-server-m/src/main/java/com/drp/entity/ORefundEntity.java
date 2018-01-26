package com.drp.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class ORefundEntity {
    private int id;
    private Integer refundId;
    private String orderNumber;
    private String refundNumber;
    private String type;
    private String isReceive;
    private String refundType;
    private String status;
    private String reviewStatus;
    private String reviewResult;
    private BigDecimal refundMoney;
    private Timestamp refundRequestTime;
    private String contractor;
    private String phone;
    private String address;
    private BigDecimal lossesMoney;
    private String note;
    private String responsiblePerson;
    private String isResell;
    private int createBy;
    private Timestamp createTime;
    private int lastUpdateBy;
    private Timestamp lastUpdateTime;
    private BigDecimal accountRefound;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getRefundId() {
        return refundId;
    }

    public void setRefundId(Integer refundId) {
        this.refundId = refundId;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getRefundNumber() {
        return refundNumber;
    }

    public void setRefundNumber(String refundNumber) {
        this.refundNumber = refundNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIsReceive() {
        return isReceive;
    }

    public void setIsReceive(String isReceive) {
        this.isReceive = isReceive;
    }

    public String getRefundType() {
        return refundType;
    }

    public void setRefundType(String refundType) {
        this.refundType = refundType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReviewStatus() {
        return reviewStatus;
    }

    public void setReviewStatus(String reviewStatus) {
        this.reviewStatus = reviewStatus;
    }

    public String getReviewResult() {
        return reviewResult;
    }

    public void setReviewResult(String reviewResult) {
        this.reviewResult = reviewResult;
    }

    public BigDecimal getRefundMoney() {
        return refundMoney;
    }

    public void setRefundMoney(BigDecimal refundMoney) {
        this.refundMoney = refundMoney;
    }

    public Timestamp getRefundRequestTime() {
        return refundRequestTime;
    }

    public void setRefundRequestTime(Timestamp refundRequestTime) {
        this.refundRequestTime = refundRequestTime;
    }

    public String getContractor() {
        return contractor;
    }

    public void setContractor(String contractor) {
        this.contractor = contractor;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getLossesMoney() {
        return lossesMoney;
    }

    public void setLossesMoney(BigDecimal lossesMoney) {
        this.lossesMoney = lossesMoney;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getResponsiblePerson() {
        return responsiblePerson;
    }

    public void setResponsiblePerson(String responsiblePerson) {
        this.responsiblePerson = responsiblePerson;
    }

    public String getIsResell() {
        return isResell;
    }

    public void setIsResell(String isResell) {
        this.isResell = isResell;
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

    public BigDecimal getAccountRefound() {
        return accountRefound;
    }

    public void setAccountRefound(BigDecimal accountRefound) {
        this.accountRefound = accountRefound;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ORefundEntity that = (ORefundEntity) o;

        if (id != that.id) return false;
        if (createBy != that.createBy) return false;
        if (lastUpdateBy != that.lastUpdateBy) return false;
        if (refundId != null ? !refundId.equals(that.refundId) : that.refundId != null) return false;
        if (orderNumber != null ? !orderNumber.equals(that.orderNumber) : that.orderNumber != null) return false;
        if (refundNumber != null ? !refundNumber.equals(that.refundNumber) : that.refundNumber != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (isReceive != null ? !isReceive.equals(that.isReceive) : that.isReceive != null) return false;
        if (refundType != null ? !refundType.equals(that.refundType) : that.refundType != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (reviewStatus != null ? !reviewStatus.equals(that.reviewStatus) : that.reviewStatus != null) return false;
        if (reviewResult != null ? !reviewResult.equals(that.reviewResult) : that.reviewResult != null) return false;
        if (refundMoney != null ? !refundMoney.equals(that.refundMoney) : that.refundMoney != null) return false;
        if (refundRequestTime != null ? !refundRequestTime.equals(that.refundRequestTime) : that.refundRequestTime != null)
            return false;
        if (contractor != null ? !contractor.equals(that.contractor) : that.contractor != null) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (lossesMoney != null ? !lossesMoney.equals(that.lossesMoney) : that.lossesMoney != null) return false;
        if (note != null ? !note.equals(that.note) : that.note != null) return false;
        if (responsiblePerson != null ? !responsiblePerson.equals(that.responsiblePerson) : that.responsiblePerson != null)
            return false;
        if (isResell != null ? !isResell.equals(that.isResell) : that.isResell != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (lastUpdateTime != null ? !lastUpdateTime.equals(that.lastUpdateTime) : that.lastUpdateTime != null)
            return false;
        if (accountRefound != null ? !accountRefound.equals(that.accountRefound) : that.accountRefound != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (refundId != null ? refundId.hashCode() : 0);
        result = 31 * result + (orderNumber != null ? orderNumber.hashCode() : 0);
        result = 31 * result + (refundNumber != null ? refundNumber.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (isReceive != null ? isReceive.hashCode() : 0);
        result = 31 * result + (refundType != null ? refundType.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (reviewStatus != null ? reviewStatus.hashCode() : 0);
        result = 31 * result + (reviewResult != null ? reviewResult.hashCode() : 0);
        result = 31 * result + (refundMoney != null ? refundMoney.hashCode() : 0);
        result = 31 * result + (refundRequestTime != null ? refundRequestTime.hashCode() : 0);
        result = 31 * result + (contractor != null ? contractor.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (lossesMoney != null ? lossesMoney.hashCode() : 0);
        result = 31 * result + (note != null ? note.hashCode() : 0);
        result = 31 * result + (responsiblePerson != null ? responsiblePerson.hashCode() : 0);
        result = 31 * result + (isResell != null ? isResell.hashCode() : 0);
        result = 31 * result + createBy;
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + lastUpdateBy;
        result = 31 * result + (lastUpdateTime != null ? lastUpdateTime.hashCode() : 0);
        result = 31 * result + (accountRefound != null ? accountRefound.hashCode() : 0);
        return result;
    }
}
