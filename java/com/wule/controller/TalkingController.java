package com.wule.controller;

import com.wule.pojo.DingdanInfo;
import com.wule.pojo.TalkingInfo;
import com.wule.pojo.TaskInfo;
import com.wule.service.DingdanService;
import com.wule.service.TalkingService;
import com.wule.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class TalkingController {
    @Resource
    private TalkingService talkingService;
    @Resource
    private TaskService taskService;
    @Resource
    private DingdanService dingdanService;

    @RequestMapping("/add_lnglat")
    @ResponseBody
    public void add_user_lnglat(String ID,String type,String lng,String lat,HttpServletRequest request, Model model)
    {
        String l;
        String talkingID;
        if(type.equals("用户"))
        {
            l=talkingService.select_user_lng(ID);
            talkingID=ID+"user";
        }
        else
        {
            l=talkingService.select_driver_lng(ID);
            talkingID=ID+"driver";
        }
        if(l==null)
        {
            TalkingInfo talkingInfo=new TalkingInfo(talkingID+"lng",ID,type+"经度",lng,"","");
            talkingService.add_talking(talkingInfo);
            talkingInfo.setTalkingID(talkingID+"lat");
            talkingInfo.setTypee(type+"纬度");
            talkingInfo.setValuee(lat);
            talkingService.add_talking(talkingInfo);
        }
        else {
            TalkingInfo talkingInfo = new TalkingInfo(talkingID+"lng",ID, type + "经度", lng, "","");
            talkingService.update_latlng(talkingInfo);
            talkingInfo.setTalkingID(talkingID+"lat");
            talkingInfo.setTypee(type + "纬度");
            talkingInfo.setValuee(lat);
            talkingService.update_latlng(talkingInfo);
        }
    }

    @RequestMapping("/select_lnglat")
    @ResponseBody
    public String[] select_lnglat(String ID,String type,String lng3,String lat3,HttpServletRequest request, Model model)
    {
        String i=String.valueOf(talkingService.see(ID,type));
        String zhuangtai=dingdanService.select_by_ID(ID).getZhuangtai();
        if(zhuangtai.equals("订单完成")||zhuangtai.equals("订单已结账"))
        {
            return new String[]{"NO","NO",i};
        }
        if(type.equals("用户"))
        {
            try {
                String lng=talkingService.select_user_lng(ID);
                String lat=talkingService.select_user_lat(ID);
                return new String[]{lng,lat,i};
            }catch (Exception e)
            {
                System.out.println("查询不到");
                return new String[]{"KONG","KONG",i};
            }
        }
        else
        {
            try {
                String lng=talkingService.select_user_lng(ID);
                String lat=talkingService.select_user_lat(ID);
                String lng2=talkingService.select_driver_lng(ID);
                String lat2=talkingService.select_driver_lat(ID);

                double lng11 = Double.parseDouble(lng);
                double lat11 = Double.parseDouble(lat);
                double lng22 = Double.parseDouble(lng2);
                double lat22 = Double.parseDouble(lat2);
                double lng33= Double.parseDouble(lng3);
                double lat33= Double.parseDouble(lat3);
                double distance = (lng22 - lng33) * (lng22 - lng33) + (lat22 - lat33) * (lat22 - lat33);
                if(distance<1E-7)
                {
                    double distance2 = (lng22 - lng11) * (lng22 - lng11) + (lat22 - lat11) * (lat22 - lat11);
                    if(distance2<1E-7)
                    {
                        TaskInfo taskInfo=new TaskInfo();
                        taskInfo.setID(ID);
                        taskInfo.setZhuangtai("订单完成");
                        taskService.task_update(taskInfo);
                        DingdanInfo dingdanInfo=new DingdanInfo();
                        dingdanInfo.setID(ID);
                        Date date=new Date();
                        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
                        String final_time=simpleDateFormat.format(date);
                        dingdanInfo.setFinal_time(final_time);
                        dingdanInfo.setZhuangtai("订单完成");
                        dingdanService.dingdan_update(dingdanInfo);
                        return new String[]{"NO","NO",i};
                    }
                }
                return new String[]{lng2,lat2,i};
            }catch (Exception e)
            {
                System.out.println("查询不到");
                return new String[]{"KONG","KONG",i};
            }
        }
    }

    @RequestMapping("/select_talking")
    public String select_talking(@RequestParam("ID") String ID, HttpServletRequest request, Model model) {
        model.addAttribute("ID",ID);
        return "talking";
    }

    @RequestMapping("/select_talking_driver")
    public String select_talking_driver(@RequestParam("ID") String ID, HttpServletRequest request, Model model) {
        model.addAttribute("ID",ID);
        return "driver_talking";
    }

    @RequestMapping("/select_talking2")
    @ResponseBody
    public List<TalkingInfo> select_talking2(String ID,String typee, HttpServletRequest request, Model model) {
        List<TalkingInfo> talkingInfos=talkingService.select_talking(ID);
        talkingService.seele(ID,typee);
        return talkingInfos;
    }

    @RequestMapping("/say")
    @ResponseBody
    public String say(String ID,String typee,String valuee,HttpServletRequest request, Model model)
    {
        Date date=new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        String timee=simpleDateFormat.format(date);
        SimpleDateFormat simpleDateFormat2=new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String talkingID=ID+simpleDateFormat2.format(date);
        TalkingInfo talkingInfo=new TalkingInfo(talkingID,ID,typee,valuee,timee,"no");
        talkingService.add_talking(talkingInfo);
        return "OK";
    }

    @RequestMapping("/jiesudingdan")
    @ResponseBody
    public String[] jiesudingdan(String ID,String price,HttpServletRequest request, Model model)
    {
        TaskInfo taskInfo=taskService.select2(ID);
        Integer price2=Integer.parseInt(taskInfo.getPrice().replace("元",""))-Integer.parseInt(price);
        taskInfo.setPrice(String.valueOf(price2));
        taskInfo.setZhuangtai("订单完成");
        taskService.task_update(taskInfo);
        DingdanInfo dingdanInfo = new DingdanInfo();
        dingdanInfo.setID(ID);
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        String final_time = simpleDateFormat.format(date);
        dingdanInfo.setFinal_time(final_time);
        dingdanInfo.setZhuangtai("订单完成");
        dingdanService.dingdan_update(dingdanInfo);
        return new String[]{"NO","NO"};
    }
}
