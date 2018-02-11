package com.drp.vo;

import java.math.BigDecimal;
import java.util.List;

public class SkuDetailVO {

    private Integer skuId;

    private List<SkuPriceDetailVO> priceList;

    private BigDecimal minPrice;

    private BigDecimal specialPrice;

    public Integer getSkuId() {
        return skuId;
    }

    public void setSkuId(Integer skuId) {
        skuId = skuId;
    }

    public List<SkuPriceDetailVO> getPriceList() {
        return priceList;
    }

    public void setPriceList(List<SkuPriceDetailVO> priceList) {
        this.priceList = priceList;
    }

    public BigDecimal getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }

    public BigDecimal getSpecialPrice() {
        return specialPrice;
    }

    public void setSpecialPrice(BigDecimal specialPrice) {
        this.specialPrice = specialPrice;
    }
}
