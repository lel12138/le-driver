package com.wule.mapper;

import com.wule.pojo.TalkingInfo;

import java.util.List;

public interface TalkingMapper {
    void add_talking(TalkingInfo talkingInfo);

    String select_lnglat(TalkingInfo talkingInfo);

    List<TalkingInfo> select_talking(String ID);

    void update_lnglat(TalkingInfo talkingInfo);

    Integer driversee(String ID);

    Integer usersee(String ID);

    void driverseele(String ID);

    void userseele(String ID);
}
