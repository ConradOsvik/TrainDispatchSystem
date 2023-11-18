package edu.ntnu.stud.input;

import edu.ntnu.stud.exceptions.InvalidFormatException;

/**
 * This class represents a callable method for primitive user input.
 *
 * @param <T> The type of the method.
 */
@FunctionalInterface
public interface PrimitiveInputCallable<T> {

  /**
   * Calls the method.
   *
   * @return the result of the method
   * @throws InvalidFormatException if the input has invalid format
   */
  T call() throws InvalidFormatException;
}
