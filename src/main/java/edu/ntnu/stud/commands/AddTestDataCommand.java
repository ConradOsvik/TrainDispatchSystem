package edu.ntnu.stud.commands;

import edu.ntnu.stud.controllers.TrainController;
import edu.ntnu.stud.models.TrainDeparture;

/**
 * A Command class adding test data when run.
 */
public class AddTestDataCommand implements Command {

  private final TrainController trainController;

  /**
   * Initializes the AddTestDataCommand.
   *
   * @param trainController The TrainController to use.
   */
  public AddTestDataCommand(TrainController trainController) {
    this.trainController = trainController;
  }

  /**
   * Returns the name of the command.
   *
   * @return The name of the command.
   */
  @Override
  public String getName() {
    return "Add test data";
  }

  /**
   * Returns the description of the command.
   *
   * @return The description of the command.
   */
  @Override
  public String getDescription() {
    return "adds test data to the application for testing";
  }

  /**
   * Executes the command.
   */
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

    trainController.addTrainToRegister(trainDeparture1);
    trainController.addTrainToRegister(trainDeparture2);
    trainController.addTrainToRegister(trainDeparture3);
  }
}
