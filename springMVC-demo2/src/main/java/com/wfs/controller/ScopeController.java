package com.wfs.controller;


import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 谨以此类演示获取域对象的情况
 * 域对象指的是在一定范围内数据存储的对象，有四种
 * 1.PageContext域：指的是当前jsp页面范围内有效
 * 2.request 一次请求内有效
 * 3.session 一次会话内有效（浏览器打开到浏览器关闭）
 * 4.application 整个web工程范围内都有效
 */

@Controller
public class ScopeController {

    /**
     * 使用ServletAPI向request域对象共享数据
     */

    @RequestMapping("/getScopeByServlet")
    public String getScopeByServlet(HttpServletRequest request){
        request.setAttribute("name","王风山");
        return "success";

    }

    /**
     * 通过ModelAndView向request域对象共享数据
     */
    @RequestMapping("/getScopeByModelAndView")
    public ModelAndView getScopeByModelAndView(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("name","modelAndView");
        modelAndView.setViewName("success");
        return modelAndView;
    }

    /**
     * 通过model的方式向request域对象共享数据
     */
    @RequestMapping("/getScopeByModel")
    public String getScopeByModel(Model model){
        model.addAttribute("name","model");
        return "success";
    }

    /**
     * 通过Map的方式向request域对象共享数据
     */
    @RequestMapping("/getScopeByMap")
    public String getScopeByMap(Map<String,Object> map){
        map.put("name","map");
        return "success";
    }

    /**
     * 通过ModelMap的方式向request域对象共享数据
     */
    @RequestMapping("/getScopeByModelMap")
    public String getScopeByModelMap(ModelMap modelMap){
        modelMap.addAttribute("name","modelMap");
        return "success";
    }


    /**
     * 通过ServletAPI向session域共享数据
     */
    @RequestMapping("/setSessionByServletAPI")
    public String setSessionByServletAPI(HttpSession session){
        session.setAttribute("name","servletApi--session");


        return "success";
    }

    /**
     * 通过servletAPI的方式向application域共享对象
     *
     */
    @RequestMapping("/setApplicationByServletApi")
    public String setApplicationByServletApi(HttpSession session){

        ServletContext application = session.getServletContext();
        application.setAttribute("name","servletApi---application");
        return "success";

    }

    /**
     * 演示重定向
     */
    @RequestMapping("/testRedirect")
    public String testRedirect(){
        return "redirect:setApplicationByServletApi";
    }

    /**
     * 演示视图解析器。当且仅当controller只用来实现页面跳转，即只需要设置视图名称时，可以将处理器方法使用view-controller标签表示，
     * 一旦使用该标签，其他控制器的请求映射全部失效，此时需要在springMVC.xml文件中开启mvc注解驱动 代码如下：
     *  <mvc:view-controller path="/index" view-name="success"></mvc:view-controller>
     *     <mvc:annotation-driven/>
     *
     */
}
