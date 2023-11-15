package edu.ntnu.stud.exceptions;

public class InvalidStringException extends Exception{
  private static final String ERROR_MESSAGE = "The string you provided is invalid, please try again.";

  public InvalidStringException(){
    super(ERROR_MESSAGE);
  }

  public InvalidStringException(String message){
    super(message);
  }
}
