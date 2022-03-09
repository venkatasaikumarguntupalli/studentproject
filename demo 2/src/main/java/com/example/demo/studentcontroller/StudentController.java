package com.example.demo.studentcontroller;

import com.example.demo.dto.Student;
import com.example.demo.dto.Studept;
import com.example.demo.entity.StudentEntity;
import com.example.demo.service.DeptService;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.ConditionalOperators;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.List;

@RestController
public class StudentController {

    @Autowired
    StudentService ss;
    DeptService ds;
    Studept sd;

    @GetMapping("/Students1")
    public List<Studept> getStudent(){
        return ss.getStudents();
    }

    @GetMapping("/Students")
    public List<Student> getAllStudent(@RequestParam String db){
        return ss.getAllStudents(db);
    }

    /*@GetMapping("/Student/{id}")
    public Student getStudent(@PathVariable Integer id){
        return ss.getStudent(id);
    }*/

    @GetMapping("/Student")
    public Student getStudent(@RequestParam(required = true) Integer id,@RequestParam String db){
        return ss.getStudent(id,db);
    }

    @PostMapping("/Stud")
    public void addStudent(@RequestBody @Valid Student student,@RequestParam String db){
        ss.addStudent(student,db);
    }

    @PutMapping("/St")
    public void putStudent(@RequestBody Student student,@RequestParam String db){
        ss.updatingStudent(student,db);
    }

    @DeleteMapping("/S")
    public void deleteStudent(@RequestParam(value ="id") Integer id,@RequestParam String db){
        ss.DeleteStudent(id,db);
    }
}
