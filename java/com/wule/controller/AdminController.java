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
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminController
{
    @Resource
    private UserService userService;
    @Resource
    private DingdanService dingdanService;
    @Resource
    private TaskService taskService;

    @RequestMapping("/admin")
    public String admin(HttpServletRequest request, Model model)//用户信息
    {
        return "admin_denglu";
    }

    @RequestMapping("/admin_denglu")
    public String admin_denglu(UserInfo userInfo,HttpServletRequest request, Model model)//用户信息
    {
        UserInfo userInfo1= userService.denglu(userInfo);
        if(userInfo1==null)
        {
            String msg = "<script type='text/javascript'>alert('账号或密码错误')</script>";
            model.addAttribute("error", msg);
            return "admin_denglu";
        }
        else{
            if(userInfo1.getIs_driver().equals("no") ||userInfo1.getIs_driver().equals("yes") ){
                String msg = "<script type='text/javascript'>alert('账号或密码错误')</script>";
                model.addAttribute("error", msg);
                return "admin_denglu";
            }
            else
            {
                request.getSession().setAttribute("quanxian",userInfo1.getIs_driver());
                model.addAttribute("quanxian",userInfo1.getIs_driver());
                return "admin";
            }
        }
    }

    @RequestMapping("/admin_select_message")
    @ResponseBody
    public UserInfo admin_select_message(String telephone,String quanxian,HttpServletRequest request, Model model)//用户信息
    {
        UserInfo userInfo1=userService.my_message(telephone);
        if(quanxian.equals("普通"))
        {
            userInfo1.setID_card_number("权限不够");
            userInfo1.setPassword("权限不够");
        }
        return userInfo1;
    }

    @RequestMapping("/to_admin")
    public String to_admin(HttpServletRequest request, Model model) { return "admin"; }

    @RequestMapping("/to_admin_all_dingdan")
    public String to_admin_all_dingdan(@RequestParam("page") String page, HttpServletRequest request, Model model)
    {
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
        List<DingdanInfo> dingdanInfo=dingdanService.select(Integer.valueOf(p));
        List<WholeDingdan> wholeDingdans=new ArrayList<>();
        for (DingdanInfo dingdan:
                dingdanInfo) {
            String ID=dingdan.getID();
            TaskInfo taskInfo=taskService.select2(ID);
            WholeDingdan wholeDingdan=new WholeDingdan(dingdan,taskInfo);
            wholeDingdans.add(wholeDingdan);
        }
        model.addAttribute("wholeDingdans",wholeDingdans);
        return "admin_all_dingdan";
    }

    @RequestMapping("/to_admin_bedriver")
    public String to_admin_bedriver(@RequestParam("page2") String page,HttpServletRequest request, Model model)
    {
        int p = 0;
        switch (page) {
            case "first":
                request.getSession().setAttribute("page2", 0);
                p = 0;
                break;
            case "up":
                p = (int) request.getSession().getAttribute("page2");
                if (p > 0) {
                    p--;
                }
                break;
            case "down":
                p = (int) request.getSession().getAttribute("page2");
                p++;
                break;
        }
        request.getSession().setAttribute("page2",p);
        UserInfo userInfo=userService.wannabe(p);
        model.addAttribute("userInfo",userInfo);
        return "admin_bedriver";
    }

    @RequestMapping("/admin_tonghuoju")
    public String admin_tonghuoju(@RequestParam("telephone") String telephone,@RequestParam("doo") String doo,HttpServletRequest request, Model model)
    {
        UserInfo userInfo1=userService.my_message(telephone);
        if(doo.equals("tong"))
        {
            userInfo1.setIs_driver("yes");
        }
        else
        {
            userInfo1.setIs_driver("no");
        }
        userService.update_message(userInfo1);
        to_admin_bedriver("first",request,model);
        return "admin_bedriver";
    }

    @RequestMapping("/to_admin_changeuser")
    public String to_admin_changeuser(HttpServletRequest request, Model model)
    {

        return "admin_changeuser";
    }

    @RequestMapping("/to_admin_dingdan_select")
    public String to_admin_dingdan_select(HttpServletRequest request, Model model) { return "admin_dingdan_select"; }

    @RequestMapping("/admin_change_user")
    public String update_message(UserInfo userInfo, HttpServletRequest request, Model model)//修改用户信息
    {
        if(userInfo.getTelephone().length()!=11)
        {
            String msg = "<script type='text/javascript'>alert('信息输入错误')</script>";
            model.addAttribute("error", msg);
            return "admin_changeuser";
        }
        
        userService.update_message(userInfo);
        String msg = "<script type='text/javascript'>alert('修改完毕')</script>";
        model.addAttribute("error", msg);
        return "admin_changeuser";
    }

    @RequestMapping("/admin_select_dingdan")
    @ResponseBody
    public WholeDingdan admin_select_dingdan(String ID,HttpServletRequest request, Model model)
    {
        DingdanInfo dingdan =dingdanService.select_by_ID(ID);
        TaskInfo taskInfo=taskService.select2(ID);
        return new WholeDingdan(dingdan,taskInfo);
    }


}
