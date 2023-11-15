package edu.ntnu.stud.exceptions;

public class InvalidTimeException extends Exception {
  private static final String ERROR_MESSAGE = "The time you provided has an invalid format, please try again.";

  public InvalidTimeException(){
    super(ERROR_MESSAGE);
  }

  public InvalidTimeException(String message){
    super(message);
  }
}
