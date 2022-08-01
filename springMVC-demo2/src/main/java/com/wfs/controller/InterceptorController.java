package com.wfs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class InterceptorController {


    /**
     * 由于配置了两个拦截器，正常的执行结果（preHandle返回true）如下：
     * 1------preHandle
     * 2------preHandle
     * Controller
     * 2------postHandle
     * 1------postHandle
     * 2------afterCompletion
     * 1------afterCompletion
     *
     * 可以看到preHandle方法在Controller方法之前执行  且执行顺序是按照配置的拦截器顺序执行
     * postHandle 和 afterCompletion 的执行顺序是按照配置拦截器的顺序倒序执行
     *
     *
     *
     * 当Second的preHandle方法返回false时，页面显示空白 执行结果如下：
     * 1------preHandle
     * 2------preHandle
     * 1------afterCompletion
     *
     *
     */


    @RequestMapping("/testInterceptor")
    public String testInterceptor(){
        System.out.println("Controller");
        return "success";
    }



    @RequestMapping("/testException")
    public String testException(){
        int i=1/0;
        return "success";
    }




}
