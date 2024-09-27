package edu.gustdev.spring_bood_rest_crud_demo.service.implementacao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.gustdev.spring_bood_rest_crud_demo.exceptions.student_exceptions.StudentNotFoundException;
import edu.gustdev.spring_bood_rest_crud_demo.model.Student;
import edu.gustdev.spring_bood_rest_crud_demo.repository.StudentRepository;
import edu.gustdev.spring_bood_rest_crud_demo.service.StudentService;


@Service("studentServImpl")
public class StudentServiceImplementacao implements StudentService {

    StudentRepository studentRepo;

    //Injeção de dependências por Método Construtor:
    @Autowired
    private StudentServiceImplementacao(StudentRepository studentRepo) {
        this.studentRepo = studentRepo;
    }


    @Override
    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }

    @Override
    public Student saveStudent(Student student) {
        return studentRepo.save(student);
    }


    @Override
    public Student getStudentById(Integer id) {
      return studentRepo.findById(id)
                    .orElseThrow(
                    () -> new StudentNotFoundException("O estudante de id " + id + " não foi encontrado")
                    );
                    /*  Aqui a exceção customizada é lançada ou melhor dizendo, instanciada através do new e usando uma,
                        usando uma expressão lambda;
                    */
    }


    
}
