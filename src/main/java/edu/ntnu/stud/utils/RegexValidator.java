package edu.ntnu.stud.utils;

public final class RegexValidator {
  public static boolean isTime(String input) {
    return input.matches("^([01]?[0-9]|2[0-3]):[0-5][0-9]$");
  }

  public static boolean isLine(String input) {
    return input.matches("^[A-Z][0-9]{1,2}$");
  }
}
