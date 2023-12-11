package edu.ntnu.stud.utils;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Test class for the RegexValidator utility class. The class methods follows the
 * methodName_stateUnderTest_expectedBehavior naming convention.
 */
class TestRegexValidator {

  @Test
  void isTime_timeIs1200_true() {
    String time = "12:00";

    boolean isTime = RegexValidator.isTime(time);

    assertTrue(isTime);
  }

  @Test
  void isTime_timeIs2401_false() {
    String time = "24:01";

    boolean isTime = RegexValidator.isTime(time);

    assertFalse(isTime);
  }

  @Test
  void isTime_timeIsBlank_false() {
    String time = "";

    boolean isTime = RegexValidator.isTime(time);

    assertFalse(isTime);
  }

  @Test
  void isTime_timeIsNegative_false() {
    String time = "-12:00";

    boolean isTime = RegexValidator.isTime(time);

    assertFalse(isTime);
  }

  @Test
  void isLine_lineIsF1_true() {
    String line = "F1";

    boolean isLine = RegexValidator.isLine(line);

    assertTrue(isLine);
  }

  @Test
  void isLine_lineIsf1_false() {
    String line = "f1";

    boolean isLine = RegexValidator.isLine(line);

    assertFalse(isLine);
  }
}
