package com.drp.service.impl;

import com.drp.Util.BeanUtils;
import com.drp.Util.InitPage;
import com.drp.Util.PageModel;
import com.drp.entity.*;
import com.drp.repository.DistributorRepository;
import com.drp.service.DistributorService;
import com.inheater.erp.common.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DistributorServiceImpl implements DistributorService {

    @Autowired(required = true)
    protected DistributorRepository distributorRepository;

    public DDistributorEntity getDistributorDetail(Integer id) {
        return distributorRepository.getDistributorDetail(id);
    }

    public DDistributorEntity getDistributorByUserId(Integer id) {
        return distributorRepository.getDistributorByUserId(id);
    }

    public Map<String, Object> getAddressList(Integer id) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<DAddressEntity> dataList = distributorRepository.getAddressList(id);
        map.put("dataList", dataList);
        return map;
    }

    public DAddressEntity getAddress(Integer id) {
        return distributorRepository.getAddress(id);
    }


    public Integer saveAddress(DAddressEntity entity) {
        Timestamp tmp = DateUtil.getCurrentDate();
        Integer distributorId = entity.getDistributorId();
        Integer id = entity.getId();
        //新建时
        if (null == distributorId || distributorId == 0) {
            List<DAddressEntity> list = distributorRepository.getAddressList(distributorId);
            //没有地址
            if (list.isEmpty()) {
                entity.setIsDefault("Y");

            } else {
                DAddressEntity dbAddress = distributorRepository.selectAddressByKey(id);
                if (dbAddress == null) {
                    return null;
                }
                entity.setIsDefault("N");


            }
            entity.setCreateBy(999);
            entity.setCreateTime(tmp);
            entity.setLastUpdateBy(999);
            entity.setLastUpdateTime(tmp);
            return distributorRepository.updateAddress(entity);
        } else {
            //先查找出改地址的记录信息
            DAddressEntity dbAddress = distributorRepository.selectAddressByKey(entity.getId());
            BeanUtils.copyProperties(entity, dbAddress);

            dbAddress.setLastUpdateBy(999);
            dbAddress.setLastUpdateTime(tmp);
            return distributorRepository.updateAddress(dbAddress);
        }
    }

    //删除地址的时候需要判断，如果删除的是默认地址的话，那么就让排在最前的那条为默认地址
    public Integer deleteAddress(Integer id, Integer distributorId) {
        Timestamp tmp = DateUtil.getCurrentDate();
        DAddressEntity address = distributorRepository.selectAddressByKey(id);
        //判断是否为 默认地址
        if ("Y".equals(address.getIsDefault())) {
            if (distributorRepository.deleteAddress(id) == 1) {
                //当删除的为默认地址时
                DAddressEntity example = new DAddressEntity();
                List<DAddressEntity> list = distributorRepository.getAddressList(distributorId);
                if (list.size() != 0) {
                    //设置最新的地址为默认地址
                    DAddressEntity addre = list.get(0);
                    addre.setIsDefault("Y");
                    addre.setLastUpdateBy(999);
                    addre.setLastUpdateTime(tmp);
                    return distributorRepository.updateAddress(addre);
                }
            }

        } else {
            //如果不是默认地址，那么直接删掉
            return distributorRepository.deleteAddress(id);
        }

        return 0;
    }

    public Integer setAddressDefault(Integer id, Integer distributorId) {
        Timestamp tmp = DateUtil.getCurrentDate();
        //先取得所有的地址
        List<DAddressEntity> list = distributorRepository.getAddressList(distributorId);
        DAddressEntity entity = (DAddressEntity) list.get(0);
        if (!list.isEmpty() && "Y".equals(entity.getIsDefault())) {
            entity.setLastUpdateBy(999);
            entity.setLastUpdateTime(tmp);
            entity.setIsDefault("N");
            distributorRepository.updateAddress(entity);
        }
        //最后更新被选中的默认地址
        DAddressEntity address = distributorRepository.selectAddressByKey(id);
        address.setIsDefault("Y");
        entity.setLastUpdateBy(999);
        entity.setLastUpdateTime(tmp);
        return distributorRepository.updateAddress(address);
    }

}
