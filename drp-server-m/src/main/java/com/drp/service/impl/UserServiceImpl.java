package com.drp.service.impl;

import com.drp.entity.DDistributorEntity;
import com.drp.entity.UExternalUserEntity;
import com.drp.repository.UserRepository;
import com.drp.service.DistributorService;
import com.drp.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired(required = true)
    protected UserRepository userRepository;
    @Autowired(required = true)
    private DistributorService distributorService;

    public DDistributorEntity login(String phone, String password){
        DDistributorEntity vo = new DDistributorEntity();
        UExternalUserEntity entity = userRepository.login(phone, password);
        //状态未完善
        if(entity.getStatus()==3){
            BeanUtils.copyProperties(vo,entity);
            return vo;
        }else{// 获取分销商信息
            return distributorService.getDistributorByUserId(entity.getId());
        }
    }

    public List<UExternalUserEntity> getUserList(List<Integer> userIds) {
        return null;
    }

    public DDistributorEntity register(String phone, String password) {
        DDistributorEntity vo = userRepository.register(phone,password);
        return vo;
    }

    public boolean checkMobileRegister(String phone) {
        return false;
    }
}
