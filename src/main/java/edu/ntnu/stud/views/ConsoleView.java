package edu.ntnu.stud.views;

import edu.ntnu.stud.commands.Command;
import edu.ntnu.stud.input.ValidatedInput;
import edu.ntnu.stud.utils.Color;
import java.util.List;

/**
 * A class representing the console view of the application.
 */
public class ConsoleView {

  private final ValidatedInput inputValidator;

  /**
   * The constructor of the class, initializing a validated input.
   */
  public ConsoleView() {
    this.inputValidator = new ValidatedInput();
  }

  /**
   * A method for displaying the menu of the application.
   *
   * @param commands the list of commands to be displayed
   */
  public void displayMenu(List<Command> commands) {
    System.out.println("Please choose a command:");
    for (int i = 0; i < commands.size(); i++) {
      System.out.println(
          Color.colorString(Integer.toString(i + 1), Color.WHITE) + ": " + Color.colorString(
              commands.get(i).getName(), Color.CYAN) + " - " + commands.get(i).getDescription());
    }
  }

  /**
   * A method for displaying a message to the user.
   *
   * @param message the message to be displayed
   */
  public void displayMessage(String message) {
    System.out.println(message);
  }

  /**
   * A method for getting the user input.
   *
   * @param commandsSize the number of commands to choose from
   * @return the user input (choice)
   */
  public int getUserInput(int commandsSize) {
    try {
      int choice = inputValidator.getInt();

      if (choice < 1 || choice > commandsSize) {
        throw new IllegalArgumentException(
            String.format("Please enter a number between 1 and %d:", commandsSize));
      }

      return choice;
    } catch (IllegalArgumentException e) {
      this.displayMessage(Color.colorString(e.getMessage(), Color.RED));
      return getUserInput(commandsSize);
    }
  }
}
