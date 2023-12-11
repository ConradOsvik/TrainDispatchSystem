package edu.ntnu.stud.utils;

/**
 * A utility class for truncating strings.
 */
public final class Truncate {

  /**
   * Truncates a string to a maximum length of 20 characters.
   *
   * @param text the text to be truncated.
   * @return the truncated string.
   */
  public static String truncate(String text) {
    if (text.length() > 20) {
      return text.substring(0, 17) + "...";
    } else {
      return text;
    }
  }
}
