package com.drp.repository;

import com.drp.entity.DAgentBrandEntity;
import com.drp.entity.RBrandAttachmentEntity;
import com.drp.entity.RBrandEntity;

import java.util.List;

public interface BrandRepository {

    public List<RBrandEntity> getBrandListByAgentBrand(Integer distributorId, String brandName,
                                                       String status, Integer pageSize, Integer startIndex);

    public List<RBrandEntity> getBrandListByCategoryId(Integer categoryId);

    public DAgentBrandEntity getAgentBrand(Integer distributorId, Integer brandId, Integer channelId);

    // 获取最新品牌
    public List<RBrandEntity> getNewBrandList();

    public RBrandEntity getBrand( Integer id);

    public RBrandAttachmentEntity getBrandAttachment(Integer brandId);

    public Integer insertAgentBrand(DAgentBrandEntity entity);

    public String isAgentBrand(Integer distributorId, Integer skuId);
}
