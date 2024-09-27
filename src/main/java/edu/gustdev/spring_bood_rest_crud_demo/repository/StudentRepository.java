package edu.gustdev.spring_bood_rest_crud_demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.gustdev.spring_bood_rest_crud_demo.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

}
