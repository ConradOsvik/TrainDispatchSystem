package edu.ntnu.stud;

import edu.ntnu.stud.commands.Command;
import edu.ntnu.stud.controllers.TrainController;
import edu.ntnu.stud.models.TrainRegister;
import edu.ntnu.stud.views.ConsoleView;
import java.util.ArrayList;

/**
 * This is the main class for the train dispatch application.
 */
public class TrainDispatchApp {
  private static TrainRegister trainRegister;
  private static ConsoleView consoleView;
  private static TrainController trainController;
  private static ArrayList<Command> commands;

  private static void init() {
    trainRegister = new TrainRegister();
    consoleView = new ConsoleView();
    trainController = new TrainController(trainRegister, consoleView);
    commands = new ArrayList<>();
    //TODO: Add commands to the list
  }

  private static void start() {

  }

  public static void main(String[] args) {
    init();

    while (true) {
      try {
        start();
      } catch (Exception e) {
        System.out.println("An error occurred: " + e.getMessage());
      }
    }
  }
}