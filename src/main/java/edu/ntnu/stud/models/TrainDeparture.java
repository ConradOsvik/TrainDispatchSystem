package edu.ntnu.stud.models;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a train departure from a station.
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
        LocalTime.parse(departureTime, DateTimeFormatter.ofPattern("HH:mm")).getHour(),
        LocalTime.parse(departureTime, DateTimeFormatter.ofPattern("HH:mm")).getMinute(),
        line,
        trainNumber,
        destination,
        track,
        LocalTime.parse(delay, DateTimeFormatter.ofPattern("HH:mm")).getHour(),
        LocalTime.parse(delay, DateTimeFormatter.ofPattern("HH:mm")).getMinute()
    );

    this.departureTime = LocalTime.parse(departureTime, DateTimeFormatter.ofPattern("HH:mm"));
    this.line = line;
    this.trainNumber = trainNumber;
    this.destination = destination;
    this.track = track;
    this.delay = LocalTime.parse(delay, DateTimeFormatter.ofPattern("HH:mm"));
  }

  /**
   * Constructs a new TrainDeparture object with the given parameters.
   *
   * @param departureHours   the departure hours of the train
   * @param departureMinutes the departure minutes of the train
   * @param line             the train line
   * @param trainNumber      the train number (unique)
   * @param destination      the destination of the train
   * @param track            the track of the train, -1 means that the train is not assigned a track
   *                         yet
   * @param delayHours       the delay hours of the train
   * @param delayMinutes     the delay minutes of the train
   */
  public TrainDeparture(
      int departureHours,
      int departureMinutes,
      String line,
      int trainNumber,
      String destination,
      int track,
      int delayHours,
      int delayMinutes
  ) {
    validateParams(
        departureHours,
        departureMinutes,
        line,
        trainNumber,
        destination,
        track,
        delayHours,
        delayMinutes
    );

    this.departureTime = LocalTime.of(departureHours, departureMinutes);
    this.line = line;
    this.trainNumber = trainNumber;
    this.destination = destination;
    this.track = track;
    this.delay = LocalTime.of(delayHours, delayMinutes);
  }

  /**
   * Constructs a new TrainDeparture object with the given parameters.
   *
   * @param departureTime the departure time of the train
   * @param line          the train line
   * @param trainNumber   the train number (unique)
   * @param destination   the destination of the train
   * @param track         the track of the train, -1 means that the train is not assigned a track
   *                      yet
   * @param delay         the delay of the train
   */
  public TrainDeparture(
      LocalTime departureTime,
      String line,
      int trainNumber,
      String destination,
      int track,
      LocalTime delay
  ) {
    validateParams(
        departureTime.getHour(),
        departureTime.getMinute(),
        line,
        trainNumber,
        destination,
        track,
        delay.getHour(),
        delay.getMinute()
    );

    this.departureTime = departureTime;
    this.line = line;
    this.trainNumber = trainNumber;
    this.destination = destination;
    this.track = track;
    this.delay = delay;
  }

  /**
   * Validates the given parameters of the constructor.
   *
   * @param departureHours   the departure hours of the train
   * @param departureMinutes the departure minutes of the train
   * @param line             the train line
   * @param trainNumber      the train number (unique)
   * @param destination      the destination of the train
   * @param track            the track of the train, -1 means that the train is not assigned a track
   *                         yet
   * @param delayHours       the delay hours of the train
   * @param delayMinutes     the delay minutes of the train
   */
  private static void validateParams(
      int departureHours,
      int departureMinutes,
      String line,
      int trainNumber,
      String destination,
      int track,
      int delayHours,
      int delayMinutes
  ) {
    if (departureHours < 0 || departureHours > 23) {
      throw new IllegalArgumentException("Departure hours must be between 0 and 23");
    }
    if (departureMinutes < 0 || departureMinutes > 59) {
      throw new IllegalArgumentException("Departure minutes must be between 0 and 59");
    }
    if (line.isBlank()) {
      throw new IllegalArgumentException("Line cannot be blank");
    }
    if (trainNumber < 0) {
      throw new IllegalArgumentException("Train number cannot be negative");
    }
    if (destination.isBlank()) {
      throw new IllegalArgumentException("Destination cannot be blank");
    }
    if (track < -1) {
      throw new IllegalArgumentException("Track cannot be negative greater than -1");
    }
    if (delayHours < 0 || delayHours > 23) {
      throw new IllegalArgumentException("Delay hours must be between 0 and 23");
    }
    if (delayMinutes < 0 || delayMinutes > 59) {
      throw new IllegalArgumentException("Delay minutes must be between 0 and 59");
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
      throw new IllegalArgumentException("Track cannot be negative");
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
    if (LocalTime.parse(delay, DateTimeFormatter.ofPattern("HH:mm"))
        .isBefore(LocalTime.parse("00:00"))) {
      throw new IllegalArgumentException("Delay cannot be negative");
    }
    if (LocalTime.parse(delay, DateTimeFormatter.ofPattern("HH:mm"))
        .isAfter(LocalTime.parse("23:59"))) {
      throw new IllegalArgumentException("Delay cannot be more than 23:59");
    }

    this.delay = LocalTime.parse(delay, DateTimeFormatter.ofPattern("HH:mm"));
  }

  /**
   * delay is a LocalTime object.
   *
   * @param delay delay of the train
   */
  public void setDelay(LocalTime delay) {
    if (delay.isBefore(LocalTime.parse("00:00"))) {
      throw new IllegalArgumentException("Delay cannot be negative");
    }
    if (delay.isAfter(LocalTime.parse("23:59"))) {
      throw new IllegalArgumentException("Delay cannot be more than 23:59");
    }

    this.delay = delay;
  }

  /**
   * delayHours is an int between 0 and 23, delayMinutes is an int between 0 and 59.
   *
   * @param delayHours   amount of delay hours of the train
   * @param delayMinutes amount of delay minutes of the train
   */
  public void setDelay(int delayHours, int delayMinutes) {
    if (LocalTime.of(delayHours, delayMinutes).isBefore(LocalTime.parse("00:00"))) {
      throw new IllegalArgumentException("Delay cannot be negative");
    }
    if (LocalTime.of(delayHours, delayMinutes).isAfter(LocalTime.parse("23:59"))) {
      throw new IllegalArgumentException("Delay cannot be more than 23:59");
    }

    this.delay = LocalTime.of(delayHours, delayMinutes);
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
