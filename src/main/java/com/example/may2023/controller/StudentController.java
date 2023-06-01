package com.example.may2023.controller;

import com.example.may2023.model.Student;
import com.example.may2023.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private static Logger log = LoggerFactory.getLogger(StudentController.class);
    @Autowired
    StudentService sService;

    @GetMapping("/all")
    public List<Student> getAllStudents(){
        return sService.getAllStudents();
    }

    @GetMapping("/get/{rollno}")
    @Operation(description = "THIS IS RETRIEVING THE STUDENT DETAILS BASED ON ROLLNO PROVIDED")
    @ApiResponse(responseCode = "200", description = "THIS IS SUCCESSFUL RESPONSE")
    @ApiResponse(responseCode = "400", description = "THIS RESPONSE INDICATES SOME WRONG INPUT BEING PROVIDED")
    public ResponseEntity<Object> getStudentByRollno(
            @PathVariable("rollno") int rollno
    ){
        Student student = sService.getStudentByRollno(rollno);
        if(student.getRollno() == 0){
            log.error("STUDENT DOESN'T EXIST");
            return new ResponseEntity<>("STUDENT DOESN'T EXIST", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @GetMapping("/get/name/{name}")
    public List<Student> getAllStudentsByName(@PathVariable("name") String name){
        return sService.getAllStudentsByName(name);
    }


    @GetMapping("/get/city/{city}")
    public List<Student> getAllStudentsByCity(@PathVariable("city") String city){
        return sService.getAllStudentsByCity(city);
    }

    @PostMapping("/add")
    public void addStudent(@RequestBody Student student){
        sService.upsert(student);
    }


    @PutMapping("/update")
    public void updateStudent(@RequestBody Student student){
        sService.upsert(student);
    }

    @DeleteMapping("/delete/{rollno}")
    public void deleteStudent(@PathVariable("rollno") int rollno){
        sService.deleteStudent(rollno);
    }

    @PostMapping("/upload")
    public ResponseEntity<?> handleFileUpload( @RequestParam("file") MultipartFile file ) {
        try {
            sService.readFileContents(file.getInputStream());
            //FileUtils.forceDelete(file.getResource().getFile());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok("File uploaded successfully.");
    }

    @GetMapping(path = "/download")
    public void getAllEmployeesInCsv(HttpServletResponse servletResponse) throws IOException {
        servletResponse.setContentType("text/csv");
        servletResponse.addHeader("Content-Disposition","attachment; filename=\"students.csv\"");
        sService.writeStudentsToCsv(servletResponse.getWriter());
    }

}

