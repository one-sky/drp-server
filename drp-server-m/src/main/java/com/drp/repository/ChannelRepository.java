package com.drp.repository;

import com.drp.entity.DChannelEntity;
import com.drp.entity.RChannelResourceEntity;

import java.util.List;

public interface ChannelRepository {

    public List<RChannelResourceEntity> getChannelOptionList(Integer userType);

    public RChannelResourceEntity selectChannelOptionByKey(Integer id);

    public Integer insertChannelOption(RChannelResourceEntity entity);

    public Integer updateChannelOption(RChannelResourceEntity entity);

    public List<DChannelEntity> getChannelList(Integer distributorId);

    public DChannelEntity getChannel(Integer distributorId, Integer channelId);

    public Integer insertChannel(DChannelEntity entity);

}
