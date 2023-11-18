package edu.ntnu.stud.commands;

import edu.ntnu.stud.controllers.TrainController;
import edu.ntnu.stud.input.ValidatedInput;
import edu.ntnu.stud.views.ConsoleView;

/**
 * This class represents the command for searching for a train by its train number.
 */
public class SearchTrainByTrainNumberCommand implements Command {

  private final TrainController trainController;
  private final ConsoleView consoleView;
  private final ValidatedInput validatedInput;

  /**
   * Initializes the SearchTrainByTrainNumberCommand.
   *
   * @param trainController The TrainController to use.
   */
  public SearchTrainByTrainNumberCommand(TrainController trainController) {
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
    return "Get train by number";
  }

  /**
   * Returns the description of the command.
   *
   * @return the description of the command.
   */
  @Override
  public String getDescription() {
    return "gets a train by its unique train number";
  }

  /**
   * executes the command.
   */
  @Override
  public void execute() {
    consoleView.displayMessage("Please enter the train number you want to look up:");
    int trainNumber = validatedInput.getTrainNumber();

    trainController.searchTrainByTrainNumberAndPrintMessage(trainNumber);
  }
}
