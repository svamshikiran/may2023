package com.example.may2023.service;

import com.example.may2023.model.Student;
import com.example.may2023.repository.StudentRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Writer;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class StudentService {

    @Autowired
    StudentRepository repo;

    public List<Student> getAllStudents(){
        return repo.findAll(); //select * from student;
    }

    public Student getStudentByRollno(int rollno){

        Optional<Student> opStudent = repo.findById(rollno);
        if(opStudent.isPresent())
            return opStudent.get();
        else
            return new Student();
    }

    public List<Student> getAllStudentsByName(String name){
        return repo.findByName(name); //select * from student where name=?;
    }

    public List<Student> getAllStudentsByCity(String city){
        return repo.findByCity(city); //select * from student where city=?;
    }

    public void upsert(Student student){ //UPdate or inSERT
        repo.save(student); //insert into student values(?????);
        // if the record is present, it will perform update operation
        // if the record is NOT present, it will perform insert operation
    }

    public void deleteStudent(int rollno){
        repo.deleteById(rollno);
    }

    public void readFileContents(InputStream inputStream)throws Exception {
        CSVParser parser = new CSVParser(new InputStreamReader(inputStream), CSVFormat.DEFAULT);

        List<CSVRecord> records = parser.getRecords();
        Random randomRollNumbers = new Random();
        List<Student> studentList = new ArrayList<>();

        for (CSVRecord record : records) {

            Student student = new Student();
            //student.setRollno(Integer.parseInt(record.get(0)));
            student.setRollno(randomRollNumbers.nextInt(400));
            student.setName(record.get(0));
            student.setCity(record.get(1));
            student.setCreatedby("FileUpload");
            student.setCreateddate(Date.valueOf(LocalDate.now()));

            studentList.add(student);
        }

        repo.saveAll(studentList);

    }

    public void writeStudentsToCsv(Writer writer) {
        //List<Student> students = getAllStudents();
        List<Student> students = repo.findAll();
        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
            csvPrinter.printRecord("ROLLNO", "NAME", "CITY", "CREATED BY", "CREATED DATE", "MODIFIED BY", "MODIFIED DATE", "COURSE ID");
            for (Student student : students) { //Enhanced For Loop
                csvPrinter.printRecord(student.getRollno(),
                        student.getName(),
                        student.getCity(),
                        student.getCreatedby(),
                        student.getCreateddate(),
                        student.getModifiedby(),
                        student.getModifieddate(),
                        student.getCourseid());
            }
        } catch (IOException e) {
            System.out.println("EXPCEPTION RAISED");
        }
    }
}
