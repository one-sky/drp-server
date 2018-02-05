package com.drp.service;

import com.drp.entity.DDistributorEntity;
import com.drp.entity.UExternalUserEntity;

import java.util.List;
import java.util.Map;

public interface UserService {
    public DDistributorEntity login(String phone, String password);

    public List<UExternalUserEntity> getUserList(List<Integer> userIds);

    public String register(String phone, String password);

    public Integer updatePassword(String phone, String oldPassword, String newPassword);

    public boolean checkMobileRegister(String phone);

    public Integer completeDistributor(DDistributorEntity entity);

}
