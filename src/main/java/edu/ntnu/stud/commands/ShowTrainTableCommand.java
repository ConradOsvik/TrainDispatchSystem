package edu.ntnu.stud.commands;

import edu.ntnu.stud.controllers.TrainController;

/**
 * This class represents the command for showing a table of all train departures.
 */
public class ShowTrainTableCommand implements Command {

  private final TrainController trainController;

  /**
   * Initializes the ShowTrainTableCommand.
   *
   * @param trainController The TrainController to use.
   */
  public ShowTrainTableCommand(TrainController trainController) {
    this.trainController = trainController;
  }

  /**
   * Returns the name of the command.
   *
   * @return the name of the command.
   */
  @Override
  public String getName() {
    return "Show train table";
  }

  /**
   * Returns the description of the command.
   *
   * @return the description of the command.
   */
  @Override
  public String getDescription() {
    return "shows a table of all train departures";
  }

  /**
   * executes the command.
   */
  @Override
  public void execute() {
    trainController.showTableOfTrains();
  }
}
