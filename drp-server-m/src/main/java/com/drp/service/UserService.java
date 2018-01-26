package com.drp.service;

import com.drp.entity.DDistributorEntity;
import com.drp.entity.UExternalUserEntity;

import java.util.List;

public interface UserService {
    DDistributorEntity login(String phone, String password);

    List<UExternalUserEntity> getUserList(List<Integer> userIds);

    DDistributorEntity register(String phone, String password);

    boolean checkMobileRegister(String phone);

}
