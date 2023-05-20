package com.wule.controller;

import com.wule.pojo.DingdanInfo;
import com.wule.pojo.TaskInfo;
import com.wule.pojo.UserInfo;
import com.wule.pojo.WholeDingdan;
import com.wule.service.DingdanService;
import com.wule.service.TaskService;
import com.wule.service.UserService;
import com.wule.service.yanzhengma;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Controller
public class UserController {
    @Resource
    private UserService userService;
    @Resource
    private TaskService taskService;
    @Resource
    private DingdanService dingdanService;
    @Resource
    private yanzhengma yanzhengma1;

    @RequestMapping("/le_daijia")
    public String kaishi() { return "le_daijia"; }

    @RequestMapping("/weideng")
    public String me(Model model) {
        String s=yanzhengma1.set_yanzhengma();
        model.addAttribute("s",s);
        return "denglu";
    }

    @RequestMapping("/denglu")
    public String denglu(UserInfo userInfo,String yanzhengma,  HttpServletRequest request, Model model) {
        if(!yanzhengma1.compare(yanzhengma))
        {
            String msg = "<script type='text/javascript'>alert('验证码错误')</script>";
            model.addAttribute("error", msg);
            String s=yanzhengma1.set_yanzhengma();
            model.addAttribute("s",s);
            return "denglu";
        }
        UserInfo userInfo1= userService.denglu(userInfo);
        if (userInfo1!=null) {
            request.getSession().setAttribute("telephone",userInfo1.getTelephone());
            request.getSession().setAttribute("name","已登录："+userInfo1.getName());
            return "le_daijia";
        } else {
            String msg = "<script type='text/javascript'>alert('用户名或密码错误')</script>";
            model.addAttribute("error", msg);
            String s=yanzhengma1.set_yanzhengma();
            model.addAttribute("s",s);
            return "denglu";
        }
    }

    @RequestMapping("/zhuce")
    public String zhuce() { return "zhuce"; }

    @RequestMapping("/zhuce2")
    public String zhuce2(String telephone,HttpServletRequest request,Model model)//注册检查手机号
    {
        UserInfo userInfo1=userService.is_zhuce(telephone);
        if(userInfo1!=null)
        {
            String msg = "<script type='text/javascript'>alert('此手机号已经注册')</script>";
            model.addAttribute("error", msg);
            return "zhuce";
        }
        request.getSession().setAttribute("new_telephone",telephone);
        return "zhuce2";
    }

    @RequestMapping("/zhuce3")
    public String zhuce3(String name,String password,HttpServletRequest request,Model model)//注册最后一步
    {
        String telephone= (String) request.getSession().getAttribute("new_telephone");
        UserInfo userInfo=new UserInfo();
        userInfo.setTelephone(telephone);
        userInfo.setPassword(password);
        userInfo.setName(name);
        userService.zhuce(userInfo);
        request.getSession().removeAttribute("new_telephone");
        String s=yanzhengma1.set_yanzhengma();
        model.addAttribute("s",s);
        return "denglu";
    }

    @RequestMapping("/out")
    public String out(HttpServletRequest request)//退出登录
    {
        request.getSession().removeAttribute("telephone");
        request.getSession().removeAttribute("name");
        return "le_daijia";
    }

    @RequestMapping("/my_message")
    public String my_message(HttpServletRequest request, Model model)//用户信息
    {
        String telephone= (String) request.getSession().getAttribute("telephone");
        UserInfo userInfo1=userService.my_message(telephone);
        model.addAttribute("userInfo",userInfo1);
        return "my_message";
    }



    @RequestMapping("/update_message")
    public String update_message(UserInfo userInfo, HttpServletRequest request, Model model)//修改用户信息
    {
        String telephone= (String) request.getSession().getAttribute("telephone");
        userInfo.setTelephone(telephone);
        if(userInfo.getPassword().length()==0&&userInfo.getName().length()==0&&userInfo.getJinji().length()==0&&userInfo.getLocation().length()==0&&userInfo.getCar_type().length()==0&&userInfo.getCar_number().length()==0)
        {

            String msg = "<script type='text/javascript'>alert('请填入需要改动的信息')</script>";
            model.addAttribute("error", msg);
            UserInfo userInfo1=userService.my_message(telephone);
            model.addAttribute("userInfo",userInfo1);
            return "my_message";
        }
        else{
            userService.update_message(userInfo);
            UserInfo userInfo1=userService.my_message(telephone);
            model.addAttribute("userInfo",userInfo1);
            return "my_message";
        }

    }



    @RequestMapping("/my_zhanghu")
    public String my_zhanghu(UserInfo userInfo, HttpServletRequest request, Model model)//用户账户
    {
        String telephone= (String) request.getSession().getAttribute("telephone");
        UserInfo userInfo1=userService.my_message(telephone);
        model.addAttribute("userInfo",userInfo1);
        return "my_zhanghu";
    }

    @RequestMapping("/money")
    @ResponseBody
    public String money(@RequestParam("telephone") String telephone,@RequestParam("do") String doo,HttpServletRequest request, Model model)
    {
        UserInfo userInfo1=userService.my_message(telephone);
        if(doo.equals("add_money"))
        {
            int money=Integer.parseInt(userInfo1.getMoney())+100;
            userInfo1.setMoney(String.valueOf(money));
            userService.update_message(userInfo1);
            return "RMB:"+money+"元";
        }
        else
        {
            int money=Integer.parseInt(userInfo1.getMoney());
            if(money>0)
            {
                money=0;
            }
            userInfo1.setMoney(String.valueOf(money));
            userService.update_message(userInfo1);
            return "RMB:"+money+"元";
        }
    }

    @RequestMapping("/qidian")
    public String qidian(HttpServletRequest request,Model model)//设置起点
    {
        String telephone= (String) request.getSession().getAttribute("telephone");
        if(telephone==null)
        {
            String msg = "<script type='text/javascript'>alert('请先登录账号')</script>";
            model.addAttribute("error", msg);
            return "le_daijia";
        }
        return "qidian";
    }

    @RequestMapping("/zhongdian")
    public String zhongdian(HttpServletRequest request,Model model)//设置终点
    {
        String telephone= (String) request.getSession().getAttribute("telephone");
        if(telephone==null)
        {
            String msg = "<script type='text/javascript'>alert('请先登录账号')</script>";
            model.addAttribute("error", msg);
            return "le_daijia";
        }
        return "zhongdian";
    }

    @RequestMapping("/zhunbeixiadan")
    public String zhunbeixiadan()//准备下单，会将路线展示给用户
    {
        return "zhunbeixiadan";
    }

    @RequestMapping("/forget")
    public String forget(@RequestParam("I_am") String I_am,HttpServletRequest request)//忘记密码
    {
        request.getSession().setAttribute("I_am",I_am);
        return "forget";
    }

    @RequestMapping("/forget2")
    public String forget2(String telephone,HttpServletRequest request,Model model)//忘记密码后修改密码第一步，手机号验证码
    {
        UserInfo userInfo1=userService.is_zhuce(telephone);
        if(userInfo1==null)
        {
            String msg = "<script type='text/javascript'>alert('此手机号尚未注册')</script>";
            model.addAttribute("error", msg);
            return "forget";
        }
        else {
            request.getSession().setAttribute("change_password_telephone", telephone);
            return "forget2";
        }
    }
    @RequestMapping("/forget3")
    public String forget3(String password,String password2,HttpServletRequest request,Model model)//忘记密码后修改密码第二步，重置密码
    {
        if(!password.equals(password2))
        {
            String msg = "<script type='text/javascript'>alert('两次输入的密码不一致')</script>";
            model.addAttribute("error", msg);
            return "forget2";
        }
        String telephone= (String) request.getSession().getAttribute("change_password_telephone");
        UserInfo userInfo=new UserInfo();
        userInfo.setTelephone(telephone);
        userInfo.setPassword(password);
        userService.update_message(userInfo);
        request.getSession().removeAttribute("change_password_telephone");

        String s=yanzhengma1.set_yanzhengma();
        model.addAttribute("s",s);

        String I_am=(String)request.getSession().getAttribute("I_am");
        request.getSession().removeAttribute("I_am");
        if(I_am.equals("driver"))
        {
            return "driver_denglu";
        }
        else{
            return "denglu";
        }
    }

    @RequestMapping("/is_user")
    @ResponseBody
    public String is_user(String telephone,HttpServletRequest request,Model model) //是已有用户吗？
    {
        UserInfo userInfo1 = userService.is_zhuce(telephone);
        if (userInfo1 == null) {
            return "NO";
        } else {
            return "OK";
        }
    }

    @RequestMapping("/shiming")
    public String shiming(@RequestParam("I_am") String I_am,HttpServletRequest request,Model model)//实名认证，用户司机共用
    {
        request.getSession().setAttribute("I_am",I_am);
        return "shiming";
    }

    @RequestMapping("/shiming2")
    public String shiming2(String shenfenzhenghao, HttpServletRequest request,Model model) {//实名认证信息修改
        String I_am = (String) request.getSession().getAttribute("I_am");
        request.getSession().removeAttribute("I_am");
        String telephone = "";
        if (I_am.equals("driver"))
            telephone = (String) request.getSession().getAttribute("driver_telephone");
        else
            telephone = (String) request.getSession().getAttribute("telephone");
        UserInfo userInfo = new UserInfo();
        userInfo.setTelephone(telephone);
        userInfo.setID_card_number(shenfenzhenghao);
        userService.update_message(userInfo);
        UserInfo userInfo1 = userService.my_message(telephone);
        model.addAttribute("userInfo", userInfo1);
        if (I_am.equals("driver"))
            return "driver_message";
        else
            return "my_message";
    }

    @RequestMapping("/be_a_driver")
    public String be_a_driver(HttpServletRequest request,Model model)
    {
        String telephone= (String) request.getSession().getAttribute("telephone");
        UserInfo userInfo=userService.my_message(telephone);
        if(userInfo.getID_card_number()==null)
        {
            String msg = "<script type='text/javascript'>alert('请先做实名认证')</script>";
            model.addAttribute("error", msg);
            model.addAttribute("userInfo", userInfo);
            return "my_message";
        }
        return "be_a_driver";
    }//司机申请

    @RequestMapping("/be_a_driver2")
    public String be_a_driver2(@RequestParam("shenfenzheng1") MultipartFile file1,@RequestParam("shenfenzheng2") MultipartFile file2,@RequestParam("jiashizheng") MultipartFile file3, HttpServletRequest request, Model model) throws Exception
    {//司机申请第二步，存图
        String telephone = (String) request.getSession().getAttribute("telephone");
        UserInfo userInfo=new UserInfo();
        userInfo.setTelephone(telephone);
        userInfo.setIs_driver("be_a_driver");
        userService.update_message(userInfo);

        String a[]={"PNG","JPG","JPEG","BMP"};
        MultipartFile files[]={file1,file2,file3};
        int i=1;
        for (MultipartFile file:files) {
            try
            {
                if(file.isEmpty())
                {
                    String msg = "<script type='text/javascript'>alert('文件为空！')</script>";
                    model.addAttribute("error", msg);
                    return "be_a_driver";
                }
                else{
                    String filename=file.getOriginalFilename();
                    boolean ok=false;
                    for (String s:a)
                    {
                        if(filename.toUpperCase().contains(s))
                        {
                            ok=true;
                            break;
                        }
                    }
                    if(!ok)
                    {
                        String msg = "<script type='text/javascript'>alert('文件格式不正确！')</script>";
                        model.addAttribute("error", msg);
                        return "be_a_driver";
                    }
                    else
                    {
                        String newname=telephone+"+"+i+filename.substring(filename.lastIndexOf("."));
                        String localPath="D:/Java EE/wule/src/main/webapp/images/";
                        File newfile=new File(localPath,newname);
                        newfile.mkdirs();
                        file.transferTo(newfile);
                    }
                }
            }catch (Exception e)
            {
                e.printStackTrace();
            }
            i++;
        }


        UserInfo userInfo1 = userService.my_message(telephone);
        model.addAttribute("userInfo", userInfo1);
        String msg = "<script type='text/javascript'>alert('申请成功，我们将会尽快联系您')</script>";
        model.addAttribute("error", msg);
        return "my_message";
    }
}

