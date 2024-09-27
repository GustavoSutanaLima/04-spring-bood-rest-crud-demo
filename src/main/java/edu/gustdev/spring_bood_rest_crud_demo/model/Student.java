package edu.gustdev.spring_bood_rest_crud_demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;
    
    /*
     * Para ser gerenciando pelo Spring Boot, uma entidade precisa ter um construtor padrão, ou seja,
     * um construtor vazio. Para o Java, se uma classe não possui nenhum construtor, é como se, por padrão
     * houvesse um construtor vazio oculto:
            *  public Stundet() {
            * 
            *  }
     * Porém, SE eu defino um construtor, como o abaixo:
            * public Student(String firstName, String lastName) {
            *    this.firstName = firstName;
            *    this.lastName = lastName;
            * }
     * eu PRECISO ESPECIFICAR UM CONSTRUTOR VAZIO em um projeto Spring Boot, se não haverão
     * erros relacionados às aplicações do tipo REST e CRUD;
     * 
    */

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


}
