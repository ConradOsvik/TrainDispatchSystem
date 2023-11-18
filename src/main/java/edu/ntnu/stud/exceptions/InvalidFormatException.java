package edu.ntnu.stud.exceptions;

public class InvalidFormatException extends Exception {
  private static final String ERROR_MESSAGE = "The input you provided has an invalid format, please try again:";

  public InvalidFormatException(){
    super(ERROR_MESSAGE);
  }

  public InvalidFormatException(String message){
    super(message);
  }
}
