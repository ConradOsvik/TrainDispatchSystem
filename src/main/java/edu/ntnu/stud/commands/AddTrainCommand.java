package edu.ntnu.stud.commands;

public class AddTrainCommand implements Command {

  @Override
  public String getName() {
    return "Add train";
  }

  @Override
  public String getDescription() {
    return "Add a train to the train register";
  }

  @Override
  public void execute() {
    System.out.println("AddTrainCommand executed");
  }
}
