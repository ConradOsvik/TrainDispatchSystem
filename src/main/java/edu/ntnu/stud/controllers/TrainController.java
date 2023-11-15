package edu.ntnu.stud.controllers;

import edu.ntnu.stud.models.TrainRegister;
import edu.ntnu.stud.views.ConsoleView;

public class TrainController {
  private TrainRegister trainRegister;
  private ConsoleView consoleView;

  public TrainController(TrainRegister trainRegister, ConsoleView consoleView){
    this.trainRegister = trainRegister;
    this.consoleView = consoleView;

  }
}
