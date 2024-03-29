package edu.ntnu.stud.models;

import edu.ntnu.stud.utils.RegexValidator;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * A class representing the data model of a train departure from a station.
 *
 * <p>
 *   The data model contains the following information:
 *   <ul>
 *     <li>departure time</li>
 *     <li>line</li>
 *     <li>train number</li>
 *     <li>destination</li>
 *     <li>track</li>
 *     <li>delay</li>
 *   </ul>
 * </p>
 */
public class TrainDeparture {

  private final LocalTime departureTime;
  private final String line;
  private final int trainNumber;
  private final String destination;
  private int track;
  private LocalTime delay;

  /**
   * Constructs a new TrainDeparture object with the given parameters.
   *
   * @param departureTime the departure time of the train in the format HH:mm
   * @param line          the train line
   * @param trainNumber   the train number (unique)
   * @param destination   the destination of the train
   * @param track         the track of the train, -1 means that the train is not assigned a track
   *                      yet
   * @param delay         the delay of the train in the format HH:mm
   */
  public TrainDeparture(
      String departureTime,
      String line,
      int trainNumber,
      String destination,
      int track,
      String delay
  ) {
    validateParams(
        departureTime,
        line,
        trainNumber,
        destination,
        track,
        delay
    );

    this.departureTime = LocalTime.parse(departureTime, DateTimeFormatter.ofPattern("HH:mm"));
    this.line = line;
    this.trainNumber = trainNumber;
    this.destination = destination;
    this.track = track;
    this.delay = LocalTime.parse(delay, DateTimeFormatter.ofPattern("HH:mm"));
  }

  /**
   * Validates the given parameters of the constructor.
   *
   * @param departureTime the departure time of the train
   * @param line          the train line
   * @param trainNumber   the train number (unique)
   * @param destination   the destination of the train
   * @param track         the track of the train, -1 means that the train is not assigned a track
   *                      yet
   * @param delay         the delay of the train
   */
  private static void validateParams(
      String departureTime,
      String line,
      int trainNumber,
      String destination,
      int track,
      String delay
  ) {
    if (departureTime == null) {
      throw new IllegalArgumentException("Departure time cannot be null");
    }
    if (!RegexValidator.isTime(departureTime)) {
      throw new IllegalArgumentException("Departure time must be in the format HH:mm");
    }

    if (line == null) {
      throw new IllegalArgumentException("Line cannot be null");
    }
    if (line.isBlank()) {
      throw new IllegalArgumentException("Line cannot be blank");
    }

    if (trainNumber < 1) {
      throw new IllegalArgumentException("Train number cannot be less than 1");
    }

    if (destination == null) {
      throw new IllegalArgumentException("Destination cannot be null");
    }
    if (destination.isBlank()) {
      throw new IllegalArgumentException("Destination cannot be blank");
    }

    if (track == 0) {
      throw new IllegalArgumentException("Track cannot be 0");
    }
    if (track < -1) {
      throw new IllegalArgumentException("Track cannot be negative less than -1");
    }

    if (delay == null) {
      throw new IllegalArgumentException("Delay cannot be null");
    }
    if (!RegexValidator.isTime(delay)) {
      throw new IllegalArgumentException("Delay must be in the format HH:mm");
    }
  }

  /**
   * Returns the departure time of the train as a LocalTime object.
   *
   * @return the departure time
   */
  public LocalTime getDepartureTime() {
    return departureTime;
  }

  /**
   * Returns the delayed departure time of the train as a LocalTime object.
   *
   * @return the delayed departure time (departure time + delay)
   */
  public LocalTime getDelayedDepartureTime() {
    return departureTime.plusHours(delay.getHour()).plusMinutes(delay.getMinute());
  }

  /**
   * Returns the train line as a String.
   *
   * @return the train line
   */
  public String getLine() {
    return line;
  }

  /**
   * Returns the unique train number as an int.
   *
   * @return the train number (unique)
   */
  public int getTrainNumber() {
    return trainNumber;
  }

  /**
   * Returns the destination of the train as a String.
   *
   * @return the destination of the train
   */
  public String getDestination() {
    return destination;
  }

  /**
   * Returns the track of the train as an int, if the train is not assigned a track yet, -1 is
   * returned.
   *
   * @return -1 means that the train is not assigned a track yet
   */
  public int getTrack() {
    return track;
  }

  /**
   * Track is an int between 1 and infinity, if -1 is given, it means that the train is not assigned
   * a track yet.
   *
   * @param track -1 means that the train is not assigned a track yet
   * @throws IllegalArgumentException if the track is 0 or negative greater than -1
   */
  public void setTrack(int track) throws IllegalArgumentException {
    if (track == 0) {
      throw new IllegalArgumentException("Track cannot be 0");
    }
    if (track < -1) {
      throw new IllegalArgumentException("Track cannot be negative less than -1");
    }
    this.track = track;
  }

  /**
   * Returns the delay of the train as a LocalTime object.
   *
   * @return the delay of the train
   */
  public LocalTime getDelay() {
    return delay;
  }

  /**
   * Delay is a string of the format HH:mm.
   *
   * @param delay delay of the train
   * @throws IllegalArgumentException if the delay is negative or more than 23:59
   */
  public void setDelay(String delay) throws IllegalArgumentException {
    if (delay == null) {
      throw new IllegalArgumentException("Delay cannot be null");
    }

    if (delay.isBlank()) {
      throw new IllegalArgumentException("Delay cannot be blank");
    }

    if (!RegexValidator.isTime(delay)) {
      throw new IllegalArgumentException("Delay must be in the format HH:mm");
    }

    this.delay = LocalTime.parse(delay, DateTimeFormatter.ofPattern("HH:mm"));
  }

  /**
   * Returns whether a train is delayed or not as a boolean.
   *
   * @return whether the train is delayed or not
   */
  public boolean isDelayed() {
    return !delay.equals(LocalTime.parse("00:00"));
  }

  @Override
  public String toString() {
    return "Departure Time: " + departureTime + "\n"
        + "Line: " + line + "\n"
        + "Train Number: " + trainNumber + "\n"
        + "Destination: " + destination + "\n"
        + "Track: " + track + "\n"
        + "Delay: " + delay;
  }
}
