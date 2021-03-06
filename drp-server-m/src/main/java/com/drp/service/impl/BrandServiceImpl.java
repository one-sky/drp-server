package com.drp.service.impl;

import com.drp.Util.BeanUtils;
import com.drp.Util.InitPage;
import com.drp.Util.PageModel;
import com.drp.entity.*;
import com.drp.repository.BrandRepository;
import com.drp.repository.ChannelRepository;
import com.drp.service.BrandService;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    protected BrandRepository brandRepository;

    @Autowired
    protected ChannelRepository channelRepository;

    public Map<String,Object> getBrandListByAgentBrand(Integer distributorId, String brandName, String status,
                                                       Integer pageNum, Integer pageSize) {
        Map<String,Object> map=new HashMap<String,Object>();
        InitPage initPage = new InitPage(pageNum, pageSize);
        Integer initPageNum = initPage.getPageNum();
        Integer initPageSize = initPage.getPageSize();
        Integer initStartIndex = initPage.getStartIndex();
        if(null == status || "".equals(status)) {
            status = "Y";
        } else if("all".equals(status)) {
            status = null;
        }

        List<RBrandEntity> dataList = new ArrayList<RBrandEntity>();
        if (null != distributorId && distributorId != 0) {
            dataList = brandRepository.getBrandListByAgentBrand(distributorId, brandName, status, initPageSize, initStartIndex);
        }
        PageModel pageInfo = new PageModel<RBrandEntity>(dataList, initPageNum, initPageSize);
        map.put("dataList",dataList);
        map.put("pageInfo",pageInfo);
        return map;
    }

    public List<RBrandEntity> getBrandListByCategoryId(Integer categoryId, Integer userType) {
        return brandRepository.getBrandListByCategoryId(categoryId, userType);
    }

    public DAgentBrandEntity getAgentBrand(Integer distributorId, Integer brandId) {
        return brandRepository.getAgentBrand(distributorId, brandId);
    }

    public List<RBrandEntity> getNewBrandList(Integer num) {
        return brandRepository.getNewBrandList(num);
    }

    public RBrandEntity getBrandById(Integer id) {
        RBrandEntity entity = brandRepository.getBrandById(id);
        entity.setBrandAttachmentEntityList(this.getBrandAttachment(id));
        return entity;
    }

    public List<RBrandAttachmentEntity> getBrandAttachment(Integer brandId) {
        return brandRepository.getBrandAttachment(brandId);
    }

    public Integer insertAgentBrand(DAgentBrandEntity entity) {

        Integer distributorId = entity.getDistributorId();
        Integer brandId = entity.getBrandId();
        Integer channelId = entity.getChannelId();
        // 用户已代理该渠道
        DChannelEntity channel =channelRepository.getChannelById(channelId);
        if(channel!=null){
            if(distributorId != 0 && brandId !=0 && channelId!=0){
                DAgentBrandEntity dbAgent =  brandRepository.getAgentBrand(distributorId, brandId);
                if(dbAgent == null){
                    Timestamp tmp = new Timestamp(new Date().getTime());
                    entity.setStatus("W");
                    entity.setCreateBy(999);
                    entity.setCreateTime(tmp);
                    entity.setLastUpdateBy(999);
                    entity.setLastUpdateTime(tmp);
                    return brandRepository.insertAgentBrand(entity);
                } else if("R".equals(dbAgent.getStatus())) {
                    Timestamp tmp = new Timestamp(new Date().getTime());
                    BeanUtils.copyProperties(dbAgent, entity);
                    entity.setStatus("W");
                    entity.setChannelId(channelId);
                    entity.setReason(null);
                    entity.setBrandCertificate(null);
                    entity.setLastUpdateBy(999);
                    entity.setLastUpdateTime(tmp);
                    return brandRepository.updateAgentBrand(entity);
                }
            }
        }

        return 0;

    }

    public List<RBrandEntity> getAgentBrandByChannel(Integer distributorId, Integer channelId) {
        return brandRepository.getAgentBrandByChannel(distributorId, channelId);
    }
}
