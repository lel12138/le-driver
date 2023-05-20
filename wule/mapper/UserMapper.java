package com.wule.mapper;

import com.wule.pojo.UserInfo;

public interface UserMapper {
    UserInfo denglu(UserInfo userInfo);
    void zhuce(UserInfo userInfo);
    UserInfo my_message(String telephone);
    void update_message(UserInfo userInfo);
    UserInfo wannabe(Integer start);
}
