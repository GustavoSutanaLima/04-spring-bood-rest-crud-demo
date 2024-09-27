package edu.gustdev.spring_bood_rest_crud_demo.service;

import java.util.*;

import org.springframework.stereotype.Service;

import edu.gustdev.spring_bood_rest_crud_demo.model.Student;

@Service
public interface StudentService {

    public Student saveStudent(Student student);

    public List<Student> getAllStudents();

    public Student getStudentById(Integer id);
    

}
