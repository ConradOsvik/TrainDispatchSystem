package edu.ntnu.stud.commands;

import edu.ntnu.stud.controllers.TrainController;
import edu.ntnu.stud.views.ConsoleView;

public class AddTrainDelayCommand implements Command {
  private final TrainController trainController;
  private final ConsoleView consoleView;

  public AddTrainDelayCommand(TrainController trainController) {
    this.trainController = trainController;
    this.consoleView = trainController.getConsoleView();
  }

  @Override
  public String getName() {
    return "Add train delay";
  }

  @Override
  public String getDescription() {
    return "adds a delay to a train";
  }

  @Override
  public void execute() {
    System.out.println("AddTrainDelayCommand executed");
  }
}
