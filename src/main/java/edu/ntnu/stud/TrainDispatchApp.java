package edu.ntnu.stud;

import edu.ntnu.stud.models.TrainDeparture;

/**
 * This is the main class for the train dispatch application.
 */
public class TrainDispatchApp {
  private static void init(){}

  private static void start(){}

  public static void main(String[] args) {
    TrainDeparture trainDeparture = new TrainDeparture("12:00", "F1", 100, "Oslo", 1, "00:10");
    System.out.println(trainDeparture.toString());
  }
}
