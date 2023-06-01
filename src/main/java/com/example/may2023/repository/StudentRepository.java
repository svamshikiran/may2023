package com.example.may2023.repository;

import com.example.may2023.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    public List<Student> findByName(String name);//select * from student where name=?

    public List<Student> findByCity(String city);
}
