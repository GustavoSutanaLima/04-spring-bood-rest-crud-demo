package edu.gustdev.spring_bood_rest_crud_demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class RestControllerDemo {


    /*Código para o end-point /hello*/
    @GetMapping("/hello")
    public String helloWordMethod(){
        return "Hello World!";
    }
}
