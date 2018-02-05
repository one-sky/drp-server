package com.drp.service;

import com.drp.entity.DAgentBrandEntity;
import com.drp.entity.RBrandAttachmentEntity;
import com.drp.entity.RBrandEntity;

import java.util.List;
import java.util.Map;

public interface BrandService {

    public Map<String,Object> getBrandListByAgentBrand(Integer distributorId,
                                                       String brandName, String status, Integer pageNum, Integer pageSize);

    public List<RBrandEntity> getBrandListByCategoryId(Integer categoryId);

    public DAgentBrandEntity getAgentBrand(Integer distributorId, Integer brandId);

    public List<RBrandEntity> getNewBrandList(Integer num);

    public RBrandEntity getBrandById(Integer id);

    public List<RBrandAttachmentEntity> getBrandAttachment(Integer brandId);

    public Integer insertAgentBrand(DAgentBrandEntity entity);
}
