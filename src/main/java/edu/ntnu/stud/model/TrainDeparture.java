package edu.ntnu.stud.model;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TrainDeparture {
  private LocalTime departureTime;
  private String line;
  private int trainNumber;
  private String destination;
  private int track;
  private LocalTime delay;

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

  private static void validateParams(
      int departureHours,
      int departureMinutes,
      String line,
      int trainNumber,
      String destination,
      int track,
      int delayHours,
      int delayMinutes
  ){
    if(departureHours < 0 || departureHours > 23)
      throw new IllegalArgumentException("Departure hours must be between 0 and 23");
    if(departureMinutes < 0 || departureMinutes > 59)
      throw new IllegalArgumentException("Departure minutes must be between 0 and 59");
    if(line.isBlank())
      throw new IllegalArgumentException("Line cannot be blank");
    if(trainNumber < 0)
      throw new IllegalArgumentException("Train number cannot be negative");
    if(destination.isBlank())
      throw new IllegalArgumentException("Destination cannot be blank");
    if(track < -1)
      throw new IllegalArgumentException("Track cannot be negative greater than -1");
    if(delayHours < 0 || delayHours > 23)
      throw new IllegalArgumentException("Delay hours must be between 0 and 23");
    if(delayMinutes < 0 || delayMinutes > 59)
      throw new IllegalArgumentException("Delay minutes must be between 0 and 59");
  }

  public LocalTime getDepartureTime() {
    return departureTime;
  }

  public LocalTime getDelayedDepartureTime() {
    return departureTime.plusHours(delay.getHour()).plusMinutes(delay.getMinute());
  }

  public String getLine() {
    return line;
  }

  public int getTrainNumber() {
    return trainNumber;
  }

  public String getDestination() {
    return destination;
  }

  /**
   * @return -1 means that the train is not assigned a track yet
   */
  public int getTrack() {
    return track;
  }

  /**
   * @param track -1 means that the train is not assigned a track yet
   */
  public void setTrack(int track) {
    if(track < -1)
      throw new IllegalArgumentException("Track cannot be negative");
    this.track = track;
  }

  public LocalTime getDelay() {
    return delay;
  }

  public void setDelay(String delay) throws IllegalArgumentException {
    if(LocalTime.parse(delay, DateTimeFormatter.ofPattern("HH:mm")).isBefore(LocalTime.parse("00:00")))
      throw new IllegalArgumentException("Delay cannot be negative");

    if(LocalTime.parse(delay, DateTimeFormatter.ofPattern("HH:mm")).isAfter(LocalTime.parse("23:59")))
      throw new IllegalArgumentException("Delay cannot be more than 23:59");

    this.delay = LocalTime.parse(delay, DateTimeFormatter.ofPattern("HH:mm"));
  }

  public boolean isDelayed() {
    return !delay.equals(LocalTime.parse("00:00"));
  }

  @Override
  public String toString(){
    return "Departure Time: " + departureTime + "\n" +
           "Line: " + line + "\n" +
           "Train Number: " + trainNumber + "\n" +
           "Destination: " + destination + "\n" +
           "Track: " + track + "\n" +
           "Delay: " + delay;
  }
}
