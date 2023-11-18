package edu.ntnu.stud.input;

import edu.ntnu.stud.exceptions.InvalidFormatException;
import edu.ntnu.stud.exceptions.InvalidInputException;
import edu.ntnu.stud.utils.Color;

public class ValidatedInput {

  private final InputHandler inputHandler;

  public ValidatedInput() {
    this.inputHandler = new InputHandler();
  }

  public String getString() {
    return execute(inputHandler::getString);
  }

  public int getInt() {
    return execute(inputHandler::getInt);
  }

  public double getDouble() {
    return execute(inputHandler::getDouble);
  }

  public String getTime() {
    return execute(inputHandler::getTime);
  }

  public String getLine() {
    return execute(inputHandler::getLine);
  }

  public int getTrainNumber() {
    return execute(inputHandler::getTrainNumber);
  }

  public int getTrack() {
    return execute(inputHandler::getTrack);
  }

  private <T> T execute(InputCallable<T> callable) {
    while (true) {
      try {
        return callable.call();
      } catch (InvalidFormatException | InvalidInputException e) {
        System.out.println(Color.colorString(e.getMessage(), Color.RED));
      }
    }
  }
}
