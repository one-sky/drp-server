package com.drp.service.impl;

import com.drp.entity.DDistributorEntity;
import com.drp.entity.UExternalUserEntity;
import com.drp.repository.UserRepository;
import com.drp.service.DistributorService;
import com.drp.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
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
        if(null != entity) {
            Timestamp tmp = new Timestamp(new Date().getTime());
            //状态未完善
            entity.setLastLoginTime(entity.getThisLoginTime());
            entity.setThisLoginTime(tmp);
            userRepository.updateUser(entity);
            if(entity.getStatus() == (int)1){
                BeanUtils.copyProperties(entity, vo);
                vo.setId(0);
                vo.setUserId(entity.getId());
            }else{// 获取分销商信息
                vo = distributorService.getDistributorByUserId(entity.getId());
                vo.setUserId(entity.getId());
                vo.setLastLoginTime(entity.getLastLoginTime());
                vo.setThisLoginTime(entity.getThisLoginTime());
            }
            return vo;
        } else {
            return null;
        }

    }

    public List<UExternalUserEntity> getUserList(List<Integer> userIds) {
        return null;
    }

    public String register(String phone, String password) {
        UExternalUserEntity entity = new UExternalUserEntity();
        Timestamp tmp = new Timestamp(new Date().getTime());

        if(userRepository.checkPhoneRegister(phone)){
            return "该手机号已被注册";
        }
        entity.setPhone(phone);
        entity.setPassword(password);
        entity.setStatus(1);
        entity.setStatus(1);
        entity.setCreateTime(tmp);
        entity.setLastLoginTime(tmp);
        entity.setThisLoginTime(tmp);
        entity.setType("D");
        return userRepository.register(entity);
    }

    public Integer updatePassword(String phone, String oldPassword, String newPassword) {
        // 判断用户原始密码是否正确
        UExternalUserEntity dbEntity = userRepository.login(phone, oldPassword);
        if(null != dbEntity) {
            dbEntity.setPassword(newPassword);
            return userRepository.updateUser(dbEntity);
        }
        return 0;

    }

    public boolean checkMobileRegister(String phone) {
        return false;
    }

    public Integer completeDistributor(DDistributorEntity entity) {
        Timestamp tmp = new Timestamp(new Date().getTime());
        List<Integer> ids = new ArrayList<Integer>();
        ids.add(entity.getUserId());
        UExternalUserEntity dbEntity = userRepository.getUserList(ids).get(0);
        dbEntity.setStatus(3);
        dbEntity.setEmail(entity.getEmail());
        dbEntity.setNickname(entity.getNickName());

        entity.setPoints(0);
        entity.setVipId(1);
        entity.setTotalAmount(new BigDecimal(0));
        entity.setPhone(dbEntity.getPhone());
        entity.setCreateBy(999);
        entity.setCreateTime(tmp);
        entity.setLastUpdateBy(999);
        entity.setLastUpdateTime(tmp);
        if(userRepository.updateUser(dbEntity) == 1 && userRepository.insertDistributor(entity) > 0) {
            return 1;
        }
        return -1;

    }
}
