package edu.ntnu.stud.commands;

import edu.ntnu.stud.controllers.TrainController;
import edu.ntnu.stud.input.ValidatedInput;
import edu.ntnu.stud.views.ConsoleView;

public class SearchTrainsByDestinationCommand implements Command {

  private final TrainController trainController;
  private final ConsoleView consoleView;
  private final ValidatedInput validatedInput;

  public SearchTrainsByDestinationCommand(TrainController trainController) {
    this.trainController = trainController;
    this.consoleView = trainController.getConsoleView();
    this.validatedInput = new ValidatedInput();
  }

  @Override
  public String getName() {
    return "Get trains by destination";
  }

  @Override
  public String getDescription() {
    return "gets trains going to a certain destination";
  }

  @Override
  public void execute() {
    consoleView.displayMessage("Please enter the destination you want to look up:");
    String destination = validatedInput.getString();

    trainController.searchTrainsByDestinationAndPrintMessage(destination);
  }
}
