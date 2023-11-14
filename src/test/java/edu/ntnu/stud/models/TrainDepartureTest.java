package edu.ntnu.stud.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TrainDepartureTest {

  TrainDeparture trainDeparture;

  @BeforeEach
  public void setup() {
    trainDeparture = new TrainDeparture("12:00", "F1", 100, "Oslo", 1, "00:10");
  }

  @Test
  public void constructor_argsAreValid_true() {
    assertEquals(LocalTime.parse("12:00"), trainDeparture.getDepartureTime());
    assertEquals("F1", trainDeparture.getLine());
    assertEquals(100, trainDeparture.getTrainNumber());
    assertEquals("Oslo", trainDeparture.getDestination());
    assertEquals(1, trainDeparture.getTrack());
    assertEquals(LocalTime.parse("00:10"), trainDeparture.getDelay());
  }

  @Test
  public void constructor_departureTimeIs2401_false() {
    assertThrows(DateTimeParseException.class, () -> {
      new TrainDeparture("24:01", "F1", 100, "Oslo", 1, "00:10");
    });
  }

  @Test
  public void constructor_departureTimeIsNegative_false() {
    assertThrows(DateTimeParseException.class, () -> {
      new TrainDeparture("-12:00", "F1", 100, "Oslo", 1, "00:10");
    });
  }

  @Test
  public void constructor_lineIsBlank_false() {
    assertThrows(IllegalArgumentException.class, () -> {
      new TrainDeparture("12:00", "", 100, "Oslo", 1, "00:10");
    });
  }

  @Test
  public void constructor_trainNumberIsNegative_false() {
    assertThrows(IllegalArgumentException.class, () -> {
      new TrainDeparture("12:00", "F1", -1, "Oslo", 1, "00:10");
    });
  }

  @Test
  public void constructor_destinationIsBlank_false() {
    assertThrows(IllegalArgumentException.class, () -> {
      new TrainDeparture("12:00", "F1", 100, "", 1, "00:10");
    });
  }

  @Test
  public void constructor_trackIsNegative2_false() {
    assertThrows(IllegalArgumentException.class, () -> {
      new TrainDeparture("12:00", "F1", 100, "Oslo", -2, "00:10");
    });
  }

  @Test
  public void constructor_delayIs2401_false() {
    assertThrows(DateTimeParseException.class, () -> {
      new TrainDeparture("12:00", "F1", 100, "Oslo", 1, "24:01");
    });
  }

  @Test
  public void constructor_delayIsNegative_false() {
    assertThrows(DateTimeParseException.class, () -> {
      new TrainDeparture("12:00", "F1", 100, "Oslo", 1, "-00:10");
    });
  }

  @Test
  public void getDepartureTime_departureTimeIs1200_true() {
    assertEquals(LocalTime.parse("12:00"), trainDeparture.getDepartureTime());
  }

  @Test
  public void getDelayedDepartureTime_delayedDepartureTimeIs1210_true() {
    assertEquals(LocalTime.parse("12:10"), trainDeparture.getDelayedDepartureTime());
  }

  @Test
  public void getLine_lineIsF1_true() {
    assertEquals("F1", trainDeparture.getLine());
  }

  @Test
  public void getTrainNumber_trainNumberIs100_true() {
    assertEquals(100, trainDeparture.getTrainNumber());
  }

  @Test
  public void getDestination_destinationIsOslo_true() {
    assertEquals("Oslo", trainDeparture.getDestination());
  }

  @Test
  public void getTrack_trackIs1_true() {
    assertEquals(1, trainDeparture.getTrack());
  }

  @Test
  public void setTrack_trackIs2_true() {
    trainDeparture.setTrack(2);
    assertEquals(2, trainDeparture.getTrack());
  }

  @Test
  public void setTrack_trackIs0_false() {
    assertThrows(IllegalArgumentException.class, () -> {
      trainDeparture.setTrack(0);
    });
  }

  @Test
  public void setTrack_TrackIsNegative_false() {
    assertThrows(IllegalArgumentException.class, () -> {
      trainDeparture.setTrack(-2);
    });
  }

  @Test
  public void getDelay_delayIs0010_true() {
    assertEquals(LocalTime.parse("00:10"), trainDeparture.getDelay());
  }

  @Test
  public void setDelay_delayIs0020_true() {
    trainDeparture.setDelay("00:20");
    assertEquals(LocalTime.parse("00:20"), trainDeparture.getDelay());
  }

  @Test
  public void setDelay_delayIsValid_false() {
    assertThrows(DateTimeParseException.class, () -> {
      trainDeparture.setDelay("24:01");
    });
  }

  @Test
  public void setDelay_delayIsNegative_false() {
    assertThrows(DateTimeParseException.class, () -> {
      trainDeparture.setDelay("-00:10");
    });
  }

  @Test
  public void isDelayed_delayIsZero_false() {
    assertTrue(trainDeparture.isDelayed());
  }
}
