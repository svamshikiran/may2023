package com.example.may2023.service;

import com.example.may2023.model.Courses;
import com.example.may2023.repository.CourseRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoursesService {

    @Autowired
    CourseRespository cRepo;

    public List<Courses> getAllCourses(){
        return cRepo.findAll();
    }

    public void addCourse(Courses course){
        cRepo.save(course);
    }
}
