package edu.ntnu.stud.exceptions;

public class InvalidInputException extends Exception {
  private static final String ERROR_MESSAGE = "The input you provided is invalid, please try again:";

  public InvalidInputException(){
    super(ERROR_MESSAGE);
  }

  public InvalidInputException(String message){
    super(message);
  }
}
