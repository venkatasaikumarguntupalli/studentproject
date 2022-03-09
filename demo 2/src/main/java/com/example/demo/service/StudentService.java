package com.example.demo.service;

import com.example.demo.dto.Student;
import com.example.demo.dto.Studept;
import com.example.demo.entity.StudentEntity;

import java.util.List;

public interface StudentService {

    List<Student> getAllStudents(String db);
    List<Studept> getStudents();

    void addStudent(Student student,String db);

    Student getStudent(Integer id,String db);

    Student updatingStudent(Student student,String db);

    void DeleteStudent(Integer id,String db);
}
