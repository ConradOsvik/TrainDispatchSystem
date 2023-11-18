package edu.ntnu.stud.commands;

import edu.ntnu.stud.controllers.TrainController;
import edu.ntnu.stud.models.TrainDeparture;

public class AddTestDataCommand implements Command {

  private final TrainController trainController;

  public AddTestDataCommand(TrainController trainController) {
    this.trainController = trainController;
  }

  @Override
  public String getName() {
    return "Add test data";
  }

  @Override
  public String getDescription() {
    return "adds test data to the application for testing";
  }

  @Override
  public void execute() {
    TrainDeparture trainDeparture1 = new TrainDeparture(
        "12:00",
        "F1",
        100,
        "Oslo",
        1,
        "00:10"
    );
    TrainDeparture trainDeparture2 = new TrainDeparture(
        "06:00",
        "A1",
        110,
        "Bergen",
        2,
        "00:20"
    );
    TrainDeparture trainDeparture3 = new TrainDeparture(
        "18:00",
        "B1",
        120,
        "Trondheim",
        3,
        "00:30"
    );

    trainController.addTrainToRegisterAndPrintMessage(trainDeparture1);
    trainController.addTrainToRegisterAndPrintMessage(trainDeparture2);
    trainController.addTrainToRegisterAndPrintMessage(trainDeparture3);
  }
}
