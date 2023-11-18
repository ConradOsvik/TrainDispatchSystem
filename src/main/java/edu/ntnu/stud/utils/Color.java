package edu.ntnu.stud.utils;

public final class Color {

  public static final String RESET = "\u001B[0m";
  public static final String BLACK = "\u001B[30m";
  public static final String RED = "\u001B[31m";
  public static final String GREEN = "\u001B[32m";
  public static final String YELLOW = "\u001B[33m";
  public static final String BLUE = "\u001B[34m";
  public static final String PURPLE = "\u001B[35m";
  public static final String CYAN = "\u001B[36m";
  public static final String WHITE = "\u001B[37m";

  public static final String BLACK_BACKGROUND = "\u001B[40m";
  public static final String RED_BACKGROUND = "\u001B[41m";
  public static final String GREEN_BACKGROUND = "\u001B[42m";
  public static final String YELLOW_BACKGROUND = "\u001B[43m";
  public static final String BLUE_BACKGROUND = "\u001B[44m";
  public static final String PURPLE_BACKGROUND = "\u001B[45m";
  public static final String CYAN_BACKGROUND = "\u001B[46m";
  public static final String WHITE_BACKGROUND = "\u001B[47m";

  public static String colorString(String string, String color) {
    return color + string + RESET;
  }

  public static String colorString(String string, String textColor, String backgroundColor) {
    return textColor + backgroundColor + string + RESET;
  }

  public static String colorString(String string, int red, int green, int blue) {
    String textColor = "\\u001B[38;2;" + red + ";" + green + ";" + blue + "m";

    return textColor + string + RESET;
  }

  public static String colorString(
      String string,
      int textRed,
      int textGreen,
      int textBlue,
      int backgroundRed,
      int backgroundGreen,
      int backgroundBlue
  ) {
    String textColor = "\\u001B[38;2;" + textRed + ";" + textGreen + ";" + textBlue + "m";
    String backgroundColor =
        "\\u001B[48;2;" + backgroundRed + ";" + backgroundGreen + ";" + backgroundBlue + "m";
    return textColor + backgroundColor + string + RESET;
  }
}
