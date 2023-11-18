package edu.ntnu.stud.commands;

import edu.ntnu.stud.controllers.TrainController;
import edu.ntnu.stud.input.ValidatedInput;
import edu.ntnu.stud.views.ConsoleView;

public class SearchTrainByTrainNumberCommand implements Command {

  private final TrainController trainController;
  private final ConsoleView consoleView;
  private final ValidatedInput validatedInput;

  public SearchTrainByTrainNumberCommand(TrainController trainController) {
    this.trainController = trainController;
    this.consoleView = trainController.getConsoleView();
    this.validatedInput = new ValidatedInput();
  }

  @Override
  public String getName() {
    return "Get train by number";
  }

  @Override
  public String getDescription() {
    return "gets a train by its unique train number";
  }

  @Override
  public void execute() {
    consoleView.displayMessage("Please enter the train number you want to look up:");
    int trainNumber = validatedInput.getTrainNumber();

    trainController.searchTrainByTrainNumberAndPrintMessage(trainNumber);
  }
}
