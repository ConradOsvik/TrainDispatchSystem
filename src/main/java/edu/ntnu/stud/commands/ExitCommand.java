package edu.ntnu.stud.commands;

import edu.ntnu.stud.controllers.TrainController;
import edu.ntnu.stud.utils.Color;
import edu.ntnu.stud.views.ConsoleView;

public class ExitCommand implements Command {

  private final TrainController trainController;
  private final ConsoleView consoleView;

  public ExitCommand(TrainController trainController) {
    this.trainController = trainController;
    this.consoleView = trainController.getConsoleView();
  }

  @Override
  public String getName() {
    return "Exit";
  }

  @Override
  public String getDescription() {
    return "exits the application";
  }

  @Override
  public void execute() {
    System.out.println(Color.colorString("Exiting the application",Color.GREEN));
    System.exit(0);
  }
}
