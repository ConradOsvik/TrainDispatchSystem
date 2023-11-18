package edu.ntnu.stud.commands;

import edu.ntnu.stud.controllers.TrainController;
import edu.ntnu.stud.input.ValidatedInput;
import edu.ntnu.stud.views.ConsoleView;

public class SetTrainDelayCommand implements Command {

  private final TrainController trainController;
  private final ConsoleView consoleView;
  private final ValidatedInput validatedInput;

  public SetTrainDelayCommand(TrainController trainController) {
    this.trainController = trainController;
    this.consoleView = trainController.getConsoleView();
    this.validatedInput = new ValidatedInput();
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
    consoleView.displayMessage("Please enter the train number:");
    int trainNumber = validatedInput.getTrainNumber();
    consoleView.displayMessage("Please enter the delay on format HH:mm:");
    String delay = validatedInput.getTime();

    trainController.setTrainDelayAndPrintMessage(trainNumber, delay);
  }
}
