package edu.ntnu.stud.input;

/**
 * This class represents a callable method for primitive user input.
 *
 * @param <T> The type of the method.
 */
@FunctionalInterface
interface PrimitiveInputCallable<T> {

  /**
   * Calls the method.
   *
   * @return the result of the method
   * @throws Exception if the input has invalid format
   */
  T call() throws Exception;
}
