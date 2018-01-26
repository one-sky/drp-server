package com.drp.vo;

import com.drp.entity.PProductArticleEntity;

import java.util.List;

public class SpuDetailVO {
    private List<SkuDetailVO> skuDetailList;

    private List<PProductArticleEntity> pProductArticleEntity;

    public List<SkuDetailVO> getSkuDetailList() {
        return skuDetailList;
    }

        public void setSkuDetailList(List<SkuDetailVO> skuDetailList) {
        this.skuDetailList = skuDetailList;
    }

    public List<PProductArticleEntity> getpProductArticleEntity() {
        return pProductArticleEntity;
    }

    public void setpProductArticleEntity(List<PProductArticleEntity> pProductArticleEntity) {
        this.pProductArticleEntity = pProductArticleEntity;
    }
}
