package edu.gustdev.spring_bood_rest_crud_demo.exceptions.student_exceptions;

public class StudentNotFoundException extends RuntimeException {

    //Definindo construtores a partir da super class (ou classe pai, neste caso, RuntimeException)
    public StudentNotFoundException(String message) {
        super(message);

        /*  Comentário a cerca da utilização do super dentro dessa classe:
         *  Quando um objeto da classe StudentNotFoundException for instanciado, 
         *  o atributo message (note que há um pequeno erro de digitação, o correto 
         *  seria message em vez de messege) será passado ao construtor da classe RuntimeException devido à utilização do super(message).
         */
    }
}
