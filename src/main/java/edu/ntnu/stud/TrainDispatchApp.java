package edu.ntnu.stud;

import edu.ntnu.stud.controllers.TrainController;
import edu.ntnu.stud.models.TrainRegister;
import edu.ntnu.stud.views.ConsoleView;

/**
 * This is the main class for the train dispatch application.
 */
public class TrainDispatchApp {

  private static TrainController trainController;

  /**
   * Initializes the TrainRegister, ConsoleView and TrainController.
   */
  private static void init() {
    TrainRegister trainRegister = new TrainRegister();
    ConsoleView consoleView = new ConsoleView();
    trainController = new TrainController(trainRegister, consoleView);
  }

  /**
   * Starts the application by pointing it to the TrainController request handler.
   */
  private static void start() {
    trainController.handleRequest();
  }

  /**
   * The main method of the application.
   *
   * @param args The command line arguments.
   */
  public static void main(String[] args) {
    init();

    while (true) {
      start();
    }
  }
}