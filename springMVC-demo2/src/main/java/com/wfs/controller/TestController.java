package com.wfs.controller;


import com.wfs.pojo.Student;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


/**
 * 总结：一定要注意:tomcat10仅支持servlet5以上，不兼容4   之前一直使用servlet4和tomcat10使用 导致一直出现404问题！！！！！！！！！！！！！
 *
 * 步骤：
 * 1.创建maven项目 导入依赖如下：
 *  <dependencies>
 *         <dependency>
 *             <groupId>org.springframework</groupId>
 *             <artifactId>spring-webmvc</artifactId>
 *             <version>5.3.21</version>
 *         </dependency>
 *         <dependency>
 *             <groupId>ch.qos.logback</groupId>
 *             <artifactId>logback-classic</artifactId>
 *             <version>1.2.3</version>
 *         </dependency>
 *         <dependency>
 *             <groupId>javax.servlet</groupId>
 *             <artifactId>javax.servlet-api</artifactId>
 *             <version>4.0.1</version>
 *             <scope>provided</scope>
 *         </dependency>
 *
 *         <dependency>
 *             <groupId>org.thymeleaf</groupId>
 *             <artifactId>thymeleaf-spring5</artifactId>
 *             <version>3.0.15.RELEASE</version>
 *         </dependency>
 *
 *
 *
 *     </dependencies>
 * 2.依次点击文件---项目结构---你的项目或模块（demo2）---web---添加---src\main\webapp\WEB-INF\web.xml
 * 3.在web.xml中创建servlet 代码如下：
 *  <servlet>
 *         <servlet-name>DispatcherServlet</servlet-name>
 *         <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
 *
 *         <init-param>
 *             <param-name>contextConfigLocation</param-name>
 *             <param-value>classpath:springMVC.xml</param-value>
 *         </init-param>
 *         <load-on-startup>1</load-on-startup>
 *     </servlet>
 *
 *     <servlet-mapping>
 *         <servlet-name>DispatcherServlet</servlet-name>
 *         <url-pattern>/</url-pattern>
 *     </servlet-mapping>
 * 4.在resource文件夹中创建springMVC.xml 并做扫描配置和thymeleaf的配置  一定要注意 上面的名称空间要全 一定要全
 * 5.在WEB-INF文件夹中创建目录templates 并在文件夹中创建index.html，在该文件中一定要声明使用thymeleaf模板引擎
 * 6.创建controller类 并添加注解  在该类中创建方法并添加注解@RequestMapping 完成框架搭建
 *
 */





@Controller
public class TestController {

    @RequestMapping("/")
    public String index(){
        return "index";
    }


    /**
     * 下面的内容用作测试@RequestMapping的各种属性
     * value、method、header、param
     */




    //页面中的请求url可以在末尾处添加空格  但是一定不能在首位出现空格   value属性的值是一个字符串数组，表示当前方法可以同时处理多个请求
    // <a th:href="@{/testValue }">测试value1</a>
    //<a th:href="@{/testvalue}">测试value2</a>
    @RequestMapping(value ={ "/testValue","testvalue"})
    public String testValue(){
        return "success";
    }


    /**
     *  1.在未设置method属性时，所有的请求方式，例如：GET POST都能成功
     * 2.当设置method属性之后，只能匹配设置好的请求方式，比如设置GET，则仅支持GET方式访问，不在支持其他方式
     */
    @RequestMapping( value="/testMethod",method = RequestMethod.GET)
    public String testMethod(){
        return "success";
    }


    /**
     * 1.params = {"username","!password"}表示请求地址中一定要满足两个条件，一个是一定要含有username参数，一定不能含有password参数
     * 测试方式：<a th:href="@{/testParam(username,passwor)}">测试param</a> 虽然IDE会报错，但是不影响功能的正常使用
     *
     * 2.params = {"username=123456","password!=admin"} 请求地址要满足两个条件，一个是一定要含有username参数，且参数值一定要为123456
     * 另一个是一定要含有password参数，且参数值不能为admin
     * <a th:href="@{/testParam(username='123456',password='admin')}">测试param</a>失败
     * <a th:href="@{/testParam(username='123456',password='adminadmin')}">测试param</a>成功
     */
    @RequestMapping(value="/testParam",params = {"username=123456","password!=admin"})
    public String testParam(){
        return "success";
    }


    /**
     * 将请求头中的host设置为8081，失败
     * @return
     */
    @RequestMapping(value = "/testHeader",headers = {"Host=8081"})
    public String testHeader(){
        return "success";
    }


    /**
     * 演示ant风格的请求---？
     * <a th:href="@{/testAnt/aaa}">测试Ant?1</a><br>
     * <a th:href="@{/testAnt/aaaa}">测试Ant?2</a><br>
     * 测试一成功 测试二失败 表明？表示任意的单个字符，特殊字符除外，比如："/"、“？”
     *
     * 其他的符号：
     *          *表示0个或多个任意字符，特殊字符除外
     *          **表示一层或多层的目录  只能使用 斜线**斜线xxx的方式。否则仅表示多个任意字符 不表示层级
     */

    @RequestMapping("/testAnt/a?a")
    public String testAnt(){
        return "success";
    }


    /**
     * 演示占位符请求
     */
    @RequestMapping("/testRest/{id}/{username}")
    public String testRest(@PathVariable("id") String id,@PathVariable("username") String username){
        System.out.println(id+"--------"+username);
        return "success";
    }


    /**
     * 演示servlet方式获取请求参数
     */
    @RequestMapping("/testServletGetParam")
    public String testServletGetParam(HttpServletRequest request){
        String username = request.getParameter("username");
        String password=request.getParameter("password");
        System.out.println(username+"______"+password);
        return "success";
    }

    /**
     * 演示通过控制器方法的形参获取请求参数
     */
    @RequestMapping("/testControllerGetParam")
    public String testControllerGetParam(String username,String password){
        System.out.println(username+"**********"+password);
        return "success";
    }


    /**
     * 演示@RequestParam的三个属性 value  required  defaultValue
     * required的默认值为true，表示请求文件中必须的包含value所设置的属性
     * defaultValue可以将必须的属性设置默认值
     *
     *
     *  @RequestHeader   @CookieValue的使用方法与RequestParam一致，不再演示
     */




    @RequestMapping("/testRequestParam")
    public String testRequestParam(@RequestParam(value = "username" ,required = true,defaultValue = "张三") String username){
        System.out.println(username);
        return "success";

    }


    /**
     * 演示实体类方式获取请求参数  此时会出现乱码的情况，这时候必须在web.xml中配置转码
     */

    @RequestMapping(value = "/testStudent",method = RequestMethod.POST)
    public String testStudent(Student student){
        System.out.println(student.getName());
        System.out.println(student.getTel());
        return "success";
    }


    /**
     * 演示RequestBody
     */
    @RequestMapping("/testRequestBody")
    public String testRequestBody(@RequestBody String requestBody){
        System.out.println(requestBody);
        return "success";
    }

    /**
     * 演示@RequestEntity
     */
    @RequestMapping("/testRequestEntity")
    public String testRequestEntity(RequestEntity<String> requestEntity){
        System.out.println(requestEntity.getBody());
        System.out.println(requestEntity.getHeaders());
        return "success";
    }

}
