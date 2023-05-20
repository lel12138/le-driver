package com.wule.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        String url=request.getRequestURI();
        if(url.indexOf("/my_message")==0)
        {
            String telephone= (String) request.getSession().getAttribute("telephone");
            if(telephone==null)
            {
                response.sendRedirect(request.getContextPath()+"/weideng");
                return false;
            }
            else{
                return true;
            }
        }
        else if (url.indexOf("/driver_message")==0||url.indexOf("/le_driver")==0)
        {
            String telephone2= (String) request.getSession().getAttribute("driver_telephone");
            if(telephone2==null)
            {
                response.sendRedirect(request.getContextPath()+"/driver_weideng");
                return false;
            }
            else{
                return true;
            }
        }
        else {
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

}
