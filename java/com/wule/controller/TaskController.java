package com.wule.controller;

import com.wule.mapper.TaskMapper;
import com.wule.pojo.DingdanInfo;
import com.wule.pojo.TalkingInfo;
import com.wule.pojo.TaskInfo;
import com.wule.pojo.UserInfo;
import com.wule.service.DingdanService;
import com.wule.service.TalkingService;
import com.wule.service.TaskService;
import com.wule.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
public class TaskController {
    @Resource
    private UserService userService;
    @Resource
    private TalkingService talkingService;
    @Resource
    private TaskService taskService;
    @Resource
    private DingdanService dingdanService;

    @RequestMapping("/xiadan")
    @ResponseBody
    public String xiadan(@CookieValue("city") String city,@CookieValue("qidian") String qidian,@CookieValue("lng") String lng,@CookieValue("lat") String lat,
                         @CookieValue("zhongdian") String zhongdian,@CookieValue("lng2") String lng2,@CookieValue("lat2") String lat2,
                         String fangan,String distance, String time,String price, HttpServletRequest request)
    {//用户正式下单，加入任务表
        String telephone= (String) request.getSession().getAttribute("telephone");
        Date date=new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        SimpleDateFormat simpleDateFormat2=new SimpleDateFormat("yyyyMMddHHmmss");
        String send_time=simpleDateFormat.format(date);
        String ID=telephone+simpleDateFormat2.format(date);
        String zhuangtai="等待接单";
        TaskInfo taskInfo=new TaskInfo(ID,telephone,city,send_time,qidian,lng,lat,zhongdian,lng2,lat2,fangan,distance,time,price,zhuangtai);
        taskService.add_task(taskInfo);
        return ID;
    }

    @RequestMapping("/xiadan2")
    public String xiadan2(@RequestParam("ID") String ID,HttpServletRequest request, Model model)//拿到任务的ID后，前往用户的等待界面
    {
        model.addAttribute("ID",ID);
        return "xiadan";
    }

    @RequestMapping("/shifoujiedan")
    @ResponseBody
    public String[] shifoujiedan(String ID,HttpServletRequest request, Model model)//在用户的等待界面，不断尝试获取是否有司机接单
    {
        String zhuangtai=taskService.shifoujiedan(ID);
        String[] s={"",""};
        if(zhuangtai.equals("司机已接单"))
        {
            s[0]="YES";
            DingdanInfo dingdanInfo=dingdanService.select_by_ID(ID);
            s[1]=dingdanInfo.getDriver_telephone();
        }
        else{
            s[0]="NO";

        }
        return s;
    }

    @RequestMapping("/go")
    public String go(@RequestParam("ID") String ID,HttpServletRequest request, Model model)
    {
        TaskInfo task=taskService.select2(ID);
        double Qidian_lng=Double.parseDouble(task.getQidian_lng());
        double Qidian_lat=Double.parseDouble(task.getQidian_lat());
        double Zhongdian_lng=Double.parseDouble(task.getZhongdian_lng());
        double Zhongdian_lat=Double.parseDouble(task.getZhongdian_lat());
        String Fangan=task.getFangan();
        model.addAttribute("ID",ID);
        model.addAttribute("Qidian_lng",Qidian_lng);
        model.addAttribute("Qidian_lat",Qidian_lat);
        model.addAttribute("Zhongdian_lng",Zhongdian_lng);
        model.addAttribute("Zhongdian_lat",Zhongdian_lat);
        model.addAttribute("Fangan",Fangan);
        return "go";
    }

    @RequestMapping("/le_driver2")
    @ResponseBody
    public List<TaskInfo> driver2(String city,String lng,String lat,String page,HttpServletRequest request, Model model)
    {//司机获取城市订单
        String telephone= (String) request.getSession().getAttribute("driver_telephone");
        int p =Integer.parseInt(page);
        request.getSession().setAttribute("page",p);
        double lng0=Double.parseDouble(lng);
        double lat0=Double.parseDouble(lat);
        List<TaskInfo> taskInfos=taskService.select(city,p);
        taskInfos.sort((o1, o2) -> {
            double lng1 = Double.parseDouble(o1.getQidian_lng());
            double lat1 = Double.parseDouble(o1.getQidian_lat());
            double distance = (lng1 - lng0) * (lng1 - lng0) + (lat1 - lat0) * (lat1 - lat0);
            double lng2 = Double.parseDouble(o2.getQidian_lng());
            double lat2 = Double.parseDouble(o2.getQidian_lat());
            double distance2 = (lng2 - lng0) * (lng2 - lng0) + (lat2 - lat0) * (lat2 - lat0);
            if (distance < distance2) {
                return -1;
            } else {
                return 0;
            }
        });
        if(telephone==null)
        {
            return null;
        }
        else{
            return taskInfos;
        }
    }

    @RequestMapping("/jiedan")
    public String jiedan(@RequestParam("ID") String ID, HttpServletRequest request, Model model)//司机接单，整个驾驶路线展示
    {
        TaskInfo task=taskService.select2(ID);
        double Qidian_lng=Double.parseDouble(task.getQidian_lng());
        double Qidian_lat=Double.parseDouble(task.getQidian_lat());
        double Zhongdian_lng=Double.parseDouble(task.getZhongdian_lng());
        double Zhongdian_lat=Double.parseDouble(task.getZhongdian_lat());
        String Fangan=task.getFangan();

        model.addAttribute("ID",ID);
        model.addAttribute("Qidian_lng",Qidian_lng);
        model.addAttribute("Qidian_lat",Qidian_lat);
        model.addAttribute("Zhongdian_lng",Zhongdian_lng);
        model.addAttribute("Zhongdian_lat",Zhongdian_lat);
        model.addAttribute("Fangan",Fangan);

        if(task.getZhuangtai().equals("等待接单"))
        {
            String telephone= task.getTelephone();
            String driver_telephone= (String) request.getSession().getAttribute("driver_telephone");
            Date date=new Date();
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
            SimpleDateFormat simpleDateFormat2=new SimpleDateFormat("yyyyMMddHHmmssSSS");
            String jiedan_time=simpleDateFormat.format(date);
            DingdanInfo dingdanInfo=new DingdanInfo(ID,telephone,driver_telephone,jiedan_time,"","","司机已接单","","","","");
            dingdanService.add_dingdan(dingdanInfo);
            task.setZhuangtai("司机已接单");
            taskService.task_update(task);

            UserInfo userInfo=userService.my_message(telephone);
            if(userInfo.getCar_number()!=null&&userInfo.getCar_type()!=null)
            {
                String s="司机您好，我的车是"+userInfo.getCar_type()+"，车牌号为"+userInfo.getCar_number()+"，来的路上请您注意交通安全";
                String final_time=simpleDateFormat.format(date);
                String talkingID=ID+simpleDateFormat2.format(date);
                TalkingInfo talkingInfo=new TalkingInfo(talkingID,ID,"用户发言",s,final_time,"no");
                talkingService.add_talking(talkingInfo);
            }
            TalkingInfo talkingInfo2=new TalkingInfo(ID+"userlng",ID,"用户经度",task.getQidian_lng(),"","");
            talkingService.add_talking(talkingInfo2);
            talkingInfo2.setTalkingID(ID+"userlat");
            talkingInfo2.setTypee("用户纬度");
            talkingInfo2.setValuee(task.getQidian_lat());
            talkingService.add_talking(talkingInfo2);
        }
        return "driver_go";
    }


}
