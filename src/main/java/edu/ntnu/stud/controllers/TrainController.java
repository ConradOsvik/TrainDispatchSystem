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
import java.util.Collections;
import java.util.List;

/**
 * A class representing the controller of the application.
 */
public class TrainController {

  private final TrainRegister trainRegister;
  private final ConsoleView consoleView;
  private final List<Command> commands;
  private LocalTime time;

  /**
   * Initializes the TrainController.
   *
   * @param trainRegister The TrainRegister to use.
   * @param consoleView   The ConsoleView to use.
   */
  public TrainController(TrainRegister trainRegister, ConsoleView consoleView) {
    this.trainRegister = trainRegister;
    this.consoleView = consoleView;
    this.commands = new ArrayList<>();
    this.time = LocalTime.parse("00:00", DateTimeFormatter.ofPattern("HH:mm"));

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

  /**
   * Truncates a string to a maximum length of 20 characters.
   *
   * @param text the text to be truncated.
   */
  private static String truncate(String text) {
    if (text.length() > 20) {
      return text.substring(0, 17) + "...";
    } else {
      return text;
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
  public void addTrainToRegisterAndPrintMessage(TrainDeparture trainDeparture) {
    try {
      if (trainDeparture.getDelayedDepartureTime().isBefore(this.time)) {
        throw new IllegalArgumentException(String.format(
            "Train with number %d cannot be added to the register because it has already departed",
            trainDeparture.getTrainNumber()));
      }

      this.trainRegister.addTrain(trainDeparture);
      consoleView.displayMessage(Color.colorString(
          String.format("Train with number %d successfully added to the register",
              trainDeparture.getTrainNumber()), Color.GREEN));
    } catch (IllegalArgumentException e) {
      consoleView.displayMessage(Color.colorString(e.getMessage(), Color.RED));
    }
  }

  /**
   * Sets the train track of a train and prints the result to the user.
   *
   * @param trainNumber the train number.
   * @param track       the track of the train.
   */
  public void setTrainTrackAndPrintMessage(int trainNumber, int track) {
    try {
      TrainDeparture trainDeparture = this.trainRegister.getTrain(trainNumber);
      if (trainDeparture == null) {
        throw new IllegalArgumentException(
            String.format("Train with number %d does not exist in the register", trainNumber));
      }
      trainDeparture.setTrack(track);

      consoleView.displayMessage(
          Color.colorString(String.format("Train track successfully set to %d", track),
              Color.GREEN));
    } catch (IllegalArgumentException e) {
      consoleView.displayMessage(Color.colorString(e.getMessage(), Color.RED));
    }
  }

  /**
   * Sets the train delay of a train and prints the result to the user.
   *
   * @param trainNumber the train number.
   * @param delay       the delay of the train.
   */
  public void setTrainDelayAndPrintMessage(int trainNumber, String delay) {
    try {
      TrainDeparture trainDeparture = this.trainRegister.getTrain(trainNumber);
      if (trainDeparture == null) {
        throw new IllegalArgumentException(
            String.format("Train with number %d does not exist in the register", trainNumber));
      }
      trainDeparture.setDelay(delay);

      consoleView.displayMessage(
          Color.colorString(String.format("Train delay successfully set to %s", delay),
              Color.GREEN));
    } catch (IllegalArgumentException e) {
      consoleView.displayMessage(Color.colorString(e.getMessage(), Color.RED));
    }
  }

  /**
   * Searches for a train by train number and prints the result to the user.
   *
   * @param trainNumber the train number.
   */
  public void searchTrainByTrainNumberAndPrintMessage(int trainNumber) {
    try {
      TrainDeparture trainDeparture = this.trainRegister.getTrain(trainNumber);
      if (trainDeparture == null) {
        throw new IllegalArgumentException(
            String.format("Train with number %d does not exist in the register", trainNumber));
      }

      printTableOfTrains(trainDeparture);
    } catch (IllegalArgumentException e) {
      consoleView.displayMessage(Color.colorString(e.getMessage(), Color.RED));
    }
  }

  /**
   * Searches for trains by destination and prints the result to the user.
   *
   * @param destination the destination of the train.
   */
  public void searchTrainsByDestinationAndPrintMessage(String destination) {
    try {
      List<TrainDeparture> trainDepartures = this.trainRegister.getTrainsByDestination(destination);
      if (trainDepartures.isEmpty()) {
        throw new IllegalArgumentException(
            String.format("No trains going to %s found in the register", destination));
      }

      printTableOfTrains(trainDepartures);
    } catch (IllegalArgumentException e) {
      consoleView.displayMessage(Color.colorString(e.getMessage(), Color.RED));
    }
  }

  /**
   * Updates the time and prints the result to the user.
   *
   * @param time the time to be set.
   */
  public void updateTimeAndPrintMessage(String time) {
    try {
      this.time = LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm"));
      this.trainRegister.removeTrainsAfterTime(this.time);

      consoleView.displayMessage(
          Color.colorString(String.format("Time successfully set to %s", time), Color.GREEN));
    } catch (IllegalArgumentException e) {
      consoleView.displayMessage(Color.colorString(e.getMessage(), Color.RED));
    }
  }

  /**
   * Shows the table of trains.
   */
  public void showTableOfTrains() {
    List<TrainDeparture> trainDepartures = this.trainRegister.getTrainsSortedByDepartureTime();
    printTableOfTrains(trainDepartures);
  }

  /**
   * Creates a table header.
   */
  private String header() {
    return Color.colorString(String.format("%-20s | %-20s | %-20s | %-20s | %-20s",
        truncate("Departure time"),
        truncate("Line"),
        truncate("Train number"),
        truncate("Destination"),
        truncate("Track")), Color.YELLOW);
  }

  /**
   * Creates a table separator.
   */
  private String separator() {
    return Color.colorString(String.join("", Collections.nCopies(header().length(), "-")),
        Color.YELLOW);
  }

  /**
   * Creates a table row using a train departure.
   *
   * @param trainDeparture the train departure.
   */
  private String row(TrainDeparture trainDeparture) {
    return Color.colorString(String.format("%-20s | %-20s | %-20s | %-20s | %-20s",
            truncate(trainDeparture.getDelayedDepartureTime().toString()),
            truncate(trainDeparture.getLine()),
            truncate(Integer.toString(trainDeparture.getTrainNumber())),
            truncate(trainDeparture.getDestination()),
            truncate(
                trainDeparture.getTrack() > 0
                    ? Integer.toString(trainDeparture.getTrack())
                    : "Unset"
            )
        ),
        Color.YELLOW
    );
  }

  /**
   * Prints a table of trains using a train departure.
   *
   * @param trainDeparture the train departure.
   */
  private void printTableOfTrains(TrainDeparture trainDeparture) {
    consoleView.displayMessage(header());
    consoleView.displayMessage(separator());
    consoleView.displayMessage(row(trainDeparture));
  }

  /**
   * Prints a table of trains using a list of train departures.
   *
   * @param trainDepartures the list of train departures.
   */
  private void printTableOfTrains(List<TrainDeparture> trainDepartures) {
    consoleView.displayMessage(header());
    trainDepartures.forEach(trainDeparture -> {
      consoleView.displayMessage(separator());
      consoleView.displayMessage(row(trainDeparture));
    });
  }
}
