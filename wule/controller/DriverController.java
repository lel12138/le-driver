package com.wule.controller;

import com.wule.pojo.DingdanInfo;
import com.wule.pojo.TaskInfo;
import com.wule.pojo.UserInfo;
import com.wule.pojo.WholeDingdan;
import com.wule.service.DingdanService;
import com.wule.service.TaskService;
import com.wule.service.UserService;
import com.wule.service.yanzhengma;
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
public class DriverController {

    @Resource
    private UserService userService;
    @Resource
    private yanzhengma yanzhengma1;


    @RequestMapping("/le_driver")
    public String driver(HttpServletRequest request, Model model)
    {
        return "le_driver";
    }

    @RequestMapping("/driver_weideng")
    public String me(HttpServletRequest request, Model model)
    {
        String s=yanzhengma1.set_yanzhengma();
        model.addAttribute("s",s);
        return "driver_denglu";
    }

    @RequestMapping("/change_yanzhengma")//公用，验证码点击更换
    @ResponseBody
    public String change_yanzhengma(HttpServletRequest request, Model model) {
        String s=yanzhengma1.set_yanzhengma();
        return s;
    }

    @RequestMapping("/driver_denglu")//司机登录
    public String driver_denglu(UserInfo userInfo,String yanzhengma, HttpServletRequest request, Model model) {
        if(!yanzhengma1.compare(yanzhengma))
        {
            String msg = "<script type='text/javascript'>alert('验证码错误')</script>";
            model.addAttribute("error", msg);
            String s=yanzhengma1.set_yanzhengma();
            model.addAttribute("s",s);
            return "driver_denglu";
        }
        UserInfo userInfo1= userService.denglu(userInfo);
        if (userInfo1!=null) {
            if(userInfo1.getIs_driver().equals("no"))
            {
                String msg = "<script type='text/javascript'>alert('您还不是司机')</script>";
                model.addAttribute("error", msg);
                String s=yanzhengma1.set_yanzhengma();
                model.addAttribute("s",s);
                return "driver_denglu";
            }
            request.getSession().setAttribute("driver_telephone",userInfo1.getTelephone());
            request.getSession().setAttribute("driver_name","已登录："+userInfo1.getName());
            return "le_driver";
        } else {
            String msg = "<script type='text/javascript'>alert('用户名或密码错误')</script>";
            model.addAttribute("error", msg);
            String s=yanzhengma1.set_yanzhengma();
            model.addAttribute("s",s);
            return "driver_denglu";
        }
    }

    @RequestMapping("/driver_out")//退登
    public String out(HttpServletRequest request)
    {
        request.getSession().removeAttribute("driver_telephone");
        request.getSession().removeAttribute("driver_name");
        return "le_driver";
    }

    @RequestMapping("/driver_message")//司机个人信息
    public String my_message(HttpServletRequest request, Model model)
    {
        String telephone= (String) request.getSession().getAttribute("driver_telephone");
        UserInfo userInfo1=userService.my_message(telephone);
        model.addAttribute("userInfo",userInfo1);
        return "driver_message";
    }



    @RequestMapping("/update_driver_message")//修改信息
    public String update_message(UserInfo userInfo, HttpServletRequest request, Model model)
    {
        String telephone= (String) request.getSession().getAttribute("driver_telephone");
        userInfo.setTelephone(telephone);
        if(userInfo.getPassword().length()==0||userInfo.getName().length()==0||userInfo.getJinji().length()==0||userInfo.getLocation().length()==0||userInfo.getCar_type().length()==0||userInfo.getCar_number().length()==0)
        {

            String msg = "<script type='text/javascript'>alert('请填入需要改动的信息')</script>";
            model.addAttribute("error", msg);
            UserInfo userInfo1=userService.my_message(telephone);
            model.addAttribute("userInfo",userInfo1);
            return "driver_message";
        }
        else{
            userService.update_message(userInfo);
            UserInfo userInfo1=userService.my_message(telephone);
            model.addAttribute("userInfo",userInfo1);
            return "driver_message";
        }
    }




    @RequestMapping("/driver_zhanghu")//司机账户
    public String my_zhanghu(UserInfo userInfo, HttpServletRequest request, Model model)
    {
        String telephone= (String) request.getSession().getAttribute("driver_telephone");
        UserInfo userInfo1=userService.my_message(telephone);
        model.addAttribute("userInfo",userInfo1);
        return "driver_zhanghu";
    }

}
