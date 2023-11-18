package edu.ntnu.stud.controllers;

import edu.ntnu.stud.commands.AddTestDataCommand;
import edu.ntnu.stud.commands.AddTrainCommand;
import edu.ntnu.stud.commands.Command;
import edu.ntnu.stud.commands.ExitCommand;
import edu.ntnu.stud.commands.SearchTrainsByDestinationCommand;
import edu.ntnu.stud.commands.SearchTrainByTrainNumberCommand;
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

public class TrainController {

  private final TrainRegister trainRegister;
  private final ConsoleView consoleView;
  private final List<Command> commands;
  private LocalTime time;

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

  public ConsoleView getConsoleView() {
    return this.consoleView;
  }

  public void handleRequest() {
    consoleView.displayMenu(commands);
    int choice = consoleView.getUserInput(commands.size());

    Command command = commands.get(choice - 1);
    command.execute();
  }

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

  public void showTableOfTrains() {
    List<TrainDeparture> trainDepartures = this.trainRegister.getTrainsSortedByDepartureTime();
    printTableOfTrains(trainDepartures);
  }

  private String header() {
    return Color.colorString(String.format("%-20s | %-20s | %-20s | %-20s | %-20s",
        truncate("Departure time"),
        truncate("Line"),
        truncate("Train number"),
        truncate("Destination"),
        truncate("Track")), Color.YELLOW);
  }

  private String separator() {
    return Color.colorString(String.join("", Collections.nCopies(header().length(), "-")),
        Color.YELLOW);
  }

  private String row(TrainDeparture trainDeparture) {
    return Color.colorString(String.format("%-20s | %-20s | %-20s | %-20s | %-20s",
        truncate(trainDeparture.getDelayedDepartureTime().toString()),
        truncate(trainDeparture.getLine()),
        truncate(Integer.toString(trainDeparture.getTrainNumber())),
        truncate(trainDeparture.getDestination()),
        truncate(trainDeparture.getTrack() > 0 ? Integer.toString(trainDeparture.getTrack()) : "Unset")), Color.YELLOW);
  }

  private void printTableOfTrains(TrainDeparture trainDeparture) {
    consoleView.displayMessage(header());
    consoleView.displayMessage(separator());
    consoleView.displayMessage(row(trainDeparture));
  }

  private void printTableOfTrains(List<TrainDeparture> trainDepartures) {
    consoleView.displayMessage(header());
    trainDepartures.forEach(trainDeparture -> {
      consoleView.displayMessage(separator());
      consoleView.displayMessage(row(trainDeparture));
    });
  }

  private static String truncate(String text) {
    if (text.length() > 17) {
      return text.substring(0, 17) + "...";
    } else {
      return text;
    }
  }
}
