package com.drp.vo;

import java.math.BigDecimal;

public class SkuPriceDetailVO {

    private Integer skuId; // skuId

    private String skuCode;

    private Integer levelId;//会员等级ID

    private Integer promotionId;//促销ID

    private Integer startPiece;//起始件数

    private Integer endPiece;//终止件数

    private BigDecimal price;//对应价格

    private Integer stock; // 库存

    private Integer salesNum;

    private String skuAttr;

    private String skuImg;

    private BigDecimal retailPrice;

    private BigDecimal minPrice;

    private String special;//是否设定的特殊分销商特定价格, 生成订单时是否为原价(promotionId undefined且special 'N'时为原价

    public Integer getLevelId() {
        return levelId;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public BigDecimal getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(BigDecimal retailPrice) {
        this.retailPrice = retailPrice;
    }

    public BigDecimal getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }

    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }

    public Integer getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(Integer promotionId) {
        this.promotionId = promotionId;
    }

    public Integer getStartPiece() {
        return startPiece;
    }

    public void setStartPiece(Integer startPiece) {
        this.startPiece = startPiece;
    }

    public Integer getEndPiece() {
        return endPiece;
    }

    public void setEndPiece(Integer endPiece) {
        this.endPiece = endPiece;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getSpecial() {
        return special;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getSkuImg() {
        return skuImg;
    }

    public void setSkuImg(String skuImg) {
        this.skuImg = skuImg;
    }

    public void setSpecial(String special) {
        this.special = special;
    }

    public String getSkuAttr() {
        return skuAttr;
    }

    public void setSkuAttr(String skuAttr) {
        this.skuAttr = skuAttr;
    }

    public Integer getSkuId() {
        return skuId;
    }

    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
    }

    public Integer getSalesNum() {
        return salesNum;
    }

    public void setSalesNum(Integer salesNum) {
        this.salesNum = salesNum;
    }
}
