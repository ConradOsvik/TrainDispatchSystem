package edu.ntnu.stud.commands;

import edu.ntnu.stud.controllers.TrainController;
import edu.ntnu.stud.input.ValidatedInput;
import edu.ntnu.stud.views.ConsoleView;

public class UpdateTimeCommand implements Command {

  private final TrainController trainController;
  private final ConsoleView consoleView;
  private final ValidatedInput validatedInput;

  public UpdateTimeCommand(TrainController trainController) {
    this.trainController = trainController;
    this.consoleView = trainController.getConsoleView();
    this.validatedInput = new ValidatedInput();
  }

  @Override
  public String getName() {
    return "Update time";
  }

  @Override
  public String getDescription() {
    return "updates the time of the day";
  }

  @Override
  public void execute() {
    consoleView.displayMessage("Please enter the time you want to set:");
    String time = validatedInput.getTime();

    trainController.updateTimeAndPrintMessage(time);
  }
}
