package edu.ntnu.stud.commands;

import edu.ntnu.stud.controllers.TrainController;
import edu.ntnu.stud.views.ConsoleView;

public class SearchTrainByDestinationCommand implements Command {

  private final TrainController trainController;
  private final ConsoleView consoleView;

  public SearchTrainByDestinationCommand(TrainController trainController) {
    this.trainController = trainController;
    this.consoleView = trainController.getConsoleView();
  }

  @Override
  public String getName() {
    return "Get trains by destination";
  }

  @Override
  public String getDescription() {
    return "gets trains going to a certain destination";
  }

  @Override
  public void execute() {
    System.out.println("SearchTrainByDestinationCommand executed");
  }
}
