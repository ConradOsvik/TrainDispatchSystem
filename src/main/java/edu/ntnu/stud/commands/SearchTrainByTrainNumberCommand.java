package edu.ntnu.stud.commands;

import edu.ntnu.stud.controllers.TrainController;
import edu.ntnu.stud.views.ConsoleView;

public class SearchTrainByNumberCommand implements Command {

  private final TrainController trainController;
  private final ConsoleView consoleView;

  public SearchTrainByNumberCommand(TrainController trainController) {
    this.trainController = trainController;
    this.consoleView = trainController.getConsoleView();
  }

  @Override
  public String getName() {
    return "Get train by number";
  }

  @Override
  public String getDescription() {
    return "gets a train by its unique train number";
  }

  @Override
  public void execute() {
    System.out.println("SearchTrainByNumberCommand executed");
  }
}
