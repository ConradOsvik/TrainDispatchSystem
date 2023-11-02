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

  public int getTrack() {
    return track;
  }

  public void setTrack(int track) {
    this.track = track;
  }

  public LocalTime getDelay() {
    return delay;
  }

  public void setDelay(String delay) {
    this.delay = LocalTime.parse(delay, DateTimeFormatter.ofPattern("HH:mm"));
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
