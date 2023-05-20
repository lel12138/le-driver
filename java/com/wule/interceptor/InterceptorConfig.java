package com.wule.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册LoginInterceptor拦截器
        InterceptorRegistration registration = registry.addInterceptor(new LoginInterceptor());

        registration.addPathPatterns("/**"); //所有路径都被拦截

        registration.excludePathPatterns(     //添加不拦截路径
                "/*.jsp",               //控制器中响应登录请求的方法url
                "/*.html",            //html页面不会被拦截
                "/js/**",              //js静态资源
                "/css/**",             //css静态资源
                "/images/**"          //images静态资源
        );
    }

}
