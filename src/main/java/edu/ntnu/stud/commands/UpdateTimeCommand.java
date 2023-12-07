package edu.ntnu.stud.commands;

import edu.ntnu.stud.controllers.TrainController;
import edu.ntnu.stud.input.ValidatedInput;
import edu.ntnu.stud.views.ConsoleView;

/**
 * This class represents the command for updating the time of the day.
 */
public class UpdateTimeCommand implements Command {

  private final TrainController trainController;
  private final ConsoleView consoleView;
  private final ValidatedInput validatedInput;

  /**
   * Initializes the UpdateTimeCommand.
   *
   * @param trainController The TrainController to use.
   */
  public UpdateTimeCommand(TrainController trainController) {
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
    return "Update time";
  }

  /**
   * Returns the description of the command.
   *
   * @return the description of the command.
   */
  @Override
  public String getDescription() {
    return "updates the time of the day";
  }

  /**
   * executes the command.
   */
  @Override
  public void execute() {
    consoleView.displayMessage("Please enter the time you want to set:");
    String time = validatedInput.getTime();

    trainController.updateTimeAndPrintMessage(time);
  }
}
