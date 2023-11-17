package edu.ntnu.stud.controllers;

import edu.ntnu.stud.commands.AddTrainCommand;
import edu.ntnu.stud.commands.Command;
import edu.ntnu.stud.models.TrainRegister;
import edu.ntnu.stud.views.ConsoleView;
import java.util.ArrayList;
import java.util.List;

public class TrainController {
  private TrainRegister trainRegister;
  private ConsoleView consoleView;
  private List<Command> commands;

  public TrainController(TrainRegister trainRegister, ConsoleView consoleView){
    this.trainRegister = trainRegister;
    this.consoleView = consoleView;

    commands = new ArrayList<>();
    //TODO: Add commands to commands list
    commands.add(new AddTrainCommand(this));
  }

  public TrainRegister getTrainRegister(){
    return this.trainRegister;
  }

  public void handleRequest(){
    consoleView.displayMenu(commands);
    int choice = consoleView.getUserInput();

    Command command = commands.get(choice - 1);
    command.execute();
  }
}
