package com.drp.vo;

import java.math.BigDecimal;
import java.util.List;

public class SkuDetailVO {

    private Integer SkuId;

    private List<SkuPriceDetailVO> priceList;

    private BigDecimal minPrice;

    private BigDecimal specialPrice;

    public Integer getSkuId() {
        return SkuId;
    }

    public void setSkuId(Integer skuId) {
        SkuId = skuId;
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
