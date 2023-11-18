package edu.ntnu.stud.commands;

import edu.ntnu.stud.controllers.TrainController;
import edu.ntnu.stud.utils.Color;
import edu.ntnu.stud.views.ConsoleView;

/**
 * This class represents the command for exiting the application.
 */
public class ExitCommand implements Command {

  private final TrainController trainController;
  private final ConsoleView consoleView;

  /**
   * Initializes the ExitCommand.
   *
   * @param trainController The TrainController to use.
   */
  public ExitCommand(TrainController trainController) {
    this.trainController = trainController;
    this.consoleView = trainController.getConsoleView();
  }

  /**
   * Returns the name of the command.
   *
   * @return the name of the command.
   */
  @Override
  public String getName() {
    return "Exit";
  }

  /**
   * Returns the description of the command.
   *
   * @return the description of the command.
   */
  @Override
  public String getDescription() {
    return "exits the application";
  }

  /**
   * executes the command.
   */
  @Override
  public void execute() {
    System.out.println(Color.colorString("Exiting the application", Color.GREEN));
    System.exit(0);
  }
}
