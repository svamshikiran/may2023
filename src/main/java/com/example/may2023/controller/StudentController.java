package com.example.may2023.controller;

import com.example.may2023.model.Student;
import com.example.may2023.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService sService;

    @GetMapping("/all")
    public List<Student> getAllStudents(){
        return sService.getAllStudents();
    }
}
