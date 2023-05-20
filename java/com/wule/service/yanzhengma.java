package com.wule.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class yanzhengma {
    private int i;

    public yanzhengma() {
        this.i = 0;
    }

    public String set_yanzhengma(){//随机函数选择一个验证码出来，并把它的编号记录在i中
        Random random=new Random();
        int ii=i;
        i=random.nextInt(6);
        while (i==ii)
        {
            i=random.nextInt(6);
        }
        return "error"+i+".jpg";
    }

    public boolean compare(String yanzhengma){//将验证码对应的编号做对比
        boolean a=false;
        switch (i)
        {
            case 0: if(yanzhengma.equalsIgnoreCase("GLGB"))
            {
                a=true;
            }break;
            case 1:if(yanzhengma.equalsIgnoreCase("PCBR"))
            {
                a=true;
            }break;
            case 2:if(yanzhengma.equalsIgnoreCase("WMYN"))
            {
                a=true;
            }break;
            case 3:if(yanzhengma.equalsIgnoreCase("BBOB"))
            {
                a=true;
            }break;
            case 4:if(yanzhengma.equalsIgnoreCase("RBOP"))
            {
                a=true;
            }break;
            case 5:if(yanzhengma.equalsIgnoreCase("NBHB"))
            {
                a=true;
            }break;
        }
        return a;
    }




}
