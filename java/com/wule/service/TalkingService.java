package com.wule.service;

import com.wule.mapper.TalkingMapper;
import com.wule.pojo.TalkingInfo;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TalkingService {
    @Resource
    private TalkingMapper talkingMapper;


    public void add_talking(TalkingInfo talkingInfo)//添加对话
    {
        talkingMapper.add_talking(talkingInfo);
    }

    public String select_user_lng(String ID)
    {
        TalkingInfo talkingInfo=new TalkingInfo("",ID,"用户经度","","","");
        return talkingMapper.select_lnglat(talkingInfo);
    }

    public String select_user_lat(String ID)
    {
        TalkingInfo talkingInfo=new TalkingInfo("",ID,"用户纬度","","","");
        return talkingMapper.select_lnglat(talkingInfo);
    }

    public String select_driver_lng(String ID)
    {
        TalkingInfo talkingInfo=new TalkingInfo("",ID,"司机经度","","","");
        return talkingMapper.select_lnglat(talkingInfo);
    }

    public String select_driver_lat(String ID)
    {
        TalkingInfo talkingInfo=new TalkingInfo("",ID,"司机纬度","","","");
        return talkingMapper.select_lnglat(talkingInfo);
    }

    public List<TalkingInfo> select_talking(String ID)
    {
        return talkingMapper.select_talking(ID);
    }

    public void update_latlng(TalkingInfo talkingInfo)
    {
        talkingMapper.update_lnglat(talkingInfo);
    }

    public Integer see(String ID,String typee){
        if(typee.equals("司机"))
        {
            return talkingMapper.driversee(ID);
        }
        else {
            return talkingMapper.usersee(ID);
        }
    }

    public void seele(String ID,String typee){
        if(typee.equals("司机"))
        {
            talkingMapper.driverseele(ID);
        }
        else {
            talkingMapper.userseele(ID);
        }
    }
}
