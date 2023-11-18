package edu.ntnu.stud.views;

import edu.ntnu.stud.commands.Command;
import edu.ntnu.stud.input.ValidatedInput;
import edu.ntnu.stud.utils.Color;
import java.util.List;

public class ConsoleView {

  private final ValidatedInput inputValidator;

  public ConsoleView() {
    this.inputValidator = new ValidatedInput();
  }

  public void displayMenu(List<Command> commands) {
    System.out.println("Please choose a command:");
    for (int i = 0; i < commands.size(); i++) {
      System.out.println(
          Color.colorString(Integer.toString(i + 1), Color.WHITE) + ": " + Color.colorString(
              commands.get(i).getName(), Color.CYAN) + " - " + commands.get(i).getDescription());
    }
  }

  public void displayMessage(String message) {
    System.out.println(message);
  }

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
