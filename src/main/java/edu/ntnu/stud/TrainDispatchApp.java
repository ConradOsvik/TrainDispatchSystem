package edu.ntnu.stud;

import edu.ntnu.stud.controllers.TrainController;
import edu.ntnu.stud.models.TrainRegister;
import edu.ntnu.stud.views.ConsoleView;

/**
 * This is the main class for the train dispatch application.
 */
public class TrainDispatchApp {

  private static TrainController trainController;

  private static void init() {
    TrainRegister trainRegister = new TrainRegister();
    ConsoleView consoleView = new ConsoleView();
    trainController = new TrainController(trainRegister, consoleView);
  }

  private static void start() {
    trainController.handleRequest();
  }

  public static void main(String[] args) {
    init();

    while (true) {
      start();
    }
  }
}