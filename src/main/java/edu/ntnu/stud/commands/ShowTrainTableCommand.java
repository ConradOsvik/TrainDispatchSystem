package edu.ntnu.stud.commands;

import edu.ntnu.stud.controllers.TrainController;

public class ShowTrainTableCommand implements Command {

  private final TrainController trainController;

  public ShowTrainTableCommand(TrainController trainController) {
    this.trainController = trainController;
  }

  @Override
  public String getName() {
    return "Show train table";
  }

  @Override
  public String getDescription() {
    return "shows a table of all train departures";
  }

  @Override
  public void execute() {
    trainController.showTableOfTrains();
  }
}
