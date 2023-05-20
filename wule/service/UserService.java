package com.wule.service;

import com.wule.mapper.UserMapper;
import com.wule.pojo.UserInfo;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@CacheConfig(cacheNames = "user")
public class UserService {
    @Resource
    private UserMapper mapper;

    public UserInfo denglu(UserInfo userInfo){//登录
        return mapper.denglu(userInfo);
    }

    public void zhuce(UserInfo userInfo){//注册
        mapper.zhuce(userInfo);
    }

    @Cacheable(key = "#telephone")
    public UserInfo my_message(String telephone){//获取信息
        return mapper.my_message(telephone);
    }

    public UserInfo is_zhuce(String telephone){
        return mapper.my_message(telephone);
    }

    @CacheEvict(key = "#userInfo.telephone")//更改信息会清空这个用户的缓存
    public void update_message(UserInfo userInfo)//更改信息
    {
        mapper.update_message(userInfo);
    }

    public UserInfo wannabe(Integer start)
    {
        return mapper.wannabe(start);
    }
}
