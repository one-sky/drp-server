package com.drp.repository;

import com.drp.entity.DDistributorEntity;
import com.drp.entity.UExternalUserEntity;

import java.util.List;

public interface UserRepository {

    UExternalUserEntity login(String phone, String password);

    List<UExternalUserEntity> getUserList(List<Integer> userIds);

    DDistributorEntity register(String phone, String password);

    boolean checkMobileRegister(String phone);
}
