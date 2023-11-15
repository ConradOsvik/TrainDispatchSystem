package edu.ntnu.stud.exceptions;

public class InvalidNumberException extends Exception{
    private static final String ERROR_MESSAGE = "The number you provided is invalid, please try again.";

    public InvalidNumberException(){
      super(ERROR_MESSAGE);
    }

    public InvalidNumberException(String message){
        super(message);
    }
}
