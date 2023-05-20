package com.wule.service;


import com.wule.mapper.DingdanMapper;
import com.wule.pojo.DingdanInfo;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CookieValue;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@CacheConfig(cacheNames = "dingdan")
public class DingdanService {
    @Resource
    private DingdanMapper dingdanMapper;

    public void add_dingdan(DingdanInfo dingdanInfo) {
        dingdanMapper.add_dingdan(dingdanInfo);
    }

    @Cacheable(key = "#ID")
    public DingdanInfo select_by_ID(String ID){
        return dingdanMapper.select_by_ID(ID);
    }

    public List<DingdanInfo> select_by_driver(String driver_telephone,int start){
        Map<String,Object> map=new HashMap<>();
        map.put("driver_telephone",driver_telephone);
        map.put("start",start*3);
        return dingdanMapper.select_by_driver(map);
    }

    @CacheEvict(key = "#dingdanInfo.ID")
    public void dingdan_update(DingdanInfo dingdanInfo){
        dingdanMapper.dingdan_update(dingdanInfo);
    }

    public List<DingdanInfo> select(Integer start){
        return dingdanMapper.select(start);
    }
}
