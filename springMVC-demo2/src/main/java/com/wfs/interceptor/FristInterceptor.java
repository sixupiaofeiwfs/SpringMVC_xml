package com.wfs.interceptor;


import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class FristInterceptor implements HandlerInterceptor {


    //访问时间：Controller方法处理之前
    //执行顺序：链式Interceptor情况下，Interceptor按照声明的顺序依次执行
    //若返回false 则中断执行，注意：不会进入afterCompletion
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        System.out.println("1------preHandle");
        return true;
    }

    /**
     *调用的前提：preHendle返回true
     * 调用时间：Controller方法处理完成之后，DispatcherServlet进行视图渲染之前，也就是说在这个方法中可以对ModelAndView进行操作
     * 执行顺序：链式Interceptor情况下，Interceptor按照声明的顺序倒序进行
     * 备注：postHandle虽然post打头，但post get方法都能处理
     *
     *
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("1------postHandle");

    }

    /**
     *调用前提：perHandle返回true
     * 调用时间：DispatcherServlet进行视图渲染之后
     * 多用于清理资源
     *
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("1------afterCompletion");
    }
}
