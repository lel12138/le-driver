package com.wule.service;

import com.wule.mapper.DingdanMapper;
import com.wule.mapper.TaskMapper;
import com.wule.pojo.DingdanInfo;
import com.wule.pojo.TaskInfo;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@CacheConfig(cacheNames = "task")
public class TaskService {
    @Resource
    private TaskMapper taskMapper;
    @Resource
    private DingdanMapper dingdanMapper;

    public void add_task(TaskInfo taskInfo){
        taskMapper.add_task(taskInfo);
    }

    public List<TaskInfo> select(String city,int start){
        Map<String,Object> map=new HashMap<>();
        map.put("city",city);
        map.put("start",start*5);
        return taskMapper.select_task(map);
    }

    @Cacheable(key = "#ID")
    public TaskInfo select2(String ID){
        return taskMapper.select_task2(ID);
    }

    @CacheEvict(key = "#taskInfo.ID")
    public void task_update(TaskInfo taskInfo){
        taskMapper.task_update(taskInfo);
    }

    public String shifoujiedan(String ID)
    {
        return taskMapper.shifoujiedan(ID);
    }

    public List<TaskInfo> select_by_user(String telephone, int start){
        Map<String,Object> map=new HashMap<>();
        map.put("telephone",telephone);
        map.put("start",start*3);
        return taskMapper.select_by_user(map);
    }
}
