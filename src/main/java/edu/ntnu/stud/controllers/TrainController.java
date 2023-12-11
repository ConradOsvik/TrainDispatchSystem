package edu.ntnu.stud.controllers;

import edu.ntnu.stud.commands.AddTestDataCommand;
import edu.ntnu.stud.commands.AddTrainCommand;
import edu.ntnu.stud.commands.Command;
import edu.ntnu.stud.commands.ExitCommand;
import edu.ntnu.stud.commands.SearchTrainByTrainNumberCommand;
import edu.ntnu.stud.commands.SearchTrainsByDestinationCommand;
import edu.ntnu.stud.commands.SetTrainDelayCommand;
import edu.ntnu.stud.commands.SetTrainTrackCommand;
import edu.ntnu.stud.commands.ShowTrainTableCommand;
import edu.ntnu.stud.commands.UpdateTimeCommand;
import edu.ntnu.stud.models.TrainDeparture;
import edu.ntnu.stud.models.TrainRegister;
import edu.ntnu.stud.utils.Color;
import edu.ntnu.stud.views.ConsoleView;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * A class representing the controller of the application.
 */
public class TrainController {

  private final TrainRegister trainRegister;
  private final ConsoleView consoleView;
  private final List<Command> commands;

  /**
   * Initializes the TrainController with a trainRegister and a consoleView.
   *
   * @param trainRegister The TrainRegister to use.
   * @param consoleView   The ConsoleView to use.
   */
  public TrainController(TrainRegister trainRegister, ConsoleView consoleView) {
    this.trainRegister = trainRegister;
    this.consoleView = consoleView;
    this.commands = new ArrayList<>();

    commands.add(new AddTrainCommand(this));
    commands.add(new SetTrainTrackCommand(this));
    commands.add(new SetTrainDelayCommand(this));
    commands.add(new SearchTrainByTrainNumberCommand(this));
    commands.add(new SearchTrainsByDestinationCommand(this));
    commands.add(new UpdateTimeCommand(this));
    commands.add(new ShowTrainTableCommand(this));
    commands.add(new AddTestDataCommand(this));
    commands.add(new ExitCommand(this));
  }

  private boolean execute(TrainControllerCallable callable) {
    try {
      callable.call();
      return true;
    } catch (Exception e) {
      consoleView.displayErrorMessage(e.getMessage());
      return false;
    }
  }

  /**
   * Returns the ConsoleView.
   *
   * @return The ConsoleView.
   */
  public ConsoleView getConsoleView() {
    return this.consoleView;
  }

  /**
   * Handles the incoming requests from the application.
   */
  public void handleRequest() {
    consoleView.displayMenu(commands);
    int choice = consoleView.getUserInput(commands.size());

    Command command = commands.get(choice - 1);
    command.execute();
  }

  /**
   * Adds a train to the register and prints the result to the user.
   *
   * @param trainDeparture the train departure to be added.
   */
  public boolean addTrainToRegister(TrainDeparture trainDeparture) {
    return execute(() -> {
      this.trainRegister.addTrain(trainDeparture);
      consoleView.displayMessage(Color.colorString(
              String.format("Train with number %d successfully added to the register",
                  trainDeparture.getTrainNumber()), Color.GREEN
          )
      );
    });
  }

  /**
   * Sets the train track of a train and prints the result to the user.
   *
   * @param trainNumber the train number.
   * @param track       the track of the train.
   */
  public boolean setTrainTrack(int trainNumber, int track) {
    return execute(() -> {
      TrainDeparture trainDeparture = this.trainRegister.getTrain(trainNumber);
      if (trainDeparture == null) {
        throw new IllegalArgumentException(
            String.format("Train with number %d does not exist in the register", trainNumber));
      }
      trainDeparture.setTrack(track);

      consoleView.displayMessage(
          Color.colorString(
              String.format("Train track successfully set to %d", track),
              Color.GREEN
          )
      );
    });
  }

  /**
   * Sets the train delay of a train and prints the result to the user.
   *
   * @param trainNumber the train number.
   * @param delay       the delay of the train.
   */
  public boolean setTrainDelay(int trainNumber, String delay) {
    return execute(() -> {
      TrainDeparture trainDeparture = this.trainRegister.getTrain(trainNumber);
      if (trainDeparture == null) {
        throw new IllegalArgumentException(
            String.format("Train with number %d does not exist in the register", trainNumber));
      }
      trainDeparture.setDelay(delay);

      consoleView.displayMessage(
          Color.colorString(
              String.format("Train delay successfully set to %s", delay),
              Color.GREEN
          )
      );
    });
  }

  /**
   * Searches for a train by train number and prints the result to the user.
   *
   * @param trainNumber the train number.
   */
  public boolean searchTrainByTrainNumber(int trainNumber) {
    return execute(() -> {
      TrainDeparture trainDeparture = this.trainRegister.getTrain(trainNumber);
      if (trainDeparture == null) {
        throw new IllegalArgumentException(
            String.format("Train with number %d does not exist in the register", trainNumber));
      }

      consoleView.printTableOfTrains(trainDeparture);
    });
  }

  /**
   * Searches for trains by destination and prints the result to the user.
   *
   * @param destination the destination of the train.
   */
  public boolean searchTrainsByDestination(String destination) {
    return execute(() -> {
      List<TrainDeparture> trainDepartures = this.trainRegister.getTrainsByDestination(destination);
      if (trainDepartures.isEmpty()) {
        throw new IllegalArgumentException(
            String.format("No trains going to %s found in the register", destination));
      }

      consoleView.printTableOfTrains(trainDepartures);
    });
  }

  /**
   * Updates the time and prints the result to the user.
   *
   * @param time the time to be set.
   */
  public boolean updateTime(String time) {
    return execute(() -> {
      trainRegister.setTime(LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm")));

      consoleView.displayMessage(
          Color.colorString(String.format("Time successfully set to %s", trainRegister.getTime()),
              Color.GREEN));
    });
  }

  /**
   * Shows the table of trains.
   */
  public boolean showTableOfTrains() {
    return execute(() -> {
      List<TrainDeparture> trainDepartures = this.trainRegister.getTrainsSortedByDepartureTime();
      consoleView.printTableOfTrains(trainDepartures);
    });
  }
}
