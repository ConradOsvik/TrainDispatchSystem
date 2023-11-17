package edu.ntnu.stud.input;

import edu.ntnu.stud.exceptions.InvalidFormatException;
import java.util.Scanner;

public class PrimitiveInputHandler {

  private final Scanner scanner;

  public PrimitiveInputHandler() {
    this.scanner = new Scanner(System.in);
  }

  public String getString() throws InvalidFormatException {
    return execute(scanner::nextLine);
  }

  public int getInt() throws InvalidFormatException {
    return execute(() -> Integer.parseInt(scanner.nextLine()));
  }

  public double getDouble() throws InvalidFormatException {
    return execute(() -> Double.parseDouble(scanner.nextLine()));
  }

  private <T> T execute(PrimitiveInputCallable<T> callable) throws InvalidFormatException {
    try {
      return callable.call();
    } catch (Exception e) {
      throw new InvalidFormatException();
    }
  }
}
