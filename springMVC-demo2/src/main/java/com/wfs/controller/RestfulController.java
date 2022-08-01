package com.wfs.controller;

import com.wfs.dao.StudentDao;
import com.wfs.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;

/**
 * 谨以此类演示restful方式实现增删改查   先用postman工具直接模拟四种请求方式GET POST PUT DELETE
 * 然后用新html模拟三种请求方式GET POST PUT 由于DELETE方式需要使用vue等 故不作演示
 */
@Controller
public class RestfulController {

    @Autowired
    StudentDao studentDao;

    //增
    @RequestMapping(value = "/studentByPostman" ,method = RequestMethod.POST)
    public String addByPostman(@RequestParam("name")String name,@RequestParam("age") int age,@RequestParam("tel")String tel){
        Student student=new Student(0,name,age ,tel);
        System.out.println("增加---"+student);
        return "success";

    }
    //删
    @RequestMapping(value = "/studentByPostman/{id}",method = RequestMethod.DELETE)
    public String deleteByPostman(@PathVariable("id") int id){
        System.out.println("删除-----"+id);
        return "success";
    }

    //修改
    @RequestMapping(value = "/studentByPostman/{id}",method = RequestMethod.PUT)
    public String updateByPostman(@PathVariable("id") int id,Student student){
        System.out.println("修改----"+student.getName());
        return "success";
    }

    //查询
    @RequestMapping(value = "/studentByPostman/{id}",method = RequestMethod.GET)
    public String getOneByPostman(@PathVariable("id") int id){
        System.out.println("查询---"+id);
        return "success";
    }
    //查询所有
    @RequestMapping(value = "/studentByPostman",method = RequestMethod.GET)
    public String getAllByPostman(){
        System.out.println("查询所有---");
        return "success";
    }


    //增
    @RequestMapping(value = "/student" ,method = RequestMethod.POST)
    public String add(@RequestParam("name")String name,@RequestParam("age") int age,@RequestParam("tel")String tel){
        Student student=new Student(0,name,age ,tel);
        System.out.println("增加---"+student);

        studentDao.save(student);

        return "redirect:/student";

    }
    //删
    @RequestMapping(value = "/student/{id}",method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") int id){
        System.out.println("删除-----"+id);
        studentDao.delete(id);
        return "redirect:/student";
    }

    //修改
    @RequestMapping(value = "/student/{id}",method = RequestMethod.PUT)
    public String update(@PathVariable("id") int id,Student student){
        System.out.println("修改----"+student.getName());
        student.setId(id);
        System.out.println(student);
        studentDao.save(student);
        return "redirect:/student";
    }

    //查询
    @RequestMapping(value = "/student/{id}",method = RequestMethod.GET)
    public String getOne(@PathVariable("id") int id){
        System.out.println("查询---"+id);
        studentDao.get(id);
        return "success";
    }
    //查询所有
    @RequestMapping(value = "/student",method = RequestMethod.GET)
    public String getAll(Model model){
        System.out.println("查询所有---");
        Collection<Student> studentList = studentDao.getAll();
        model.addAttribute("studentList",studentList);
        return "student_list";
    }


    //修改页面
    @RequestMapping("/studentUpdatePage/{id}")
    public String studentUpdatePage(@PathVariable("id")int id,Model model){
        model.addAttribute("id",id);
        Student student = studentDao.get(id);
        model.addAttribute("name",student.getName());
        model.addAttribute("age",student.getAge());
        model.addAttribute("tel",student.getTel());
        return "student_update";
    }

    //添加页面
    @RequestMapping("/studentAddPage")
    public String studentAddPage(){
        return "student_add";
    }

}
