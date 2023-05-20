package com.wule.controller;

import com.wule.pojo.DingdanInfo;
import com.wule.pojo.TaskInfo;
import com.wule.pojo.UserInfo;
import com.wule.pojo.WholeDingdan;
import com.wule.service.DingdanService;
import com.wule.service.TaskService;
import com.wule.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class DingdanController {

    @Resource
    private TaskService taskService;
    @Resource
    private DingdanService dingdanService;
    @Resource
    private UserService userService;

    @RequestMapping("/my_dingdan")
    public String my_dingdan(@RequestParam("page") String page,UserInfo userInfo, HttpServletRequest request, Model model)//用户查看历史订单
    {
        String telephone= (String) request.getSession().getAttribute("telephone");
        int p = 0;
        switch (page) {
            case "first":
                request.getSession().setAttribute("page", 0);
                p = 0;
                break;
            case "up":
                p = (int) request.getSession().getAttribute("page");
                if (p > 0) {
                    p--;
                }
                break;
            case "down":
                p = (int) request.getSession().getAttribute("page");
                p++;
                break;
        }
        request.getSession().setAttribute("page",p);

        List<TaskInfo> taskInfos=taskService.select_by_user(telephone,p);
        List<WholeDingdan> wholeDingdans=new ArrayList<>();

        for (TaskInfo taskInfo:taskInfos){
            String ID=taskInfo.getID();
            if(taskInfo.getZhuangtai().equals("等待接单"))
            {
                WholeDingdan wholeDingdan=new WholeDingdan(taskInfo);
                wholeDingdans.add(wholeDingdan);
            }
            else
            {
                DingdanInfo dingdan=dingdanService.select_by_ID(ID);
                WholeDingdan wholeDingdan=new WholeDingdan(dingdan,taskInfo);
                wholeDingdans.add(wholeDingdan);
            }
        }
        model.addAttribute("wholeDingdans",wholeDingdans);
        return "my_dingdan";
    }

    @RequestMapping("/driver_dingdan")
    public String my_dingdan2(@RequestParam("page") String page, UserInfo userInfo, HttpServletRequest request, Model model)//司机查看历史接单
    {
        String driver_telephone= (String) request.getSession().getAttribute("driver_telephone");
        int p = 0;
        switch (page) {
            case "first":
                request.getSession().setAttribute("page", 0);
                p = 0;
                break;
            case "up":
                p = (int) request.getSession().getAttribute("page");
                if (p > 0) {
                    p--;
                }
                break;
            case "down":
                p = (int) request.getSession().getAttribute("page");
                p++;
                break;
        }
        request.getSession().setAttribute("page",p);
        List<DingdanInfo> dingdanInfo=dingdanService.select_by_driver(driver_telephone,p);
        List<WholeDingdan> wholeDingdans=new ArrayList<>();
        TaskInfo taskInfo;

        for (DingdanInfo dingdan:
                dingdanInfo) {
            String ID=dingdan.getID();
            taskInfo=taskService.select2(ID);
            WholeDingdan wholeDingdan=new WholeDingdan(dingdan,taskInfo);
            wholeDingdans.add(wholeDingdan);
        }
        model.addAttribute("wholeDingdans",wholeDingdans);
        return "driver_dingdan";
    }



    @RequestMapping("/about")
    public String about(@RequestParam("ID") String ID,@RequestParam("I_am") String I_am,HttpServletRequest request, Model model)//订单详情查看
    {
        TaskInfo taskInfo=taskService.select2(ID);
        if(taskInfo.getZhuangtai().equals("等待接单"))
        {
            WholeDingdan wholeDingdan=new WholeDingdan(taskInfo);
            model.addAttribute("need",wholeDingdan);
            model.addAttribute("I_am",I_am);
            return "task_about";
        }
        else
        {
            DingdanInfo dingdan =dingdanService.select_by_ID(ID);
            WholeDingdan wholeDingdan=new WholeDingdan(dingdan,taskInfo);
            model.addAttribute("need",wholeDingdan);
            model.addAttribute("I_am",I_am);
            return "dingdan_about";
        }
    }

    @RequestMapping("/finish")
    public String finish(@RequestParam("ID") String ID,HttpServletRequest request, Model model)
    {
        model.addAttribute("ID",ID);
        return "finish";
    }

    @RequestMapping("/pingjia")
    public String pingjia(@RequestParam("ID") String ID, String[] pingjia, HttpServletRequest request, Model model)
    {
        StringBuilder pingjia1= new StringBuilder();
        int price=0;
        for (String s: pingjia) {
            pingjia1.append(s).append(" ");
            switch (s) {
                case "态度良好，服务到位":
                    price += 0;
                    break;
                case "因司机人为原因导致超时":
                    price += 2;
                    break;
                case "未按照预定路线行驶":
                    price += 4;
                    break;
                case "态度恶劣，服务很差":
                    price +=4;
                    break;
            }
        }
        String price2= price +"%";
        DingdanInfo dingdanInfo=dingdanService.select_by_ID(ID);
        dingdanInfo.setKanjia(price2);
        dingdanInfo.setWhy_kanjia(String.valueOf(pingjia1));
        dingdanService.dingdan_update(dingdanInfo);
        if(!dingdanInfo.getTijia().equals("")&&dingdanInfo.getZhuangtai().equals("订单完成"))
        {
            jiezhang(ID);
        }
        String msg = "<script type='text/javascript'>alert('评价完毕，可以在历史订单中查看详情')</script>";
        model.addAttribute("error", msg);
        return "le_daijia";
    }

    @RequestMapping("/driver_finish")
    public String driver_finish(@RequestParam("ID") String ID,HttpServletRequest request, Model model)
    {
        model.addAttribute("ID",ID);
        return "driver_finish";
    }

    @RequestMapping("/driver_pingjia")
    public String driver_finish(@RequestParam("ID") String ID,String[] pingjia,HttpServletRequest request, Model model)
    {
        StringBuilder pingjia1= new StringBuilder();
        int price=0;
        for (String s: pingjia) {
            pingjia1.append(s).append(" ");
            switch (s) {
                case "态度良好，服务到位":
                    price += 0;
                    break;
                case "为乘客提供额外帮助":
                    price += 3;
                    break;
                case "天气或交通状况恶劣":
                    price += 3;
                    break;
                case "乘客态度恶劣，干扰行驶":
                    price +=4;
                    break;
            }
        }
        String price2= price +"%";
        DingdanInfo dingdanInfo=dingdanService.select_by_ID(ID);
        dingdanInfo.setTijia(price2);
        dingdanInfo.setWhy_tijia(String.valueOf(pingjia1));
        dingdanService.dingdan_update(dingdanInfo);
        if(!dingdanInfo.getKanjia().equals("")&&dingdanInfo.getZhuangtai().equals("订单完成"))
        {
            jiezhang(ID);
        }

        String msg = "<script type='text/javascript'>alert('评级完毕，可以在历史订单中查看详情')</script>";
        model.addAttribute("error", msg);
        return "le_driver";
    }

    public void jiezhang(String ID)
    {
        DingdanInfo dingdanInfo=dingdanService.select_by_ID(ID);
        UserInfo userInfo=userService.my_message(dingdanInfo.getTelephone());
        int score=Integer.parseInt(userInfo.getScore());//用户信誉分
        int usermoney=Integer.parseInt(userInfo.getMoney());//用户余额
        userInfo =userService.my_message(dingdanInfo.getDriver_telephone());
        int score2=Integer.parseInt(userInfo.getScore());//司机信誉分
        int usermoney2=Integer.parseInt(userInfo.getMoney());//司机余额
        TaskInfo taskInfo=taskService.select2(ID);
        int price=Integer.parseInt(taskInfo.getPrice().replace("元",""));
        int kanjia=Integer.parseInt(dingdanInfo.getKanjia().replace("%",""));
        int tijia=Integer.parseInt(dingdanInfo.getTijia().replace("%",""));

        int final_price=(int)((kanjia*score-tijia*score2+1000)*0.001*price);
        usermoney-=final_price;
        usermoney2+=final_price;

        userInfo.setMoney(String.valueOf(usermoney2));
        userService.update_message(userInfo);
        userInfo=userService.my_message(dingdanInfo.getTelephone());
        userInfo.setMoney(String.valueOf(usermoney));
        userService.update_message(userInfo);
        dingdanInfo.setFinal_price(final_price+"元");
        dingdanInfo.setZhuangtai("订单已结账");
        dingdanService.dingdan_update(dingdanInfo);
        taskInfo.setZhuangtai("订单已结账");
        taskService.task_update(taskInfo);
    }
}
