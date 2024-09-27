package edu.gustdev.spring_bood_rest_crud_demo.exceptions.student_exceptions;

/** Classe costumizada para resposta a uma exceção de estudante não encontrato (StudentNotFoundException.class) */
public class StudentErrorResponse {
    //fields (atributos)
    private int status;
    private String messege;
    private long timeStamp;

    //construtor padrão default (vazio):
    public StudentErrorResponse() {

    }

    //construtor passando os devidos atributos do novo objeto:
    public StudentErrorResponse(int status, String messege, long timeStamp) {
        this.status = status;
        this.messege = messege;
        this.timeStamp = timeStamp;
    }

    //getters e setters (padrão java beans):
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessege() {
        return messege;
    }

    public void setMessege(String messege) {
        this.messege = messege;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timestamp) {
        this.timeStamp = timestamp;
    }
    
    


}
