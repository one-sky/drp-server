package com.drp.repository;

import com.drp.entity.*;
import com.drp.vo.SearchVO;

import java.util.List;

public interface DistributorRepository {

    // 获取分销商的所有详细信息
    public DDistributorEntity getDistributorDetail(Integer id);

    // 用于登陆，获取基本信息
    public DDistributorEntity getDistributorByUserId(Integer id);

    // 更改分销商信息
    public Integer updateDistributor(DDistributorEntity entity);

    // 获取分销商的vip信息
    public DDistributorEntity getDistributorVip(Integer distributorId, Integer vipId);

    // 获取分销商地址列表
    public List<DAddressEntity> getAddressList(Integer id);

    // 获取分销商地址
    public DAddressEntity getAddress(Integer id);

    public DAddressEntity selectAddressByKey(Integer id);

    public Integer insertAddress(DAddressEntity entity);

    public Integer updateAddress(DAddressEntity entity);

    public Integer deleteAddress(Integer id);

    public Integer insertPoint(DPointsHistoryEntity entity);

    public List<DPointsHistoryEntity> getPointList(SearchVO entity);


}
