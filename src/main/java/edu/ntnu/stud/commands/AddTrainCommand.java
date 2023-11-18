package edu.ntnu.stud.commands;

import edu.ntnu.stud.controllers.TrainController;
import edu.ntnu.stud.input.ValidatedInput;
import edu.ntnu.stud.models.TrainDeparture;
import edu.ntnu.stud.views.ConsoleView;

public class AddTrainCommand implements Command {

  private final TrainController trainController;
  private final ConsoleView consoleView;
  private final ValidatedInput validatedInput;

  public AddTrainCommand(TrainController trainController) {
    this.trainController = trainController;
    this.consoleView = trainController.getConsoleView();
    this.validatedInput = new ValidatedInput();
  }

  @Override
  public String getName() {
    return "Add train";
  }

  @Override
  public String getDescription() {
    return "adds a train to the train register";
  }

  @Override
  public void execute() {
    consoleView.displayMessage("Please enter departure time on format HH:mm:");
    String departureTime = validatedInput.getTime();
    consoleView.displayMessage("Please enter the line");
    String line = validatedInput.getLine();
    consoleView.displayMessage("Please enter train number:");
    int trainNumber = validatedInput.getTrainNumber();
    consoleView.displayMessage("Please enter destination:");
    String destination = validatedInput.getString();
    consoleView.displayMessage("Please enter the track number, -1 for unset:");
    int trackNumber = validatedInput.getTrack();
    consoleView.displayMessage("Please enter the delay on format HH:mm:");
    String delay = validatedInput.getTime();

    TrainDeparture trainDeparture = new TrainDeparture(departureTime, line, trainNumber,
        destination, trackNumber, delay);

    trainController.addTrainToRegisterAndPrintMessage(trainDeparture);
  }
}
