package project.demo;

public class StudentNameExistException extends RuntimeException{
    public StudentNameExistException(String message){
        super(message);
    }
}
