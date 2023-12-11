package edu.ntnu.stud.input;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import edu.ntnu.stud.exceptions.InvalidFormatException;
import edu.ntnu.stud.exceptions.InvalidInputException;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for the InputHandler class. The class methods follows the
 * methodName_stateUnderTest_expectedBehavior naming convention.
 */
class TestInputHandler {

  private final InputStream standardIn = System.in;
  private final PrintStream standardOut = System.out;

  @AfterEach
  void cleanup() {
    System.setIn(standardIn);
    System.setOut(standardOut);
  }

  private InputHandler act(String input) {
    InputStream in = new ByteArrayInputStream((input + "\n").getBytes());
    System.setIn(in);

    return new InputHandler();
  }

  @Test
  void getString_TextIsString_ReturnsText() {
    String input = "text input";

    String result = assertDoesNotThrow(() -> act(input).getString());

    assertEquals(input, result);
  }

  @Test
  void getInt_TextIsInt_ReturnsInt() {
    String input = "0";

    int result = assertDoesNotThrow(() -> act(input).getInt());

    assertEquals(0, result);
  }

  @Test
  void getInt_TextIsNotInt_ThrowsException() {
    String input = "not an int";

    assertThrows(InvalidFormatException.class, () -> act(input).getInt());
  }

  @Test
  void getTime_TextIsTime_ReturnsTime() {
    String input = "12:00";

    String result = assertDoesNotThrow(() -> act(input).getTime());

    assertEquals(input, result);
  }

  @Test
  void getTime_TextIsNotTime_ThrowsException() {
    String input = "not a time";

    assertThrows(InvalidFormatException.class, () -> act(input).getTime());
  }

  @Test
  void getTime_TextIsBlank_ReturnsDefaultTime() {
    String input = "";

    String result = assertDoesNotThrow(() -> act(input).getTime());

    assertEquals("00:00", result);
  }

  @Test
  void getTime_TextIsNull_ThrowsException() {
    String input = null;

    assertThrows(InvalidFormatException.class, () -> act(input).getTime());
  }

  @Test
  void getLine_TextIsLine_ReturnsLine() {
    String input = "A1";

    String result = assertDoesNotThrow(() -> act(input).getLine());

    assertEquals(input, result);
  }

  @Test
  void getLine_TextIsNotLine_ThrowsException() {
    String input = "not a line";

    assertThrows(InvalidFormatException.class, () -> act(input).getLine());
  }

  @Test
  void getLine_TextIsNull_ThrowsException() {
    String input = null;

    assertThrows(InvalidFormatException.class, () -> act(input).getLine());
  }

  @Test
  void getTrainNumber_TextIsTrainNumber_ReturnsTrainNumber() {
    String input = "1";

    int result = assertDoesNotThrow(() -> act(input).getTrainNumber());

    assertEquals(1, result);
  }

  @Test
  void getTrainNumber_TextIsNotTrainNumber_ThrowsException() {
    String input = "not a train number";

    assertThrows(InvalidFormatException.class, () -> act(input).getTrainNumber());
  }

  @Test
  void getTrainNumber_TextIsZero_ThrowsException() {
    String input = "0";

    assertThrows(InvalidInputException.class, () -> act(input).getTrainNumber());
  }

  @Test
  void getTrack_TextIsTrack_ReturnsTrack() {
    String input = "1";

    int result = assertDoesNotThrow(() -> act(input).getTrack());

    assertEquals(1, result);
  }

  @Test
  void getTrack_TextIsNotTrack_ThrowsException() {
    String input = "not a track";

    assertThrows(InvalidFormatException.class, () -> act(input).getTrack());
  }

  @Test
  void getTrack_TextIsZero_ThrowsException() {
    String input = "0";

    assertThrows(InvalidInputException.class, () -> act(input).getTrack());
  }

  @Test
  void getTrack_TextIsMinusOne_ReturnsMinusOne() {
    String input = "-1";

    int result = assertDoesNotThrow(() -> act(input).getTrack());

    assertEquals(-1, result);
  }

  @Test
  void getTrack_TextIsNegative_ThrowsException() {
    String input = "-2";

    assertThrows(InvalidInputException.class, () -> act(input).getTrack());
  }
}
