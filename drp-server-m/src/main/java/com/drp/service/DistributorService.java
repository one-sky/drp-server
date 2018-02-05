package com.drp.service;

import com.drp.entity.DAddressEntity;
import com.drp.entity.DAgentBrandEntity;
import com.drp.entity.DDistributorEntity;
import com.drp.vo.SearchVO;

import java.util.List;
import java.util.Map;

public interface DistributorService {

    public DDistributorEntity getDistributorDetail(Integer id);

    public DDistributorEntity getDistributorByUserId(Integer id);

    public Map<String,Object> getAddressList(Integer id);

    public DAddressEntity getAddress(Integer id);

    public Integer saveAddress(DAddressEntity entity);

    public Integer deleteAddress(Integer id, Integer distributorId);

    public Integer setAddressDefault(Integer id, Integer distributorId);

    public Map<String,Object> getPointList(SearchVO entity);


}
