package edu.ntnu.stud.input;

import edu.ntnu.stud.exceptions.InvalidFormatException;
import edu.ntnu.stud.exceptions.InvalidInputException;
import edu.ntnu.stud.utils.RegexValidator;

/**
 * This class represents a handler for high level input.
 */
class InputHandler {

  private final PrimitiveInputHandler primitiveInputHandler;

  /**
   * Initializes the InputHandler.
   */
  InputHandler() {
    this.primitiveInputHandler = new PrimitiveInputHandler();
  }

  /**
   * Gets the string a user inputted.
   *
   * @return the string a user inputted
   * @throws InvalidFormatException if the input has invalid format
   */
  String getString() throws InvalidFormatException {
    return primitiveInputHandler.getString();
  }

  /**
   * Gets the int a user inputted.
   *
   * @return the int a user inputted
   * @throws InvalidFormatException if the input has invalid format
   */
  int getInt() throws InvalidFormatException {
    return primitiveInputHandler.getInt();
  }

  /**
   * Gets the double a user inputted.
   *
   * @return the double a user inputted
   * @throws InvalidFormatException if the input has invalid format
   */
  double getDouble() throws InvalidFormatException {
    return primitiveInputHandler.getDouble();
  }

  /**
   * Gets the time a user inputted on the format HH:mm.
   *
   * @return the time a user inputted
   * @throws InvalidFormatException if the input has invalid format
   */
  String getTime() throws InvalidFormatException {
    String input = primitiveInputHandler.getString();

    if (input.isBlank()) {
      // If the input is blank, set it to 00:00
      input = "00:00";
    }

    boolean validPattern = RegexValidator.isTime(input);

    if (!validPattern) {
      throw new InvalidFormatException(
          "The time must be in the format HH:mm, please try again:");
    }

    return input;
  }

  /**
   * Gets the line a user inputted on the format A1, B2, etc.
   *
   * @return the line a user inputted
   * @throws InvalidFormatException if the input has invalid format
   */
  String getLine() throws InvalidFormatException {
    String input = primitiveInputHandler.getString();

    boolean validPattern = RegexValidator.isLine(input);

    if (!validPattern) {
      throw new InvalidFormatException(
          "The line must be in the format of a capital"
              + " letter followed by 1 or 2 digits, please try again:");
    }

    return input;
  }

  /**
   * Gets the train number a user inputted.
   *
   * @return the train number a user inputted
   * @throws InvalidFormatException if the input has invalid format
   * @throws InvalidInputException  if the input is invalid (0 or less)
   */
  int getTrainNumber() throws InvalidFormatException, InvalidInputException {
    int input = primitiveInputHandler.getInt();

    if (input < 1) {
      throw new InvalidInputException("A train number cannot be less than 1, please try again:");
    }

    return input;
  }

  /**
   * Gets the track a user inputted.
   *
   * @return the track a user inputted
   * @throws InvalidFormatException if the input has invalid format
   * @throws InvalidInputException  if the input is invalid (equal 0 or less than -1)
   */
  int getTrack() throws InvalidFormatException, InvalidInputException {
    int input = primitiveInputHandler.getInt();

    if (input < -1 || input == 0) {
      throw new InvalidInputException(
          "A track cannot be less than 1, except for -1 if unset, please try again:");
    }

    return input;
  }
}
