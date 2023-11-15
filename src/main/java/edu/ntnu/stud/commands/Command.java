package edu.ntnu.stud.commands;

@FunctionalInterface
public interface Command {

  default String getName() {
    return "Command name";
  }

  default String getDescription() {
    return "Description of a command";
  }

  void execute();
}
