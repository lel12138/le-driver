package com.wule.mapper;
import com.wule.pojo.DingdanInfo;
import com.wule.pojo.TaskInfo;

import java.util.List;
import java.util.Map;

public interface DingdanMapper {
    void add_dingdan(DingdanInfo dingdanInfo);


    List<DingdanInfo> select_by_driver(Map<String, Object> driver_telephone);

    DingdanInfo select_by_ID(String ID);

    void dingdan_update(DingdanInfo dingdanInfo);

    List<DingdanInfo> select(Integer start);
}
