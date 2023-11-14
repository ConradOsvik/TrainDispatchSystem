package edu.ntnu.stud.models;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * A register storing train departures in a hashmap.
 */
public class TrainRegister {

  private final HashMap<Integer, TrainDeparture> register;

  /**
   * Constructs a new TrainRegister object, initializing the register hashmap.
   */
  public TrainRegister() {
    this.register = new HashMap<>();
  }

  /**
   * Adds a train departure to the register.
   *
   * @param trainDeparture the train departure to add
   * @throws IllegalArgumentException if the train number already exists in the register
   */
  public void addTrain(TrainDeparture trainDeparture) throws IllegalArgumentException {
    if (this.getTrain(trainDeparture.getTrainNumber()) != null) {
      throw new IllegalArgumentException(
          "Cannot add a train departure with a train number that already exists in the register");
    }

    register.put(trainDeparture.getTrainNumber(), trainDeparture);
  }

  /**
   * Removes a train departure from the register.
   *
   * @param trainNumber the train number of the train departure to remove
   */
  public void removeTrain(int trainNumber) {
    register.remove(trainNumber);
  }

  /**
   * Removes all train departures from the register with a departure time before the given time.
   *
   * @param time the time to remove train departures before
   */
  public void removeTrainsAfterTime(LocalTime time) {
    for (TrainDeparture trainDeparture : register.values()) {
      if (trainDeparture.getDelayedDepartureTime().isBefore(time)) {
        this.removeTrain(trainDeparture.getTrainNumber());
      }
    }
  }

  /**
   * Gets a train departure from the register by its train number.
   *
   * @param trainNumber the train number of the train departure to get
   * @return the train departure with the given train number, or null if no such train departure
   *     exists
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
    List<TrainDeparture> trainDepartures = new ArrayList<>();

    for (TrainDeparture trainDeparture : register.values()) {
      if (trainDeparture.getDestination().equals(destination)) {
        trainDepartures.add(trainDeparture);
      }
    }

    return trainDepartures;
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

  /**
   * Gets the register itself (the hashmap).
   *
   * @return the register
   */
  public HashMap<Integer, TrainDeparture> getRegister() {
    return register;
  }

  /**
   * Checks if the register is empty.
   *
   * @return true if the register is empty, false otherwise
   */
  public boolean isEmpty() {
    return register.isEmpty();
  }

  /**
   * Gets the size of the register.
   *
   * @return the size of the register
   */
  public int size() {
    return register.size();
  }
}
