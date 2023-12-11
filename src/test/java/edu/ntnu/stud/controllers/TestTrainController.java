package edu.ntnu.stud.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import edu.ntnu.stud.models.TrainDeparture;
import edu.ntnu.stud.models.TrainRegister;
import edu.ntnu.stud.views.ConsoleView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for the TrainController controller class. The class methods follows the
 * methodName_stateUnderTest_expectedBehavior naming convention.
 */
class TestTrainController {

  private ConsoleView consoleView;
  private TrainController trainController;

  @BeforeEach
  void setUp() {
    TrainRegister trainRegister = new TrainRegister();
    consoleView = new ConsoleView();
    trainController = new TrainController(trainRegister, consoleView);
  }

  @Test
  void getConsoleView_ReturnsConsoleView() {
    ConsoleView consoleView = trainController.getConsoleView();

    assertEquals(this.consoleView, consoleView);
  }

  @Test
  void addTrainToRegister_TrainIsAddedToRegister_Succeeds() {
    TrainDeparture trainDeparture = new TrainDeparture(
        "12:00",
        "F1",
        100,
        "Oslo",
        1,
        "00:00"
    );

    assertTrue(trainController.addTrainToRegister(trainDeparture));
  }

  @Test
  void addTrainToRegister_TrainAlreadyExistsInRegister_Fails() {
    TrainDeparture trainDeparture = new TrainDeparture(
        "12:00",
        "F1",
        100,
        "Oslo",
        1,
        "00:00"
    );

    TrainDeparture newTrainDeparture = new TrainDeparture(
        "12:00",
        "F1",
        100,
        "Oslo",
        1,
        "00:00"
    );

    trainController.addTrainToRegister(trainDeparture);

    assertFalse(trainController.addTrainToRegister(newTrainDeparture));
  }

  @Test
  void addTrainToRegister_TrainIsNull_Fails() {
    TrainDeparture trainDeparture = null;

    assertFalse(trainController.addTrainToRegister(trainDeparture));
  }

  @Test
  void setTrainTrack_TrackIsUpdated_Succeeds() {
    TrainDeparture trainDeparture = new TrainDeparture(
        "12:00",
        "F1",
        100,
        "Oslo",
        1,
        "00:00"
    );

    trainController.addTrainToRegister(trainDeparture);

    assertTrue(trainController.setTrainTrack(100, 2));
  }

  @Test
  void setTrainTrack_TrainDoesNotExist_Fails() {
    TrainDeparture trainDeparture = new TrainDeparture(
        "12:00",
        "F1",
        100,
        "Oslo",
        1,
        "00:00"
    );

    trainController.addTrainToRegister(trainDeparture);

    assertFalse(trainController.setTrainTrack(101, 2));
  }

  @Test
  void setTrainTrack_TrackIsNegative_Fails() {
    TrainDeparture trainDeparture = new TrainDeparture(
        "12:00",
        "F1",
        100,
        "Oslo",
        1,
        "00:00"
    );

    trainController.addTrainToRegister(trainDeparture);

    assertFalse(trainController.setTrainTrack(100, -2));
  }

  @Test
  void setTrainDelay_DelayIsUpdated_Succeeds() {
    TrainDeparture trainDeparture = new TrainDeparture(
        "12:00",
        "F1",
        100,
        "Oslo",
        1,
        "00:00"
    );

    trainController.addTrainToRegister(trainDeparture);

    assertTrue(trainController.setTrainDelay(100, "00:10"));
  }

  @Test
  void setTrainDelay_DelayIsOutOfRange_Fails() {
    TrainDeparture trainDeparture = new TrainDeparture(
        "12:00",
        "F1",
        100,
        "Oslo",
        1,
        "00:00"
    );

    trainController.addTrainToRegister(trainDeparture);

    assertFalse(trainController.setTrainDelay(100, "25:00"));
  }

  @Test
  void setTrainDelay_TrainDoesNotExist_Fails() {
    TrainDeparture trainDeparture = new TrainDeparture(
        "12:00",
        "F1",
        100,
        "Oslo",
        1,
        "00:00"
    );

    trainController.addTrainToRegister(trainDeparture);

    assertFalse(trainController.setTrainDelay(101, "00:10"));
  }

  @Test
  void searchTrainByTrainNumber_TrainIsFound_Succeeds() {
    TrainDeparture trainDeparture = new TrainDeparture(
        "12:00",
        "F1",
        100,
        "Oslo",
        1,
        "00:00"
    );

    trainController.addTrainToRegister(trainDeparture);

    assertTrue(trainController.searchTrainByTrainNumber(100));
  }

  @Test
  void searchTrainByTrainNumber_TrainIsNotFound_Fails() {
    TrainDeparture trainDeparture = new TrainDeparture(
        "12:00",
        "F1",
        100,
        "Oslo",
        1,
        "00:00"
    );

    trainController.addTrainToRegister(trainDeparture);

    assertFalse(trainController.searchTrainByTrainNumber(101));
  }

  @Test
  void searchTrainsByDestination_TrainsAreFound_Succeeds() {
    TrainDeparture trainDeparture1 = new TrainDeparture(
        "12:00",
        "F1",
        100,
        "Oslo",
        1,
        "00:00"
    );

    TrainDeparture trainDeparture2 = new TrainDeparture(
        "12:00",
        "F1",
        101,
        "Oslo",
        1,
        "00:00"
    );

    trainController.addTrainToRegister(trainDeparture1);
    trainController.addTrainToRegister(trainDeparture2);

    assertTrue(trainController.searchTrainsByDestination("Oslo"));
  }

  @Test
  void searchTrainsByDestination_TrainsAreNotFound_Fails() {
    TrainDeparture trainDeparture1 = new TrainDeparture(
        "12:00",
        "F1",
        100,
        "Oslo",
        1,
        "00:00"
    );

    TrainDeparture trainDeparture2 = new TrainDeparture(
        "12:00",
        "F1",
        101,
        "Oslo",
        1,
        "00:00"
    );

    trainController.addTrainToRegister(trainDeparture1);
    trainController.addTrainToRegister(trainDeparture2);

    assertFalse(trainController.searchTrainsByDestination("Trondheim"));
  }

  @Test
  void updateTime_UpdatesTime_Succeeds() {
    boolean time = trainController.updateTime("12:00");

    assertTrue(time);
  }

  @Test
  void updateTime_TimeIsBefore_Fails() {
    trainController.updateTime("12:00");

    boolean time = trainController.updateTime("11:00");

    assertFalse(time);
  }

  @Test
  void updateTime_TimeIsOutOfRange_Fails() {
    boolean time = trainController.updateTime("25:00");

    assertFalse(time);
  }

  @Test
  void showTableOfTrains_Succeeds() {
    TrainDeparture trainDeparture = new TrainDeparture(
        "12:00",
        "F1",
        100,
        "Oslo",
        1,
        "00:00"
    );

    trainController.addTrainToRegister(trainDeparture);

    assertTrue(trainController.showTableOfTrains());
  }
}