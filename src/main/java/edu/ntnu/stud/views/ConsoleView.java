package edu.ntnu.stud.views;

import static edu.ntnu.stud.utils.Truncate.truncate;

import edu.ntnu.stud.commands.Command;
import edu.ntnu.stud.input.ValidatedInput;
import edu.ntnu.stud.models.TrainDeparture;
import edu.ntnu.stud.utils.Color;
import java.util.Collections;
import java.util.List;

/**
 * A class representing the console view of the application.
 */
public class ConsoleView {

  private final ValidatedInput inputValidator;

  /**
   * The constructor of the class, initializing a validated input.
   */
  public ConsoleView() {
    this.inputValidator = new ValidatedInput(this);
  }

  /**
   * Creates a table header.
   */
  private String header() {
    return Color.colorString(
        String.format(
            "%-20s | %-20s | %-20s | %-20s | %-20s | %-20s",
            truncate("Departure time"),
            truncate("Line"),
            truncate("Train number"),
            truncate("Destination"),
            truncate("Track"),
            truncate("Delay")
        ),
        Color.YELLOW
    );
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
    return Color.colorString(String.format("%-20s | %-20s | %-20s | %-20s | %-20s | %-20s",
            truncate(trainDeparture.getDepartureTime().toString()),
            truncate(trainDeparture.getLine()),
            truncate(Integer.toString(trainDeparture.getTrainNumber())),
            truncate(trainDeparture.getDestination()),
            truncate(
                trainDeparture.getTrack() > 0
                    ? Integer.toString(trainDeparture.getTrack())
                    : "Unset"
            ),
            truncate(trainDeparture.getDelay().toString())
        ),
        Color.YELLOW
    );
  }

  /**
   * Prints a table of trains using a train departure.
   *
   * @param trainDeparture the train departure.
   */
  public void printTableOfTrains(TrainDeparture trainDeparture) {
    this.displayMessage(header());
    this.displayMessage(separator());
    this.displayMessage(row(trainDeparture));
  }

  /**
   * Prints a table of trains using a list of train departures.
   *
   * @param trainDepartures the list of train departures.
   */
  public void printTableOfTrains(List<TrainDeparture> trainDepartures) {
    this.displayMessage(header());
    trainDepartures.forEach(trainDeparture -> {
      this.displayMessage(separator());
      this.displayMessage(row(trainDeparture));
    });
  }

  /**
   * A method for displaying the menu of the application.
   *
   * @param commands the list of commands to be displayed
   */
  public void displayMenu(List<Command> commands) {
    System.out.println("Please choose a command:");
    for (int i = 0; i < commands.size(); i++) {
      System.out.println(
          Color.colorString(Integer.toString(i + 1), Color.WHITE) + ": " + Color.colorString(
              commands.get(i).getName(), Color.CYAN) + " - " + commands.get(i).getDescription());
    }
  }

  /**
   * A method for getting the user input.
   *
   * @param commandsSize the number of commands to choose from
   * @return the user input (choice)
   */
  public int getUserInput(int commandsSize) {
    try {
      int choice = inputValidator.getInt();

      if (choice < 1 || choice > commandsSize) {
        throw new IllegalArgumentException(
            String.format("Please enter a number between 1 and %d:", commandsSize));
      }

      return choice;
    } catch (IllegalArgumentException e) {
      this.displayMessage(Color.colorString(e.getMessage(), Color.RED));
      return getUserInput(commandsSize);
    }
  }

  /**
   * A method for displaying a message to the user.
   *
   * @param message the message to be displayed
   */
  public void displayMessage(String message) {
    System.out.println(message);
  }

  public void displayErrorMessage(String message) {
    System.err.println(message);
  }
}
