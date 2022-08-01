package com.wfs.dao;

import com.wfs.pojo.Student;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class StudentDao {
    public static Map<Integer, Student> students=null;

    static {
        students=new HashMap<Integer,Student>();

        students.put(1001,new Student(1001,"张三",15,"13333333333"));
        students.put(1002,new Student(1002,"李四",12,"13333333334"));
        students.put(1003,new Student(1003,"王五",14,"13333333353"));
        students.put(1004,new Student(1004,"赵六",13,"13333333335"));
        students.put(1005,new Student(1005,"田七",12,"13333333365"));
    }

    public static Integer initId=1006;

    public void save(Student student){
        if(student.getId()==0){
            student.setId(initId++);
        }
        students.put(student.getId(),student);

    }
    public Collection<Student> getAll(){
        return students.values();
    }
    public Student get(Integer id){
        return students.get(id);
    }

    public void delete(Integer id){
        students.remove(id);
    }
}
