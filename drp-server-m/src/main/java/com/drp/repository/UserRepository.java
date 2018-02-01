package com.drp.repository;

import com.drp.entity.DDistributorEntity;
import com.drp.entity.UExternalUserEntity;

import java.util.List;

public interface UserRepository {

    UExternalUserEntity login(String phone, String password);

    Integer updateLoginTime(UExternalUserEntity entity);

    List<UExternalUserEntity> getUserList(List<Integer> userIds);

    String register(UExternalUserEntity entity);

    boolean checkPhoneRegister(String phone);

    Integer updateUser(UExternalUserEntity entity);

    Integer insertDistributor(DDistributorEntity entity);

}
