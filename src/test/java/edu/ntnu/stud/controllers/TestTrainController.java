package edu.ntnu.stud.controllers;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import edu.ntnu.stud.models.TrainDeparture;
import edu.ntnu.stud.models.TrainRegister;
import edu.ntnu.stud.views.ConsoleView;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestTrainController {

  private TrainRegister trainRegister;
  private ConsoleView consoleView;
  private TrainController trainController;
  private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

  private final PrintStream standardOut = System.out;

  @BeforeEach
  void setUp() {
    trainRegister = new TrainRegister();
    consoleView = new ConsoleView();
    trainController = new TrainController(trainRegister, consoleView);
    System.setOut(new PrintStream(outputStream));
  }

  @Test
  void getConsoleView_ReturnsConsoleView() {
    ConsoleView consoleView = trainController.getConsoleView();

    assertEquals(this.consoleView, consoleView);
  }

  @Test
  void addTrainToRegister_TrainIsAddedToRegister_Succeeds() {
    String successMessage = "";

    TrainDeparture trainDeparture = new TrainDeparture(
        "12:00",
        "F1",
        100,
        "Oslo",
        1,
        "00:00"
    );

    trainController.addTrainToRegister(trainDeparture);
    String output = outputStream.toString().trim();

    //assertEquals();
  }

  @Test
  void addTrainToRegister_TrainAlreadyExistsInRegister_ThrowsException() {
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

    assertThrows(IllegalArgumentException.class, () -> trainController.addTrainToRegister(newTrainDeparture));
  }
}
