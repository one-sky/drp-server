package com.drp.service.impl;

import com.drp.entity.DChannelEntity;
import com.drp.entity.RChannelResourceEntity;
import com.drp.repository.ChannelRepository;
import com.drp.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class ChannelServiceImpl implements ChannelService {

    @Autowired
    protected ChannelRepository channelRepository;

    public List<RChannelResourceEntity> getChannelOptionList() {
        return channelRepository.getChannelOptionList();
    }

    public Integer saveChannelOption(RChannelResourceEntity entity) {
        Integer id= entity.getId();
        //新建时
        if(null == id || id ==0){
            entity.setStatus(0);//新建时设为无效状态
            return channelRepository.insertChannelOption(entity);
        }else{
            RChannelResourceEntity dbChannel=channelRepository.selectChannelOptionByKey(id);
            if(dbChannel == null) {
                return null;
            }
            return channelRepository.updateChannelOption(entity);
        }
    }

    public List<DChannelEntity> getChannelList(Integer distributorId) {
        return channelRepository.getChannelList(distributorId);
    }

    public DChannelEntity getChannel(Integer distributorId, Integer channelId) {
        return channelRepository.getChannel(distributorId, channelId);
    }

    public Integer saveChannel(DChannelEntity entity) {
        Timestamp tmp = new Timestamp(new Date().getTime());
        Integer id= entity.getId();
        //新建时
        if(null == id || id ==0){
            entity.setCreateBy(999);
            entity.setCreateTime(tmp);
            entity.setLastUpdateBy(999);
            entity.setLastUpdateTime(tmp);
            return channelRepository.insertChannel(entity);
        }
        return 0;
    }
}
