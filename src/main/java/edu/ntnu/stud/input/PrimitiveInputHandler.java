package edu.ntnu.stud.input;

import edu.ntnu.stud.exceptions.InvalidFormatException;
import java.util.Scanner;

/**
 * This class represents a handler for low level primitive input.
 */
public class PrimitiveInputHandler {

  private final Scanner scanner;

  /**
   * Initializes the PrimitiveInputHandler using system.in for input.
   */
  public PrimitiveInputHandler() {
    this.scanner = new Scanner(System.in);
  }

  /**
   * Gets the string a user inputted.
   *
   * @return the string a user inputted
   * @throws InvalidFormatException if the input has invalid format
   */
  public String getString() throws InvalidFormatException {
    return execute(scanner::nextLine);
  }

  /**
   * Gets the int a user inputted.
   *
   * @return the int a user inputted
   * @throws InvalidFormatException if the input has invalid format
   */
  public int getInt() throws InvalidFormatException {
    return execute(() -> Integer.parseInt(scanner.nextLine()));
  }

  /**
   * Gets the double a user inputted.
   *
   * @return the double a user inputted
   * @throws InvalidFormatException if the input has invalid format
   */
  public double getDouble() throws InvalidFormatException {
    return execute(() -> Double.parseDouble(scanner.nextLine()));
  }

  /**
   * A generic method for executing the primitive input methods.
   *
   * @param callable the method to execute
   * @param <T>      the type of the result
   * @return the result of the method
   * @throws InvalidFormatException if the input has invalid format
   */
  private <T> T execute(PrimitiveInputCallable<T> callable) throws InvalidFormatException {
    try {
      return callable.call();
    } catch (Exception e) {
      throw new InvalidFormatException();
    }
  }
}
