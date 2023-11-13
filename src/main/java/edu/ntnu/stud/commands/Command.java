package edu.ntnu.stud.commands;

@FunctionalInterface
public interface Command {
    void execute();
    default String getName(){
      return "Command";
    }

    default String getDescription(){
      return "Description of a command";
    }
}
