package com.wule.mapper;

import com.wule.pojo.DingdanInfo;
import com.wule.pojo.TaskInfo;

import java.util.List;
import java.util.Map;

public interface TaskMapper {
    void add_task(TaskInfo taskInfo);

    List<TaskInfo> select_task(Map<String, Object> map);

    TaskInfo select_task2(String ID);

    void task_update(TaskInfo taskInfo);

    String shifoujiedan(String ID);

    List<TaskInfo> select_by_user(Map<String, Object> map);
}
