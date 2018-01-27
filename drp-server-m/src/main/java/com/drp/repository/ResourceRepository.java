package com.drp.repository;

import com.drp.entity.PProductPromotionEntity;
import com.drp.entity.RArticleEntity;
import com.drp.entity.RBannerEntity;
import com.drp.entity.RRegionEntity;

import java.util.List;
import java.util.Map;

public interface ResourceRepository {

    public List<RBannerEntity> getBannerList(Integer status, Integer pageSize, Integer startIndex);

    public RBannerEntity selectBannerByKey(Integer id);

    public Integer insertBanner(RBannerEntity entity);

    public Integer updateBanner(RBannerEntity entity);

    public List<RArticleEntity> getArticleList( Integer displayArea, Integer status, Integer pageSize, Integer startIndex);

    public RArticleEntity getArticleDetailById(Integer id);

    public RArticleEntity selectArticleByKey(Integer id);

    public Integer insertArticle(RArticleEntity entity);

    public Integer updateArticle(RArticleEntity entity);

    public List<RArticleEntity> getNoticeList( Integer userType, Integer pageSize, Integer startIndex);

    public List<PProductPromotionEntity> getPromotionList(Integer userType, Integer pageSize, Integer startIndex);

    public List<RRegionEntity> getProvinceList();

    public List<RRegionEntity> getCityByProvince(String provinceId);

    public List<RRegionEntity> getAreaByCity(String cityId);


}
