package com.drp.service;

import com.drp.entity.DChannelEntity;
import com.drp.entity.RChannelResourceEntity;

import java.util.List;

public interface ChannelService {

    public List<RChannelResourceEntity> getChannelOptionList();

    public Integer saveChannelOption(RChannelResourceEntity entity);

    public List<DChannelEntity> getChannelList(Integer distributorId);

    public DChannelEntity getChannel(Integer distributorId, Integer channelId);

    public Integer saveChannel(DChannelEntity entity);
}