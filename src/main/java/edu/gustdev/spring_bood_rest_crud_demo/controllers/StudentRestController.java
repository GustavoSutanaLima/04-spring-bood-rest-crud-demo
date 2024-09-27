package edu.gustdev.spring_bood_rest_crud_demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import edu.gustdev.spring_bood_rest_crud_demo.model.Student;
import edu.gustdev.spring_bood_rest_crud_demo.service.StudentService;

import java.util.*;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private StudentService studentServ;

    @Autowired
    public StudentRestController(@Qualifier("studentServImpl") StudentService studentServ) {
        this.studentServ = studentServ;
    }

    @GetMapping("/students")
    public ResponseEntity<List<Student>> getAllStudents(){
        List<Student> allStudents = studentServ.getAllStudents();
        return ResponseEntity.ok(allStudents);
    }
    
    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getAStudent(@PathVariable Integer id){
        Student foundStudent = studentServ.getStudentById(id);
        return ResponseEntity.ok(foundStudent);
    }

    @PostMapping
    public ResponseEntity<Student> saveStudent(@RequestBody Student student){
        Student savedStudent = studentServ.saveStudent(student);
        return ResponseEntity.ok(savedStudent);
    }

}
