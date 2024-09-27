package edu.gustdev.spring_bood_rest_crud_demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import edu.gustdev.spring_bood_rest_crud_demo.exceptions.student_exceptions.StudentErrorResponse;
import edu.gustdev.spring_bood_rest_crud_demo.exceptions.student_exceptions.StudentNotFoundException;


@ControllerAdvice
//Esta classe, por estar anotada com @ControllerAdvice, permite o controle de exception handler por toda aplicação Spring,
//Qualquer serviço ou controlador pode usufruir dela, ou seja, dos @ExceptionHanlder's abaixo:
public class GlobalCustomExceptionHandler {


    //Quando a exceção StudentNotFoundException for lançada, este método será executado;
    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<StudentErrorResponse> handlerStudentNotFoundException(StudentNotFoundException exc) {
        return new ResponseEntity<StudentErrorResponse>(
            new StudentErrorResponse(
                HttpStatus.NOT_FOUND.value(), exc.getMessage(), System.currentTimeMillis()),HttpStatus.NOT_FOUND
                );
    }

    //Quando uma exceção genérica (Exception) for lançada, esse método será executado:
    @ExceptionHandler(Exception.class)
    public ResponseEntity<StudentErrorResponse> handlerGenericExcpetion(Exception exc) {
        StudentErrorResponse badStudentRequest = 
            new StudentErrorResponse(HttpStatus.BAD_REQUEST.value(), exc.getMessage(), System.currentTimeMillis());
        
        return new ResponseEntity<StudentErrorResponse>(badStudentRequest, HttpStatus.BAD_REQUEST);
    }
}
