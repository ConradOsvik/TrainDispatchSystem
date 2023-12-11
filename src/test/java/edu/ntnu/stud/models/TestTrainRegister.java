package edu.ntnu.stud.models;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for the TrainRegister entity class. The class methods follows the
 * methodName_stateUnderTest_expectedBehavior naming convention.
 */
class TestTrainRegister {

  TrainRegister trainRegister;

  @BeforeEach
  void setup() {
    trainRegister = new TrainRegister();
    trainRegister.setTime(LocalTime.parse("11:00"));
    trainRegister.addTrain(
        new TrainDeparture(
            "12:00",
            "F1",
            100,
            "Oslo",
            1,
            "00:10"
        )
    );
  }

  @Test
  void getTime_TimeIsSet_ReturnsTime() {
    LocalTime time = LocalTime.parse("11:00");

    LocalTime currentTime = trainRegister.getTime();

    assertEquals(time, currentTime);
  }

  @Test
  void setTime_TimeIsSet_ReturnsTime() {
    LocalTime time = LocalTime.parse("12:00");

    trainRegister.setTime(time);

    LocalTime currentTime = trainRegister.getTime();

    assertEquals(time, currentTime);
  }

  @Test
  void setTime_TimeIsBeforeCurrentTime_ThrowsException() {
    LocalTime time = LocalTime.parse("10:00");

    assertThrows(IllegalArgumentException.class, () -> trainRegister.setTime(time));
  }

  @Test
  void addTrain_TrainIsAdded_DoesNotThrow() {
    TrainDeparture trainDeparture = new TrainDeparture(
        "12:00",
        "F2",
        101,
        "Oslo",
        1,
        "00:10"
    );

    assertDoesNotThrow(() -> trainRegister.addTrain(trainDeparture));
  }

  @Test
  void addTrain_TrainNumberAlreadyExists_ThrowsException() {
    TrainDeparture trainDeparture = new TrainDeparture(
        "12:00",
        "F1",
        100,
        "Oslo",
        1,
        "00:10"
    );

    assertThrows(IllegalArgumentException.class, () -> trainRegister.addTrain(trainDeparture));
  }

  @Test
  void addTrain_TrainDepartureIsBeforeCurrentTime_ThrowsException() {
    TrainDeparture trainDeparture = new TrainDeparture(
        "10:00",
        "F2",
        101,
        "Oslo",
        1,
        "00:10"
    );

    assertThrows(IllegalArgumentException.class, () -> trainRegister.addTrain(trainDeparture));
  }

  @Test
  void addTrain_TrainDepartureIsAtCurrentTime_ThrowsException() {
    TrainDeparture trainDeparture = new TrainDeparture(
        "11:00",
        "F2",
        101,
        "Oslo",
        1,
        "00:00"
    );

    assertThrows(IllegalArgumentException.class, () -> trainRegister.addTrain(trainDeparture));
  }

  @Test
  void addTrain_TrainDepartureIsNull_ThrowsException() {
    assertThrows(IllegalArgumentException.class, () -> trainRegister.addTrain(null));
  }

  @Test
  void getTrain_TrainNumberExists_ReturnsTrain() {
    TrainDeparture trainDeparture = new TrainDeparture(
        "12:00",
        "F1",
        100,
        "Oslo",
        1,
        "00:10"
    );

    TrainDeparture result = trainRegister.getTrain(100);

    assertEquals(trainDeparture.getDepartureTime(), result.getDepartureTime());
    assertEquals(trainDeparture.getLine(), result.getLine());
    assertEquals(trainDeparture.getTrainNumber(), result.getTrainNumber());
    assertEquals(trainDeparture.getDestination(), result.getDestination());
    assertEquals(trainDeparture.getTrack(), result.getTrack());
    assertEquals(trainDeparture.getDelay(), result.getDelay());
  }

  @Test
  void getTrain_TrainNumberDoesNotExist_ReturnsNull() {
    TrainDeparture result = trainRegister.getTrain(101);

    assertNull(result);
  }

  @Test
  void getTrainsByDestination_DestinationExists_ReturnsTrains() {
    TrainDeparture trainDeparture = new TrainDeparture(
        "12:00",
        "F1",
        100,
        "Oslo",
        1,
        "00:10"
    );

    TrainDeparture result = trainRegister.getTrainsByDestination("Oslo").get(0);

    assertEquals(trainDeparture.getDepartureTime(), result.getDepartureTime());
    assertEquals(trainDeparture.getLine(), result.getLine());
    assertEquals(trainDeparture.getTrainNumber(), result.getTrainNumber());
    assertEquals(trainDeparture.getDestination(), result.getDestination());
    assertEquals(trainDeparture.getTrack(), result.getTrack());
    assertEquals(trainDeparture.getDelay(), result.getDelay());
  }

  @Test
  void getTrainsByDestination_DestinationDoesNotExist_ReturnsEmptyList() {
    List<TrainDeparture> emptyList = new ArrayList<>();

    List<TrainDeparture> result = trainRegister.getTrainsByDestination("Trondheim");

    assertEquals(emptyList, result);
  }

  @Test
  void getTrainsSortedByDepartureTime_TrainsExist_ReturnsTrainsSorted() {
    TrainDeparture trainDeparture = new TrainDeparture(
        "11:00",
        "A1",
        110,
        "Bergen",
        1,
        "00:10"
    );

    trainRegister.addTrain(trainDeparture);

    TrainDeparture result = trainRegister.getTrainsSortedByDepartureTime().get(0);

    assertEquals(trainDeparture, result);
  }
}
