package edu.ntnu.stud.commands;

/**
 * A functional interface representing the baseline of a command.
 */
@FunctionalInterface
public interface Command {

  /**
   * A getter for the name of the command.
   *
   * @return the name of the command
   */
  default String getName() {
    return "Command name";
  }

  /**
   * A getter for the description of the command.
   *
   * @return the description of the command
   */
  default String getDescription() {
    return "Description of a command";
  }

  /**
   * The code to be executed when a command is run.
   */
  void execute();
}
