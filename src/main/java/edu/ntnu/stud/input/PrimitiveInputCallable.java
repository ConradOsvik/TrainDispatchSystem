package edu.ntnu.stud.input;

import edu.ntnu.stud.exceptions.InvalidFormatException;

@FunctionalInterface
public interface PrimitiveInputCallable<T> {
  T call() throws InvalidFormatException;
}
