package edu.ntnu.stud.input;

import edu.ntnu.stud.exceptions.InvalidFormatException;
import edu.ntnu.stud.exceptions.InvalidInputException;
import edu.ntnu.stud.utils.RegexValidator;

public class InputHandler {

  private final PrimitiveInputHandler primitiveInputHandler;

  public InputHandler() {
    this.primitiveInputHandler = new PrimitiveInputHandler();
  }

  public String getString() throws InvalidFormatException {
    return primitiveInputHandler.getString();
  }

  public int getInt() throws InvalidFormatException {
    return primitiveInputHandler.getInt();
  }

  public double getDouble() throws InvalidFormatException {
    return primitiveInputHandler.getDouble();
  }

  public String getTime() throws InvalidFormatException, InvalidInputException {
    String input = primitiveInputHandler.getString();
    boolean validPattern = RegexValidator.isTime(input);

    if (!validPattern) {
      throw new InvalidFormatException(
          "The time must be in the format of HH:mm, please try again:");
    }

    return input;
  }

  public String getLine() throws InvalidFormatException {
    String input = primitiveInputHandler.getString();
    boolean validPattern = RegexValidator.isLine(input);

    if (!validPattern) {
      throw new InvalidFormatException(
          "The line must be in the format of a capital letter followed by 1 or 2 digits, please try again:");
    }

    return input;
  }

  public int getTrainNumber() throws InvalidFormatException, InvalidInputException {
    int input = primitiveInputHandler.getInt();

    if (input < 1) {
      throw new InvalidInputException("A train number cannot be less than 1, please try again:");
    }

    return input;
  }

  public int getTrack() throws InvalidFormatException, InvalidInputException {
    int input = primitiveInputHandler.getInt();

    if (input < -1 || input == 0) {
      throw new InvalidInputException(
          "A track cannot be less than 1, except for -1 if unset, please try again:");
    }

    return input;
  }
}
