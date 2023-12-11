package edu.ntnu.stud.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class TestTruncate {

  @Test
  void truncate_textIsLongerThan20Characters_truncated() {
    String text = "This text is longer than 20 characters";

    String truncatedText = Truncate.truncate(text);

    assertEquals(20, truncatedText.length());
    assertTrue(truncatedText.endsWith("..."));
  }

  @Test
  void truncate_textIsShorterThan20Characters_notTruncated() {
    String text = "This text is shorter";

    String truncatedText = Truncate.truncate(text);

    assertEquals(text, truncatedText);
  }
}
