package edu.ntnu.stud.views;

import edu.ntnu.stud.commands.Command;
import edu.ntnu.stud.input.InputValidator;
import java.util.List;

public class ConsoleView {

  private final InputValidator inputValidator;

  public ConsoleView() {
    this.inputValidator = new InputValidator();
  }

  public void displayMenu(List<Command> commands) {
    System.out.println("Please choose a command:");
    for (int i = 0; i < commands.size(); i++) {
      System.out.println(
          (i + 1) + ": " + commands.get(i).getName() + " - " + commands.get(i).getDescription());
    }
  }

  public String getUserInput() {
    return inputValidator.getTime();
  }
}
