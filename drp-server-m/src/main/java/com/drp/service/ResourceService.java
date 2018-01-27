package com.drp.service;

import com.drp.entity.RArticleEntity;
import com.drp.entity.RBannerEntity;
import com.drp.entity.RRegionEntity;

import java.util.List;
import java.util.Map;

public interface ResourceService {
    public Map<String,Object> getBannerList(Integer status, Integer pageNum, Integer pageSize);

    public Integer saveBanner(RBannerEntity entity, Integer userId);

    public Map<String,Object> getArticleList( Integer displayArea, Integer status, Integer pageNum, Integer pageSize);

    public RArticleEntity getArticleDetailById(Integer distributorId, Integer id);

    public Integer saveArticle(RArticleEntity entity, Integer userId);

    public List<RRegionEntity> getProvinceList();

    public List<RRegionEntity> getCityByProvince(String provinceId);

    public List<RRegionEntity> getAreaByCity(String cityId);

}
