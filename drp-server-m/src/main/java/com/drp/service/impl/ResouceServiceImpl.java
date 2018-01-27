package com.drp.service.impl;

import com.drp.Util.*;
import com.drp.entity.RArticleEntity;
import com.drp.entity.RBannerEntity;
import com.drp.Util.Insert;
import com.drp.entity.RRegionEntity;
import com.drp.repository.ResourceRepository;
import com.drp.service.ResourceService;
import com.inheater.erp.common.utils.DateUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ResouceServiceImpl implements ResourceService {

    @Autowired(required = true)
    protected ResourceRepository resourceRepository;
    public Map<String,Object> getBannerList(Integer status, Integer pageNum,
                                            Integer pageSize) {
        Map<String,Object> map=new HashMap<String,Object>();
        InitPage initPage = new InitPage(pageNum, pageSize);
        Integer initPageNum = initPage.getPageNum();
        Integer initPageSize = initPage.getPageSize();
        Integer initStartIndex = initPage.getStartIndex();
        List<RBannerEntity> dataList = resourceRepository.getBannerList(status, initPageNum, initStartIndex);
        PageModel pageInfo = new PageModel<RBannerEntity>(dataList, initPageNum, initPageSize);
        map.put("dataList",dataList);
        map.put("pageInfo",pageInfo);
        return map;
    }

    public Integer saveBanner(RBannerEntity entity, Integer userId) {
        Timestamp currentTime = DateUtil.getCurrentDate();
        Integer id= entity.getId();
        //新建时
        if(null == id || id ==0){
            entity.setStatus(0);//新建时设为无效状态
            entity.setCreateBy(userId);
            entity.setLastUpdateBy(userId);
            return resourceRepository.insertBanner(entity);
        }else{
            RBannerEntity dbBanner=resourceRepository.selectBannerByKey(id);
            if(dbBanner == null){
                return null;
            }
            entity.setCreateBy(dbBanner.getCreateBy());
            entity.setCreateTime(dbBanner.getCreateTime());
            entity.setLastUpdateBy(userId);
            entity.setLastUpdateTime(currentTime);
            return resourceRepository.updateBanner(entity);


        }
    }

    public Map<String,Object> getArticleList(Integer displayArea, Integer status,
                                               Integer pageNum, Integer pageSize) {
        Map<String,Object> map=new HashMap<String,Object>();
        InitPage initPage = new InitPage(pageNum, pageSize);
        Integer initPageNum = initPage.getPageNum();
        Integer initPageSize = initPage.getPageSize();
        Integer initStartIndex = initPage.getStartIndex();

        List<RArticleEntity> dataList = resourceRepository.getArticleList(displayArea, status,
                 initPageSize, initStartIndex);
        PageModel pageInfo = new PageModel<RArticleEntity>(dataList, initPageNum, initPageSize);
        map.put("dataList",dataList);
        map.put("pageInfo",pageInfo);
        return map;
    }

    public RArticleEntity getArticleDetailById(Integer distributorId, Integer id) {
        return null;
    }

    public Integer saveArticle(RArticleEntity entity, Integer userId) {
        Timestamp currentTime = DateUtil.getCurrentDate();
        Integer id= entity.getId();
        //新建时
        if(null == id || id ==0){
            entity.setStatus(0);//新建时设为无效状态
            entity.setCreateBy(userId);
            entity.setLastUpdateBy(userId);
            return resourceRepository.insertArticle(entity);
        }else{
            RArticleEntity dbArticle=resourceRepository.selectArticleByKey(id);
            if(dbArticle == null){
                return null;
            }
            BeanUtils.copyProperties(dbArticle,entity);
            entity.setLastUpdateBy(userId);
            entity.setLastUpdateTime(currentTime);
            return resourceRepository.updateArticle(entity);


        }
    }

    public List<RRegionEntity> getProvinceList() {
        return resourceRepository.getProvinceList();
    }

    public List<RRegionEntity> getCityByProvince(String provinceId) {
        return resourceRepository.getCityByProvince(provinceId);
    }

    public List<RRegionEntity> getAreaByCity(String cityId) {
        return resourceRepository.getAreaByCity(cityId);
    }


}
