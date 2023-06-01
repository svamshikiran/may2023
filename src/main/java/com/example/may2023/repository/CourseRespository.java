package com.example.may2023.repository;

import com.example.may2023.model.Courses;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRespository extends MongoRepository<Courses, Integer> {
}
