package edu.ntnu.stud.commands;

import edu.ntnu.stud.controllers.TrainController;
import edu.ntnu.stud.input.ValidatedInput;
import edu.ntnu.stud.views.ConsoleView;

/**
 * This class represents the command for adding a delay to a train.
 */
public class SetTrainDelayCommand implements Command {

  private final TrainController trainController;
  private final ConsoleView consoleView;
  private final ValidatedInput validatedInput;

  /**
   * Initializes the SetTrainDelayCommand.
   *
   * @param trainController The TrainController to use.
   */
  public SetTrainDelayCommand(TrainController trainController) {
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
    return "Add train delay";
  }

  /**
   * Returns the description of the command.
   *
   * @return the description of the command.
   */
  @Override
  public String getDescription() {
    return "adds a delay to a train";
  }

  /**
   * executes the command.
   */
  @Override
  public void execute() {
    consoleView.displayMessage("Please enter the train number:");
    int trainNumber = validatedInput.getTrainNumber();
    consoleView.displayMessage("Please enter the delay on format HH:mm:");
    String delay = validatedInput.getTime();

    trainController.setTrainDelay(trainNumber, delay);
  }
}
