package edu.ntnu.stud.commands;

import edu.ntnu.stud.controllers.TrainController;
import edu.ntnu.stud.input.ValidatedInput;
import edu.ntnu.stud.views.ConsoleView;

/**
 * This class represents the command for setting the track of a train.
 */
public class SetTrainTrackCommand implements Command {

  private final TrainController trainController;
  private final ConsoleView consoleView;
  private final ValidatedInput validatedInput;

  /**
   * Initializes the SetTrainTrackCommand.
   *
   * @param trainController The TrainController to use.
   */
  public SetTrainTrackCommand(TrainController trainController) {
    this.trainController = trainController;
    this.consoleView = trainController.getConsoleView();
    this.validatedInput = new ValidatedInput(this.consoleView);
  }

  /**
   * Returns the name of the command.
   *
   * @return the name of the command.
   */
  @Override
  public String getName() {
    return "Set train track";
  }

  /**
   * Returns the description of the command.
   *
   * @return the description of the command.
   */
  @Override
  public String getDescription() {
    return "sets the track of a train";
  }

  /**
   * executes the command.
   */
  @Override
  public void execute() {
    consoleView.displayMessage("Enter the train number:");
    int trainNumber = validatedInput.getTrainNumber();
    consoleView.displayMessage("Enter the track number:");
    int track = validatedInput.getTrack();

    trainController.setTrainTrackAndPrintMessage(trainNumber, track);
  }
}
