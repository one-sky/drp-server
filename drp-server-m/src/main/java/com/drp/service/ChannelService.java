package com.drp.service;

import com.drp.entity.DChannelEntity;
import com.drp.entity.RChannelResourceEntity;

import java.util.List;

public interface ChannelService {

    public List<RChannelResourceEntity> getChannelOptionList(Integer userType);

    public Integer saveChannelOption(RChannelResourceEntity entity);

    public List<DChannelEntity> getChannelList(Integer distributorId);

    public DChannelEntity getChannelById(Integer id);

    public Integer saveChannel(DChannelEntity entity);
}
