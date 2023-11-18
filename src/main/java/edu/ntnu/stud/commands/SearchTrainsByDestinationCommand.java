package edu.ntnu.stud.commands;

import edu.ntnu.stud.controllers.TrainController;
import edu.ntnu.stud.input.ValidatedInput;
import edu.ntnu.stud.views.ConsoleView;

/**
 * This class represents the command for searching trains by destination.
 */
public class SearchTrainsByDestinationCommand implements Command {

  private final TrainController trainController;
  private final ConsoleView consoleView;
  private final ValidatedInput validatedInput;

  /**
   * Initializes the SearchTrainByTrainNumberCommand.
   *
   * @param trainController The TrainController to use.
   */
  public SearchTrainsByDestinationCommand(TrainController trainController) {
    this.trainController = trainController;
    this.consoleView = trainController.getConsoleView();
    this.validatedInput = new ValidatedInput();
  }

  /**
   * Returns the name of the command.
   *
   * @return the name of the command.
   */
  @Override
  public String getName() {
    return "Get trains by destination";
  }

  /**
   * returns the description of the command.
   *
   * @return the description of the command.
   */
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
