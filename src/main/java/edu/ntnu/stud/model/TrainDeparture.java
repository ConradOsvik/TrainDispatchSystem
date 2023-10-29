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
    this.departureTime = LocalTime.parse(departureTime, DateTimeFormatter.ofPattern("HH:mm"));
    this.line = line;
    this.trainNumber = trainNumber;
    this.destination = destination;
    this.track = track;
    this.delay = LocalTime.parse(delay, DateTimeFormatter.ofPattern("HH:mm"));
  }

  public LocalTime getDepartureTime() {
    return departureTime;
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

  public int getTrack() {
    return track;
  }

  public LocalTime getDelay() {
    return delay;
  }
}
