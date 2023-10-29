package edu.ntnu.stud.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import org.junit.jupiter.api.Test;

public class TrainDepartureTest {
  @Test
  public void testTrainDeparturePositive() {
    String departureTime = "12:30";
    String line = "Line1";
    int trainNumber = 123;
    String destination = "Destination1";
    int track = 1;
    String delay = "00:15";

    TrainDeparture trainDeparture = new TrainDeparture(departureTime, line, trainNumber, destination, track, delay);

    assertEquals(LocalTime.parse(departureTime), trainDeparture.getDepartureTime());
    assertEquals(line, trainDeparture.getLine());
    assertEquals(trainNumber, trainDeparture.getTrainNumber());
    assertEquals(destination, trainDeparture.getDestination());
    assertEquals(track, trainDeparture.getTrack());
    assertEquals(LocalTime.parse(delay), trainDeparture.getDelay());
  }

  @Test
  public void testTrainDepartureNegative() {
    String departureTime = "25:30";
    String line = "Line1";
    int trainNumber = 123;
    String destination = "Destination1";
    int track = 1;
    String delay = "00:15";

    assertThrows(DateTimeParseException.class, () -> {
      new TrainDeparture(departureTime, line, trainNumber, destination, track, delay);
    });
  }
}
