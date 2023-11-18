package edu.ntnu.stud.input;

import edu.ntnu.stud.exceptions.InvalidFormatException;
import edu.ntnu.stud.exceptions.InvalidInputException;

/**
 * This class represents a callable method for user input.
 *
 * @param <T> The type of the method.
 */
@FunctionalInterface
public interface InputCallable<T> {

  /**
   * Calls the method.
   *
   * @return the result of the method
   * @throws InvalidFormatException if the input has invalid format
   * @throws InvalidInputException  if the input is logically invalid
   */
  T call() throws InvalidFormatException, InvalidInputException;
}
