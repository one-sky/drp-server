package com.drp.service.impl;

import com.drp.Util.InitPage;
import com.drp.Util.PageModel;
import com.drp.entity.*;
import com.drp.repository.BrandRepository;
import com.drp.repository.ChannelRepository;
import com.drp.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        List<RBrandEntity> dataList = brandRepository.getBrandListByAgentBrand(distributorId, brandName, status, initPageSize, initStartIndex);
        PageModel pageInfo = new PageModel<RBrandEntity>(dataList, initPageNum, initPageSize);
        map.put("dataList",dataList);
        map.put("pageInfo",pageInfo);
        return map;
    }

    public List<RBrandEntity> getBrandListByCategoryId(Integer categoryId) {
        return brandRepository.getBrandListByCategoryId(categoryId);
    }

    public DAgentBrandEntity getAgentBrand(Integer distributorId, Integer brandId, Integer channelId) {
        return brandRepository.getAgentBrand(distributorId, brandId, channelId);
    }

    public List<RBrandEntity> getNewBrandList() {
        return brandRepository.getNewBrandList();
    }

    public RBrandEntity getBrand(Integer id) {
        return brandRepository.getBrand(id);
    }

    public RBrandAttachmentEntity getBrandAttachment(Integer brandId) {
        return brandRepository.getBrandAttachment(brandId);
    }

    public Integer insertAgentBrand(DAgentBrandEntity entity) {

        Integer distributorId = entity.getDistributorId();
        Integer brandId = entity.getDistributorId();
        Integer channelId = entity.getChannelId();
        // 用户已代理该渠道
        DChannelEntity channel =channelRepository.getChannel(distributorId, channelId);
        if(channel!=null){
            if(distributorId != 0 && brandId !=0 && channelId!=0){
                DAgentBrandEntity dbAgent =  brandRepository.getAgentBrand(distributorId, brandId, channelId);
                if(dbAgent ==null){
                    Timestamp tmp = new Timestamp(new Date().getTime());
                    entity.setStatus("W");
                    entity.setCreateBy(999);
                    entity.setCreateTime(tmp);
                    entity.setLastUpdateBy(999);
                    entity.setLastUpdateTime(tmp);
                    return brandRepository.insertAgentBrand(entity);
                }
            }
        }

        return 0;

    }
}
