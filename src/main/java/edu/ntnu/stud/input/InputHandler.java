package edu.ntnu.stud.input;

import edu.ntnu.stud.exceptions.InvalidFormatException;
import edu.ntnu.stud.exceptions.InvalidInputException;

public class InputHandler {

  private final PrimitiveInputHandler primitiveInputHandler;

  public InputHandler() {
    this.primitiveInputHandler = new PrimitiveInputHandler();
  }

  public String getTime() throws InvalidFormatException, InvalidInputException {
    return primitiveInputHandler.getString();
  }
}
