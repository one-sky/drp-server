package com.drp.vo;

import java.util.Date;
import java.util.List;

public class ShoppingCartItemVO {

    private Integer id; // 主键

    private Integer skuId;//商品skuid

    private Integer spuId;//spuId

    private Integer quantity;

    private Integer changeQuantity;

    private String status;//Y是上架，N是下架

    private Integer type;

    private Integer brandId;

    private String skuCode;

    private String price;

    private String spuName;

    private Date updateTime;

    private String skuAttr;//sku属性描述字符串

    private String skuImg;

    private List<SkuPriceDetailVO> skuPriceDetailVO;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSkuId() {
        return skuId;
    }

    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
    }

    public Integer getSpuId() {
        return spuId;
    }

    public void setSpuId(Integer spuId) {
        this.spuId = spuId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getChangeQuantity() {
        return changeQuantity;
    }

    public void setChangeQuantity(Integer changeQuantity) {
        this.changeQuantity = changeQuantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSpuName() {
        return spuName;
    }

    public void setSpuName(String spuName) {
        this.spuName = spuName;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getSkuAttr() {
        return skuAttr;
    }

    public void setSkuAttr(String skuAttr) {
        this.skuAttr = skuAttr;
    }

    public String getSkuImg() {
        return skuImg;
    }

    public void setSkuImg(String skuImg) {
        this.skuImg = skuImg;
    }

    public List<SkuPriceDetailVO> getSkuPriceDetailVO() {
        return skuPriceDetailVO;
    }

    public void setSkuPriceDetailVO(List<SkuPriceDetailVO> skuPriceDetailVO) {
        this.skuPriceDetailVO = skuPriceDetailVO;
    }

}
