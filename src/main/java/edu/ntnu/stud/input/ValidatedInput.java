package edu.ntnu.stud.input;

import edu.ntnu.stud.exceptions.InvalidFormatException;
import edu.ntnu.stud.exceptions.InvalidInputException;
import edu.ntnu.stud.utils.Color;
import edu.ntnu.stud.views.ConsoleView;

/**
 * This class represents a validator for user input.
 */
public class ValidatedInput {

  private final ConsoleView consoleView;
  private final InputHandler inputHandler;

  /**
   * Initializes the ValidatedInput.
   */
  public ValidatedInput(ConsoleView consoleView) {
    this.consoleView = consoleView;
    this.inputHandler = new InputHandler();
  }

  /**
   * A generic method for executing the input methods until they return a valid value.
   *
   * @see InputCallable
   * @param callable the method to execute
   * @param <T>      the type of the result
   * @return the result of the method
   */
  private <T> T execute(InputCallable<T> callable) {
    while (true) {
      try {
        return callable.call();
      } catch (InvalidFormatException | InvalidInputException e) {
        consoleView.displayMessage(Color.colorString(e.getMessage(), Color.RED));
      }
    }
  }

  /**
   * Gets the string a user inputted.
   *
   * @return the string a user inputted
   */
  public String getString() {
    return execute(inputHandler::getString);
  }

  /**
   * Gets the int a user inputted.
   *
   * @return the int a user inputted
   */
  public int getInt() {
    return execute(inputHandler::getInt);
  }

  /**
   * Gets the double a user inputted.
   *
   * @return the double a user inputted
   */
  public double getDouble() {
    return execute(inputHandler::getDouble);
  }

  /**
   * Gets the time a user inputted on the format HH:mm.
   *
   * @return the time a user inputted
   */
  public String getTime() {
    return execute(inputHandler::getTime);
  }

  /**
   * Gets the line a user inputted.
   *
   * @return the line a user inputted
   */
  public String getLine() {
    return execute(inputHandler::getLine);
  }

  /**
   * Gets the train number a user inputted.
   *
   * @return the train number a user inputted
   */
  public int getTrainNumber() {
    return execute(inputHandler::getTrainNumber);
  }

  /**
   * Gets the track a user inputted.
   *
   * @return the track a user inputted
   */
  public int getTrack() {
    return execute(inputHandler::getTrack);
  }
}
