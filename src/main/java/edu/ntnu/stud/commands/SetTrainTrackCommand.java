package edu.ntnu.stud.commands;

import edu.ntnu.stud.controllers.TrainController;
import edu.ntnu.stud.input.ValidatedInput;
import edu.ntnu.stud.views.ConsoleView;

public class SetTrainTrackCommand implements Command {

  private final TrainController trainController;
  private final ConsoleView consoleView;
  private final ValidatedInput validatedInput;

  public SetTrainTrackCommand(TrainController trainController) {
    this.trainController = trainController;
    this.consoleView = trainController.getConsoleView();
    this.validatedInput = new ValidatedInput();
  }

  @Override
  public String getName() {
    return "Set train track";
  }

  @Override
  public String getDescription() {
    return "sets the track of a train";
  }

  @Override
  public void execute() {
    consoleView.displayMessage("Enter the train number:");
    int trainNumber = validatedInput.getTrainNumber();
    consoleView.displayMessage("Enter the track number:");
    int track = validatedInput.getTrack();

    trainController.setTrainTrackAndPrintMessage(trainNumber, track);
  }
}
