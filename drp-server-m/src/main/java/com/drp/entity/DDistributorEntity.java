package com.drp.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class DDistributorEntity {
    private int id;
    private int userId;
    private String name;
    private String contractor;
    private String phone;
    private String email;
    private String sexual;
    private String qq;
    private String wwId;
    private String province;
    private String provinceDesc;
    private String city;
    private String cityDesc;
    private String area;
    private String areaDesc;
    private String contractAddress;
    private String comment;
    private String headUrl;
    private Integer vipId;
    private int points;
    private BigDecimal totalAmount;
    private int createBy;
    private Timestamp createTime;
    private int lastUpdateBy;
    private Timestamp lastUpdateTime;
    private String saleChannel;
    private String cooperateMode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSexual() {
        return sexual;
    }

    public void setSexual(String sexual) {
        this.sexual = sexual;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getWwId() {
        return wwId;
    }

    public void setWwId(String wwId) {
        this.wwId = wwId;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getProvinceDesc() {
        return provinceDesc;
    }

    public void setProvinceDesc(String provinceDesc) {
        this.provinceDesc = provinceDesc;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityDesc() {
        return cityDesc;
    }

    public void setCityDesc(String cityDesc) {
        this.cityDesc = cityDesc;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAreaDesc() {
        return areaDesc;
    }

    public void setAreaDesc(String areaDesc) {
        this.areaDesc = areaDesc;
    }

    public String getContractAddress() {
        return contractAddress;
    }

    public void setContractAddress(String contractAddress) {
        this.contractAddress = contractAddress;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public Integer getVipId() {
        return vipId;
    }

    public void setVipId(Integer vipId) {
        this.vipId = vipId;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
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

    public String getSaleChannel() {
        return saleChannel;
    }

    public void setSaleChannel(String saleChannel) {
        this.saleChannel = saleChannel;
    }

    public String getCooperateMode() {
        return cooperateMode;
    }

    public void setCooperateMode(String cooperateMode) {
        this.cooperateMode = cooperateMode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DDistributorEntity that = (DDistributorEntity) o;

        if (id != that.id) return false;
        if (userId != that.userId) return false;
        if (points != that.points) return false;
        if (createBy != that.createBy) return false;
        if (lastUpdateBy != that.lastUpdateBy) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (contractor != null ? !contractor.equals(that.contractor) : that.contractor != null) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (sexual != null ? !sexual.equals(that.sexual) : that.sexual != null) return false;
        if (qq != null ? !qq.equals(that.qq) : that.qq != null) return false;
        if (wwId != null ? !wwId.equals(that.wwId) : that.wwId != null) return false;
        if (province != null ? !province.equals(that.province) : that.province != null) return false;
        if (provinceDesc != null ? !provinceDesc.equals(that.provinceDesc) : that.provinceDesc != null) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (cityDesc != null ? !cityDesc.equals(that.cityDesc) : that.cityDesc != null) return false;
        if (area != null ? !area.equals(that.area) : that.area != null) return false;
        if (areaDesc != null ? !areaDesc.equals(that.areaDesc) : that.areaDesc != null) return false;
        if (contractAddress != null ? !contractAddress.equals(that.contractAddress) : that.contractAddress != null)
            return false;
        if (comment != null ? !comment.equals(that.comment) : that.comment != null) return false;
        if (headUrl != null ? !headUrl.equals(that.headUrl) : that.headUrl != null) return false;
        if (vipId != null ? !vipId.equals(that.vipId) : that.vipId != null) return false;
        if (totalAmount != null ? !totalAmount.equals(that.totalAmount) : that.totalAmount != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (lastUpdateTime != null ? !lastUpdateTime.equals(that.lastUpdateTime) : that.lastUpdateTime != null)
            return false;
        if (saleChannel != null ? !saleChannel.equals(that.saleChannel) : that.saleChannel != null) return false;
        if (cooperateMode != null ? !cooperateMode.equals(that.cooperateMode) : that.cooperateMode != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + userId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (contractor != null ? contractor.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (sexual != null ? sexual.hashCode() : 0);
        result = 31 * result + (qq != null ? qq.hashCode() : 0);
        result = 31 * result + (wwId != null ? wwId.hashCode() : 0);
        result = 31 * result + (province != null ? province.hashCode() : 0);
        result = 31 * result + (provinceDesc != null ? provinceDesc.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (cityDesc != null ? cityDesc.hashCode() : 0);
        result = 31 * result + (area != null ? area.hashCode() : 0);
        result = 31 * result + (areaDesc != null ? areaDesc.hashCode() : 0);
        result = 31 * result + (contractAddress != null ? contractAddress.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + (headUrl != null ? headUrl.hashCode() : 0);
        result = 31 * result + (vipId != null ? vipId.hashCode() : 0);
        result = 31 * result + points;
        result = 31 * result + (totalAmount != null ? totalAmount.hashCode() : 0);
        result = 31 * result + createBy;
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + lastUpdateBy;
        result = 31 * result + (lastUpdateTime != null ? lastUpdateTime.hashCode() : 0);
        result = 31 * result + (saleChannel != null ? saleChannel.hashCode() : 0);
        result = 31 * result + (cooperateMode != null ? cooperateMode.hashCode() : 0);
        return result;
    }
}
