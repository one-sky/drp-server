package com.drp.service;

import com.drp.entity.DDistributorEntity;
import com.drp.entity.RBrandEntity;
import com.drp.entity.UExternalUserEntity;
import com.drp.vo.AgentBrandVO;
import com.drp.vo.ManageUserVO;

import java.util.Map;

public interface ManageService {
    public Map<String, Object>  getUserList(ManageUserVO entity, Integer pageNum, Integer pageSize);

    public Integer updatePoint(Integer userId, Integer point, String reason);

    public Map<String, Object>  getBrandList(RBrandEntity entity, Integer pageNum, Integer pageSize);

    public Integer saveBrand(RBrandEntity entity);

    public Integer deleteBrand(Integer brandId);

    public Map<String, Object>  getAgentBrandList(AgentBrandVO entity, Integer pageNum, Integer pageSize);

}
