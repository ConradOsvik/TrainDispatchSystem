package edu.ntnu.stud.models;

import java.time.LocalTime;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A register storing train departures in a hashmap.
 */
public class TrainRegister {

  private final HashMap<Integer, TrainDeparture> register;
  private LocalTime time;

  /**
   * Constructs a new TrainRegister object, initializing the register hashmap.
   */
  public TrainRegister() {
    this.register = new HashMap<>();
    this.time = LocalTime.parse("00:00");
  }

  /**
   * Checks if a train departure is before or equal the current time.
   *
   * @param trainDeparture the train departure to check
   * @return true if the train departure is before or equal the current time, false otherwise
   */
  private boolean isTrainDepartureBeforeTime(TrainDeparture trainDeparture) {
    return !trainDeparture.getDelayedDepartureTime().isAfter(this.time);
  }

  /**
   * Removes all train departures from the register with a departure time before or equal the current time.
   */
  private void removeTrainsBeforeTime() {
    this.register.values()
        .removeIf(this::isTrainDepartureBeforeTime);
  }

  /**
   * Gets the current time of the register.
   *
   * @return the current time of the register
   */
  public LocalTime getTime() {
    return time;
  }

  /**
   * Sets the current time of the register.
   *
   * @param time the time to set
   * @throws IllegalArgumentException if the time is null or before the current time
   */
  public void setTime(LocalTime time) throws IllegalArgumentException {
    if (time == null) {
      throw new IllegalArgumentException("Time cannot be null");
    }

    if (time.isBefore(this.time)) {
      throw new IllegalArgumentException(
          String.format("Cannot set time to %s, as it is before the current time of %s", time,
              this.time));
    }

    this.time = time;

    this.removeTrainsBeforeTime();
  }

  /**
   * Adds a train departure to the register.
   *
   * @param trainDeparture the train departure to add
   * @throws IllegalArgumentException if the train number already exists in the register
   */
  public void addTrain(TrainDeparture trainDeparture) throws IllegalArgumentException {
    if (trainDeparture == null) {
      throw new IllegalArgumentException("Train departure cannot be null");
    }

    if (this.getTrain(trainDeparture.getTrainNumber()) != null) {
      throw new IllegalArgumentException(
          "Cannot add a train departure with a train number that already exists in the register");
    }

    if (this.isTrainDepartureBeforeTime(trainDeparture)) {
      throw new IllegalArgumentException(
          String.format(
              "Train with number %d cannot be added to the"
                  + " register because it has already departed",
              trainDeparture.getTrainNumber()));
    }

    register.put(trainDeparture.getTrainNumber(), trainDeparture);
  }

  /**
   * Gets a train departure from the register by its train number.
   *
   * @param trainNumber the train number of the train departure to get
   * @return the train departure for a given train number, or null if no such train departure exists
   */
  public TrainDeparture getTrain(int trainNumber) {
    return register.get(trainNumber);
  }

  /**
   * Gets all train departures from the register with the given destination.
   *
   * @param destination the destination of the train departures to get
   * @return a list of train departures with the given destination
   */
  public List<TrainDeparture> getTrainsByDestination(String destination) {
    return this.register.values().stream()
        .filter(trainDeparture -> trainDeparture.getDestination().equals(destination))
        .collect(Collectors.toList());
  }

  /**
   * Gets all train departures from the register with the given line.
   *
   * @return a list of train departures with the given line
   */
  public List<TrainDeparture> getTrainsSortedByDepartureTime() {
    return register.values().stream()
        .sorted(Comparator.comparing(TrainDeparture::getDepartureTime)).toList();
  }
}
