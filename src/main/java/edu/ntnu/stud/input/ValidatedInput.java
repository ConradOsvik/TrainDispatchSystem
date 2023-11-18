package edu.ntnu.stud.input;

import edu.ntnu.stud.exceptions.InvalidFormatException;
import edu.ntnu.stud.exceptions.InvalidInputException;
import java.util.concurrent.Callable;

public class InputValidator {

  private final InputHandler inputHandler;

  public InputValidator() {
    this.inputHandler = new InputHandler();
  }

  public String getTime() {
    return execute(inputHandler::getTime);
  }

  private <T> T execute(InputCallable<T> callable) {
    while (true) {
      try {
        return callable.call();
      } catch (InvalidFormatException | InvalidInputException e) {
        System.out.println(e.getMessage());
      }
    }
  }
}
