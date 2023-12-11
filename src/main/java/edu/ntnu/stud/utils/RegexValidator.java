package edu.ntnu.stud.utils;

/**
 * A utility class for validating strings to follow certain formats.
 */
public final class RegexValidator {

  /**
   * A method checking if string is of format [00-23]:[00-59] (HH:mm).
   */
  public static boolean isTime(String input) {
    return input.matches("^([01]?[0-9]|2[0-3]):[0-5][0-9]$");
  }

  /**
   * A method checking if string is of format [Capital letter][Capital letter][0-99].
   * Example: F1, A12, RE2, RE42, etc.
   */
  public static boolean isLine(String input) {
    return input.matches("^[A-Z]{1,2}[0-9]{1,2}$");
  }
}
