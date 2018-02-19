package com.drp.repository;

import com.drp.entity.DDistributorEntity;
import com.drp.entity.RBrandEntity;
import com.drp.vo.ManageUserVO;

import java.util.List;

public interface ManageRepository {
    // 用于登陆，获取基本信息
    public List<ManageUserVO> getUserList(ManageUserVO entity, Integer pageSize, Integer startIndex );

    // 获取分销商的vip信息
    public List<ManageUserVO> getDistributorVip(List<ManageUserVO> entity);

    public List<RBrandEntity> getBrandList(RBrandEntity entity, Integer pageSize, Integer startIndex );
}
