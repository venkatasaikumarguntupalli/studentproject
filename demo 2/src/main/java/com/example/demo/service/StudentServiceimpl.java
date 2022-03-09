package com.example.demo.service;

import com.example.demo.dto.Department;
import com.example.demo.dto.Student;
import com.example.demo.dto.Studept;
import com.example.demo.entity.StudentEntity;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceimpl implements StudentService {

    @Autowired
    Studentdb studentdb;

    @Autowired
    Departmentdb departmentdb;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    StudentRedisRepository studentRedisRepository;

    @Autowired
    StudentMongoRepository studentMongoRepository;

    
    @Override
    public List<Student> getAllStudents(String db) {
        switch(db){
            case "l":
                return studentdb.getstudentList();
            case "redis":
                return studentRedisRepository.findAll();
            case "pg":
                List<Student> s2 = new ArrayList<>();
                for (StudentEntity student : studentRepository.findAll()) {
                    Student s1 = new Student();
                    s1.setId(student.getId());
                    s1.setPname(student.getPname());
                    s1.setLname(student.getLname());
                    s1.setBranch(student.getBranch());
                    s2.add(s1);
                }
                return s2;
            case "mg":
                List<Student> s = new ArrayList<>();
                for (StudentEntity student : studentMongoRepository.findAll()) {
                    Student s1 = new Student();
                    s1.setId(student.getId());
                    s1.setPname(student.getPname());
                    s1.setLname(student.getLname());
                    s1.setBranch(student.getBranch());
                    s.add(s1);
                }
                return s;
            default:
                List<Student> s4 = new ArrayList<>();
                for (StudentEntity student : studentMongoRepository.findAll()) {
                    Student s1 = new Student();
                    s1.setId(student.getId());
                    s1.setPname(student.getPname());
                    s1.setLname(student.getLname());
                    s1.setBranch(student.getBranch());
                    s4.add(s1);
                }
                for (StudentEntity student : studentRepository.findAll()) {
                    Student s1 = new Student();
                    s1.setId(student.getId());
                    s1.setPname(student.getPname());
                    s1.setLname(student.getLname());
                    s1.setBranch(student.getBranch());
                    s4.add(s1);
                }
                for (Student student : studentRedisRepository.findAll()) {
                    Student s1 = new Student();
                    s1.setId(student.getId());
                    s1.setPname(student.getPname());
                    s1.setLname(student.getLname());
                    s1.setBranch(student.getBranch());
                    s4.add(s1);
                }
                return s4;
        }

    }

    @Override
    public void addStudent(Student student,String db) {
        switch(db){
            case "l":
                studentdb.getstudentList().add(new Student(student.getId(),student.getPname(),student.getLname(),student.getBranch()));
                break;
            case "pg":
                StudentEntity se1=new StudentEntity();
                se1.setId(student.getId());
                se1.setBranch(student.getBranch());
                se1.setPname(student.getPname());
                se1.setLname(student.getLname());
                studentRepository.save(new StudentEntity(se1.getId(),se1.getPname(),se1.getLname(),se1.getBranch()));
                break;
            case "redis":
                studentRedisRepository.addOne(student);
                break;
            case "mg":
                StudentEntity se = new StudentEntity();
                se.setId(student.getId());
                se.setBranch(student.getBranch());
                se.setPname(student.getPname());
                se.setLname(student.getLname());
                studentMongoRepository.save(new StudentEntity(se.getId(), se.getPname(), se.getLname(), se.getBranch()));
                break;
        }
    }

    @Override
    public Student getStudent(Integer id,String db) {
        switch(db){
            case "l":
                for(Student student:studentdb.getstudentList()){
                    if(student.getId()==id){
                        return student;
                    }
                }
                return null;
            case "pg":
                Student o=new Student();
                for(StudentEntity student:studentRepository.findAll()){
                    if(student.getId()==id){
                        o.setId(student.getId());
                        o.setPname(student.getPname());
                        o.setLname(student.getLname());
                        o.setBranch(student.getBranch());
                    }
                }
                return o;
            case "redis":
                return studentRedisRepository.findOne(id);
            case "mg":
                Student o1 = new Student();
                for (StudentEntity student : studentMongoRepository.findAll()) {
                    if (student.getId() == id) {
                        o1.setId(student.getId());
                        o1.setPname(student.getPname());
                        o1.setLname(student.getLname());
                        o1.setBranch(student.getBranch());
                        break;
                    }
                }
                return o1;
        }
        return null;
    }

    @Override
    public Student updatingStudent(Student student,String db) {
        switch(db){
            case "l":
                for(Student stu:studentdb.getstudentList()){
                    if(stu.getId()==student.getId()) {
                        stu.setLname(student.getLname());
                    }
                }
                return student;
            case "pg":
                Student so=new Student();
                for(StudentEntity se:studentRepository.findAll()){
                    if(se.getId()==student.getId()){
                        se.setPname(student.getPname());
                        studentRepository.save(new StudentEntity(se.getId(),se.getPname(),se.getLname(),se.getBranch()));
                        so.setId(se.getId());
                        so.setBranch(se.getBranch());
                        so.setPname(se.getPname());
                        so.setLname(se.getLname());
                    }
                }
                return so;
            case "redis":
                return studentRedisRepository.updatingStudent(student);
            case "mg":
                Student so1 = new Student();
                for (StudentEntity se : studentMongoRepository.findAll()) {
                    if (se.getId() == student.getId()) {
                        se.setPname(student.getPname());
                        studentMongoRepository.save(new StudentEntity(se.getId(), se.getPname(), se.getLname(), se.getBranch()));
                        so1.setId(se.getId());
                        so1.setBranch(se.getBranch());
                        so1.setPname(se.getPname());
                        so1.setLname(se.getLname());
                    }
                }
                return so1;
        }
        Student so = new Student();
        for (StudentEntity se : studentMongoRepository.findAll()) {
            if (se.getId() == student.getId()) {
                se.setPname(student.getPname());
                studentMongoRepository.save(new StudentEntity(se.getId(), se.getPname(), se.getLname(), se.getBranch()));
                so.setId(se.getId());
                so.setBranch(se.getBranch());
                so.setPname(se.getPname());
                so.setLname(se.getLname());
            }
        }
        return so;
    }

    @Override
    public void DeleteStudent(Integer id,String db) {
        switch (db){
            case "l":
                for(Student stu:studentdb.getstudentList()){
                    if(stu.getId()==id){
                        studentdb.getstudentList().remove(stu);
                        break;
                    }
                }
            case "pg":
                for(StudentEntity stu:studentRepository.findAll()){
                    if(stu.getId()==id){
                        studentRepository.deleteById(id);
                        break;
                    }
                }
            case "redis":
                studentRedisRepository.deleteOne(id);
                break;
            case "mg":
                for (StudentEntity stu : studentMongoRepository.findAll()) {
                    if (stu.getId() == id) {
                        studentMongoRepository.deleteById(id);
                        break;
                    }
                }
        }
    }

    @Override
    public List<Studept> getStudents() {
        List<Studept> sde=new ArrayList<>();
        Studept sd=new Studept();
        for(Student student:studentdb.getstudentList()){
            for(Department department:departmentdb.getDeptList()){
                if(student.getBranch().equals(department.getDept_name())){
                    sd.setId(student.getId());
                    sd.setPname(student.getPname());
                    sd.setBranch(student.getBranch());
                    sd.setLname(student.getLname());
                    sd.setDept_id(department.getDept_id());
                    sd.setDept_name(department.getDept_name());
                    sd.setDept_hod(department.getDept_hod());
                    sde.add(sd);
                }
            }
        }
        return sde;
    }

    /*@Override
    public List<Student> getAllStudents(){
        return studentRedisRepository.findAll();
    }

    @Override
    public void addStudent(Student student){
        studentRedisRepository.addOne(student);
    }

    @Override
    public Student getStudent(Integer id){
        return studentRedisRepository.findOne(id);
    }


    @Override
    public void DeleteStudent(Integer id){
        studentRedisRepository.deleteOne(id);
    }

    @Override
    public Student updatingStudent(Student student){
        return studentRedisRepository.updatingStudent(student);
    }*/

    /* Postgres
    @Override
    public List<Student> getAllStudents(){
        List<Student> s=new ArrayList<>();
        for(StudentEntity student:studentRepository.findAll()){
            Student s1=new Student();
            s1.setId(student.getId());
            s1.setPname(student.getPname());
            s1.setLname(student.getLname());
            s1.setBranch(student.getBranch());
            s.add(s1);
        }
        return s;
    }

    @Override
    public void addStudent(Student student){
        StudentEntity se=new StudentEntity();
        se.setId(student.getId());
        se.setBranch(student.getBranch());
        se.setPname(student.getPname());
        se.setLname(student.getLname());
        studentRepository.save(new StudentEntity(se.getId(),se.getPname(),se.getLname(),se.getBranch()));
    }

    @Override
    public Student getStudent(Integer id){
        Student o=new Student();
        for(StudentEntity student:studentRepository.findAll()){
            if(student.getId()==id){
                o.setId(student.getId());
                o.setPname(student.getPname());
                o.setLname(student.getLname());
                o.setBranch(student.getBranch());
            }
        }
        return o;
    }
    @Override
    public void DeleteStudent(Integer id){
        for(StudentEntity stu:studentRepository.findAll()){
            if(stu.getId()==id){
                studentRepository.deleteById(id);
                break;
            }
        }
    }
*/
   /* @Override
    public Student updatingStudent(Student student){
        Student so=new Student();
        for(StudentEntity se:studentRepository.findAll()){
            if(se.getId()==student.getId()){
                se.setPname(student.getPname());
                studentRepository.save(new StudentEntity(se.getId(),se.getPname(),se.getLname(),se.getBranch()));
                so.setId(se.getId());
                so.setBranch(se.getBranch());
                so.setPname(se.getPname());
                so.setLname(se.getLname());
            }
        }
        return so;
    }*/


    /* linked list
    @Override
    public List<Student> getAllStudents(){
        return studentdb.getstudentList();
    }

    @Override
    public Student getStudent(Integer id,String name){
        for(Student student:studentdb.getstudentList()){
            if(student.getId()==id && student.getPname().equals(name)){
                return student;
            }
        }
        return null;
    }

    @Override
    public void addStudent(Student student){
        studentdb.getstudentList().add(new Student(student.getId(),student.getPname(),student.getLname(),student.getBranch()));

    }

    @Override
    public Student updatingStudent(Student student){
        for(Student stu:studentdb.getstudentList()){
            if(stu.getId()==student.getId()) {
                stu.setLname(student.getLname());
            }
        }
        return student;
    }

    @Override
    public void DeleteStudent(Integer id){
        for(Student stu:studentdb.getstudentList()){
            if(stu.getId()==id){
                studentdb.getstudentList().remove(stu);
                break;
            }
        }

    }*/
}
